
package com.compsis.configurador.atividades;

import java.util.ArrayList;
import java.util.List;

import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.compsis.configurador.util.Versoes;
import com.compsis.configurador.util.Versoes.VersaoComparator;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade para definir a lista de scripts que precisam ser executados para atualização
 * de versão
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 18/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class PrepararScriptsAtualizacao implements Atividade {

	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		List<String[]> scriptsDisponiveis = execucao.obterDaSessao(Constantes.VERSOES_SCRIPTS_ATUALIZAO_DISPONIVEIS.toString());
		List<String[]> scriptsParaSeremExecutados = new ArrayList<String[]>();
		AtualizacaoVersao atualizacaoVersao = execucao.obterDaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString());
		String[] versaoSelecionada = Versoes.obterVersaoAPartirDoNome(atualizacaoVersao.getVersaoSelecionada());
		VersaoComparator versaoComparator = new VersaoComparator();
		for (String[] versao : scriptsDisponiveis) {
			if(versaoComparator.compare(versao, versaoSelecionada) <= 0) {
				scriptsParaSeremExecutados.add(versao);
			}
		}
		execucao.adicionarNaSessao(Constantes.VERSOES_SCRIPTS_PARA_EXECUCAO.toString(), scriptsParaSeremExecutados);
	}

}
