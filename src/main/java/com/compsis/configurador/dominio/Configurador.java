
package com.compsis.configurador.dominio;

import java.io.File;
import java.io.Serializable;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Define configurações do configurador
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings("serial")
public class Configurador implements Serializable {
	private String dataSource;
	private String hostDataSource;
	private File pastaScriptsAtualizacao;
	private File pastaScriptsRollback;
	private File pastaBackup;
	private File pastaSistema;
	private String tipoArquivoSistema;
	private String sistema;
	private File pastaAtualizacaoVersao;
	private File pastaNovasInfra;
	private File pastaRaizConfigurador;
	private File pastaRaizInfra;
	
	/**
	 * Método de recuperação do campo dataSource
	 *
	 * @return valor do campo dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * Valor de dataSource atribuído a dataSource
	 *
	 * @param dataSource Atributo da Classe
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * Método de recuperação do campo hostDataSource
	 *
	 * @return valor do campo hostDataSource
	 */
	public String getHostDataSource() {
		return hostDataSource;
	}
	/**
	 * Valor de hostDataSource atribuído a hostDataSource
	 *
	 * @param hostDataSource Atributo da Classe
	 */
	public void setHostDataSource(String hostDataSource) {
		this.hostDataSource = hostDataSource;
	}
	/**
	 * Método de recuperação do campo pastaScriptsAtualizacao
	 *
	 * @return valor do campo pastaScriptsAtualizacao
	 */
	public File getPastaScriptsAtualizacao() {
		return pastaScriptsAtualizacao;
	}
	/**
	 * Valor de pastaScriptsAtualizacao atribuído a pastaScriptsAtualizacao
	 *
	 * @param pastaScriptsAtualizacao Atributo da Classe
	 */
	public void setPastaScriptsAtualizacao(File pastaScriptsAtualizacao) {
		this.pastaScriptsAtualizacao = pastaScriptsAtualizacao;
	}
	/**
	 * Método de recuperação do campo pastaScriptsRollback
	 *
	 * @return valor do campo pastaScriptsRollback
	 */
	public File getPastaScriptsRollback() {
		return pastaScriptsRollback;
	}
	/**
	 * Valor de pastaScriptsRollback atribuído a pastaScriptsRollback
	 *
	 * @param pastaScriptsRollback Atributo da Classe
	 */
	public void setPastaScriptsRollback(File pastaScriptsRollback) {
		this.pastaScriptsRollback = pastaScriptsRollback;
	}
	/**
	 * Método de recuperação do campo pastaBackup
	 *
	 * @return valor do campo pastaBackup
	 */
	public File getPastaBackup() {
		return pastaBackup;
	}
	/**
	 * Valor de pastaBackup atribuído a pastaBackup
	 *
	 * @param pastaBackup Atributo da Classe
	 */
	public void setPastaBackup(File pastaBackup) {
		this.pastaBackup = pastaBackup;
	}
	/**
	 * Método de recuperação do campo pastaSistema
	 *
	 * @return valor do campo pastaSistema
	 */
	public File getPastaSistema() {
		return pastaSistema;
	}
	/**
	 * Valor de pastaSistema atribuído a pastaSistema
	 *
	 * @param pastaSistema Atributo da Classe
	 */
	public void setPastaSistema(File pastaSistema) {
		this.pastaSistema = pastaSistema;
	}
	/**
	 * Método de recuperação do campo tipoArquivoSistema
	 *
	 * @return valor do campo tipoArquivoSistema
	 */
	public String getTipoArquivoSistema() {
		return tipoArquivoSistema;
	}
	/**
	 * Valor de tipoArquivoSistema atribuído a tipoArquivoSistema
	 *
	 * @param tipoArquivoSistema Atributo da Classe
	 */
	public void setTipoArquivoSistema(String tipoArquivoSistema) {
		this.tipoArquivoSistema = tipoArquivoSistema;
	}

	/**
	 * Valor de sistema atribuído a sistema
	 *
	 * @param sistema Atributo da Classe
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	/**
	 * Método de recuperação do campo sistema
	 *
	 * @return valor do campo sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Valor de pastaAtualizacaoVersao atribuído a pastaAtualizacaoVersao
	 *
	 * @param pastaAtualizacaoVersao Atributo da Classe
	 */
	public void setPastaAtualizacaoVersao(File pastaAtualizacaoVersao) {
		this.pastaAtualizacaoVersao = pastaAtualizacaoVersao;
	}
	
	/**
	 * Método de recuperação do campo pastaAtualizacaoVersao
	 *
	 * @return valor do campo pastaAtualizacaoVersao
	 */
	public File getPastaAtualizacaoVersao() {
		return pastaAtualizacaoVersao;
	}
	/**
	 * Método de recuperação do campo pastaNovasInfra
	 *
	 * @return valor do campo pastaNovasInfra
	 */
	public File getPastaNovasInfra() {
		return pastaNovasInfra;
	}
	/**
	 * Valor de pastaNovasInfra atribuído a pastaNovasInfra
	 *
	 * @param pastaNovasInfra Atributo da Classe
	 */
	public void setPastaNovasInfra(File pastaNovasInfra) {
		this.pastaNovasInfra = pastaNovasInfra;
	}
	/**
	 * Método de recuperação do campo pastaRaizConfigurador
	 *
	 * @return valor do campo pastaRaizConfigurador
	 */
	public File getPastaRaizConfigurador() {
		return pastaRaizConfigurador;
	}
	/**
	 * Valor de pastaRaizConfigurador atribuído a pastaRaizConfigurador
	 *
	 * @param pastaRaizConfigurador Atributo da Classe
	 */
	public void setPastaRaizConfigurador(File pastaRaizConfigurador) {
		this.pastaRaizConfigurador = pastaRaizConfigurador;
	}
	/**
	 * Método de recuperação do campo pastaRaizInfra
	 *
	 * @return valor do campo pastaRaizInfra
	 */
	public File getPastaRaizInfra() {
		return pastaRaizInfra;
	}
	/**
	 * Valor de pastaRaizInfra atribuído a pastaRaizInfra
	 *
	 * @param pastaRaizInfra Atributo da Classe
	 */
	public void setPastaRaizInfra(File pastaRaizInfra) {
		this.pastaRaizInfra = pastaRaizInfra;
	}
	/** 
	 * TODO Descrição do Método
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Configurador [dataSource=");
		builder.append(dataSource);
		builder.append(", hostDataSource=");
		builder.append(hostDataSource);
		builder.append(", pastaScriptsAtualizacao=");
		builder.append(pastaScriptsAtualizacao);
		builder.append(", pastaScriptsRollback=");
		builder.append(pastaScriptsRollback);
		builder.append(", pastaBackup=");
		builder.append(pastaBackup);
		builder.append(", pastaSistema=");
		builder.append(pastaSistema);
		builder.append(", tipoArquivoSistema=");
		builder.append(tipoArquivoSistema);
		builder.append(", sistema=");
		builder.append(sistema);
		builder.append(", pastaAtualizacaoVersao=");
		builder.append(pastaAtualizacaoVersao);
		builder.append(", pastaNovasInfra=");
		builder.append(pastaNovasInfra);
		builder.append(", pastaRaizConfigurador=");
		builder.append(pastaRaizConfigurador);
		builder.append(", pastaRaizInfra=");
		builder.append(pastaRaizInfra);
		builder.append("]");
		return builder.toString();
	}
		
}
