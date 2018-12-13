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

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InserisciEvento extends JDialog {
	EventoDAOP eDAOP = new EventoDAOP();

	private JTextField txtIDEVENTO;
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
			InserisciEvento dialog = new InserisciEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciEvento() {
		setBounds(100, 100, 407, 312);
		getContentPane().setLayout(null);

		txtIDEVENTO = new JTextField();
		txtIDEVENTO.setBounds(82, 28, 116, 22);
		getContentPane().add(txtIDEVENTO);
		txtIDEVENTO.setColumns(10);

		txtDATA = new JTextField();
		
		txtDATA.setBounds(82, 85, 116, 22);
		getContentPane().add(txtDATA);
		txtDATA.setColumns(10);

		txtTIPO = new JTextField();
		
		txtTIPO.setBounds(261, 85, 116, 22);
		getContentPane().add(txtTIPO);
		txtTIPO.setColumns(10);

		JLabel lblIDEVENTO = new JLabel("Id evento");
		lblIDEVENTO.setBounds(14, 31, 56, 16);
		getContentPane().add(lblIDEVENTO);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(14, 88, 56, 16);
		getContentPane().add(lblData);

		JLabel lblTIPO = new JLabel("Tipo");
		lblTIPO.setBounds(210, 88, 56, 16);
		getContentPane().add(lblTIPO);

		JLabel lblLivello = new JLabel("Livello");
		lblLivello.setBounds(14, 147, 56, 16);
		getContentPane().add(lblLivello);

		JComboBox cbLIVELLO = new JComboBox();
		cbLIVELLO.setBounds(82, 144, 113, 22);
		cbLIVELLO.setModel(new DefaultComboBoxModel(new String[] {"PROVINCIALE", "REGIONALE", "NAZIONALE"}));
		getContentPane().add(cbLIVELLO);

		JLabel lblAvviso = new JLabel("avviso");
		lblAvviso.setBounds(228, 34, 56, 16);
		lblAvviso.setForeground(Color.RED);
		getContentPane().add(lblAvviso);

		JButton btnINSERISCI = new JButton("Inserisci");








		btnINSERISCI.addActionListener(new ActionListener() { //INSERIMENTO EVENTO
			public void actionPerformed(ActionEvent arg0) {
				idEvento = Integer.parseInt(txtIDEVENTO.getText()); //CAST DA STRING A INT
				data = data.valueOf(txtDATA.getText());
				livello = (String) cbLIVELLO.getModel().getElementAt(cbLIVELLO.getSelectedIndex());
				tipo = txtTIPO.getText();
				Evento e = new Evento(idEvento, data, livello, tipo);
				if (eDAOP.salvaEvento(e)) {
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					//---------------------------------------PARTE PER AZZERARE----------------------------------------------------
					txtDATA.setText("");
					txtIDEVENTO.setText("");
					txtTIPO.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");

				}
		});
		btnINSERISCI.setBounds(133, 195, 97, 25);
		getContentPane().add(btnINSERISCI);





		//---LISTENER----PER L'ATTIVAZIONE DEL BOTTONE PER IL CONTROLLO DEGLI ID-----
		txtIDEVENTO.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI AVVISI ID DUPLICATO---
				lblAvviso.setText(ControlloAvvisoEvento());
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				if(controlloBottone()==false) {
					btnINSERISCI.setEnabled(false);
					System.out.println("blocca ID");
				}
				else {
					btnINSERISCI.setEnabled(true);
					System.out.println("Sblocca ID");
				}
			}
		});
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
			}
		});



	}
	//CONTROLLO PER AVVISO ID DUPLICATO O ESISTENTE
	public String ControlloAvvisoEvento() {
		String risultato="";
		String IDE = txtIDEVENTO.getText();
		if(IDE.equals("")) {
			System.out.println("NON C'E NESSUN VALORE");
			return risultato;
		}
		Integer ID=Integer.parseInt(IDE);
		if(!IDE.equals("")&&eDAOP.ControlloDinamicoEvento(ID)) {
			System.out.println("ID ESISTENTE");
			risultato="ID esistente";
		}

		return risultato;
	}
	
	public boolean controlloIDIscritto() {
		return eDAOP.ControlloDinamicoEvento(Integer.parseInt(txtIDEVENTO.getText()));
	}










	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----RITORNA FALSO SE IL BOTTONE NON SI DEVE ATTIVARE
	//----PROVO A TOGLIERE TUTTI GLI ELSE IF E LI SOSTITUISCO CON DEGL'IF SEMPLICI
	public boolean controlloBottone() {
		boolean risultato=true ;
		//---------CONTROLLI COMPILAZIONE CAMPI EVENTO----------
		if (txtIDEVENTO.getText().equals("") || txtTIPO.getText().equals("") || txtDATA.getText().equals("")) {
			risultato=false;
			System.out.println("CAMPI CORSO INDISPENSABILI NON COMPILATI ");
		}
		//----CONTROLLO PRECEDENTE ESISTENZA ID EVENTO----
		else if (eDAOP.ControlloDinamicoEvento(Integer.parseInt(txtIDEVENTO.getText()))) {
			risultato=false;
			System.out.println("ID EVENTO GIA ESISTENTE");
		}
		System.out.println("____________________________________________________________________________");

		return risultato;

	}




}
