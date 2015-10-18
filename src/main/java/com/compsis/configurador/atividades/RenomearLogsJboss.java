
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.google.common.base.Strings;
import com.google.common.io.Files;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Renomeia os logs do jboss
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class RenomearLogsJboss implements Atividade {
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		AtualizacaoVersao atualizacaoVersao = execucao.obterDaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString());
		String stdoutPath = System.getenv("STDOUT");
		if(atualizacaoVersao.isRenomearLogs()) {
			if(Strings.isNullOrEmpty(stdoutPath)) {
				execucao.warn("Variável de ambiente STDOUT não definida. Favor definir variavel de ambiente referenciando a pasta do STDOUT do sistema. Não será possível renomear logs.");
			} else {
				File rootLog = new File(stdoutPath);
				File pastasRenomeados = new File(rootLog, "logs_renomeados");
				if(!pastasRenomeados.exists()) {
					pastasRenomeados.mkdirs();
				}
				File[] logs = rootLog.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".log");
					}
				});
				String prefixo = String.valueOf(System.currentTimeMillis()).substring(7) + "_";
				for (File log : logs) {
					try {
						Files.move(log, new File(pastasRenomeados, prefixo + log.getName()));
					} catch (IOException e) {
						execucao.logger().error(e.getMessage());
						execucao.logger().debug(e.getMessage(), e);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
