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
import javax.swing.DefaultComboBoxModel;

public class InserisciInfortunio extends JDialog {
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	IscrittoDAOP iscrittoDAOP =new IscrittoDAOP();
	private JTextField txtData;
	//VARIABILI DA PASSARE ALLA QUERY
	private int idInfortunio=0;
	private Date data;
	private int GiorniSosta;
	private int gravita;
	private int MatricolaFIN;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			InserisciInfortunio dialog = new InserisciInfortunio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public InserisciInfortunio(Boolean modifica, Infortunio info) {
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
			JLabel lblGravita = new JLabel("Gravit\u00E0");
			lblGravita.setBounds(12, 103, 56, 16);
			getContentPane().add(lblGravita);
		}

		
			JButton btnInserisci = new JButton("Inserisci");
			btnInserisci.setEnabled(false);
			btnInserisci.setBounds(102, 131, 97, 25);
			getContentPane().add(btnInserisci);
			
			JLabel lblAtleta = new JLabel("Atleta");
			lblAtleta.setBounds(12, 13, 56, 16);
			getContentPane().add(lblAtleta);
			
			JComboBox<Iscritto> comboMatricola = new JComboBox<Iscritto>();
			comboMatricola.setBounds(102, 10, 329, 22);
			comboMatricola.setModel(iscrittoDAOP.getAtleticb());
			getContentPane().add(comboMatricola);
			
			JSpinner spinnerGiorni = new JSpinner();
			spinnerGiorni.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spinnerGiorni.setBounds(102, 68, 116, 22);
			getContentPane().add(spinnerGiorni);
			
			JComboBox comboGravita = new JComboBox();
			comboGravita.setModel(new DefaultComboBoxModel(new String[] {"Lieve", "Media", "Elevata"}));
			comboGravita.setBounds(102, 100, 130, 22);
			getContentPane().add(comboGravita);
			
			JButton buttonModifica = new JButton("Modifica");
			buttonModifica.setEnabled(false);
			buttonModifica.setBounds(211, 131, 97, 25);
			getContentPane().add(buttonModifica);
			txtData.addCaretListener(new CaretListener() {
				public void caretUpdate(CaretEvent e) {
					if(controlloBottone()==false)
						btnInserisci.setEnabled(false);
					else
						btnInserisci.setEnabled(true);
				}
			});
			
			
			btnInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//Prendere la matricola 
					MatricolaFIN = iscrittoDAOP.getAtleticb().getElementAt(comboMatricola.getSelectedIndex()).getMatricolaFIN();
					data = Date.valueOf(txtData.getText());
					GiorniSosta = (Integer)spinnerGiorni.getValue();   //   Integer.parseInt(txtSosta.getText());
					System.out.println(Integer.toString(GiorniSosta) );
					gravita = comboGravita.getSelectedIndex()+1;
					System.out.println(gravita) ;
					//DEVO PASSARE IL VALORE MATRICOLAFIN
					Infortunio i = new Infortunio(null, data, GiorniSosta, gravita, MatricolaFIN);
					
					if (iDAOP.salva(i))
						JOptionPane.showMessageDialog(null, "Inserimento Riuscito");
					else
						JOptionPane.showMessageDialog(null, "Inserimento Fallito");
					
				}
			});
			
			
			
	}
	//CONTROLLO  PER L'ATTIVAZIONE DEL BOTTONE
	public boolean controlloBottone() {
		boolean risultato=true;
		if(txtData.getText().equals("")/*||txtSosta.getText().equals("")||txtGravita.getText().equals("")*/)
			risultato=false;
		else
			risultato=true;
		return risultato;
	}
}
