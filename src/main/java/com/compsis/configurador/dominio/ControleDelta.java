
package com.compsis.configurador.dominio;

import java.util.Date;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa o Delta de uma determinada versão (script executado em banco)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ControleDelta extends Entity {
	private String nomeArquivo;
	private String observacao;
	private Date dataInsercao;
	/**
	 * Método de recuperação do campo nomeArquivo
	 *
	 * @return valor do campo nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	/**
	 * Valor de nomeArquivo atribuído a nomeArquivo
	 *
	 * @param nomeArquivo Atributo da Classe
	 */
	public ControleDelta setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		return this;
	}
	/**
	 * Método de recuperação do campo observacao
	 *
	 * @return valor do campo observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * Valor de observacao atribuído a observacao
	 *
	 * @param observacao Atributo da Classe
	 */
	public ControleDelta setObservacao(String observacao) {
		this.observacao = observacao;
		return this;
	}
	/**
	 * Método de recuperação do campo dataInsercao
	 *
	 * @return valor do campo dataInsercao
	 */
	public Date getDataInsercao() {
		return dataInsercao;
	}
	/**
	 * Valor de dataInsercao atribuído a dataInsercao
	 *
	 * @param dataInsercao Atributo da Classe
	 */
	public ControleDelta setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
		return this;
	}
	/** 
	 * TODO Descrição do Método
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataInsercao == null) ? 0 : dataInsercao.hashCode());
		result = prime * result
				+ ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		return result;
	}
	/** 
	 * TODO Descrição do Método
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof ControleDelta)) {
			return false;
		}
		ControleDelta other = (ControleDelta) obj;
		if (dataInsercao == null) {
			if (other.dataInsercao != null) {
				return false;
			}
		} else if (!dataInsercao.equals(other.dataInsercao)) {
			return false;
		}
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null) {
				return false;
			}
		} else if (!nomeArquivo.equals(other.nomeArquivo)) {
			return false;
		}
		if (observacao == null) {
			if (other.observacao != null) {
				return false;
			}
		} else if (!observacao.equals(other.observacao)) {
			return false;
		}
		return true;
	}
	/** 
	 * TODO Descrição do Método
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ControleDelta [nomeArquivo=");
		builder.append(nomeArquivo);
		builder.append(", observacao=");
		builder.append(observacao);
		builder.append(", dataInsercao=");
		builder.append(dataInsercao);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}

	
	
}
