
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.IOException;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.google.common.io.Files;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Efetua backup da versão da aplicação atual (será criado uma cópia na pasta backup)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class FazerBackupVersaoAtual implements Atividade {
	private Configuracao configuracao;
	
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Iniciando backup das versões atuais");
		File[] arquivosEncontrados = configuracao.arquivosDoSistema();
		
		for (File arquivoParaBackup : arquivosEncontrados) {
			File arquivoBkp = new File(configuracao.getConfigurador().getPastaBackup(), arquivoParaBackup.getName());
			execucao.info("Realizando backup de "+arquivoParaBackup.getAbsolutePath()+" para "+arquivoBkp.getAbsolutePath());
			try {
				Files.copy(arquivoParaBackup, arquivoBkp);
			} catch (IOException e) {
				execucao.logger().error(e.getMessage());
				execucao.logger().debug(e.getMessage() , e);
			}
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
