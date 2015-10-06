
package com.compsis.configurador.telas;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import com.compsis.configurador.Configuracao;
import java.awt.Font;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class TelaInicial {

	private JFrame telaInicial;
	private Configuracao configuracao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@PostConstruct
	public void initialize() {
		telaInicial = new JFrame();
		telaInicial.setBounds(100, 100, 513, 341);
		telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaInicial.getContentPane().setLayout(null);
		
		JButton btUpdateVersao = new JButton("Atualizar versão do sistema");
		btUpdateVersao.setBounds(10, 11, 477, 29);
		
		telaInicial.getContentPane().add(btUpdateVersao);
		
		JButton btRollbackVersao = new JButton("Fazer rollback da versão");
		btRollbackVersao.setBounds(10, 51, 477, 29);
		telaInicial.getContentPane().add(btRollbackVersao);
		
		final JTextPane txtDescription = new JTextPane();
		txtDescription.setFont(new Font("Arial", Font.BOLD, 12));
		txtDescription.setBounds(10, 91, 477, 200);
		telaInicial.getContentPane().add(txtDescription);
		
		StringBuilder builder = new StringBuilder();
		builder.append("Atualização do sistema \n")
		.append("Irá realizar atualização do sistema com as versões diponíveis no diretório {DIRSYSTEMUPDATE}.\n")
		.append("Os scripts de atualização do sistema devem estar disponíveis no diretório {DIRSCRIPTSUPDATE}");
		final String textoUpdate = builder.toString().replace("{DIRSYSTEMUPDATE}", configuracao.getConfigurador().getPastaAtualizacaoVersao().getAbsolutePath())
				.replace("{DIRSCRIPTSUPDATE}", configuracao.getConfigurador().getPastaScriptsAtualizacao().getAbsolutePath());
		builder.setLength(0);
		builder.append("Rollback de sistema \n")
		.append("Irá realizar rollback do sistema com as versões diponíveis no diretório {DIRSYSTEMROLLBACK}.\n")
		.append("Os scripts de rollback do sistema devem estar disponíveis no diretório {DIRSCRIPTSROLLBACK}");
		final String textoRollback = builder.toString().replace("{DIRSYSTEMROLLBACK}", configuracao.getConfigurador().getPastaBackup().getAbsolutePath())
				.replace("{DIRSCRIPTSROLLBACK}", configuracao.getConfigurador().getPastaScriptsRollback().getAbsolutePath());
		
		btRollbackVersao.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) { }
			
			@Override
			public void mousePressed(MouseEvent e) {
				txtDescription.setText("");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				txtDescription.setText("");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				txtDescription.setText(textoRollback);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDescription.setText("clicked");
			}
		});
		btUpdateVersao.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				txtDescription.setText("");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				txtDescription.setText("");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				txtDescription.setText(textoUpdate);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDescription.setText("clicked");
			}
		});
		
		telaInicial.setVisible(true);
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
