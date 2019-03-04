package it.unirc.bd.gui.dipendente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class RicercaDipendente extends JDialog {
	private JTextField txtNome;
	private JTextField txtCognome;

	ButtonGroup TipoRicerca = new ButtonGroup();
	ButtonGroup Sesso = new ButtonGroup();
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
		setTitle("Ricerca");
		setBounds(100, 100, 427, 343);
		getContentPane().setLayout(null);
		
		JPanel PannelloGeneralita = new JPanel();
		PannelloGeneralita.setBorder(new TitledBorder(null, "Generalit\u00E0", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PannelloGeneralita.setBounds(161, 41, 231, 123);
		getContentPane().add(PannelloGeneralita);
		PannelloGeneralita.setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 31, 43, 16);
		PannelloGeneralita.add(lblNome);
		lblNome.setEnabled(false);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(12, 60, 61, 16);
		PannelloGeneralita.add(lblCognome);
		lblCognome.setEnabled(false);
		
		txtNome = new JTextField();
		txtNome.setBounds(82, 28, 116, 22);
		PannelloGeneralita.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setEnabled(false);
		
		txtCognome = new JTextField();
		txtCognome.setBounds(82, 57, 116, 22);
		PannelloGeneralita.add(txtCognome);
		txtCognome.setColumns(10);
		txtCognome.setEnabled(false);
		
		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setBounds(12, 94, 56, 16);
		PannelloGeneralita.add(lblSesso);
		lblSesso.setEnabled(false);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(82, 88, 43, 25);
		PannelloGeneralita.add(rdbtnM);
		Sesso.add(rdbtnM);
		rdbtnM.setEnabled(false);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(137, 88, 48, 25);
		PannelloGeneralita.add(rdbtnF);
		Sesso.add(rdbtnF);
		rdbtnF.setEnabled(false);
		
		JRadioButton rdbtnGeneralit = new JRadioButton("Generalit\u00E0");
		rdbtnGeneralit.setBounds(8, 37, 127, 25);
		getContentPane().add(rdbtnGeneralit);
		TipoRicerca.add(rdbtnGeneralit);
		
		JRadioButton rdbtnTipologiaDipendente = new JRadioButton("Tipologia Dipendente");
		rdbtnTipologiaDipendente.setBounds(8, 176, 149, 25);
		getContentPane().add(rdbtnTipologiaDipendente);
		TipoRicerca.add(rdbtnTipologiaDipendente);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(161, 177, 120, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Segretario", "Tecnico", "Allenatore"}));
		getContentPane().add(comboBox);
		comboBox.setEnabled(false);
		
		JRadioButton rdbtnTutti = new JRadioButton("Tutti");
		rdbtnTutti.setBounds(8, 221, 69, 25);
		getContentPane().add(rdbtnTutti);
		TipoRicerca.add(rdbtnTutti);
		
		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.setBounds(144, 247, 97, 25);
		getContentPane().add(btnRicerca);
	}
}
