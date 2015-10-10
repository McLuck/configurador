
package com.compsis.configurador.telas;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.compsis.configurador.Configuracao;
import com.compsis.configurador.Constantes;
import com.compsis.configurador.dominio.AcaoExecucao;
import com.compsis.configurador.dominio.Atividade;
import com.compsis.configurador.dominio.Execucao;
import com.compsis.configurador.dominio.usuario.InstalacaoJboss;
import com.google.common.base.Strings;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class TelaInstalacaoInfraJboss implements Atividade {
	
	private JDialog frame;
	private InstalacaoJboss instalacaoJboss;
	private Configuracao configuracao;
	private JTextField txtServico;
	private JTextField txtDiretorioInstalacao;
	private JCheckBox chkBkpInfraAtual;
	private JCheckBox chSubstituirInfraAtual;
	private Execucao execucao;
	private JTextField txtHostBD;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JTextArea taURL;
	private JComboBox cbBanco;
	private JTextField txtInstancia;
	private JLabel lblInstanciaservicoDoBanco;
	private JComboBox cbVersaoBD;
	private JLabel lblVerso;
	
	/**
	 * Launch the application.
	 * SOMENTE PARA TESTE
	 */
	public static void main(String[] args) {
		TelaInstalacaoInfraJboss window = new TelaInstalacaoInfraJboss();
		window.exibir();
	}
	
	public void exibir() {
		frame.setVisible(true);
	}
	

	/**
	 * Create the application.
	 */
	public TelaInstalacaoInfraJboss() {
		initialize();
	}

	private void suspenderExecucao() {
		execucao.proximaAcaoExecucao(AcaoExecucao.PARAR_EXECUCAO);
		execucao.adicionarMensagem("Instalação do Jboss", "Instalação do Jboss foi abortada pelo usuário");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				suspenderExecucao();
			}
		});
		 
		frame.setAlwaysOnTop(true);
		frame.setTitle("Instalação do Jboss");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setModal(true); 
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		
		frame.setBounds(100, 100, 385, 516);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLabel = new JLabel("Nome do serviço:");
		lblLabel.setBounds(10, 11, 96, 14);
		frame.getContentPane().add(lblLabel);
		
		txtServico = new JTextField();
		txtServico.setBounds(10, 25, 359, 20);
		frame.getContentPane().add(txtServico);
		txtServico.setColumns(10);
		
		txtDiretorioInstalacao = new JTextField();
		txtDiretorioInstalacao.setColumns(10);
		txtDiretorioInstalacao.setBounds(10, 70, 359, 20);
		frame.getContentPane().add(txtDiretorioInstalacao);
		
		JLabel lblDiretrioDeInstalao = new JLabel("Diretório de instalação:");
		lblDiretrioDeInstalao.setBounds(10, 56, 131, 14);
		frame.getContentPane().add(lblDiretrioDeInstalao);
		
		chkBkpInfraAtual = new JCheckBox("Realizar backup da infra atual");
		chkBkpInfraAtual.setBounds(9, 101, 350, 23);
		frame.getContentPane().add(chkBkpInfraAtual);
		
		chSubstituirInfraAtual = new JCheckBox("Substituir infra atual");
		chSubstituirInfraAtual.setBounds(10, 128, 349, 23);
		frame.getContentPane().add(chSubstituirInfraAtual);
		
		JButton btnCancelarInstalao = new JButton("Cancelar Instalação");
		btnCancelarInstalao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suspenderExecucao();
				frame.dispose();
			}
		});
		btnCancelarInstalao.setBounds(10, 453, 170, 23);
		frame.getContentPane().add(btnCancelarInstalao);
		
		JButton btnProsseguirInstalao = new JButton("Prosseguir Instalação");
		btnProsseguirInstalao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarValoresInstalacao();
				frame.dispose();
			}
		});
		btnProsseguirInstalao.setBounds(199, 453, 170, 23);
		frame.getContentPane().add(btnProsseguirInstalao);
		
		cbBanco = new JComboBox();
		cbBanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarUrlBD();
			}
		});
		cbBanco.setModel(new DefaultComboBoxModel(new String[] {"Oracle RAC", "Oracle", "MS SQL Server"}));
		cbBanco.setBounds(10, 171, 170, 20);
		frame.getContentPane().add(cbBanco);
		
		JLabel lblBancoDeDados = new JLabel("Banco de dados:");
		lblBancoDeDados.setBounds(10, 157, 170, 14);
		frame.getContentPane().add(lblBancoDeDados);
		
		txtHostBD = new JTextField();
		txtHostBD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				montarUrlBD();
			}
		});
		
		txtHostBD.setColumns(10);
		txtHostBD.setBounds(10, 216, 359, 20);
		frame.getContentPane().add(txtHostBD);
		
		JLabel lblHostDoBanco = new JLabel("Host do banco de dados:");
		lblHostDoBanco.setBounds(10, 202, 131, 14);
		frame.getContentPane().add(lblHostDoBanco);
		
		txtUsuario = new JTextField();
		txtUsuario.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				montarUrlBD();
			}
		});
		
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(10, 256, 359, 20);
		frame.getContentPane().add(txtUsuario);
		
		JLabel lblUsurio = new JLabel("Usuário do banco de dados:");
		lblUsurio.setBounds(10, 242, 359, 14);
		frame.getContentPane().add(lblUsurio);
		
		txtSenha = new JTextField();
		txtSenha.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				montarUrlBD();
			}
		});
		txtSenha.setColumns(10);
		txtSenha.setBounds(10, 296, 359, 20);
		frame.getContentPane().add(txtSenha);
		
		JLabel lblSenhaDoBanco = new JLabel("Senha do banco de dados:");
		lblSenhaDoBanco.setBounds(10, 282, 359, 14);
		frame.getContentPane().add(lblSenhaDoBanco);
		
		taURL = new JTextArea();
		taURL.setLineWrap(true);
		taURL.setBounds(10, 385, 359, 62);
		frame.getContentPane().add(taURL);
		
		JLabel lblUrlDoBanco = new JLabel("URL do BD - Editar somente se necessário:");
		lblUrlDoBanco.setBounds(10, 371, 359, 14);
		frame.getContentPane().add(lblUrlDoBanco);
		
		txtInstancia = new JTextField();
		txtInstancia.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				montarUrlBD();
			}
		});
		txtInstancia.setText("SGAP");
		txtInstancia.setColumns(10);
		txtInstancia.setBounds(10, 336, 359, 20);
		frame.getContentPane().add(txtInstancia);
		
		lblInstanciaservicoDoBanco = new JLabel("Instancia/Servico do banco de dados:");
		lblInstanciaservicoDoBanco.setBounds(10, 322, 359, 14);
		frame.getContentPane().add(lblInstanciaservicoDoBanco);
		
		cbVersaoBD = new JComboBox();
		cbVersaoBD.setModel(new DefaultComboBoxModel(new String[] {"11.2.0.3", "11.2.0.4", "12", "2005", "2008", "2012"}));
		cbVersaoBD.setBounds(199, 171, 170, 20);
		frame.getContentPane().add(cbVersaoBD);
		
		lblVerso = new JLabel("Versão:");
		lblVerso.setBounds(199, 157, 170, 14);
		frame.getContentPane().add(lblVerso);
	}

	/** 
	 * @see com.compsis.configurador.dominio.Atividade#executar(com.compsis.configurador.dominio.Execucao)
	 */
	@Override
	public void executar(Execucao execucao) {
		this.execucao = execucao;
		instalacaoJboss = execucao.obterDaSessao(Constantes.INSTALACAO_JBOSS_INTERACAO_USUARIO.toString());
		if(instalacaoJboss == null) {
			instalacaoJboss = new InstalacaoJboss();
			instalacaoJboss.setDiretorioInstalacao(configuracao.getConfigurador().getPastaRaizInfra().getAbsolutePath());
			instalacaoJboss.setNomeServico(configuracao.getConfigurador().getSistema());

			execucao.adicionarNaSessao(Constantes.INSTALACAO_JBOSS_INTERACAO_USUARIO.toString()	, instalacaoJboss);
		}
		alterarValoresComponentes();
		
		exibir();
	}
	
	private void alterarValoresComponentes() {
		txtServico.setText(instalacaoJboss.getNomeServico());
		txtDiretorioInstalacao.setText(instalacaoJboss.getDiretorioInstalacao());
		txtHostBD.setText(instalacaoJboss.getHostBD());
		txtSenha.setText(instalacaoJboss.getSenhaBD());
		txtUsuario.setText(instalacaoJboss.getUsuarioBD());
		taURL.setText(instalacaoJboss.getUrlBD());
		chkBkpInfraAtual.setSelected(instalacaoJboss.isRealizarBkp());
		chSubstituirInfraAtual.setSelected(instalacaoJboss.isSubstituirInfraOriginal());
		cbBanco.setSelectedItem(instalacaoJboss.getNomeBD());
		String instanciaBD = instalacaoJboss.getInstanciaBD();
		if(Strings.isNullOrEmpty(instanciaBD)) {
			instanciaBD = "SGAP";
		}
		txtInstancia.setText(instanciaBD);
	}
	
	private void alterarValoresInstalacao() {
		instalacaoJboss.setDiretorioInstalacao(new File(txtDiretorioInstalacao.getText()).getAbsolutePath());
		instalacaoJboss.setNomeServico(txtServico.getText());
		instalacaoJboss.setRealizarBkp(chkBkpInfraAtual.isSelected());
		instalacaoJboss.setSubstituirInfraOriginal(chSubstituirInfraAtual.isSelected());
		instalacaoJboss.setHostBD(txtHostBD.getText());
		instalacaoJboss.setSenhaBD(txtSenha.getText());
		instalacaoJboss.setUsuarioBD(txtUsuario.getText());
		instalacaoJboss.setUrlBD(taURL.getText());
		instalacaoJboss.setNomeBD(cbBanco.getSelectedItem().toString());
		instalacaoJboss.setInstanciaBD(txtInstancia.getText());
	}
	
	private void montarUrlBD() {
		String usuario = txtUsuario.getText();
		String host = txtHostBD.getText();
		String instancia = txtInstancia.getText();
		String banco = String.valueOf(cbBanco.getSelectedItem());
		String url = null;
		if("ms sql server".equals(banco.toLowerCase())) {
			url = "jdbc:sqlserver://${host}\\${instancia};database=${user-name};sendStringParametersAsUnicode=false";
		} else if("oracle rac".equals(banco.toLowerCase())) {
			url = "jdbc:oracle:oci:@(description=(address=(host=${host})(protocol=tcp)(port=1521))(connect_data=(SERVICE_NAME=${instancia})))";			
		} else if("oracle".equals(banco.toLowerCase())) {
			url = "jdbc:oracle:thin:@${host}:1521:${instancia}";			
		}
		if(!Strings.isNullOrEmpty(url)) {
			url = url.replace("${host}", host);
			url = url.replace("${instancia}", instancia);
			url = url.replace("${user-name}", usuario);
			instalacaoJboss.setUrlBD(url);
			taURL.setText(url);
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
