package com.compsis.configurador.dominio.usuario;

import java.io.Serializable;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Classe de interação com usuário (via interface)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings("serial")
public class InteracaoUsuario implements Serializable {
	private String identificacaoUsuario;
	private OperacaoUsuario operacao;
	/**
	 * Método de recuperação do campo identificacaoUsuario
	 *
	 * @return valor do campo identificacaoUsuario
	 */
	public String getIdentificacaoUsuario() {
		return identificacaoUsuario;
	}
	/**
	 * Valor de identificacaoUsuario atribuído a identificacaoUsuario
	 *
	 * @param identificacaoUsuario Atributo da Classe
	 */
	public void setIdentificacaoUsuario(String identificacaoUsuario) {
		this.identificacaoUsuario = identificacaoUsuario;
	}
	/**
	 * Método de recuperação do campo operacao
	 *
	 * @return valor do campo operacao
	 */
	public OperacaoUsuario getOperacao() {
		return operacao;
	}
	/**
	 * Valor de operacao atribuído a operacao
	 *
	 * @param operacao Atributo da Classe
	 */
	public void setOperacao(OperacaoUsuario operacao) {
		this.operacao = operacao;
	}
	
	
}
