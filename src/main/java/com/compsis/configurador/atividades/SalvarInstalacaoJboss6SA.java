
package com.compsis.configurador.atividades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss6SA;
import com.thoughtworks.xstream.XStream;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade para logar {@link InstalacaoJboss6SA}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class SalvarInstalacaoJboss6SA implements Atividade {
	private Configuracao configuracao;
	
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		InstalacaoJboss6SA instalacao = execucao.obterDaSessao(Constantes.INSTALACAO_JBOSS6_SA_INTERACAO_USUARIO.toString());
		execucao.info(instalacao.toString());
		File jboss6XML = new File(configuracao.getConfigurador().getPastaRaizConfigurador(), "jboss6.xml");
		XStream xStream = new XStream();
		FileWriter writer = null;
		try {
			writer = new FileWriter(jboss6XML);
			xStream.toXML(instalacao, new FileWriter(jboss6XML));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			execucao.error(e.getMessage());
			execucao.logger().error(e.getMessage(), e);
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
