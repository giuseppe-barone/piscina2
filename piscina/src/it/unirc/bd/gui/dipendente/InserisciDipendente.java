package it.unirc.bd.gui.dipendente;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.gui.InserimentoSuccesso;


import javax.swing.event.CaretEvent;

public class InserisciDipendente extends JDialog {
	DipendenteDAOP dDAOP = new DipendenteDAOP();
	//private Dipendente d = new Dipendente();
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textCellulare;
	//---------VARIABILI DIPENDENTE DA PASSARE ALLE QUERY---------
	private Integer IdDipendente;
	private String Nome;
	private String Cognome;
	private String Cellulare;
	private String Sesso;
	private int Tipologia;
	//---------VARIABILI ALLENATORE DA PASSARE ALLE QUERY--------






	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciDipendente dialog = new InserisciDipendente(false, null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @param dipendente 
	 * @param modifica 
	 */
	public InserisciDipendente(boolean modifica, Dipendente dipendente) {

		getContentPane().setEnabled(false);
		setTitle("Inserisci Dipendente");
		setBounds(100, 100, 410, 255);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 30, 38, 16);
		getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(62, 27, 116, 22);
		getContentPane().add(textNome);
		textNome.setColumns(10);

		textCognome = new JTextField();
		textCognome.setBounds(258, 27, 116, 22);
		textCognome.setColumns(10);
		getContentPane().add(textCognome);

		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(190, 30, 68, 16);
		getContentPane().add(lblCognome);

		JLabel lblNumeroDiCellulare = new JLabel("Numero di cellulare:");
		lblNumeroDiCellulare.setBounds(12, 74, 116, 16);
		getContentPane().add(lblNumeroDiCellulare);

		textCellulare = new JTextField();
		textCellulare.setBounds(142, 71, 116, 22);
		getContentPane().add(textCellulare);
		textCellulare.setColumns(10);

		JLabel lblSesso = new JLabel("Sesso:");
		lblSesso.setBounds(12, 120, 47, 16);
		getContentPane().add(lblSesso);

		JComboBox cbSesso = new JComboBox();
		cbSesso.setBounds(62, 117, 80, 22);
		cbSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbSesso.setToolTipText("");
		getContentPane().add(cbSesso);



		JLabel lblNewLabel = new JLabel("Tipologia Dipendente:");
		lblNewLabel.setBounds(150, 120, 125, 16);
		getContentPane().add(lblNewLabel);

		JComboBox cbTipoDipendente = new JComboBox();

		cbTipoDipendente.setBounds(282, 117, 92, 22);
		cbTipoDipendente.setModel(new DefaultComboBoxModel(new String[] {"Segretario", "Tecnico", "Allenatore"}));
		cbTipoDipendente.setToolTipText("");

		getContentPane().add(cbTipoDipendente);

		JButton btnInserisci = new JButton("Inserisci");

		btnInserisci.setEnabled(true);
		btnInserisci.setBounds(142, 172, 97, 25);
		getContentPane().add(btnInserisci);

		JLabel lblAvvisoD = new JLabel("");
		lblAvvisoD.setForeground(Color.RED);
		lblAvvisoD.setBounds(142, 27, 209, 16);
		getContentPane().add(lblAvvisoD);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//AQUISIZIONE VALORI DIPENDENTE
				Cellulare=textCellulare.getText();
				Nome=textNome.getText();
				Cognome=textCognome.getText();
				Sesso=(String) cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex());	//ATTENZIONE AL CASTING
				Tipologia=cbTipoDipendente.getSelectedIndex();
				System.out.println(Cellulare+" "+Nome+" "+Cognome+" "+Sesso+" "+Tipologia);


