package it.unirc.bd.gui.infortunio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Infortunio;
import it.unirc.bd.dao.beans.InfortunioDAOP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class InserisciInfortunio extends JDialog {
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	private JTextField txtData;
	private JTextField txtSosta;
	private JTextField txtGravita;
	private JTextField txtMatricola;
	//VARIABILI DA PASSARE ALLA QUERY
	private int idInfortunio=0;
	private Date data;
	private int GiorniSosta;
	private int gravita;
	private int MatricolaFIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciInfortunio dialog = new InserisciInfortunio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciInfortunio() {
		setBounds(100, 100, 497, 188);
		getContentPane().setLayout(null);
		{
			JLabel lblData = new JLabel("Data");
			lblData.setBounds(12, 56, 56, 16);
			getContentPane().add(lblData);
		}
		{
			txtData = new JTextField();
			txtData.setBounds(102, 53, 116, 22);
			getContentPane().add(txtData);
			txtData.setColumns(10);
		}
		{
			JLabel lblGiorniDiSosta = new JLabel("Giorni di Sosta");
			lblGiorniDiSosta.setBounds(12, 102, 89, 16);
			getContentPane().add(lblGiorniDiSosta);
		}
		{
			txtSosta = new JTextField();
			txtSosta.setBounds(102, 99, 116, 22);
			getContentPane().add(txtSosta);
			txtSosta.setColumns(10);
		}
		{
			JLabel lblGravita = new JLabel("Gravit\u00E0");
			lblGravita.setBounds(249, 13, 56, 16);
			getContentPane().add(lblGravita);
		}
		{
			txtGravita = new JTextField();
			txtGravita.setBounds(338, 10, 116, 22);
			getContentPane().add(txtGravita);
			txtGravita.setColumns(10);
		}
		{
			JLabel lblMatricolaFin = new JLabel("Matricola FIN");
			lblMatricolaFin.setBounds(249, 56, 81, 16);
			getContentPane().add(lblMatricolaFin);
		}
		{
			txtMatricola = new JTextField();
			txtMatricola.setBounds(338, 53, 116, 22);
			getContentPane().add(txtMatricola);
			txtMatricola.setColumns(10);
		}

		
			JButton btnInserisci = new JButton("Inserisci");
			btnInserisci.setEnabled(false);
			btnInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Inserimento Riuscito");
					idInfortunio = Integer.parseInt(txtIdInfortunio.getText());
					data = data.valueOf(txtData.getText());
					GiorniSosta = Integer.parseInt(txtSosta.getText());
					gravita = Integer.parseInt(txtGravita.getText());
					MatricolaFIN = Integer.parseInt(txtMatricola.getText());
					Infortunio i = new Infortunio(idInfortunio, data, GiorniSosta, gravita, MatricolaFIN);
					iDAOP.salva(i);
				}
			});
			btnInserisci.setBounds(338, 98, 97, 25);
			getContentPane().add(btnInserisci);
			txtData.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if(controlloBottone()==false)
						btnInserisci.setEnabled(false);
					else
						btnInserisci.setEnabled(true);
				}
			});
			txtSosta.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if(controlloBottone()==false)
						btnInserisci.setEnabled(false);
					else
						btnInserisci.setEnabled(true);
				}
			});
			txtGravita.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if(controlloBottone()==false)
						btnInserisci.setEnabled(false);
					else
						btnInserisci.setEnabled(true);
				}
			});
			txtMatricola.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if(controlloBottone()==false)
						btnInserisci.setEnabled(false);
					else
						btnInserisci.setEnabled(true);
				}
			});
	}
	//CONTROLLO  PER L'ATTIVAZIONE DEL BOTTONE
	public boolean controlloBottone() {
		boolean risultato=true;
		if(txtIdInfortunio.getText().equals("")||txtData.getText().equals("")||txtSosta.getText().equals("")||txtGravita.getText().equals("")||txtMatricola.getText().equals(""))
			risultato=false;
		else
			risultato=true;
		return risultato;
	}

}
