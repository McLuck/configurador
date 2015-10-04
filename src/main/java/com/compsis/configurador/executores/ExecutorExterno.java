
package com.compsis.configurador.executores;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executor de comandos externos (no sistema operacional)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ExecutorExterno implements ApplicationContextAware {

	private String sistemaOperacional;
	private Map<String, ExecutorSO> executores = new HashMap<String, ExecutorSO>();
	private ApplicationContext applicationContext;
	
	@PostConstruct
	public void setUp() {
		sistemaOperacional = System.getenv("OS");
		Map<String, ExecutorSO> execs = applicationContext.getBeansOfType(ExecutorSO.class);
		for (ExecutorSO executorSO : execs.values()) {
			executores.put(executorSO.sistemaOperacional(), executorSO);
		}
	}
	
	private ExecutorSO findExecutorSO() {
		for (String soName : executores.keySet()) {
			boolean soFound = soName.trim().toLowerCase().contains(sistemaOperacional.toLowerCase().trim()) 
					|| sistemaOperacional.toLowerCase().trim().contains(soName.trim().toLowerCase());
			
			if(soFound)
				return executores.get(soName);
		}
		throw new RuntimeException("Executor de SO não encontrado para sistema operacional "+sistemaOperacional+". Executores disponíveis: "+executores.toString());
	}
	

	/** 
	 * @see com.compsis.configurador.executores.Executor#executar(java.lang.String)
	 */
	public Execucao executar(String parametroExecucao) {
		ExecutorSO executorSO = findExecutorSO();
		return executorSO.executar(parametroExecucao);
	}

	/** 
	 * @see com.compsis.configurador.executores.ExecutorSO#iniciarServico(java.lang.String)
	 */
	public void iniciarServico(String nomeServico) {
		ExecutorSO executorSO = findExecutorSO();
		executorSO.iniciarServico(nomeServico);
	}

	/** 
	 * @see com.compsis.configurador.executores.ExecutorSO#pararServico(java.lang.String)
	 */
	public void pararServico(String nomeServico) {
		ExecutorSO executorSO = findExecutorSO();
		executorSO.pararServico(nomeServico);
	}

	/** 
	 * @see com.compsis.configurador.executores.ExecutorSO#sistemaOperacional()
	 */
	public String sistemaOperacional() {
		return sistemaOperacional;
	}

	/** 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
