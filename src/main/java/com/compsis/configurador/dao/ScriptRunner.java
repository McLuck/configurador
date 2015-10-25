/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 25/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.dao;

/*
 * Slightly modified version of the com.ibatis.common.jdbc.ScriptRunner class
 * from the iBATIS Apache project. Only removed dependency on Resource class
 * and a constructor 
 * GPSHansl, 06.08.2015: regex for delimiter, rearrange comment/delimiter detection, remove some ide warnings.
 */
/*
 *  Copyright 2004 Clinton Begin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ScriptRunner {

	  private static final String DEFAULT_DELIMITER = ";";

	  private Connection connection;

	  private boolean stopOnError;
	  private boolean autoCommit;
	  private boolean sendFullScript;
	  private boolean isPlsqlIniciado = false;
	  private boolean fechandoPlsql = false;

	  private PrintWriter logWriter = new PrintWriter(System.out);
	  private PrintWriter errorLogWriter = new PrintWriter(System.err);

	  private String delimiter = DEFAULT_DELIMITER;
	  private boolean fullLineDelimiter = false;
	  private String characterSetName;

	  public ScriptRunner(Connection connection, boolean autoCommit, boolean stopOnError) {
	    this.connection = connection;
	    this.stopOnError = stopOnError;
	    this.autoCommit = autoCommit;
	  }

	  public void setCharacterSetName(String characterSetName) {
	    this.characterSetName = characterSetName;
	  }

	  public void setStopOnError(boolean stopOnError) {
	    this.stopOnError = stopOnError;
	  }

	  public void setAutoCommit(boolean autoCommit) {
	    this.autoCommit = autoCommit;
	  }

	  public void setSendFullScript(boolean sendFullScript) {
	    this.sendFullScript = sendFullScript;
	  }

	  public void setLogWriter(PrintWriter logWriter) {
	    this.logWriter = logWriter;
	  }

	  public void setErrorLogWriter(PrintWriter errorLogWriter) {
	    this.errorLogWriter = errorLogWriter;
	  }

	  public void setDelimiter(String delimiter) {
	    this.delimiter = delimiter;
	  }

	  public void setFullLineDelimiter(boolean fullLineDelimiter) {
	    this.fullLineDelimiter = fullLineDelimiter;
	  }

	  public void runScript(Reader reader) {
	    setAutoCommit();

	    try {
	      if (sendFullScript) {
	        executeFullScript(reader);
	      } else {
	        executeLineByLine(reader);
	      }
	    } finally {
	      rollbackConnection();
	      flush();
	    }
	  }

	  private void executeFullScript(Reader reader) {
	    final String lineseparator = System.getProperty("line.separator");
	    StringBuffer script = new StringBuffer();
	    try {
	      BufferedReader lineReader = new BufferedReader(reader);
	      String line;
	      while ((line = lineReader.readLine()) != null) {
	        script.append(line);
	        script.append(lineseparator);
	      }
	      System.out.println(script);
	      executeStatement(script.toString());
	      commitConnection();
	    } catch (Exception e) {
	      String message = "Error executing: " + script + ".  Cause: " + e;
	      printlnError(message);
	      throw new RuntimeException(message, e);
	    }
	  }

	  private void executeLineByLine(Reader reader) {
	    StringBuffer command = new StringBuffer();
	    try {
	      BufferedReader lineReader = new BufferedReader(reader);
	      String line;
	      while ((line = lineReader.readLine()) != null) {
	        command = handleLine(command, line);
	      }
	      commitConnection();
	      checkForMissingLineTerminator(command);
	    } catch (Exception e) {
	      String message = "Error executing: " + command + ".  Cause: " + e;
	      printlnError(message);
	      throw new RuntimeException(message, e);
	    }
	  }

	  public void closeConnection() {
	    try {
	      connection.close();
	    } catch (Exception e) {
	      // ignore
	    }
	  }

	  private void setAutoCommit() {
	    try {
	      if (autoCommit != connection.getAutoCommit()) {
	        connection.setAutoCommit(autoCommit);
	      }
	    } catch (Throwable t) {
	      throw new RuntimeException("Could not set AutoCommit to " + autoCommit + ". Cause: " + t, t);
	    }
	  }

	  private void commitConnection() {
	    try {
	      if (!connection.getAutoCommit()) {
	        connection.commit();
	      }
	    } catch (Throwable t) {
	      throw new RuntimeException("Could not commit transaction. Cause: " + t, t);
	    }
	  }

	  private void rollbackConnection() {
	    try {
	      if (!connection.getAutoCommit()) {
	        connection.rollback();
	      }
	    } catch (Throwable t) {
	      // ignore
	    }
	  }

	  private void checkForMissingLineTerminator(StringBuffer command) {
	    if (command != null && command.toString().trim().length() > 0) {
	      throw new RuntimeException("Line missing end-of-line terminator (" + delimiter + ") => " + command);
	    }
	  }

	  private StringBuffer handleLine(StringBuffer command, String line) throws SQLException, UnsupportedEncodingException {
	    String trimmedLine = line.trim();
	    
	    if (lineIsComment(trimmedLine)) {
	      println(trimmedLine);
	    } else if (commandReadyToExecute(trimmedLine)) {
	    	if(trimmedLine.contains(delimiter) && !isPlsqlIniciado) {
	    		command.append(line.substring(0, line.lastIndexOf(delimiter)));	    		
	    	} else if(!isPlsqlFimDelimitador(trimmedLine)){
	    		command.append(line);	    		
	    	}
	      command.append(" ");
	      if (isPlsqlInicio(trimmedLine)) {
	    	  isPlsqlIniciado = true;
	    	  fechandoPlsql = false;	    	  
	      } else if (isPlsqlIniciado && isPlsqlFim(trimmedLine)) {
	    	  fechandoPlsql = true;
	      } else if (isPlsqlIniciado && fechandoPlsql && isPlsqlFimDelimitador(trimmedLine)) {
	    	  isPlsqlIniciado = false;
	    	  fechandoPlsql = false;
	    	  fechandoPlsql = false;
	      } else if(!line.isEmpty()) {
	    	  fechandoPlsql = false;	    	  
	      }
	      
	      if( !isPlsqlIniciado ) {
	    	  println(command);
	    	  executeStatement(command.toString());
	    	  command.setLength(0);	    	  
	      } else {
	    	  
	      }
	    } else if (trimmedLine.length() > 0) {
	      command.append(line);
	      command.append(" ");
	    }
	    return command;
	  }

	  private boolean lineIsComment(String trimmedLine) {
	    return trimmedLine.startsWith("//") || trimmedLine.startsWith("--");
	  }
	  
	  private boolean isPlsqlInicio(String trimmedLine) {
		  return trimmedLine.toLowerCase().startsWith("declare") || trimmedLine.toLowerCase().startsWith("begin");
	  }

	  private boolean isPlsqlFim(String trimmedLine) {
		  return trimmedLine.toLowerCase().equals("end;") || trimmedLine.toLowerCase().equals("end");
	  }
	  
	  private boolean isPlsqlFimDelimitador(String trimmedLine) {
		  return trimmedLine.toLowerCase().equals("/") || trimmedLine.toLowerCase().equals("go");
	  }
	  

	  private boolean commandReadyToExecute(String trimmedLine) {
	    return isPlsqlInicio(trimmedLine) || isPlsqlFimDelimitador(trimmedLine) || ( !fullLineDelimiter && trimmedLine.endsWith(delimiter)
	        || fullLineDelimiter && trimmedLine.equals(delimiter) );
	  }

	  private void executeStatement(String command) throws SQLException, UnsupportedEncodingException {
	    if(characterSetName != null){
	      command = new String(command.getBytes(), characterSetName);
	    }
	    boolean hasResults = false;
	    CallableStatement statement = null;
	    statement = connection.prepareCall(command);
	    if (stopOnError) {
	    	hasResults = statement.execute();
	    } else {
	      try {
	        hasResults = statement.execute(command);
	      } catch (SQLException e) {
	        String message = "Error executing: " + command + ".  Cause: " + e;
	        printlnError(message);
	      }
	    }
	    printResults(statement, hasResults);
	    try {
	      statement.close();
	    } catch (Exception e) {
	      // Ignore to workaround a bug in some connection pools
	    }
	    commitConnection();
	  }

	  private void printResults(Statement statement, boolean hasResults) {
	    try {
	      if (hasResults) {
	        ResultSet rs = statement.getResultSet();
	        if (rs != null) {
	          ResultSetMetaData md = rs.getMetaData();
	          int cols = md.getColumnCount();
	          for (int i = 0; i < cols; i++) {
	            String name = md.getColumnLabel(i + 1);
	            print(name + "\t");
	          }
	          println("");
	          while (rs.next()) {
	            for (int i = 0; i < cols; i++) {
	              String value = rs.getString(i + 1);
	              print(value + "\t");
	            }
	            println("");
	          }
	        }
	      }
	    } catch (SQLException e) {
	      printlnError("Error printing results: " + e.getMessage());
	    }
	  }

	  private void print(Object o) {
	    if (logWriter != null) {
	      logWriter.print(o);
	    }
	  }

	  private void println(Object o) {
	    if (logWriter != null) {
	      logWriter.println(o);
	    }
	  }

	  private void printlnError(Object o) {
	    if (errorLogWriter != null) {
	      errorLogWriter.println(o);
	    }
	  }

	  private void flush() {
	    if (logWriter != null) {
	      logWriter.flush();
	    }
	    if (errorLogWriter != null) {
	      errorLogWriter.flush();
	    }
	  }

}
