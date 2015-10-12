
package com.compsis.configurador.atividades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss6SA;
import com.compsis.configurador.util.FilesUtil;
import com.google.common.io.Files;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade responsável por adicionar o driver apropriado e criar o XML de configuração
 * do DataSource para conexões do container Jboss6 com o Banco de Dados
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ConfigurarBDJbossSA6 implements Atividade {
	private Configuracao configuracao;
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Configurando conexões para o banco de dados apropriado");
		InstalacaoJboss6SA instalacaoJboss = execucao.obterDaSessao(Constantes.INSTALACAO_JBOSS6_SA_INTERACAO_USUARIO.toString());
		String banco = String.valueOf(instalacaoJboss.getNomeBD());
		if(banco.toLowerCase().contains("oracle")) {
			instalarOracle(instalacaoJboss, execucao);
		}else if(banco.toLowerCase().contains("ms sql")) {
			instalarMSSqlServer(instalacaoJboss, execucao);
		}
	}

	/** 
	 * Instala os drivers apropriado do MS SQL Server e os XML's de conexão do DataSource
	 * @param instalacaoJboss
	 * @param execucao 
	 */
	private void instalarMSSqlServer(InstalacaoJboss6SA instalacaoJboss, Execucao execucao) {
		String banco = "mssql";
		String versao = instalacaoJboss.getVersaoBD();
		
		configurarBanco(execucao, banco, versao, instalacaoJboss);
	}

	/** 
	 * TODO Descrição do Método
	 * @param execucao
	 * @param banco
	 * @param versao
	 * @param instalacaoJboss 
	 */
	private void configurarBanco(Execucao execucao, String banco, String versao, InstalacaoJboss6SA instalacaoJboss) {
		File pastaRaiz = configuracao.getConfigurador().getPastaRaizInfra();
		if(pastaRaiz.getName().toLowerCase().endsWith("app")) {
			pastaRaiz = pastaRaiz.getParentFile();
		}
		File pastaDriver = new File(pastaRaiz, "drivers");
		pastaDriver = new File(pastaDriver, banco);
		if(!pastaDriver.exists()) {
			execucao.error("Era esperado um diretório para configurar o Jboss para conexão com o banco de dados. Diretório não encontrado: "+pastaDriver.getAbsolutePath());
		} else {
			File dataSourceXML = null;
			File arquivoEncontrado = FilesUtil.obterArquivoNaPasta(pastaDriver, "-ds.xml");
			if(arquivoEncontrado==null) {
				execucao.error("Era esperado um arquivo terminado com '-ds.xml' para configurar o Jboss para conexão com o banco de dados. Arquivo não encontrado na pasta: "+pastaDriver.getAbsolutePath());				
			} else {
				try {
					dataSourceXML = new File(configuracao.getConfigurador().getPastaSistema(), arquivoEncontrado.getName());
					Files.copy(arquivoEncontrado, dataSourceXML);
				} catch (IOException e) {
					execucao.error(e.getMessage());
					execucao.logger().debug(e.getMessage(), e);
				}
				
				File pastaVersaoDriver = new File(pastaDriver, versao);
				if(!pastaDriver.exists()) {
					execucao.error("Era esperado um diretório contendo driver para configurar o Jboss para conexão com o banco de dados. Diretório não encontrado: "+pastaVersaoDriver.getAbsolutePath());
				} else {
					arquivoEncontrado = FilesUtil.obterArquivoNaPasta(pastaVersaoDriver, ".jar");
					if(arquivoEncontrado==null) {
						execucao.error("Era esperado um arquivo de driver do tipo '.jar' para configurar o Jboss para conexão com o banco de dados. Arquivo não encontrado na pasta: "+pastaVersaoDriver.getAbsolutePath());				
					} else {
						File libJboss = new File(configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile(), "lib");
						try {
							Files.copy(arquivoEncontrado, new File(libJboss, arquivoEncontrado.getName()) );
						} catch (IOException e) {
							execucao.error(e.getMessage());
							execucao.logger().debug(e.getMessage(), e);
						}
						configurarXMLDataSource(dataSourceXML, instalacaoJboss, execucao);
					}
				}
			}
		}
	}
	
	private void configurarXMLDataSource(File dataSource, InstalacaoJboss6SA instalacaoJboss, Execucao execucao) {
		if(dataSource.exists()) {
			File bkpOriginal = new File(dataSource.getParentFile(), dataSource.getName()+".bkp");
			BufferedWriter writer = null;
			BufferedReader reader = null;
			try {
				Files.move(dataSource, bkpOriginal);
				reader = new BufferedReader(new FileReader(bkpOriginal));
				writer = new BufferedWriter(new FileWriter(dataSource));
				String line;
				List<String> metaTags = Arrays.asList(new String[]{"${url}", "${user-name}", "${password}", "${instancia}"});
				while ( (line = reader.readLine()) != null ) {
					for (String metaTag : metaTags) {
						if(line.toLowerCase().contains(metaTag.toLowerCase())) {
							line = line.replace(metaTag, getvalorMetaTag(metaTag, instalacaoJboss));
						}
					}
					writer.write(line);
					writer.newLine();
				}
			} catch (IOException e) {
				execucao.error(e.getMessage());
				execucao.logger().debug(e.getMessage(), e);
			} finally {
				if(reader!=null) {
					try {
						reader.close();
						bkpOriginal.delete();
					} catch (IOException e) {
						execucao.logger().debug(e.getMessage(), e);
					}
				}
				if(writer!=null) {
					try {
						writer .flush();
						writer.close();
					} catch (IOException e) {
						execucao.logger().debug(e.getMessage(), e);
					}
				}
			}
		}
	}

	/** 
	 * Obtem valor apropriado de metaTag
	 * @param metaTag
	 * @param instalacaoJboss
	 * @return
	 */
	private String getvalorMetaTag(String metaTag, InstalacaoJboss6SA instalacaoJboss) {
		if("${url}".equals(metaTag)) {
			return instalacaoJboss.getUrlBD();
		}
		if("${user-name}".equals(metaTag)) {
			return instalacaoJboss.getUsuarioBD();
		}
		if("${password}".equals(metaTag)) {
			return instalacaoJboss.getSenhaBD();
		}
		if("${instancia}".equals(metaTag)) {
			return instalacaoJboss.getInstanciaBD();
		}
		return null;
	}

	/** 
	 * Instala os drivers apropriado do Oracle e os XML's de conexão do DataSource
	 * @param instalacaoJboss
	 * @param execucao 
	 */
	private void instalarOracle(InstalacaoJboss6SA instalacaoJboss, Execucao execucao) {
		String banco = "oracle";
		String versao = instalacaoJboss.getVersaoBD();
		
		configurarBanco(execucao, banco, versao, instalacaoJboss);
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
