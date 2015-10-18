
package com.compsis.configurador.executores;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executa comandos no windows
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ExecutorWindows implements ExecutorSO {
	private final static Logger LOGGER = LoggerFactory.getLogger(ExecutorWindows.class);
	private Configuracao configuracao;
	private File pastaBats;
	
	@PostConstruct
	public void setUp() {
		pastaBats = new File(configuracao.getHomeConfigurador(), "BATS");
		if(!pastaBats.exists()) {
			pastaBats.mkdirs();
		}
		for (File batFile : pastaBats.listFiles()) {
			batFile.delete();
		}
	}
	
	public Execucao executar(final String comando) {
		LOGGER.info("Executando comando: "+comando);
		int respostaDoPrograma = 0;
		try {
			Process processo = Runtime.getRuntime().exec(comando);
			respostaDoPrograma = processo.waitFor();
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
//		File novoBat = new File(pastaBats, System.currentTimeMillis()+".BAT");
//		try {
//			Files.write(comando, novoBat, Charsets.US_ASCII);
//			LOGGER.info("Arquivo temporário criado para execução: "+novoBat.getAbsolutePath());
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		
//
//
//		try {
//			Runtime.getRuntime().exec("cmd /c start "+novoBat.getAbsolutePath());
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
		Execucao execucao = new Execucao(null, LOGGER);
		execucao.adicionarNaSessao(Constantes.RETORNO_PROGRAMA.toString(), respostaDoPrograma);
		return execucao;
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
	 * @see com.compsis.configurador.executores.ExecutorSO#iniciarServico(java.lang.String)
	 */
	@Override
	public Execucao iniciarServico(String nomeServico) {
		return executar("net start "+nomeServico);
	}

	/** 
	 * @see com.compsis.configurador.executores.ExecutorSO#pararServico(java.lang.String)
	 */
	@Override
	public Execucao pararServico(String nomeServico) {
		return executar("net stop "+nomeServico);
	}

	/** 
	 * @see com.compsis.configurador.executores.ExecutorSO#sistemaOperacional()
	 */
	@Override
	public String sistemaOperacional() {
		return "windows";
	}
}
