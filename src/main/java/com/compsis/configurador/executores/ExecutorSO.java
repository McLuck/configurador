
package com.compsis.configurador.executores;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define execuções chamadas no Sistema Operacional
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface ExecutorSO extends Executor {
	/**
	 * Inicia serviço no sistema operacional
	 * @param nomeServico
	 */
	void iniciarServico(final String nomeServico);
	
	/**
	 * Parar serviço no sistema operacional
	 * @param nomeServico
	 */
	void pararServico(final String nomeServico);
	
	/**
	 * Especifica o sistema operacional do executor
	 * @return
	 */
	String sistemaOperacional();
}
