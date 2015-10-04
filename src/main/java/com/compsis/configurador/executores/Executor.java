/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 03/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.executores;

import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define contrato para executores
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface Executor {
	/**
	 * Executa a tarefa
	 * @param parametroExecucao
	 * @return
	 */
	Execucao executar(final String parametroExecucao);
}
