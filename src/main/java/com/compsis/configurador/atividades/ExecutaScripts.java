
package com.compsis.configurador.atividades;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.compsis.configurador.Constantes;
import com.compsis.configurador.dao.ExecutorQueryGenerico;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Executa os scripts definidos em {@link Constantes#VERSOES_SCRIPTS_PARA_EXECUCAO}
 * disponíveis nos arquivos do mapa {@link Constantes#MAPA_SCRIPTS_ATUALIZACOES_DISPONIVEIS}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ExecutaScripts implements Atividade {
	private ExecutorQueryGenerico executorQueryGenerico;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Iniciando execução de scripts");
		List<String[]> versoesDisponiveis = execucao.obterDaSessao(Constantes.VERSOES_SCRIPTS_PARA_EXECUCAO.toString());
		Map<String, File> scriptPorVersao = execucao.obterDaSessao(Constantes.MAPA_SCRIPTS_ATUALIZACOES_DISPONIVEIS.toString());
		for (String[] versao : versoesDisponiveis) {
			File arquivoSQL = scriptPorVersao.get(Arrays.toString(versao));
			executorQueryGenerico.executarArquivo(arquivoSQL, execucao.logger());
		}		
	}

	/**
	 * Valor de executorQueryGenerico atribuído a executorQueryGenerico
	 *
	 * @param executorQueryGenerico Atributo da Classe
	 */
	public void setExecutorQueryGenerico(ExecutorQueryGenerico executorQueryGenerico) {
		this.executorQueryGenerico = executorQueryGenerico;
	}
}
