/*
 * COMPSIS – Computadores e Sistemas Ind. e Com. LTDA<br>
 * Produto ${product_name} - ${product_description}<br>
 *
 * Data de Criação: 18/10/2015<br>
 * <br>
 * Todos os direitos reservados.
 */

package com.compsis.configurador.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.AcaoExecucao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.AtualizacaoVersao;
import com.compsis.configurador.telas.listeners.HelpActionListenerFactory;
import com.compsis.configurador.telas.listeners.HelpActionListenerFactory.Ajuda;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 18/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings("serial")
public class TelaUpdateVersao extends JDialog implements Atividade {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUpdateVersao dialog = new TelaUpdateVersao();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Execucao execucao;
	private AtualizacaoVersao atualizacaoVersao;
	private HelpActionListenerFactory helpActionListenerFactory;
	private JLabel lblVersesDisponveisPara;
	private JComboBox cbVeroesDisponiveis;
	private JButton btHelpVersoesDisponiveis;
	private JCheckBox chckBxBkpVersaoAnterior;
	private JCheckBox chckbxApagarTmp;
	private JCheckBox chckbxApagarWork;
	private JCheckBox chckbxApagarPastaLogs;
	private JCheckBox chckbxRenomearLogs;
	private JButton btHelpRenomearLogs;
	private JButton btHelpBkpVersaoAnterior;
	private JButton btHelpApagarTmp;
	private JButton btHelpApagarWork;
	private JButton btHelpApagarPastalogs;
	private JButton btCancelarAtualizacao;
	private JButton btProsseguirAtualizacao; 
	private JCheckBox chckbxApagarPastaData;
	private JButton btHelpApagarPastaMensagens;
	
	private void suspenderExecucao() {
		execucao.proximaAcaoExecucao(AcaoExecucao.PARAR_EXECUCAO);
		execucao.adicionarMensagem("Atualização de versão", "Atualização de versão foi abortada pelo usuário");
	}
	
