
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

@SuppressWarnings("serial")
public class InstalacaoJboss6SA extends InteracaoUsuario {
	private String nomeServico;
	private boolean substituirInfraOriginal;
	private boolean realizarBkp;
	private String diretorioInstalacao;
	private String nomeBD;
	private String hostBD;
	private String usuarioBD;
	private String senhaBD;
	private String urlBD;
	private String instanciaBD;
	private String versaoBD;
	
	/** 
	 * Construtor padrão da classe
	 */
	public InstalacaoJboss6SA() {
		setOperacao(OperacaoUsuario.INSTALACAO_JBOSS);
	}
	
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
	 * Valor de diretorioInstalacao atribuído a diretorioInstalacao
	 *
	 * @param diretorioInstalacao Atributo da Classe
	 */
	public void setDiretorioInstalacao(String diretorioInstalacao) {
		this.diretorioInstalacao = diretorioInstalacao;
	}
	
	/**
	 * Método de recuperação do campo diretorioInstalacao
	 *
	 * @return valor do campo diretorioInstalacao
	 */
	public String getDiretorioInstalacao() {
		return diretorioInstalacao;
	}

	/**
	 * Método de recuperação do campo nomeBD
	 *
	 * @return valor do campo nomeBD
	 */
	public String getNomeBD() {
		return nomeBD;
	}

	/**
	 * Valor de nomeBD atribuído a nomeBD
	 *
	 * @param nomeBD Atributo da Classe
	 */
	public void setNomeBD(String nomeBD) {
		this.nomeBD = nomeBD;
	}

	/**
	 * Método de recuperação do campo hostBD
	 *
	 * @return valor do campo hostBD
	 */
	public String getHostBD() {
		return hostBD;
	}

	/**
	 * Valor de hostBD atribuído a hostBD
	 *
	 * @param hostBD Atributo da Classe
	 */
	public void setHostBD(String hostBD) {
		this.hostBD = hostBD;
	}

	/**
	 * Método de recuperação do campo usuarioBD
	 *
	 * @return valor do campo usuarioBD
	 */
	public String getUsuarioBD() {
		return usuarioBD;
	}

	/**
	 * Valor de usuarioBD atribuído a usuarioBD
	 *
	 * @param usuarioBD Atributo da Classe
	 */
	public void setUsuarioBD(String usuarioBD) {
		this.usuarioBD = usuarioBD;
	}

	/**
	 * Método de recuperação do campo senhaBD
	 *
	 * @return valor do campo senhaBD
	 */
	public String getSenhaBD() {
		return senhaBD;
	}

	/**
	 * Valor de senhaBD atribuído a senhaBD
	 *
	 * @param senhaBD Atributo da Classe
	 */
	public void setSenhaBD(String senhaBD) {
		this.senhaBD = senhaBD;
	}

	/**
	 * Método de recuperação do campo urlBD
	 *
	 * @return valor do campo urlBD
	 */
	public String getUrlBD() {
		return urlBD;
	}

	/**
	 * Valor de urlBD atribuído a urlBD
	 *
	 * @param urlBD Atributo da Classe
	 */
	public void setUrlBD(String urlBD) {
		this.urlBD = urlBD;
	}

	/**
	 * Método de recuperação do campo instanciaBD
	 *
	 * @return valor do campo instanciaBD
	 */
	public String getInstanciaBD() {
		return instanciaBD;
	}

	/**
	 * Valor de instanciaBD atribuído a instanciaBD
	 *
	 * @param instanciaBD Atributo da Classe
	 */
	public void setInstanciaBD(String instanciaBD) {
		this.instanciaBD = instanciaBD;
	}
	
	/**
	 * Valor de versaoBD atribuído a versaoBD
	 *
	 * @param versaoBD Atributo da Classe
	 */
	public void setVersaoBD(String versaoBD) {
		this.versaoBD = versaoBD;
	}
	
	/**
	 * Método de recuperação do campo versaoBD
	 *
	 * @return valor do campo versaoBD
	 */
	public String getVersaoBD() {
		return versaoBD;
	}

	/** 
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
		builder.append(", diretorioInstalacao=");
		builder.append(diretorioInstalacao);
		builder.append(", nomeBD=");
		builder.append(nomeBD);
		builder.append(", hostBD=");
		builder.append(hostBD);
		builder.append(", usuarioBD=");
		builder.append(usuarioBD);
		builder.append(", senhaBD=");
		builder.append(senhaBD);
		builder.append(", urlBD=");
		builder.append(urlBD);
		builder.append(", instanciaBD=");
		builder.append(instanciaBD);
		builder.append(", versaoBD=");
		builder.append(versaoBD);
		builder.append(", getIdentificacaoUsuario()=");
		builder.append(getIdentificacaoUsuario());
		builder.append(", getOperacao()=");
		builder.append(getOperacao());
		builder.append("]");
		return builder.toString();
	}
}
