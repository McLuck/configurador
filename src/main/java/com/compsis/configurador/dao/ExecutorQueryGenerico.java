
package com.compsis.configurador.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.intl.chardet.HtmlCharsetDetector;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;
import org.slf4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ibatis.common.jdbc.ScriptRunner;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executor generico de query
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ExecutorQueryGenerico {
	private ConnectionWrapper connectionWrapper;
	/**
	 * Valor de connectionWrapper atribuído a connectionWrapper
	 *
	 * @param connectionWrapper Atributo da Classe
	 */
	public void setConnectionWrapper(ConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}
	
	public void executarPLSQL(final String plsql, Logger logger) {
		if(logger.isDebugEnabled()) {
			logger.debug("Executando query: "+plsql);
		} else {
			logger.info("Executando query");
		}
		Connection connection = connectionWrapper.getConnection();
		CallableStatement cs = null;
        try {
        	cs = connection.prepareCall(plsql);
			cs.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			logger.info("Concluido execucao de query");
			connectionWrapper.releaseResources(connection, cs, null, null);
		}
	}
	
	public Charset definirCharsetPeloArquivo(File file) throws IOException {
		 int lang = nsPSMDetector.ALL ;
		 nsDetector det = new nsDetector(lang) ;
		 
	    URL url = file.toURI().toURL();
        BufferedInputStream imp = new BufferedInputStream(url.openStream());

        byte[] buf = new byte[1024] ;
        int len;
        boolean done = false ;
        boolean isAscii = true ;
        final List<String> chatsets = new ArrayList<String>();
        
        det.Init(new nsICharsetDetectionObserver() {
	            public void Notify(String charset) {
	                HtmlCharsetDetector.found = true ;
	                chatsets.add(charset);
	            }
	    });

        while( (len=imp.read(buf,0,buf.length)) != -1) {

                // Check if the stream is only ascii.
                if (isAscii)
                    isAscii = det.isAscii(buf,len);

                // DoIt if non-ascii and not done yet.
                if (!isAscii && !done)
                    done = det.DoIt(buf,len, false);
        }
        det.DataEnd();
        imp.close();
        det.Done();
        String[] probableCharsets = det.getProbableCharsets();
        
        if(chatsets.isEmpty()) {
        	if(probableCharsets != null && probableCharsets.length>0 ) {
        		for (String charset : probableCharsets) {
					if(!"nomatch".equals(charset)) {
						return Charset.forName(charset);						
					}
				}
        	} 
        	return isAscii ? Charsets.US_ASCII : Charsets.ISO_8859_1; 
        } else {
        	return Charset.forName(chatsets.get(0));
        }
	}
	
	/**
	 * Executa scripts do arquivo informado por parametro
	 * @param arquivoScript
	 * @param logger
	 */
	public void executarArquivo(final File arquivoScript, final Logger logger) {
		logger.info("Executando script do arquivo "+arquivoScript.getAbsolutePath());
		Connection connection = connectionWrapper.getConnection();
		ScriptRunner sr = new ScriptRunner(connection, false, false);
		try {
			sr.runScript(new FileReader(arquivoScript));
			connection.commit();
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		} 
//		
//		String lineSeparator = System.getProperty("line.separator");
//		List<String> linhas = null;
//		boolean iniciouPLSQL = false;
//		StringBuffer buffer = new StringBuffer();
//		try {
//			Charset charset = definirCharsetPeloArquivo(arquivoScript);
//			linhas = Files.readLines(arquivoScript, charset);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		for (String linhaSQL : linhas) {
//			if(linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("spool") 
//					|| linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("alter session")
//					|| linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("set linesize")
//					|| linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("--")
//					|| linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("set pagesize")
//					|| linhaSQL.replace("\t", "").trim().toLowerCase().startsWith("set serveroutput on") ) {
//				continue;
//			}
//			buffer.append(linhaSQL);				
//			//Inicia PLSQL
//			if(linhaSQL.trim().replace("\t", "").toLowerCase().startsWith("begin") || linhaSQL.trim().replace("\t", "").toLowerCase().startsWith("declare")) {
//				iniciouPLSQL = true;
//			}
//			//executa sql em buffer
//			if(linhaSQL.trim().replace("\t", "").endsWith(";") && !iniciouPLSQL) {
//				executarPLSQL(buffer.toString(), logger);
//				buffer.setLength(0);
//				continue;
//			}
//			//Finaliza PLSQL
//			if(iniciouPLSQL && linhaSQL.trim().replace("\t", "").toLowerCase().equals("/") || linhaSQL.trim().replace("\t", "").toLowerCase().equals("go")  ) {
//				executarPLSQL(buffer.toString(), logger);
//				buffer.setLength(0);
//				continue;				
//			}
//			if(iniciouPLSQL)
//				buffer.append(lineSeparator);
//		}
	}
}
