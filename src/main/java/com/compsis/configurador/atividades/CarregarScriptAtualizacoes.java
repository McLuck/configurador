
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.util.Versoes;
import com.compsis.configurador.util.Versoes.VersaoComparator;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Carrega scripts disponiveis na pasta de atualizações <br>
 * Irá manter na lista de atualizações somente os que forem mais recente 
 * ao ultimo delta executado no banco de dados {@link Configuracao#getUltimoDeltaExecutado()} 
 * referenciado por {@link Constantes#ULTIMO_DELTA_EXECUTADO} e a lista referenciada por
 * {@link Constantes#MAPA_SCRIPTS_ATUALIZACOES_DISPONIVEIS}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class CarregarScriptAtualizacoes implements Atividade {
	private Configuracao configuracao;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		File[] scriptsDisponiveis = configuracao.getConfigurador().getPastaScriptsAtualizacao().listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.trim().toLowerCase().endsWith(".sql")
						&& Pattern.compile("(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)").matcher(name).matches();
			}
		});
		execucao.info("Encontrado "+scriptsDisponiveis.length+" scripts para atualização disponiveis");
		List<File> scripts = Arrays.asList(scriptsDisponiveis);
		Map<String, File> scriptPorVersao = Versoes.montarMapaDeArquivosPorVersao(scripts);
		List<String[]> versoesDisponiveis = Versoes.montarListaDeVersoes(scripts);
		String[] versaoAtual = Versoes.obterVersaoAPartirDoNome(configuracao.getUltimoDeltaExecutado().getNomeArquivo());
		
		Iterator<String[]> listIterator = versoesDisponiveis.iterator();
		while (listIterator.hasNext()) {
			String[] versao = (String[]) listIterator.next();
			if( new VersaoComparator().compare(versaoAtual, versao) <= 0 ) {
				execucao.info("Removendo script anterior ou igual a versão atual. Versão atual: "
								+Arrays.toString(versaoAtual)+". Removendo script para versão "+Arrays.toString(versao));
				scriptPorVersao.remove(Arrays.toString(versao));
				listIterator.remove();
			}			
		}
		execucao.adicionarNaSessao(Constantes.MAPA_SCRIPTS_ATUALIZACOES_DISPONIVEIS.toString(), scriptPorVersao);
		execucao.adicionarNaSessao(Constantes.VERSOES_SCRIPTS_ATUALIZAO_DISPONIVEIS.toString(), versoesDisponiveis);
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
