
package com.compsis.configurador.dao;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

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
	
	/**
	 * Executa scripts do arquivo informado por parametro
	 * @param arquivoScript
	 * @param logger
	 */
	public void executarArquivo(final File arquivoScript, final Logger logger) {
		logger.info("Executando script do arquivo "+arquivoScript.getAbsolutePath());
		String lineSeparator = System.getProperty("line.separator");
		List<String> linhas = null;
		boolean iniciouPLSQL = false;
		StringBuffer buffer = new StringBuffer();
		try {
			linhas = Files.readLines(arquivoScript, Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (String linhaSQL : linhas) {
			buffer.append(linhaSQL);
			//Inicia PLSQL
			if(linhaSQL.trim().replace("\t", "").toLowerCase().startsWith("begin") || linhaSQL.trim().replace("\t", "").toLowerCase().startsWith("declare")) {
				iniciouPLSQL = true;
			}
			//executa sql em buffer
			if(linhaSQL.trim().replace("\t", "").endsWith(";") && !iniciouPLSQL) {
				executarPLSQL(buffer.toString(), logger);
				buffer.setLength(0);
				continue;
			}
			//Finaliza PLSQL
			if(iniciouPLSQL && linhaSQL.trim().replace("\t", "").toLowerCase().equals("/") || linhaSQL.trim().replace("\t", "").toLowerCase().equals("go")) {
				executarPLSQL(buffer.toString(), logger);
				buffer.setLength(0);
				continue;				
			}
			buffer.append(lineSeparator);
		}
	}
}
