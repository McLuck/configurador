
package com.compsis.configurador.atividades;

import java.io.File;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss;
import com.compsis.configurador.executores.ExecutorExterno;
import com.compsis.configurador.util.FilesUtil;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Instala e configura Jboss a partir da pasta raiz
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 06/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class InstalarJboss implements Atividade {
	private Configuracao configuracao;
	private ExecutorExterno executorExterno;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.logger().info("Iniciando instalação da infra");
		String sicatHome = configuracao.getConfigurador().getPastaRaizInfra().getAbsolutePath();
		File pastaInfra = configuracao.getConfigurador().getPastaNovasInfra();
		File pastaSicat = new File(sicatHome);
		FilesUtil.validarDiretorio(pastaInfra);
		File infraJboss = new File(pastaInfra, Constantes.INFRA_JBOSS.toString() + ".zip");
		if(!infraJboss.exists()) {
			new RuntimeException("Arquivo esperado para realizar instalação da infra não disponível: " + infraJboss.getAbsolutePath());
		}
		
		InstalacaoJboss instalacao = execucao.obterDaSessao(Constantes.INSTALACAO_JBOSS_INTERACAO_USUARIO.toString());
		
		if(pastaSicat.exists()) {
			if(instalacao.isSubstituirInfraOriginal()) {
				execucao.logger().warn("Infra já existe no caminho "+pastaSicat.getAbsolutePath()+" e será substituida.");
				if(instalacao.isRealizarBkp()) {
					execucao.logger().info("Realizando backup do diretorio "+pastaSicat.getAbsolutePath()+" em "+configuracao.getConfigurador().getPastaBackup());
					File arquivoBkp = new File(configuracao.getConfigurador().getPastaBackup(), "bkpJboss.zip");
					FilesUtil.zipDir(pastaSicat, arquivoBkp); 
					execucao.logger().info("Backup do diretorio "+pastaSicat.getAbsolutePath()+" realizado em "+arquivoBkp.getAbsolutePath());
				}
				execucao.logger().warn("Apagando diretório "+pastaSicat.getAbsolutePath());
				FilesUtil.apagarDiretorio(pastaSicat, execucao.logger());
			} else {
				throw new RuntimeException("Diretório "+pastaSicat.getAbsolutePath()+" já existe e não será substituido. Instalação abortada.");
			}
		}
		pastaSicat.mkdirs();
		FilesUtil.unzip(infraJboss, pastaSicat );
		
		execucao.logger().warn("Definindo variáveis de ambiente SICAT_HOME, JBOSS_HOME e STDOUT. Este recurso exige que a execução do configurador esteja como administradora do sistema.");
		executorExterno.executar("SETX JBOSS_HOME \""+pastaSicat.getAbsolutePath()+"\" /M");
		executorExterno.executar("SETX SICAT_HOME \""+pastaSicat.getAbsolutePath()+"\" /M");
		executorExterno.executar("SETX STDOUT \""+new File(pastaSicat, "app").getAbsolutePath()+"\" /M");
		
		executorExterno.executar("CALL "+new File(new File(new File(sicatHome, "app"), "bin"), "install.bat").getAbsolutePath());
		
		execucao.logger().info("Instalação da infra Jboss concluida");
	}

	/**
	 * Valor de configuracao atribuído a configuracao
	 *
	 * @param configuracao Atributo da Classe
	 */
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	/**
	 * Valor de executorExterno atribuído a executorExterno
	 *
	 * @param executorExterno Atributo da Classe
	 */
	public void setExecutorExterno(ExecutorExterno executorExterno) {
		this.executorExterno = executorExterno;
	}
	
}
