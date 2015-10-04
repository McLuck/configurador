
package com.compsis.configurador.atividades;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Carrega scripts disponiveis na pasta de atualizações <br>
 * Irá manter na lista de atualizações somente os que forem mais recente 
 * ao ultimo delta executado no banco de dados {@link Configuracao#getUltimoDeltaExecutado()} 
 * referenciado por {@link Constantes#ULTIMO_DELTA_EXECUTADO} e a lista referenciada por
 * {@link Constantes#SCRIPTS_ATUALIZACOES_DISPONIVEIS}
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
