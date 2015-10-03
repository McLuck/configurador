/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 03/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.dominio;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa execução da {@link Tarefa} <br>
 * Esta classe poderá armazenar estado da execução durante a operação <br>
 * As {@link Tarefa} e as {@link Atividade} não devem armazenar estados
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@SuppressWarnings("unchecked")
public class Execucao {
	private Tarefa tarefa;
	private Logger logger;
	private AcaoExecucao acaoExecucao = AcaoExecucao.CONTINUAR_EXECUCAO;
	private Map<String, Object> sessao = new HashMap<String, Object>();
	
	/** 
	 * Construtor padrão da classe
	 * @param tarefa
	 * @param logger
	 */
	public Execucao(final Tarefa tarefa, final Logger logger) {
		this.tarefa = tarefa;
		this.logger = logger;
	}

	/**
	 * Método de recuperação do campo tarefa
	 *
	 * @return valor do campo tarefa
	 */
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	/**
	 * Define a próxima ação de execução
	 * @param acao
	 */
	public Execucao proximaAcaoExecucao(AcaoExecucao acao) {
		this.acaoExecucao = acao;
		return this;
	}
	
	/**
	 * Adicionar objeto na sessao da execução
	 * @param chave
	 * @param objeto
	 */
	public Execucao adicionarNaSessao(final String chave, final Object objeto) {
		sessao.put(chave, objeto);
		return this;
	}
	
	/**
	 * Obtém objetos da sessão da {@link Execucao} da {@link Tarefa}
	 * @param chave
	 * @return
	 */
	public <O> O obterDaSessao(final String chave) {
		return (O) sessao.get(chave);
	}
	
	/**
	 * Método de recuperação do campo acaoExecucao
	 * <br>
	 * Ação da execução para o Executor de atividades da {@link Tarefa}
	 * @return valor do campo acaoExecucao
	 */
	public AcaoExecucao getAcaoExecucao() {
		return acaoExecucao;
	}
	
	/**
	 * Método de recuperação do campo logger
	 * <br>
	 * Retorna o {@link Logger} da execução da tarefa
	 * @return valor do campo logger
	 */
	public Logger logger() {
		return logger;
	}
	
	/**
	 * Compatilha a mesma sessão da execução informada pelo parametro <br>
	 * OBS: A sessão da instancia de {@link Execucao} atual será perdida
	 * @param sessao
	 * @return
	 */
	public Execucao compartilharSessao(final Execucao execucao) {
		if(execucao!=null) {
			this.sessao = execucao.sessao;			
		}
		return this;
	}
}
