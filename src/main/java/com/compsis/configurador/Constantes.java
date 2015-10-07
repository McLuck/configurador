
package com.compsis.configurador;

import java.util.ArrayList;
import java.util.HashMap;

import com.compsis.configurador.dominio.ControleDelta;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss;
import com.compsis.configurador.dominio.usuario.InteracaoUsuario;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Constantes utilizadas como chave nas sessoes de tarefas <br>
 * Utilizar o {@link #toString()} do enum para garantir unicidade
 * pelo próprio compilador (não é possível repetir enum)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public enum Constantes {
	/**
	 * Referencia instancia de {@link ControleDelta} com as informações
	 * do último delta executado no banco
	 */
	ULTIMO_DELTA_EXECUTADO, 
	/**
	 * Referencia instancia de {@link HashMap} com chave String 
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor é o arquivo .SQL correspondente. <br>
	 * Este mapa deverá conter somente scripts disponíveis para atualizar,
	 * ou seja, scripts com versões mais novas da última versão
	 * @see #ULTIMO_DELTA_EXECUTADO
	 */
	MAPA_SCRIPTS_ATUALIZACOES_DISPONIVEIS, 
	/**
	 * Referencia instancia de {@link HashMap} com chave String 
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor é o arquivo .SQL correspondente. <br>
	 * Este mapa deverá conter somente scripts disponíveis para realizar rollback,
	 * ou seja, scripts com versões anteriores da última versão
	 * @see #ULTIMO_DELTA_EXECUTADO
	 */
	MAPA_SCRIPTS_ROLLBACK_DISPONIVEIS, 
	/**
	 * Referencia instancia de {@link HashMap} com chave String
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor é o arquivo .SQL correspondente. <br>
	 * Este mapa deverá conter somente scripts que irão ser executados, seja de atualização
	 * ou rollback
	 */
	MAPA_SCRIPTS_PARA_EXECUCAO,
	/**
	 * Referencia instancia de {@link HashMap} com chave {@link String}
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor do mapa é o arquivo de versão do sistema (Ex: WAR, EAR, EXE).
	 * Somente para arquivos disponiveis para atualização (versão maior que a atual)
	 */
	MAPA_SISTEMA_ATUALIZACOES_DISPONIVEIS,
	/**
	 * Referencia instancia de {@link HashMap} com chave {@link String}
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor do mapa é o arquivo de versão do sistema (Ex: WAR, EAR, EXE).
	 * Somente para arquivos disponíveis para rollback (versão menor que a atual)
	 */
	MAPA_SISTEMA_ROLLBACK_DISPONIVEIS,
	/**
	 * Referencia instancia de {@link HashMap} com chave {@link String}
	 * (representando o vetor de {@link String} da versão correspondente)
	 * e o valor do mapa é o arquivo de versão do sistema (Ex: WAR, EAR, EXE).
	 * Este mapa contém os arquivos para instalação do sistem
	 */
	MAPA_SISTEMA_PARA_INSTALAR,
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * contendo as versões disponíveis para realizar o rollback da aplicação,
	 * ou seja, somente versão atual e anteriores da versão do último delta
	 * @see #ULTIMO_DELTA_EXECUTADO
	 */
	VERSOES_SCRIPTS_ROLLBACK_DISPONIVEIS, 
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * contendo as versões disponíveis para realizar a atualização da aplicação,
	 * ou seja, somente versão posteriores da versão do último delta
	 * @see #ULTIMO_DELTA_EXECUTADO
	 */
	VERSOES_SCRIPTS_ATUALIZAO_DISPONIVEIS,
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * contendo as versões que serão executadas no banco de dados; 
	 * As versões aqui deverão obter o arquivo de script no {@link #MAPA_SCRIPTS_PARA_EXECUCAO}
	 */
	VERSOES_SCRIPTS_PARA_EXECUCAO,
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * condendo as versões de sistema (WAR, EAR, EXE, etc) disponíveis para atualização
	 */
	VERSOES_SISTEMA_ATUALIZACOES_DISPONIVEIS, 
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * condendo as versões de sistema (WAR, EAR, EXE, etc) disponíveis para rollback
	 */
	VERSOES_SISTEMA_ROLLBACK_DISPONIVEIS, 
	/**
	 * Referencia instancia de {@link ArrayList} com vetor de {@link String}
	 * condendo as versões de sistema (WAR, EAR, EXE, etc) selecionadas para realizar
	 * instalação
	 */
	VERSOES_PARA_INSTALAR, 
	/**
	 * Referencia instancia de {@link AtualizacaoVersao}, subclasse de {@link InteracaoUsuario}
	 * utilizada para coletar dados do usuario nas interfaces (TO's) 
	 */
	ATUALIZACAO_VERSAO_INTERACAO_USUARIO,
	/**
	 * Referencia instancia de {@link InstalacaoJboss}, subclasse de {@link InteracaoUsuario}
	 * utilizada para coletar dados do usuario nas interfaces (TO's)
	 */
	INSTALACAO_JBOSS_INTERACAO_USUARIO, 
	/**
	 * Referencia {@link String} representando nome do arquivo zip contendo o conteúdo
	 * da infra jboss <br>
	 */
	INFRA_JBOSS
}
