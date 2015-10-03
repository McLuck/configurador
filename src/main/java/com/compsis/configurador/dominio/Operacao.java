package com.compsis.configurador.dominio;

/** 
 * DOCUMENTA��O DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define Operação, contrato que deve ser utilizado para implementar qualquer Operação do configurador <br>
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
	
	/**
	 * Define um nome unico para operação
	 * @return
	 */
	String nome();
}
