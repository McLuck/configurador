
package com.compsis.configurador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.compsis.configurador.dao.resultset.ControleDeltaResultSet;
import com.compsis.configurador.dominio.ControleDelta;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * DAO para manipular controle delta
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ControleDeltaDAO {
	private ConnectionWrapper connectionWrapper;
	
	/**
	 * Carrega todos os deltas executados no banco
	 * @return
	 */
	public List<ControleDelta> carregarTodos() {
		List<ControleDelta> deltas = new ArrayList<ControleDelta>();
		
		String sql = "SELECT IDDELTA, NOMEARQUIVO, OBSERVACAO, DATAINSERCAO FROM CONTROLEDELTAS ORDER BY IDDELTA DESC";
		
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			ControleDeltaResultSet adapter = new ControleDeltaResultSet(resultSet);
			while(resultSet.next()) {
				ControleDelta controleDelta = adapter.criar();
				deltas.add(controleDelta);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connectionWrapper.releaseResources(connection, statement, resultSet);
		}
		
		return deltas;
	}

	/**
	 * Carrega ultimo delta executado no banco
	 * @return
	 */
	public ControleDelta carregarUltimoDelta() {
		
		String sql = "SELECT IDDELTA, NOMEARQUIVO, OBSERVACAO, DATAINSERCAO FROM CONTROLEDELTAS WHERE IDDELTA = (SELECT MAX(IDDELTA) FROM CONTROLEDELTAS);";
		
		Connection connection = connectionWrapper.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			ControleDeltaResultSet adapter = new ControleDeltaResultSet(resultSet);
			if(resultSet.next()) {
				ControleDelta controleDelta = adapter.criar();
				return controleDelta;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connectionWrapper.releaseResources(connection, statement, resultSet);
		}
		
		return null;
	}
	
	/**
	 * Valor de connectionWrapper atribuído a connectionWrapper
	 *
	 * @param connectionWrapper Atributo da Classe
	 */
	public void setConnectionWrapper(ConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}
}