				if (controlloCampiOperazione(Nome, Cognome, Tipologia)==0) {
					Dipendente dip=new Dipendente();
					dip.setCellulare(Cellulare);
					dip.setNome(Nome);
					dip.setIdDipendente(dipendente.getIdDipendente());
					dip.setCognome(Cognome);
					dip.setSesso(Sesso);
					dip.setTipologiaDipendente(Tipologia); 

					//Dipendente d = new Dipendente( IdDipendente, Nome, Cognome, Cellulare, Sesso, Tipologia);	
					if(dDAOP.ModificaIscritto(dip))	//INSERIMENTO TUPLA
						JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
					else
						JOptionPane.showMessageDialog(null, "MODIFICA NON RIUSCITA");
				
				
				
				}

				
			}
		});
		btnModifica.setEnabled(true);
		btnModifica.setBounds(254, 172, 97, 25);
		getContentPane().add(btnModifica);
		
		//ABILITAZIONE BOTTONE MODIFICA O INSERISCI
		if (modifica) {
			btnInserisci.setVisible(false);
			btnModifica.setVisible(true);
			textNome.setText(dipendente.getNome());
			textCognome.setText(dipendente.getCognome());
			textCellulare.setText(dipendente.getCellulare());
		}
		else {
			btnInserisci.setVisible(true);
			btnModifica.setVisible(false);
		}
		
		
		
		
		/*textNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//----CONTROLLI DI ABILITAZIONE BOTTONE----
				if (controllobottone()==false )
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});*/
		/*textCognome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//----CONTROLLI DI ABILITAZIONE BOTTONE----
				if (controllobottone()==false )
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});*/


		//------AQUISIZIONE VALORI DELLA VARIABILI DAI COMPONENTI E PASSAGGIO ALLE QUERY TRAMITE BOTTONE ----------------
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//AQUISIZIONE VALORI DIPENDENTE
				Cellulare=textCellulare.getText();
				Nome=textNome.getText();
				Cognome=textCognome.getText();
				Sesso=(String) cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex());	//ATTENZIONE AL CASTING
				Tipologia=cbTipoDipendente.getSelectedIndex();
				System.out.println(Cellulare+" "+Nome+" "+Cognome+" "+Sesso+" "+Tipologia);


				if (controlloCampiOperazione(Nome, Cognome, Tipologia)==0) {
					Dipendente dip=new Dipendente();
					dip.setCellulare(Cellulare);
					dip.setNome(Nome);
					dip.setCognome(Cognome);
					dip.setSesso(Sesso);
					dip.setTipologiaDipendente(Tipologia); 

					//Dipendente d = new Dipendente( IdDipendente, Nome, Cognome, Cellulare, Sesso, Tipologia);	
					if(dDAOP.salvaDipendente(dip))	//INSERIMENTO TUPLA
						JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					else
						JOptionPane.showMessageDialog(null, "INSERIMENTO NON RIUSCITO");
				
				
				
				}




			}
		});
	}








	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----
	/*	public boolean controllobottone() {
		boolean risultato=true ;
		//---------CONTROLLI LATO DIPENDENTE----------
		if (textNome.getText().equals("") || textCognome.getText().equals("")) {
			risultato=false;
			System.out.println("CAMPI DIPENDENTE NON COMPILATI O ID ESISTENTE");
		}
		else
			System.out.println("CAMPI DIPENDENTE COMPILATI");
		return risultato;

	}
	 */



	public String convertTipo(int tipo) {
		String result;
		if (tipo==0)
			result="Segretario";
		else if (tipo==1)
			result="Tecnico";
		else
			result="Allenatore";
		return result;
	}


	//METODO PER IL CONTROLLO DEI CAMPI BISOGNA PASSARE NELLA FIRMA I CAMPI CHE SI VOGLIONO CONTROLLARE (SI POSSONO AGGIUNGERE ANCHE I CAMPI NON OBBLIGATORI SE SI VOGLIONO INSERIE NEL MESSAGGIO DI RIEPILOGO)
	public int controlloCampiOperazione(String nome, String cognome, int tipo) {	//QUESTO METODO RITORNA 0 SE è CONSENTITO PROCEDERE CON LA QUERY ALTRIMENTI NON DA IL CONSENSO A MANDARE LA QUERY
		int result ;
		if (nome.equals("") || cognome.equals("")) {	//SE NON SONO STATI COMPILATI TORNA UN VALORE
			result=1;
			JOptionPane.showMessageDialog(null, "NON SONO STATI COMPILATI TUTTI I CAMPI OBBLIGATORI \n Nome \n Cognome");
		}
		else {	 //TUTTI I CAMPI SONO STATI COMPILATI, MESSAGGIO DI RIEPILOGO E CONFERMA
			result= JOptionPane.showConfirmDialog (null, "I dati fondamentali da te inseriti sono:\n Nome: "+nome+";\n Cognome: "+cognome+";\n Tipologia: "+convertTipo(tipo),"RIEPILOGO",JOptionPane.YES_NO_OPTION);
		}
		System.out.println("il valore della selezione è: "+Integer.toString(result));
		return result;
	}
}
