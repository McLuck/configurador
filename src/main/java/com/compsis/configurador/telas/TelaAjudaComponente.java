
package com.compsis.configurador.telas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Tela de ajuda que pode ser chamada de qualquer componente
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 10/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

@SuppressWarnings("serial")
public class TelaAjudaComponente extends JDialog {
	
	/**
	 * Launch the application.
	 * Somente para teste
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaAjudaComponente("Titulo", "Help");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaAjudaComponente(final String tituloAjuda, final String textoAjuda) {
		setTitle(tituloAjuda);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 700, 305);
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 674, 254);
		textArea.setText(textoAjuda);
		getContentPane().add(textArea);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
