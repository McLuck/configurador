/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 03/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.dominio;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define Operação, contrato que deve ser utilizado para implementar qualquer operação do configurador <br>
 * Operacoes nao devem armazenar estados
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
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
