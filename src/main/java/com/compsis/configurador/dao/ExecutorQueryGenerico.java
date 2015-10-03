
package com.compsis.configurador.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

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
}
