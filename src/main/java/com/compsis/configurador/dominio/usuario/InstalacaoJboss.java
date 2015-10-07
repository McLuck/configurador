
package com.compsis.configurador.dominio.usuario;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Configuração do usuário para instalação do jboss
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 06/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class InstalacaoJboss extends InteracaoUsuario {
	private String nomeServico;
	private boolean substituirInfraOriginal;
	private boolean realizarBkp;
	/**
	 * Método de recuperação do campo nomeServico
	 *
	 * @return valor do campo nomeServico
	 */
	public String getNomeServico() {
		return nomeServico;
	}
	/**
	 * Valor de nomeServico atribuído a nomeServico
	 *
	 * @param nomeServico Atributo da Classe
	 */
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	/**
	 * Método de recuperação do campo substituirInfraOriginal
	 *
	 * @return valor do campo substituirInfraOriginal
	 */
	public boolean isSubstituirInfraOriginal() {
		return substituirInfraOriginal;
	}
	/**
	 * Valor de substituirInfraOriginal atribuído a substituirInfraOriginal
	 *
	 * @param substituirInfraOriginal Atributo da Classe
	 */
	public void setSubstituirInfraOriginal(boolean substituirInfraOriginal) {
		this.substituirInfraOriginal = substituirInfraOriginal;
	}
	/**
	 * Método de recuperação do campo realizarBkp
	 *
	 * @return valor do campo realizarBkp
	 */
	public boolean isRealizarBkp() {
		return realizarBkp;
	}
	/**
	 * Valor de realizarBkp atribuído a realizarBkp
	 *
	 * @param realizarBkp Atributo da Classe
	 */
	public void setRealizarBkp(boolean realizarBkp) {
		this.realizarBkp = realizarBkp;
	}
	/** 
	 * TODO Descrição do Método
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstalacaoJboss [nomeServico=");
		builder.append(nomeServico);
		builder.append(", substituirInfraOriginal=");
		builder.append(substituirInfraOriginal);
		builder.append(", realizarBkp=");
		builder.append(realizarBkp);
		builder.append("]");
		return builder.toString();
	}
	
	
}
