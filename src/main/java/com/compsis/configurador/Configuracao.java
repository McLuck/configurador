
package com.compsis.configurador;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compsis.configurador.dominio.Configurador;
import com.compsis.configurador.dominio.ControleDelta;
import com.google.common.base.Strings;
import com.thoughtworks.xstream.XStream;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Possui informações de configurações do condifurador
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class Configuracao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Configuracao.class);
	private Configurador configurador;
	private File homeConfigurador;
	private ControleDelta ultimoDeltaExecutado;
	
	@PostConstruct
	public void setUp() {
		String CONFIGURADOR_SICAT = System.getenv("CONFIGURADOR_SICAT");
		if(Strings.isNullOrEmpty(CONFIGURADOR_SICAT)) {
			throw new RuntimeException("Variável de ambiente CONFIGURADOR_SICAT não está definida. Ela deve ser definida informando o diretório raiz do configurador");
		}
		String sicatHome = System.getenv("SICAT_HOME");
		if(Strings.isNullOrEmpty(sicatHome)) {
			sicatHome = "D:\\sicat";
		}
		File sicatHomeFile = new File(sicatHome);
		if(!sicatHomeFile.exists()) {
			sicatHomeFile.mkdirs();
		}
		homeConfigurador = new File(CONFIGURADOR_SICAT, "configurador");
		if(!homeConfigurador.exists()) {
			homeConfigurador.mkdirs();
		}
		File configuradorFile = new File(homeConfigurador, "configudador.xml");
		if(!configuradorFile.getParentFile().exists()) {
			configuradorFile.getParentFile().mkdirs();
		}
		XStream xStream = new XStream();
		if(configuradorFile.exists()) {
			configurador = (Configurador) xStream.fromXML(configuradorFile);
		} else {
			File pastaBackup = new File(homeConfigurador, "backup");
			File pastaVersao = new File(homeConfigurador, "versao");
			File pastaScripts = new File(homeConfigurador, "scripts");
			File pastaScriptsAtualizacao = new File(pastaScripts, "update");
			File pastaScriptsRollback = new File(pastaScripts, "rollback");
			File pastaNovasInfras = new File(homeConfigurador, "infra");
			if(!pastaNovasInfras.exists()) {
				pastaNovasInfras.mkdirs();
			}
			if(!pastaScriptsAtualizacao.exists()) {
				pastaScriptsAtualizacao.mkdirs();
			}
			if(!pastaScriptsRollback.exists()) {
				pastaScriptsRollback.mkdirs();
			}
			if(!pastaVersao.exists()) {
				pastaVersao.mkdirs();
			}
			if(!pastaBackup.exists()) {
				pastaBackup.mkdirs();
			}
			configurador = new Configurador();
			configurador.setDataSource("xp");
			configurador.setSistema("SGAPSA");
			configurador.setHostDataSource("SICATSGAP");
			configurador.setPastaBackup(pastaBackup);
			configurador.setPastaScriptsAtualizacao(pastaScriptsAtualizacao);
			configurador.setPastaScriptsRollback(pastaScriptsRollback);
			configurador.setPastaNovasInfra(pastaNovasInfras);
			configurador.setPastaRaizConfigurador(homeConfigurador);
			configurador.setPastaRaizInfra(sicatHomeFile);
			File pastaSistema = new File( new File ( new File( new File( new File(sicatHomeFile, "app") , "server"), "sgap"), "deploy"), "sgap-sa");
			configurador.setPastaSistema(pastaSistema);
			configurador.setTipoArquivoSistema("WAR,EAR");
			configurador.setPastaAtualizacaoVersao(pastaVersao);
			
			Writer writer;
			try {
				writer = new FileWriter(configuradorFile);
				xStream.toXML(configurador, writer);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}
		LOGGER.info("Configuração carregada a partir do arquivo "+configuradorFile.getAbsolutePath());
		LOGGER.info(configurador.toString());
	}
	
	/**
	 * Método de recuperação do campo configurador <br>
	 * Configurações do Configurador
	 * @return valor do campo configurador
	 */
	public Configurador getConfigurador() {
		return configurador;
	}

	/**
	 * Método de recuperação do campo homeConfigurador
	 * <br>
	 * Diretório raiz do configurador
	 * @return valor do campo homeConfigurador
	 */
	public File getHomeConfigurador() {
		return homeConfigurador;
	}
	
	/**
	 * Realiza filtro dos arquivos do sistema que o configurador está sendo utilizado <br>
	 * Exemplo: O arquivo atual do sistema
	 * @return
	 */
	public File[] arquivosDoSistema() {
		final String[] extensoes = getConfigurador().getTipoArquivoSistema().toLowerCase().trim().split(",");
		File[] arquivosEncontrados = getConfigurador().getPastaSistema().listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				for (String extensao : extensoes) {
					if(name.toLowerCase().endsWith("."+extensao)) {
						return true;
					}
				}
				return false;
			}
		});
		return arquivosEncontrados;
	}
	
	
	/**
	 * Realiza filtro dos arquivos de backup do sistema
	 * @return
	 */
	public File[] arquivosDoSistemaBackup() {
		final String[] extensoes = getConfigurador().getTipoArquivoSistema().toLowerCase().trim().split(",");
		File[] arquivosEncontrados = getConfigurador().getPastaBackup().listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				for (String extensao : extensoes) {
					if(name.toLowerCase().endsWith("."+extensao)) {
						return true;
					}
				}
				return false;
			}
		});
		return arquivosEncontrados;
	}

	/**
	 * Valor de ultimoDeltaExecutado atribuído a ultimoDeltaExecutado
	 *
	 * @param ultimoDelta Atributo da Classe
	 */
	public void setUltimoDeltaExecutado(final ControleDelta ultimoDelta) {
		this.ultimoDeltaExecutado = ultimoDelta;
	}
	
	/**
	 * Método de recuperação do campo ultimoDeltaExecutado
	 * <br>
	 * Ultimo delta executado no banco de dados
	 * @return valor do campo ultimoDeltaExecutado
	 */
	public ControleDelta getUltimoDeltaExecutado() {
		return ultimoDeltaExecutado;
	}
	
}
