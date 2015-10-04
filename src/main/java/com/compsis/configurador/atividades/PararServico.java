
package com.compsis.configurador.atividades;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.executores.ExecutorExterno;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade responsável por parar serviço no do sistema que está em execução
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class PararServico implements Atividade {
	private ExecutorExterno executorExterno;
	private Configuracao configuracao;
	
	/** 
	 * see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(final Execucao execucao) {
		execucao.logger().info("Parando serviço "+configuracao.getConfigurador().getSistema());
		executorExterno.pararServico(configuracao.getConfigurador().getSistema());
	}

	/**
	 * Valor de executorExterno atribuído a executorExterno
	 *
	 * @param executorExterno Atributo da Classe
	 */
	public void setExecutorExterno(ExecutorExterno executorExterno) {
		this.executorExterno = executorExterno;
	}
	/**
	 * Valor de configuracao atribuído a configuracao
	 *
	 * @param configuracao Atributo da Classe
	 */
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
}
