
package com.compsis.configurador.atividades;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.dao.ControleDeltaDAO;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.ControleDelta;
import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Carregar o último delta do banco na {@link Configuracao}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class CarregarUltimoDelta implements Atividade {
	private Configuracao configuracao;
	private ControleDeltaDAO controleDeltaDAO;
	
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.logger().info("Carregando o último delta executado no banco de dados no configurador");
		
		ControleDelta ultimoDelta = controleDeltaDAO.carregarUltimoDelta();
		execucao.logger().info("Ultimo delta carregado: "+ultimoDelta);
		configuracao.setUltimoDeltaExecutado(ultimoDelta);
	}
	
	/**
	 * Valor de configuracao atribuído a configuracao
	 *
	 * @param configuracao Atributo da Classe
	 */
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	/**
	 * Valor de controleDeltaDAO atribuído a controleDeltaDAO
	 *
	 * @param controleDeltaDAO Atributo da Classe
	 */
	public void setControleDeltaDAO(ControleDeltaDAO controleDeltaDAO) {
		this.controleDeltaDAO = controleDeltaDAO;
	}
}
