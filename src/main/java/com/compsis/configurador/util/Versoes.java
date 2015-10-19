
package com.compsis.configurador.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Classe utilitária para tratar questões de versionamento
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 04/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public final class Versoes {
	private Versoes(){}
	
	/**
	 * Obtem vetor String contendo a versão informada a partir do nome
	 * <br>
	 * Irá extrair os blocos de versionamento (major version, minor version, patch version, build number)
	 * @param nome Nome da versão (arquivo script ou do sistema). 
	 * @return vetor de {@link String} contendo os blocos de versionamento
	 */
	public static String[] obterVersaoAPartirDoNome(String nome) {
		nome = nome . replace("-", ".").replace("[", "").replace("]", "").replace(",", ".").replace(" ", "");
		String[] versaoSplit = nome.split("\\.");
		List<String> versoes = new ArrayList<String>(Arrays.asList(versaoSplit));
		Iterator<String> listIterator = versoes.iterator();
		while (listIterator.hasNext()) {
			String versao = (String) listIterator.next();
			char[] digitos = versao.toCharArray();
			for (char c : digitos) {
				if(!Character.isDigit(c)){
					listIterator.remove();
					break;
				}
			}
		}
		return versoes.toArray(new String[]{});
	}
	
	/**
	 * Irá ordernar a lista de versões de forma crescente
	 * @param versoes
	 */
	public static void ordernarVersoes(List<String[]> versoes) {
		Collections.sort(versoes, new VersaoComparator());
	}
	
	/**
	 * Monta mapa dos arquivos informados <br>
	 * A chave do mapa é a versão do arquivo
	 * @param arquivos
	 * @return
	 */
	public static Map<String, File> montarMapaDeArquivosPorVersao(final List<File> arquivos) {
		Map<String, File> mapaArquivos = new HashMap<String, File>();
		for (File arquivo : arquivos) {
			String[] versaoArquivo = obterVersaoAPartirDoNome(arquivo.getName());
			mapaArquivos.put(Arrays.toString(versaoArquivo), arquivo);
		}
		return mapaArquivos;
	}
	
	/**
	 * Monta lista de vetores de strings com as versões disponíveis a partir
	 * da lista de arquivo informada.
	 * <br>
	 * Retornará a lista ordenada de forma crescente
	 * @param arquivos
	 * @return
	 */
	public static List<String[]> montarListaDeVersoes(final List<File> arquivos) {
		List<String[]> versoes = new ArrayList<String[]>();
		for (File arquivo : arquivos) {
			String[] versaoArquivo = obterVersaoAPartirDoNome(arquivo.getName());
			versoes.add(versaoArquivo);
		}
		ordernarVersoes(versoes);
		return versoes;
	}
	
	/**
	 * 
	 * DOCUMENTAÇÃO DA CLASSE <br>
	 * ---------------------- <br>
	 * FINALIDADE: <br>
	 * {@link Comparator} para blocos de versão gerados em {@link Versoes#obterVersaoAPartirDoNome(String)}
	 * <br>
	 * HISTÓRICO DE DESENVOLVIMENTO: <br>
	 * 04/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
	 *<br>
	 *<br>
	 * LISTA DE CLASSES INTERNAS: <br>
	 */
	public static class VersaoComparator implements Comparator<String[]> {
		public int compare(String[] o1, String[] o2) {
			int menorTamanho = o1.length;
			if(o2.length < menorTamanho) {
				menorTamanho = o2.length;
			}
			
			for (int i = 0; i < menorTamanho; i++) {
				Long versao1 = Long.valueOf(o1[i]);
				Long versao2 = Long.valueOf(o2[i]);
				
				int comparacaoPorBloco = versao1.compareTo(versao2);
				if(comparacaoPorBloco!=0) {
					return comparacaoPorBloco;
				}
			}
			if(o1.length > o2.length) 
				return 1;
			if(o2.length < o1.length)
				return -1;
			
			return 0;
		}
	}
}
