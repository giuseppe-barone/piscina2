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
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class InserisciInfortunio extends JDialog {
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	IscrittoDAOP iscrittoDAOP =new IscrittoDAOP();
	private JTextField txtData;
	private JTextField txtSosta;
	private JTextField txtGravita;
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
		setBounds(100, 100, 466, 216);
		getContentPane().setLayout(null);
		{
			JLabel lblData = new JLabel("Data");
			lblData.setBounds(12, 39, 56, 16);
			getContentPane().add(lblData);
		}
		{
			txtData = new JTextField();
			txtData.setBounds(102, 36, 116, 22);
			getContentPane().add(txtData);
			txtData.setColumns(10);
		}
		{
			JLabel lblGiorniDiSosta = new JLabel("Giorni di Sosta");
			lblGiorniDiSosta.setBounds(12, 71, 89, 16);
			getContentPane().add(lblGiorniDiSosta);
		}
		{
			txtSosta = new JTextField();
			txtSosta.setBounds(102, 68, 116, 22);
			getContentPane().add(txtSosta);
			txtSosta.setColumns(10);
		}
		{
			JLabel lblGravita = new JLabel("Gravit\u00E0");
			lblGravita.setBounds(12, 103, 56, 16);
			getContentPane().add(lblGravita);
		}
		{
			txtGravita = new JTextField();
			txtGravita.setBounds(101, 100, 116, 22);
			getContentPane().add(txtGravita);
			txtGravita.setColumns(10);
		}

		
			JButton btnInserisci = new JButton("Inserisci");
			btnInserisci.setEnabled(false);
			btnInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Inserimento Riuscito");

					data = Date.valueOf(txtData.getText());
					GiorniSosta = Integer.parseInt(txtSosta.getText());
					gravita = Integer.parseInt(txtGravita.getText());
					//DEVO PASSARE IL VALORE MATRICOLAFIN
					Infortunio i = new Infortunio(idInfortunio, data, GiorniSosta, gravita, MatricolaFIN);
					iDAOP.salva(i);
				}
			});
			btnInserisci.setBounds(67, 131, 97, 25);
			getContentPane().add(btnInserisci);
			
			JLabel lblAtleta = new JLabel("Atleta");
			lblAtleta.setBounds(12, 13, 56, 16);
			getContentPane().add(lblAtleta);
			
			JComboBox<Iscritto> comboMatricola = new JComboBox<Iscritto>();
			comboMatricola.setBounds(102, 10, 329, 22);
			comboMatricola.setModel(iscrittoDAOP.getAtleticb());
			getContentPane().add(comboMatricola);
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
	}
	//CONTROLLO  PER L'ATTIVAZIONE DEL BOTTONE
	public boolean controlloBottone() {
		boolean risultato=true;
		if(txtData.getText().equals("")||txtSosta.getText().equals("")||txtGravita.getText().equals(""))
			risultato=false;
		else
			risultato=true;
		return risultato;
	}
}
