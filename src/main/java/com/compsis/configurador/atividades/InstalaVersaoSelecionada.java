
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.AcaoExecucao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.google.common.io.Files;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Instala a versão selecionada na aplicação
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class InstalaVersaoSelecionada implements Atividade {
	private Configuracao configuracao;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Instalando versões selecionada");
		AtualizacaoVersao atualizacaoVersao = execucao.obterDaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString());
		
		Map<String, File> sistemasPorVersao = execucao.obterDaSessao(Constantes.MAPA_SISTEMA_ATUALIZACOES_DISPONIVEIS.toString());
		File arquivoParaSerInstalado = sistemasPorVersao.get(atualizacaoVersao.getVersaoSelecionada());
		if(arquivoParaSerInstalado == null) {
			execucao.logger().error("Instalação da versão "+atualizacaoVersao.getVersaoSelecionada()+" não foi realizada. Arquivo não encontrado para a versão.");
			execucao.proximaAcaoExecucao(AcaoExecucao.DESFAZER_EXECUCAO);
		} else {
			File destino = new File(configuracao.getConfigurador().getPastaSistema(), arquivoParaSerInstalado.getName());
			execucao.info("Instalando versão "+atualizacaoVersao.getVersaoSelecionada()+". Arquivo original "
					+arquivoParaSerInstalado.getAbsolutePath()+" Arquivo destino "+destino.getAbsolutePath());
			try {
				Files.copy(arquivoParaSerInstalado, destino);
			} catch (IOException e) {
				execucao.proximaAcaoExecucao(AcaoExecucao.DESFAZER_EXECUCAO);
				throw new RuntimeException(e);
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
