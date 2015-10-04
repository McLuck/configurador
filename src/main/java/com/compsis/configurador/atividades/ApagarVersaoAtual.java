
package com.compsis.configurador.atividades;

import java.io.File;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Apaga arquivo da versão da aplicação atual
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ApagarVersaoAtual implements Atividade {
	private Configuracao configuracao;
	
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.logger().info("Apagando arquivos das versões atuais");
		File[] arquivosEncontrados = configuracao.arquivosDoSistema();
		
		for (File arquivoEncontrado : arquivosEncontrados) {
			execucao.logger().info("Apagando arquivo "+arquivoEncontrado.getAbsolutePath());
			arquivoEncontrado.delete();
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
