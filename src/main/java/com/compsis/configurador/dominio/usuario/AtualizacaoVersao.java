
package com.compsis.configurador.dominio.usuario;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * {@link InteracaoUsuario} para atualizações de versões
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings("serial")
public class AtualizacaoVersao extends InteracaoUsuario {
	private boolean apagarPastaTemp;
	private boolean apagarPastaData;
	private boolean apagarPastaWork;
	private boolean apagarPastaLog;
	private boolean renomearLogs;
	private boolean realizarBackup;
	private String versaoSelecionada;
	
	/** 
	 * Construtor padrão da classe
	 */
	public AtualizacaoVersao() {
		setOperacao(OperacaoUsuario.ATUALIZAR_VERSAO);
	}
	
	/**
	 * Método de recuperação do campo apagarPastaTemp
	 *
	 * @return valor do campo apagarPastaTemp
	 */
	public boolean isApagarPastaTemp() {
		return apagarPastaTemp;
	}
	/**
	 * Valor de apagarPastaTemp atribuído a apagarPastaTemp
	 *
	 * @param apagarPastaTemp Atributo da Classe
	 */
	public void setApagarPastaTemp(boolean apagarPastaTemp) {
		this.apagarPastaTemp = apagarPastaTemp;
	}
	/**
	 * Método de recuperação do campo apagarPastaData
	 *
	 * @return valor do campo apagarPastaData
	 */
	public boolean isApagarPastaData() {
		return apagarPastaData;
	}
	/**
	 * Valor de apagarPastaData atribuído a apagarPastaData
	 *
	 * @param apagarPastaData Atributo da Classe
	 */
	public void setApagarPastaData(boolean apagarPastaData) {
		this.apagarPastaData = apagarPastaData;
	}
	/**
	 * Método de recuperação do campo apagarPastaWork
	 *
	 * @return valor do campo apagarPastaWork
	 */
	public boolean isApagarPastaWork() {
		return apagarPastaWork;
	}
	/**
	 * Valor de apagarPastaWork atribuído a apagarPastaWork
	 *
	 * @param apagarPastaWork Atributo da Classe
	 */
	public void setApagarPastaWork(boolean apagarPastaWork) {
		this.apagarPastaWork = apagarPastaWork;
	}
	/**
	 * Método de recuperação do campo apagarPastaLog
	 *
	 * @return valor do campo apagarPastaLog
	 */
	public boolean isApagarPastaLog() {
		return apagarPastaLog;
	}
	/**
	 * Valor de apagarPastaLog atribuído a apagarPastaLog
	 *
	 * @param apagarPastaLog Atributo da Classe
	 */
	public void setApagarPastaLog(boolean apagarPastaLog) {
		this.apagarPastaLog = apagarPastaLog;
	}
	/**
	 * Método de recuperação do campo renomearLogs
	 *
	 * @return valor do campo renomearLogs
	 */
	public boolean isRenomearLogs() {
		return renomearLogs;
	}
	/**
	 * Valor de renomearLogs atribuído a renomearLogs
	 *
	 * @param renomearLogs Atributo da Classe
	 */
	public void setRenomearLogs(boolean renomearLogs) {
		this.renomearLogs = renomearLogs;
	}
	
	/**
	 * Valor de versaoSelecionada atribuído a versaoSelecionada
	 *
	 * @param versaoSelecionada Atributo da Classe
	 */
	public void setVersaoSelecionada(String versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}
	
	/**
	 * Método de recuperação do campo versaoSelecionada
	 *
	 * @return valor do campo versaoSelecionada
	 */
	public String getVersaoSelecionada() {
		return versaoSelecionada;
	}
	
	/**
	 * Valor de realizarBackup atribuído a realizarBackup
	 *
	 * @param realizarBackup Atributo da Classe
	 */
	public void setRealizarBackup(boolean realizarBackup) {
		this.realizarBackup = realizarBackup;
	}
	
	/**
	 * Método de recuperação do campo realizarBackup
	 *
	 * @return valor do campo realizarBackup
	 */
	public boolean isRealizarBackup() {
		return realizarBackup;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AtualizacaoVersao [apagarPastaTemp=");
		builder.append(apagarPastaTemp);
		builder.append(", apagarPastaData=");
		builder.append(apagarPastaData);
		builder.append(", apagarPastaWork=");
		builder.append(apagarPastaWork);
		builder.append(", apagarPastaLog=");
		builder.append(apagarPastaLog);
		builder.append(", renomearLogs=");
		builder.append(renomearLogs);
		builder.append(", realizarBackup=");
		builder.append(realizarBackup);
		builder.append(", versaoSelecionada=");
		builder.append(versaoSelecionada);
		builder.append(", getIdentificacaoUsuario()=");
		builder.append(getIdentificacaoUsuario());
		builder.append(", getOperacao()=");
		builder.append(getOperacao());
		builder.append("]");
		return builder.toString();
	}
}
