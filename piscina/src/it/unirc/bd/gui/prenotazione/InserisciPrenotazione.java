package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Prenotazione;
import it.unirc.bd.dao.beans.PrenotazioneDAOP;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InserisciPrenotazione extends JDialog {
	PrenotazioneDAOP pDAOP = new PrenotazioneDAOP();

	private JTextField txtIdPrenotazione;
	private JTextField txtCorsia;
	private JTextField txtData;
	private JTextField txtIdIscritto;
	private JTextField txtTipoPiscina;
	private JTextField txtOra;
	private JTextField txtIdDipendente;
	//VARIABILI DA PASSARE ALLA QUERY
	private int idPrenotazione;
	private int corsia;
	private Date data;
	private int idIscritto;
	private String tipoPiscina;
	private int ora;
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
		setTitle("Inserimento Prenotazione");
		setBounds(100, 100, 510, 300);
		getContentPane().setLayout(null);

		JLabel lblCorsia = new JLabel("Corsia");
		lblCorsia.setBounds(12, 58, 56, 16);
		getContentPane().add(lblCorsia);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(12, 99, 56, 16);
		getContentPane().add(lblData);

		JLabel lblIdIsciritto = new JLabel("Id Isciritto");
		lblIdIsciritto.setBounds(12, 144, 68, 16);
		getContentPane().add(lblIdIsciritto);

		JLabel lblTipoPiscina = new JLabel("Tipo Piscina");
		lblTipoPiscina.setBounds(237, 19, 68, 16);
		getContentPane().add(lblTipoPiscina);

		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(237, 61, 56, 16);
		getContentPane().add(lblOra);

		JLabel lblIdDipendente = new JLabel("Id Dipendente");
		lblIdDipendente.setBounds(237, 99, 92, 16);
		getContentPane().add(lblIdDipendente);

		txtCorsia = new JTextField();
		txtCorsia.setBounds(109, 52, 116, 22);
		getContentPane().add(txtCorsia);
		txtCorsia.setColumns(10);

		txtData = new JTextField();
		txtData.setBounds(109, 93, 116, 22);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		txtIdIscritto = new JTextField();
		txtIdIscritto.setBounds(109, 138, 116, 22);
		getContentPane().add(txtIdIscritto);
		txtIdIscritto.setColumns(10);

		txtTipoPiscina = new JTextField();
		txtTipoPiscina.setBounds(341, 13, 116, 22);
		getContentPane().add(txtTipoPiscina);
		txtTipoPiscina.setColumns(10);

		txtOra = new JTextField();
		txtOra.setBounds(341, 55, 116, 22);
		getContentPane().add(txtOra);
		txtOra.setColumns(10);

		txtIdDipendente = new JTextField();
		txtIdDipendente.setBounds(341, 93, 116, 22);
		getContentPane().add(txtIdDipendente);
		txtIdDipendente.setColumns(10);

		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setEnabled(false);
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idPrenotazione = Integer.parseInt(txtIdPrenotazione.getText());
				corsia = Integer.parseInt(txtCorsia.getText());
				data = data.valueOf(txtData.getText());
				idIscritto = Integer.parseInt(txtIdIscritto.getText());
				tipoPiscina = txtTipoPiscina.getText();
				ora = Integer.parseInt(txtOra.getText());
				idDipendente = Integer.parseInt(txtIdDipendente.getText());
				Prenotazione p = new Prenotazione(idPrenotazione, corsia, data, idIscritto, tipoPiscina, ora, idDipendente);
				if(pDAOP.salva(p)==true)
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
					
			}
		});
		btnInserisci.setBounds(341, 203, 97, 25);
		getContentPane().add(btnInserisci);

		txtIdPrenotazione = new JTextField();
		txtIdPrenotazione.setBounds(109, 13, 116, 22);
		getContentPane().add(txtIdPrenotazione);
		txtIdPrenotazione.setColumns(10);

		JLabel lblIdPrenotazione = new JLabel("Id Prenotazione");
		lblIdPrenotazione.setBounds(12, 19, 97, 16);
		getContentPane().add(lblIdPrenotazione);

		txtIdPrenotazione.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtCorsia.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtData.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtIdIscritto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtTipoPiscina.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtOra.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		
		txtIdDipendente.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(controlloBottone()==false)
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});

	}

	//CONTROLLO ATTIVAZIONE DEL BOTTONE
	public boolean controlloBottone() {
		boolean risultato=true;
		if(txtIdPrenotazione.getText().equals("")||txtCorsia.getText().equals("")||txtData.getText().equals("")||txtIdIscritto.getText().equals("")||txtTipoPiscina.getText().equals("")||txtOra.getText().equals("")||txtIdDipendente.getText().equals(""))
			risultato=false;
		else
			risultato=true;
		return risultato;
	}
}
