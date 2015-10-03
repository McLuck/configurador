/*
 * COMPSIS � Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Cria��o: 03/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.dominio;

/** 
 * DOCUMENTA��O DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define Opera��o, contrato que deve ser utilizado para implementar qualquer opera��o do configurador <br>
 * Operacoes nao devem armazenar estados
 * <br>
 * HIST�RICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira vers�o da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface Operacao<D extends Descritor> {
	/**
	 * Executa a operacao para o descritor definido
	 * @param descritorOperacao
	 */
	void executar(D descritorOperacao);
}
