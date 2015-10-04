
package com.compsis.configurador.dao.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.compsis.configurador.dominio.ControleDelta;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Cria ControleDelta a partir do {@link ResultSet}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ControleDeltaResultSet {
	private ResultSet resultSet;

	/** 
	 * Construtor padrão/alternativo da classe
	 * @param resultSet
	 */
	public ControleDeltaResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	public ControleDelta criar() {
		ControleDelta controleDelta = new ControleDelta();
		try {
			controleDelta .setDataInsercao(resultSet.getDate("DATAINSERCAO"))
							.setNomeArquivo(resultSet.getString("NOMEARQUIVO"))
							.setObservacao(resultSet.getString("OBSERVACAO"))
							.setId(resultSet.getLong("IDDELTA"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return controleDelta;
	}
}
