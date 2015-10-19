
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * Carrega versões do sistema (arquivos EAR, WAR, EXE, etc) disponíveis para atualização
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class CarregarVersoesAtualizacao implements Atividade{
	private Configuracao configuracao;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Carregando versões de atualizações disponíveis do diretório "+configuracao.getConfigurador().getPastaAtualizacaoVersao().getAbsolutePath());
		final String tiposSistema = configuracao.getConfigurador().getTipoArquivoSistema();
		File[] arquivosSistema = configuracao.getConfigurador().getPastaAtualizacaoVersao().listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String[] tipos = tiposSistema.trim().toLowerCase().split(",");
				for (String tipo : tipos) {
					if(name.toLowerCase().endsWith("."+tipo)) {
						return true;
					}
				}
				return false;
			}
		});
		List<File> arquivosEncontrados = new ArrayList<File>(Arrays.asList(arquivosSistema));
		Map<String, File> arquivosPorVersao = Versoes.montarMapaDeArquivosPorVersao(arquivosEncontrados);
		List<String[]> listaDeVersoes = Versoes.montarListaDeVersoes(arquivosEncontrados);
		
		String[] versaoAtual = Versoes.obterVersaoAPartirDoNome(configuracao.getUltimoDeltaExecutado().getNomeArquivo());
		
		Iterator<String[]> listIterator = listaDeVersoes.iterator();
		while (listIterator.hasNext()) {
			String[] versao = (String[]) listIterator.next();
			if( new VersaoComparator().compare(versaoAtual, versao) >= 0 ) {
				execucao.info("Removendo arquivo anterior ou igual a versão atual. Versão atual: "
								+Arrays.toString(versaoAtual)+". Removendo arquivo da versão "+Arrays.toString(versao));
				arquivosPorVersao.remove(Arrays.toString(versao));
				listIterator.remove();
			}			
		}
		execucao.info("Arquivos carregados: "+arquivosPorVersao);
		execucao.adicionarNaSessao(Constantes.MAPA_SISTEMA_ATUALIZACOES_DISPONIVEIS.toString(), arquivosPorVersao);
		execucao.adicionarNaSessao(Constantes.VERSOES_SISTEMA_ATUALIZACOES_DISPONIVEIS.toString(), listaDeVersoes);
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
