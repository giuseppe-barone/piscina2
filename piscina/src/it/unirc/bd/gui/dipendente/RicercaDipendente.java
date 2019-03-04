package it.unirc.bd.gui.dipendente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RicercaDipendente extends JDialog {
	private JTextField txtNome;
	private JTextField txtCognome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaDipendente dialog = new RicercaDipendente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaDipendente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel PannelloGeneralita = new JPanel();
		PannelloGeneralita.setBorder(new TitledBorder(null, "Generalit\u00E0", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PannelloGeneralita.setBounds(161, 27, 231, 123);
		getContentPane().add(PannelloGeneralita);
		PannelloGeneralita.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 31, 43, 16);
		PannelloGeneralita.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(12, 60, 61, 16);
		PannelloGeneralita.add(lblCognome);
		
		txtNome = new JTextField();
		txtNome.setBounds(82, 28, 116, 22);
		PannelloGeneralita.add(txtNome);
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.setBounds(82, 57, 116, 22);
		PannelloGeneralita.add(txtCognome);
		txtCognome.setColumns(10);
	}
}
