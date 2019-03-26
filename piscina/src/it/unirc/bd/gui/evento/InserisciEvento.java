package it.unirc.bd.gui.evento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InserisciEvento extends JDialog {
	EventoDAOP eDAOP = new EventoDAOP();
	private JTextField txtDATA;
	private JTextField txtTIPO;
	//VARIABILI DA PASSARE ALLA QUERY
	private int idEvento;
	private Date data;
	private String livello;
	private String tipo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciEvento dialog = new InserisciEvento(false, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param evento 
	 * @param b 
	 */
	public InserisciEvento(boolean Modifica, Evento evento) {
		setBounds(100, 100, 416, 235);
		getContentPane().setLayout(null);


		
		txtDATA = new JTextField();
		
		txtDATA.setBounds(82, 33, 116, 22);
		getContentPane().add(txtDATA);
		txtDATA.setColumns(10);

		txtTIPO = new JTextField();
		
		txtTIPO.setBounds(261, 33, 116, 22);
		getContentPane().add(txtTIPO);
		txtTIPO.setColumns(10);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(14, 36, 56, 16);
		getContentPane().add(lblData);

		JLabel lblTIPO = new JLabel("Tipo");
		lblTIPO.setBounds(210, 36, 56, 16);
		getContentPane().add(lblTIPO);

		JLabel lblLivello = new JLabel("Livello");
		lblLivello.setBounds(14, 95, 56, 16);
		getContentPane().add(lblLivello);

		JComboBox cbLIVELLO = new JComboBox();
		cbLIVELLO.setBounds(82, 92, 113, 22);
		cbLIVELLO.setModel(new DefaultComboBoxModel(new String[] {"PROVINCIALE", "REGIONALE", "NAZIONALE"}));
		getContentPane().add(cbLIVELLO);

		JButton btnINSERISCI = new JButton("Inserisci");








		btnINSERISCI.addActionListener(new ActionListener() { //INSERIMENTO EVENTO
			public void actionPerformed(ActionEvent arg0) {
				//idEvento = Integer.parseInt(txtIDEVENTO.getText()); //CAST DA STRING A INT
				data = data.valueOf(txtDATA.getText());
				livello = (String) cbLIVELLO.getModel().getElementAt(cbLIVELLO.getSelectedIndex());
				tipo = txtTIPO.getText();
				Evento e = new Evento(idEvento, data, livello, tipo);
				//--------------CODICE PER IL CALCOLO DELL'ETA DA INSERIRE A PARTE PER PEPPE----------------
				/*LocalDate corrente=LocalDate.now();
				Date nascita=data.valueOf(txtDATA.getText());
				LocalDate LNascita=nascita.toLocalDate();
				System.out.println(LNascita.toString());

				System.out.println(corrente.toString());
				if ((corrente != null) && (data != null)) {
		           System.out.println(Period.between(LNascita, corrente).getYears());
		        }*/
				if (eDAOP.salvaEvento(e)) {
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					//---------------------------------------PARTE PER AZZERARE----------------------------------------------------
					txtDATA.setText("");
					//txtIDEVENTO.setText("");
					txtTIPO.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");

				}
		});
		btnINSERISCI.setBounds(133, 143, 97, 25);
		getContentPane().add(btnINSERISCI);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setEnabled(false);
		btnModifica.setVisible(false);
		btnModifica.setBounds(261, 143, 97, 25);
		getContentPane().add(btnModifica);
		
		txtDATA.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				if(controlloBottone()==false) {
					btnINSERISCI.setEnabled(false);
					System.out.println("blocca DATA");
				}
				else {
					btnINSERISCI.setEnabled(true);
					System.out.println("Sblocca DATA");
				}
				if(controlloBottone()==false)//|| txtMatricolaFin.getText().equals(""))
					btnModifica.setEnabled(false);
				else
					btnModifica.setEnabled(true);
			}
		});
		txtTIPO.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				if(controlloBottone()==false) {
					btnINSERISCI.setEnabled(false);
					System.out.println("blocca TIPO");
				}
				else {
					btnINSERISCI.setEnabled(true);
					System.out.println("Sblocca TIPO");
				}
				//---CONTROLLI ABILITAZIONE BOTTONE MODIFICA----
				if(controlloBottone()==false)//|| txtMatricolaFin.getText().equals(""))
					btnModifica.setEnabled(false);
				else
					btnModifica.setEnabled(true);

			}
		});

		if (Modifica==true) {
			btnModifica.setVisible(true);
			btnINSERISCI.setVisible(false);
		}
		else{
			btnModifica.setVisible(false);
			btnINSERISCI.setVisible(true);
		}
		
		if (Modifica==true) {
			btnModifica.setVisible(true);
			btnINSERISCI.setVisible(false);
		}
		else{
			btnModifica.setVisible(false);
			btnINSERISCI.setVisible(true);
		}
		
		//MECCANISMO DI MODIFICA
		if (Modifica) {
			txtDATA.setText(evento.getData().toString());
			txtTIPO.setText(evento.getTipo());
			livello = (String) cbLIVELLO.getModel().getElementAt(cbLIVELLO.getSelectedIndex());
		}
		
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipo = txtTIPO.getText();
				livello=(String) cbLIVELLO.getModel().getElementAt(cbLIVELLO.getSelectedIndex());	//CASTING
				data = Date.valueOf(txtDATA.getText());
				
				Evento e = new Evento(evento.getIdEvento(),data, livello, tipo);
				eDAOP.ModificaEvento(e);
			}
		});
		
		
		
		



	}
	//CONTROLLO PER AVVISO ID DUPLICATO O ESISTENTE
//public String ControlloAvvisoEvento() {
		//String risultato="";
		//String IDE = txtIDEVENTO.getText();
		/*if(IDE.equals("")) {
			System.out.println("NON C'E NESSUN VALORE");
			return risultato;
		}
		Integer ID=Integer.parseInt(IDE);
		if(!IDE.equals("")&&eDAOP.ControlloDinamicoEvento(ID)) {
			System.out.println("ID ESISTENTE");
			risultato="ID esistente";
		}*/

		//return risultato;
	
	
	/*public boolean controlloIDIscritto() {
		return eDAOP.ControlloDinamicoEvento(Integer.parseInt(txtIDEVENTO.getText()));
	}
*/
	


	public void CalcoloEta() {
		LocalDate corrente=LocalDate.now();
		Date nascita=data.valueOf(txtDATA.getText());
		LocalDate LNascita=nascita.toLocalDate();
		System.out.println(LNascita.toString());

		System.out.println(corrente.toString());
		if ((corrente != null) && (data != null)) {
           System.out.println(Period.between(LNascita, corrente).getYears());
        }
	}






	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----RITORNA FALSO SE IL BOTTONE NON SI DEVE ATTIVARE
	//----PROVO A TOGLIERE TUTTI GLI ELSE IF E LI SOSTITUISCO CON DEGL'IF SEMPLICI
	public boolean controlloBottone() {
		boolean risultato=true ;
		//---------CONTROLLI COMPILAZIONE CAMPI EVENTO----------
		if (txtTIPO.getText().equals("") || txtDATA.getText().equals("")) {
			risultato=false;
			System.out.println("CAMPI CORSO INDISPENSABILI NON COMPILATI ");
		}
		//----CONTROLLO PRECEDENTE ESISTENZA ID EVENTO----
	/*	else if (eDAOP.ControlloDinamicoEvento(Integer.parseInt(txtIDEVENTO.getText()))) {
			risultato=false;
			System.out.println("ID EVENTO GIA ESISTENTE");
		}*/
		System.out.println("____________________________________________________________________________");

		return risultato;

	}
}
