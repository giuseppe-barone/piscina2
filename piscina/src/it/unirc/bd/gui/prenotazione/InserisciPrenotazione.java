package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
public class InserisciPrenotazione extends JDialog {
	private JTextField txtIdPrenotazione;
	private JTextField txtCorsia;
	private JTextField txtData;
	private JTextField txtIdIscritto;
	private JTextField txtTipoPiscina;
	private JTextField txtOra;
	private JTextField txtGiorno;
	private JTextField txtIdDipendente;
	//VARIABILI DA PASSARE ALLA QUERY
	private int idPrenotazione;
	private int corsia;
	private Date data;
	private int idIscritto;
	private String TipoPiscina;
	private int ora;
	private int giorno;
	private int idDipendente;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciPrenotazione dialog = new InserisciPrenotazione();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciPrenotazione() {
		setTitle("Inserisci Prenotazione");
		setBounds(100, 100, 511, 258);
		getContentPane().setLayout(null);
		
		txtIdPrenotazione = new JTextField();
		txtIdPrenotazione.setBounds(121, 13, 116, 22);
		getContentPane().add(txtIdPrenotazione);
		txtIdPrenotazione.setColumns(10);
		
		txtCorsia = new JTextField();
		txtCorsia.setBounds(121, 48, 116, 22);
		getContentPane().add(txtCorsia);
		txtCorsia.setColumns(10);
		
		txtData = new JTextField();
		txtData.setBounds(121, 83, 116, 22);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		txtIdIscritto = new JTextField();
		txtIdIscritto.setBounds(121, 118, 116, 22);
		getContentPane().add(txtIdIscritto);
		txtIdIscritto.setColumns(10);
		
		txtTipoPiscina = new JTextField();
		txtTipoPiscina.setBounds(365, 13, 116, 22);
		getContentPane().add(txtTipoPiscina);
		txtTipoPiscina.setColumns(10);
		
		txtOra = new JTextField();
		txtOra.setBounds(365, 48, 116, 22);
		getContentPane().add(txtOra);
		txtOra.setColumns(10);
		
		txtGiorno = new JTextField();
		txtGiorno.setBounds(365, 83, 116, 22);
		getContentPane().add(txtGiorno);
		txtGiorno.setColumns(10);
		
		txtIdDipendente = new JTextField();
		txtIdDipendente.setBounds(365, 118, 116, 22);
		getContentPane().add(txtIdDipendente);
		txtIdDipendente.setColumns(10);
		
		JLabel lblIdPrenotazione = new JLabel("Id Prenotazione");
		lblIdPrenotazione.setBounds(12, 16, 89, 16);
		getContentPane().add(lblIdPrenotazione);
		
		JLabel lblCorsia = new JLabel("Corsia");
		lblCorsia.setBounds(12, 51, 56, 16);
		getContentPane().add(lblCorsia);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(12, 83, 56, 16);
		getContentPane().add(lblData);
		
		JLabel lblIdIscritto = new JLabel("Id Iscritto");
		lblIdIscritto.setBounds(12, 121, 56, 16);
		getContentPane().add(lblIdIscritto);
		
		JLabel lblTipoPiscina = new JLabel("Tipo Piscina");
		lblTipoPiscina.setBounds(249, 19, 76, 16);
		getContentPane().add(lblTipoPiscina);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(249, 54, 56, 16);
		getContentPane().add(lblOra);
		
		JLabel lblGiorno = new JLabel("Giorno");
		lblGiorno.setBounds(249, 89, 56, 16);
		getContentPane().add(lblGiorno);
		
		JLabel lblIdDipendente = new JLabel("Id Dipendente");
		lblIdDipendente.setBounds(249, 124, 94, 16);
		getContentPane().add(lblIdDipendente);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnInserisci.setBounds(185, 171, 97, 25);
		getContentPane().add(btnInserisci);
	}
}
