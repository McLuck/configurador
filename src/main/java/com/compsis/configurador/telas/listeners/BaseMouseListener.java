
package com.compsis.configurador.telas.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JEditorPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.executores.Executor;
import com.compsis.configurador.telas.TelaResultados;
import com.google.common.base.Strings;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Evento {@link MouseListener} base
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class BaseMouseListener implements MouseListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(Execucao.class);
	private JEditorPane observacoes;
	private String tarefa;
	private Executor executorTarefas;
	private String help;
	
	/**
	 * Valor de tarefa atribuído a tarefa
	 *
	 * @param tarefa Atributo da Classe
	 */
	public BaseMouseListener setTarefa(String tarefa) {
		this.tarefa = tarefa;
		return this;
	}
	
	/**
	 * Valor de executorTarefas atribuído a executorTarefas
	 *
	 * @param executorTarefas Atributo da Classe
	 * @return this
	 */
	public BaseMouseListener setExecutorTarefas(Executor executorTarefas) {
		this.executorTarefas = executorTarefas;
		return this;
	}
	
	/**
	 * Valor de observacoes atribuído a observacoes
	 * <br>
	 * Sumario. Local onde observacoes do serão exibidas para o usuário. <br>
	 * Será utilizado pelo {@link #help}
	 * @see #setHelp(String)
	 * @param observacoes Atributo da Classe
	 * @return this
	 */
	public BaseMouseListener setObservacoes(JEditorPane observacoes) {
		this.observacoes = observacoes;
		return this;
	}
	
	/**
	 * Valor de help atribuído a help
	 * <br>
	 * Descrição ou help que será realizado o {@link JEditorPane#setText(String)}
	 * no sumario de observacoes disponivel neste listener
	 * @see #setObservacoes(JEditorPane) 
	 * @param help Atributo da Classe
	 * @return this
	 */
	public BaseMouseListener setHelp(String help) {
		this.help = help;
		return this;
	}
	
	/** 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!Strings.isNullOrEmpty(tarefa)) {
			StringBuffer buffer = new StringBuffer();
			try {
				Execucao execucao = executorTarefas.executar(tarefa) ;
				if(execucao==null) {
					buffer.append("Execução não realizada. Tarefa ").append(tarefa).append(" não encontrada. Talvez não tenha sido definida ou o nome da tarefa esteja incorreto. \n");
				} else if(!execucao.getMensagens().isEmpty()) {
					buffer .append("Execução da tarefa ").append(tarefa).append(" concluida! \n");
					for (String mensagem : execucao.getMensagens()) {
						buffer.append(mensagem).append("\n");
					}
				}
			} catch(Exception ex) {
				LOGGER.debug(ex.getMessage(), ex);
				LOGGER.error(ex.getMessage());
				StringWriter outputWriter = new StringWriter();
				ex.printStackTrace(new PrintWriter(outputWriter));
				buffer.append(outputWriter.getBuffer().toString());
			}
			buffer.append("\n\n\nPara maiores detalhes, acompanhe o log. Se optar por debugar o processo, coloque ")
			.append(Executor.class.getName()).append(" nas descrições do log em nível de DEBUG.");
			new TelaResultados(buffer.toString());
		}
		limparTextoObservacao();
	}
	
	private void limparTextoObservacao() {
		if(observacoes!=null) {
			observacoes.setText("");			
		}
	}

	/** 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/** 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/** 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if(observacoes!=null && !Strings.isNullOrEmpty(help)) {
			observacoes.setText(help);
		}
	}

	/** 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
