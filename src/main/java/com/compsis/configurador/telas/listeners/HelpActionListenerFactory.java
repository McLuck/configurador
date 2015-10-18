
package com.compsis.configurador.telas.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.telas.TelaAjudaComponente;
import com.google.common.base.Strings;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Irá criar nova instancia de {@link ActionListener}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 18/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class HelpActionListenerFactory {
	private Properties properties;
	private Configuracao configuracao;
	
	public ActionListener createActionListener(final Ajuda ajuda) {
		carregarProperties();
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = properties.getProperty(ajuda.ajuda());
				if(!Strings.isNullOrEmpty(mensagem)) {
					mensagem = substituirMetaTags(mensagem);
					
					String titulo = properties.getProperty(ajuda.titulo());
					if(Strings.isNullOrEmpty(titulo)) {
						titulo = "Ajuda";
					} else {
						titulo = substituirMetaTags(titulo);												
					}
					
					new TelaAjudaComponente(titulo, mensagem);
				}
				
			}
		};
		return actionListener;
	}
	
	private synchronized void carregarProperties() {
		if(properties == null) {
			properties = new Properties();
			InputStream inputStream = HelpActionListenerFactory.class.getClassLoader().getResourceAsStream("mensagens.properties");
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private String substituirMetaTags(final String mensagem) {
		return mensagem
				.replace("${diretorio_update_versao}", configuracao.getConfigurador().getPastaAtualizacaoVersao().getAbsolutePath())
				.replace("${diretorio_update_scripts}", configuracao.getConfigurador().getPastaScriptsAtualizacao().getAbsolutePath())
				.replace("${diretorio_rollback_scripts}", configuracao.getConfigurador().getPastaScriptsRollback().getAbsolutePath())
				.replace("${diretorio_backup}", configuracao.getConfigurador().getPastaBackup() .getAbsolutePath())
				.replace("${diretorio_novas_infras}", configuracao.getConfigurador().getPastaNovasInfra() .getAbsolutePath())
				.replace("${diretorio_raiz_configurador}", configuracao.getConfigurador().getPastaRaizConfigurador() .getAbsolutePath())
				.replace("${diretorio_raiz_infra}", configuracao.getConfigurador().getPastaRaizInfra() .getAbsolutePath())
				.replace("${diretorio_sistema}", configuracao.getConfigurador().getPastaSistema() .getAbsolutePath())
				.replace("${diretorio_stdout}", new File(configuracao.getConfigurador().getPastaRaizInfra(), "bin") .getAbsolutePath())
				.replace("${diretorio_tmp}", new File(configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile(), "temp") .getAbsolutePath())
				.replace("${diretorio_work}", new File(configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile(), "work") .getAbsolutePath())
				.replace("${diretorio_log}", new File(configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile(), "log") .getAbsolutePath())
				.replace("${diretorio_data}", new File(configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile(), "data") .getAbsolutePath())
				.replace("${diretorio_deploy}", configuracao.getConfigurador().getPastaSistema().getParentFile() .getAbsolutePath())
				
				;
	}
	
	
	public static enum Ajuda {
		VERSOES_DISPONIVEIS_PARA_UPDATE("versaodisponiveisparaupdate"), 
		BACKUP_VERSAO_ANTERIOR("backupversaoanterior"), 
		RENOMEAR_LOGS_STDOUT("renomearlogs"), 
		APAGAR_PASTA_WORK("apagarpastawork"), 
		APAGAR_PASTA_LOGS("apagarpastalogs"), 
		APAGAR_PASTA_TEMPORARIA("apagarpastatemp"), 
		APAGAR_PASTA_DATA("apagarpastadata");
		Ajuda(String valor) {
			this.valor = valor;
		}
		String valor;
		
		/** 
		 * see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ajuda."+valor;
		}
		
		public String ajuda() {
			return toString();
		}
		
		public String titulo() {
			return toString()+".titulo";
		}
	}
	
	/**
	 * Valor de configuracao atribuído a configuracao
	 *
	 * @param configuracao Atributo da Classe
	 */
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
}
