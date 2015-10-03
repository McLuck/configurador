package com.compsis.configurador.dominio;

/** 
 * DOCUMENTA��O DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define atividade, contrato que deve ser utilizado para implementar qualquer atividade que sera realizada pelo configurador <br>
 * {@link Atividade} e {@link Tarefa} nao devem armazenar estados
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public interface Atividade {
	/**
	 * Executa a operacao para o descritor definido
	 * @param descritorOperacao
	 */
	void executar(Execucao execucao);
	
}
