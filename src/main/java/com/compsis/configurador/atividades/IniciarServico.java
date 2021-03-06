
package com.compsis.configurador.atividades;

import java.sql.Connection;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dao.ConnectionWrapper;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.executores.ExecutorExterno;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade responsável por iniciar serviço no do sistema que está em execução
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class IniciarServico implements Atividade {
	private ExecutorExterno executorExterno;
	private Configuracao configuracao;
	private ConnectionWrapper connectionWrapper;
	private int segundosParaEsperarJboss = 5;
	/** 
	 * see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(final Execucao execucao) {
		execucao.info("Iniciando serviço "+configuracao.getConfigurador().getSistema());
		Execucao inicioServico = executorExterno.iniciarServico(configuracao.getConfigurador().getSistema());
		if(inicioServico==null) {
			execucao.error("Erro na execução do serviço. Sem retorno da execução.");
		} else {
			Integer retornoDoPrograma = inicioServico.obterDaSessao(Constantes.RETORNO_PROGRAMA.toString());
			execucao.info("Retorno do programa: "+retornoDoPrograma);				
			
			Connection connection = null;
			boolean conexaoValida = false;
			do {
				try {
					connection = connectionWrapper.getConnection();
					conexaoValida = !connection.isClosed();
				} catch (Exception e) {
					execucao.warn("Aguardando Jboss. Datasource ainda não respondeu conexão com o banco. Tempo de espera: "
							+ segundosParaEsperarJboss + " segundos");
					execucao.logger().warn(e.getMessage(), e);
					try {
						Thread.sleep(segundosParaEsperarJboss * 1000);
					} catch (InterruptedException e1) {
						execucao.logger().error(e1.getMessage(), e1);
					}
				}
			} while (connection == null || !conexaoValida);
		}
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
	
	/**
	 * Valor de connectionWrapper atribuído a connectionWrapper
	 *
	 * @param connectionWrapper Atributo da Classe
	 */
	public void setConnectionWrapper(ConnectionWrapper connectionWrapper) {
		this.connectionWrapper = connectionWrapper;
	}
}
