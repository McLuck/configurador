
package com.compsis.configurador.executores;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.compsis.configurador.dominio.AcaoExecucao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.Tarefa;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executor de regras de negócio
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ExecutorTarefas implements ApplicationContextAware, Executor {
	private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class);
	private ApplicationContext applicationContext;
	private Map<String, Tarefa> tarefas;
	
	@PostConstruct
	public void setUpExecutor() {
		Map<String, Tarefa> beansTarefas = applicationContext.getBeansOfType(Tarefa.class);
		tarefas = Collections.synchronizedMap(new HashMap<String, Tarefa>());
		synchronized (tarefas) {
			for (Tarefa tarefa : beansTarefas.values()) {
				tarefas.put(tarefa.getNome(), tarefa);
			}			
		}
	}
	
	

	/** 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	/** 
	 * @see com.compsis.configurador.executores.Executor#executar(java.lang.String)
	 */
	@Override
	public Execucao executar(String nomeTarefa) {
		return executar(nomeTarefa, null);
	}



	/** 
	 * Executa {@link Tarefa}
	 * @param nomeTarefa
	 * @param execucaoTarefa
	 */
	private Execucao executar(final String nomeTarefa, final Execucao execucaoTarefa) {
		LOGGER.info("Solicitando execução da tarefa "+nomeTarefa);
		Tarefa tarefa = null;
		synchronized (tarefas) {
			tarefa = tarefas.get(nomeTarefa);
		}
		if(tarefa != null) {
			if(tarefa.getAtividades() == null || tarefa.getAtividades().isEmpty()) {
				LOGGER.warn("Tarefa "+tarefa.getNome() +" não possui atividades para serem executadas." );
			} else {
				Execucao execucao = new Execucao(tarefa, LOGGER ).compartilharSessao(execucaoTarefa);
				LOGGER.info("Iniciada execução da tarefa "+nomeTarefa+" composta por "+tarefa.getAtividades().size()+" atividades");
				for (Atividade atividade : tarefa.getAtividades()) {
					atividade.executar(execucao);
					if(AcaoExecucao.DESFAZER_EXECUCAO.equals(execucao.getAcaoExecucao())) {
						LOGGER.warn("Solicitação de rollback. Parando execução da tarefa "+tarefa.getNome()+". Ultima atividade executada: "
								+atividade.getClass().getName()+" - Tarefa de rollback: " + tarefa.getRollback() );
						
						executar(tarefa.getRollback(), execucao);
						break;
					}
					if(AcaoExecucao.PARAR_EXECUCAO.equals(execucao.getAcaoExecucao())) {
						LOGGER.info("Parando execução da tarefa "+tarefa.getNome()+". Ultima atividade executada: "+atividade.getClass().getName());
						break;
					}
					if(AcaoExecucao.CONTINUAR_EXECUCAO.equals(execucao.getAcaoExecucao())) {
						LOGGER.debug("Continuando execução da tarefa "+tarefa.getNome()+". Ultima atividade executada: "+atividade.getClass().getName());
					}
				}
				return execucao;				
			}
		} else {
			LOGGER.warn("Tarefa com nome "+nomeTarefa+" não foi encontrada na aplicação");
		}
		return null;
	}
}
