
package com.compsis.configurador;

import java.util.ArrayList;
import java.util.HashMap;

import com.compsis.configurador.dominio.ControleDelta;

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
	VERSOES_SCRIPTS_ATUALIZAO_DISPONIVEIS
}
