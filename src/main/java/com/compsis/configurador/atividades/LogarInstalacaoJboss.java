
package com.compsis.configurador.atividades;

import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Atividade para logar {@link InstalacaoJboss}
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class LogarInstalacaoJboss implements Atividade {

	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		InstalacaoJboss instalacao = execucao.obterDaSessao(Constantes.INSTALACAO_JBOSS_INTERACAO_USUARIO.toString());
		execucao.info(instalacao.toString());
	}

}
