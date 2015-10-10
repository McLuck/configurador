
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
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
		List<String[]> versoesDisponiveis = execucao.obterDaSessao(Constantes.VERSOES_PARA_INSTALAR.toString());
		Map<String, File> scriptPorVersao = execucao.obterDaSessao(Constantes.MAPA_SISTEMA_PARA_INSTALAR.toString());
		for (String[] versao : versoesDisponiveis) {
			String labelVersao = Arrays.toString(versao);
			File arquivoVersao = scriptPorVersao.get(labelVersao);
			if(arquivoVersao == null) {
				execucao.logger().error("Instalação da versão "+labelVersao+" não foi realizada. Arquivo não encontrado para a versão.");
			} else {
				File destino = new File(configuracao.getConfigurador().getPastaSistema(), arquivoVersao.getName());
				execucao.info("Instalando versão "+labelVersao+". Arquivo original "
										+arquivoVersao.getAbsolutePath()+" Arquivo destino "+destino.getAbsolutePath());
				try {
					Files.copy(arquivoVersao, destino);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
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
