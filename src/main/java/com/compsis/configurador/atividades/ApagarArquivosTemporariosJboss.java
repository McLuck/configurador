
package com.compsis.configurador.atividades;

import java.io.File;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.compsis.configurador.util.FilesUtil;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade para apagar arquivos temporários do jboss
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ApagarArquivosTemporariosJboss implements Atividade {
	private Configuracao configuracao;
	
	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		execucao.info("Apagando arquivos temporários do sistema "+configuracao.getConfigurador().getSistema());
		File root = configuracao.getConfigurador().getPastaSistema().getParentFile().getParentFile();
		AtualizacaoVersao atualizacaoVersao = execucao.obterDaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString());
		if(atualizacaoVersao.isApagarPastaData()) {
			FilesUtil.apagarDiretorio(new File(root, "data"), execucao.logger());
		}
		if(atualizacaoVersao.isApagarPastaLog()) {
			FilesUtil.apagarDiretorio(new File(root, "log"), execucao.logger());
		}
		if(atualizacaoVersao.isApagarPastaTemp()) {
			FilesUtil.apagarDiretorio(new File(root, "tmp"), execucao.logger());
		}
		if(atualizacaoVersao.isApagarPastaWork()) {
			FilesUtil.apagarDiretorio(new File(root, "work"), execucao.logger());
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
