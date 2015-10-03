
package com.compsis.configurador.dominio;

import com.compsis.configurador.ExecutorTarefas;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa as ações possíveis durante a {@link Execucao} de {@link Tarefa} <br>
 * O {@link ExecutorTarefas} utilizar esta ação para tomar dexição do que fará entre a execução
 * de uma ou outra {@link Atividade}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public enum AcaoExecucao {
	CONTINUAR_EXECUCAO, PARAR_EXECUCAO, DESFAZER_EXECUCAO
}
