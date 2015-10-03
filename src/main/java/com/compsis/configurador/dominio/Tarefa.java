/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 03/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.dominio;

import java.util.List;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Tarefa é o conjunto de atividades que serão executadas <br>
 * A Tarefa deve possuir nome, lista de atividades e não deve armazenar estado
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class Tarefa {
	private List<Atividade> atividades;
	private String nome;
	private String rollback;
	
	/** 
	 * Construtor padrão da classe
	 * @param atividades
	 */
	public Tarefa(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	/**
	 * Método de recuperação do campo atividades
	 *
	 * @return valor do campo atividades
	 */
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	/**
	 * Método de recuperação do campo nome
	 *
	 * @return valor do campo nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Valor de nome atribuído a nome
	 *
	 * @param nome Atributo da Classe
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Método de recuperação do campo rollback
	 *
	 * @return valor do campo rollback
	 */
	public String getRollback() {
		return rollback;
	}
	/**
	 * Valor de rollback atribuído a rollback
	 *
	 * @param rollback Atributo da Classe
	 */
	public void setRollback(String rollback) {
		this.rollback = rollback;
	}
}
