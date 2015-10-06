
package com.compsis.configurador.util;

import java.io.File;

import org.slf4j.Logger;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Utilitário de arquivos
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public final class FilesUtil {
	private FilesUtil(){}
	
	/**
	 * Apagar diretório solicitado recursivamente <br>
	 * Irá apagar todos os arquivos e diretorios internos do diretório
	 * solicitado
	 * @param arquivo
	 * @param logger
	 */
	public static void apagarDiretorio(final File arquivo, final Logger logger) {
		if(arquivo!=null && arquivo.exists()) {
			if(arquivo.isFile()) {
				if(logger.isDebugEnabled()) {
					logger.debug("Apagando arquivo "+arquivo.getAbsolutePath());
				}
			} else {
				for (File oArquivo : arquivo.listFiles()) {
					apagarDiretorio(oArquivo, logger); 
				}
			}			
		}
	}
}