	@PostConstruct
	public void init() {
		
		btHelpVersoesDisponiveis.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.VERSOES_DISPONIVEIS_PARA_UPDATE));
		
		btHelpRenomearLogs.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.RENOMEAR_LOGS_STDOUT));
		
		btHelpBkpVersaoAnterior.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.BACKUP_VERSAO_ANTERIOR));
	
		btHelpApagarTmp.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.APAGAR_PASTA_TEMPORARIA));
		
		btHelpApagarWork.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.APAGAR_PASTA_WORK));
		
		btHelpApagarPastalogs.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.APAGAR_PASTA_LOGS));
		
		btHelpApagarPastaMensagens.addActionListener(helpActionListenerFactory.createActionListener(Ajuda.APAGAR_PASTA_DATA));
	}
	
	
	
	/**
	 * Create the dialog.
	 */
	public TelaUpdateVersao() {
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Configuração para atualização da versão");
		setBounds(100, 100, 326, 323);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent winEvent) {
				suspenderExecucao();
			}
		});
		
		
		lblVersesDisponveisPara = new JLabel("Versões disponíveis para atualização:");
		lblVersesDisponveisPara.setBounds(10, 11, 232, 14);
		getContentPane().add(lblVersesDisponveisPara);
		
		cbVeroesDisponiveis = new JComboBox();
		cbVeroesDisponiveis.setBounds(10, 26, 232, 20);
		getContentPane().add(cbVeroesDisponiveis);
		
		btHelpVersoesDisponiveis = new JButton("?");
		btHelpVersoesDisponiveis.setBounds(252, 25, 53, 23);
		getContentPane().add(btHelpVersoesDisponiveis);
		
		chckBxBkpVersaoAnterior = new JCheckBox("Fazer backup da versão anterior");
		chckBxBkpVersaoAnterior.setBounds(10, 79, 232, 23);
		getContentPane().add(chckBxBkpVersaoAnterior);
		
		chckbxApagarTmp = new JCheckBox("Apagar pasta de arquivos temporários");
		chckbxApagarTmp.setBounds(10, 105, 232, 23);
		getContentPane().add(chckbxApagarTmp);
		
		chckbxApagarWork = new JCheckBox("Apagar pasta de trabalho do JBoss");
		chckbxApagarWork.setBounds(10, 131, 232, 23);
		getContentPane().add(chckbxApagarWork);
		
		chckbxApagarPastaLogs = new JCheckBox("Apagar pasta de logs");
		chckbxApagarPastaLogs.setBounds(10, 157, 232, 23);
		getContentPane().add(chckbxApagarPastaLogs);
		
		chckbxRenomearLogs = new JCheckBox("Renomear logs");
		chckbxRenomearLogs.setBounds(10, 53, 232, 23);
		getContentPane().add(chckbxRenomearLogs);
		
		btHelpRenomearLogs = new JButton("?");
		btHelpRenomearLogs.setBounds(252, 53, 53, 23);
		getContentPane().add(btHelpRenomearLogs);
		
		btHelpBkpVersaoAnterior = new JButton("?");
		btHelpBkpVersaoAnterior.setBounds(252, 79, 53, 23);
		getContentPane().add(btHelpBkpVersaoAnterior);
		
		btHelpApagarTmp = new JButton("?");
		btHelpApagarTmp.setBounds(252, 105, 53, 23);
		getContentPane().add(btHelpApagarTmp);
		
		btHelpApagarWork = new JButton("?");
		btHelpApagarWork.setBounds(252, 131, 53, 23);
		getContentPane().add(btHelpApagarWork);
		
		btHelpApagarPastalogs = new JButton("?");
		btHelpApagarPastalogs.setBounds(252, 157, 53, 23);
		getContentPane().add(btHelpApagarPastalogs);
		
		btCancelarAtualizacao = new JButton("Cancelar Instalação");
		btCancelarAtualizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suspenderExecucao();
				dispose();
			}
		});
		btCancelarAtualizacao.setBounds(10, 238, 144, 23);
		getContentPane().add(btCancelarAtualizacao);
		
		btProsseguirAtualizacao = new JButton("Prosseguir Instalação");
		btProsseguirAtualizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarAtualizacaoVersao();
				TelaUpdateVersao.this.dispose();
				execucao.logger().info("Dados do usuário: " + atualizacaoVersao.toString());
			}
		});
		btProsseguirAtualizacao.setBounds(161, 238, 144, 23);
		getContentPane().add(btProsseguirAtualizacao);
		
		chckbxApagarPastaData = new JCheckBox("Apagar pasta de mensagens");
		chckbxApagarPastaData.setBounds(10, 185, 232, 23);
		getContentPane().add(chckbxApagarPastaData);
		
		btHelpApagarPastaMensagens = new JButton("?");
		btHelpApagarPastaMensagens.setBounds(252, 185, 53, 23);
		getContentPane().add(btHelpApagarPastaMensagens);
	}

	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		this.execucao = execucao;
		atualizacaoVersao = execucao.obterDaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString());
		if(atualizacaoVersao == null) {
			atualizacaoVersao = new AtualizacaoVersao();
			atualizacaoVersao.setApagarPastaTemp(true);
			atualizacaoVersao.setApagarPastaWork(true);
			atualizacaoVersao.setRenomearLogs(true);
			execucao.adicionarNaSessao(Constantes.ATUALIZACAO_VERSAO_INTERACAO_USUARIO.toString(), atualizacaoVersao);
		}
		List<String[]> listaDeVersoes = execucao.obterDaSessao(Constantes.VERSOES_SISTEMA_ATUALIZACOES_DISPONIVEIS.toString());
		if(listaDeVersoes!=null && !listaDeVersoes.isEmpty()) {
			alterarValoresComponentes();
			setVisible(true);			
		} else {
			execucao.proximaAcaoExecucao(AcaoExecucao.PARAR_EXECUCAO);
			execucao.error("Nenhuma versão disponível para atualização!");
		}
	}
	
	private void alterarValoresComponentes() {
		List<String[]> listaDeVersoes = execucao.obterDaSessao(Constantes.VERSOES_SISTEMA_ATUALIZACOES_DISPONIVEIS.toString());
		Vector<String> labelsDeVersoes = new Vector<String>();
		for (String[] versao : listaDeVersoes) {
			labelsDeVersoes.add(Arrays.toString(versao));
		}
		this.cbVeroesDisponiveis.setModel(new DefaultComboBoxModel(labelsDeVersoes));
		this.cbVeroesDisponiveis.setSelectedItem(atualizacaoVersao.getVersaoSelecionada());
		this.chckbxApagarPastaLogs.setSelected(atualizacaoVersao.isApagarPastaLog());
		this.chckbxApagarTmp.setSelected(atualizacaoVersao.isApagarPastaTemp());
		this.chckbxApagarWork.setSelected(atualizacaoVersao.isApagarPastaWork());
		this.chckbxRenomearLogs.setSelected(atualizacaoVersao.isRenomearLogs());
		this.chckBxBkpVersaoAnterior.setSelected(atualizacaoVersao.isRealizarBackup());
		this.chckbxApagarPastaData.setSelected(atualizacaoVersao.isApagarPastaData());
	}
	
	private void alterarAtualizacaoVersao() {
		atualizacaoVersao.setVersaoSelecionada(String.valueOf(this.cbVeroesDisponiveis.getSelectedItem()));
		atualizacaoVersao.setApagarPastaData(this.chckbxApagarPastaData.isSelected());
		atualizacaoVersao.setApagarPastaLog(this.chckbxApagarPastaLogs.isSelected());
		atualizacaoVersao.setApagarPastaTemp(this.chckbxApagarTmp.isSelected());
		atualizacaoVersao.setApagarPastaWork(this.chckbxApagarWork.isSelected());
		atualizacaoVersao.setRealizarBackup(this.chckBxBkpVersaoAnterior.isSelected());
		atualizacaoVersao.setRenomearLogs(this.chckbxRenomearLogs.isSelected());
	}
	
	/**
	 * Valor de helpActionListenerFactory atribuído a helpActionListenerFactory
	 *
	 * @param helpActionListenerFactory Atributo da Classe
	 */
	public void setHelpActionListenerFactory(
			HelpActionListenerFactory helpActionListenerFactory) {
		this.helpActionListenerFactory = helpActionListenerFactory;
	}
	
	
}
