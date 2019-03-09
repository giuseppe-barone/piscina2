package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RicercaPrenotazioni extends JDialog {
	private JTextField txtData;
	private JTextField txtCorsia;
	private JTextField txtTipo;
	private JTextField txtOra;
	
	//PARAMETRI DA PASSARE ALLA QUERY
	private Date data;
	private int corsia;
	private String tipo;
	private int ora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaPrenotazioni dialog = new RicercaPrenotazioni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaPrenotazioni() {
		setTitle("Ricerca Prenotazioni");
		setBounds(100, 100, 374, 300);
		getContentPane().setLayout(null);
		
		JCheckBox checkData = new JCheckBox("Data");
		checkData.setBounds(65, 50, 65, 25);
		getContentPane().add(checkData);
		
		JLabel lblRicercaPer = new JLabel("Ricerca per:");
		lblRicercaPer.setBounds(26, 13, 76, 28);
		getContentPane().add(lblRicercaPer);
		
		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setText("yy-mm-dd");
		txtData.setBounds(171, 50, 116, 22);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		JCheckBox checkCorsia = new JCheckBox("Corsia");
		checkCorsia.setBounds(65, 85, 76, 25);
		getContentPane().add(checkCorsia);
		
		txtCorsia = new JTextField();
		txtCorsia.setBounds(171, 85, 116, 22);
		getContentPane().add(txtCorsia);
		txtCorsia.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(171, 120, 116, 22);
		getContentPane().add(txtTipo);
		txtTipo.setColumns(10);
		
		txtOra = new JTextField();
		txtOra.setBounds(171, 155, 116, 22);
		getContentPane().add(txtOra);
		txtOra.setColumns(10);
		
		JCheckBox checkTipo = new JCheckBox("Tipo Piscina");
		checkTipo.setBounds(65, 120, 113, 25);
		getContentPane().add(checkTipo);
		
		JCheckBox checkOra = new JCheckBox("Orario");
		checkOra.setBounds(65, 155, 113, 25);
		getContentPane().add(checkOra);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(124, 203, 97, 25);
		getContentPane().add(btnCerca);
	}
}
