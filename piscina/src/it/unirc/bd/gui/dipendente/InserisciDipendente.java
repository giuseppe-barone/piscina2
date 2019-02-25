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

import it.unirc.bd.dao.beans.Allenatore;
import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.gui.InserimentoSuccesso;


import javax.swing.event.CaretEvent;

public class InserisciDipendente extends JDialog {
	DipendenteDAOP dDAOP = new DipendenteDAOP();



	private JTextField textIDDipendente;
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textCellulare;
	//---------VARIABILI DIPENDENTE DA PASSARE ALLE QUERY---------
	private int IDDipendente;
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
					InserisciDipendente dialog = new InserisciDipendente();
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
	 */
	public InserisciDipendente() {

		getContentPane().setEnabled(false);
		setTitle("Inserisci Dipendente");
		setBounds(100, 100, 410, 303);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 27, 17, 16);
		getContentPane().add(lblId);

		textIDDipendente = new JTextField();
		textIDDipendente.setText("0");

		textIDDipendente.setBounds(62, 24, 56, 22);
		getContentPane().add(textIDDipendente);
		textIDDipendente.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 67, 38, 16);
		getContentPane().add(lblNome);

		textNome = new JTextField();

		textNome.setBounds(62, 64, 116, 22);
		getContentPane().add(textNome);
		textNome.setColumns(10);

		textCognome = new JTextField();
		textCognome.setBounds(258, 64, 116, 22);
		textCognome.setColumns(10);
		getContentPane().add(textCognome);

		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(190, 67, 68, 16);
		getContentPane().add(lblCognome);

		JLabel lblNumeroDiCellulare = new JLabel("Numero di cellulare:");
		lblNumeroDiCellulare.setBounds(12, 111, 116, 16);
		getContentPane().add(lblNumeroDiCellulare);

		textCellulare = new JTextField();
		textCellulare.setBounds(142, 108, 116, 22);
		getContentPane().add(textCellulare);
		textCellulare.setColumns(10);

		JLabel lblSesso = new JLabel("Sesso:");
		lblSesso.setBounds(12, 157, 47, 16);
		getContentPane().add(lblSesso);

		JComboBox cbSesso = new JComboBox();
		cbSesso.setBounds(62, 154, 80, 22);
		cbSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbSesso.setToolTipText("");
		getContentPane().add(cbSesso);



		JLabel lblNewLabel = new JLabel("Tipologia Dipendente:");
		lblNewLabel.setBounds(150, 157, 125, 16);
		getContentPane().add(lblNewLabel);

		JComboBox cbTipoDipendente = new JComboBox();

		cbTipoDipendente.setBounds(282, 154, 92, 22);
		cbTipoDipendente.setModel(new DefaultComboBoxModel(new String[] {"Segretario", "Tecnico", "Allenatore"}));
		cbTipoDipendente.setToolTipText("");

		getContentPane().add(cbTipoDipendente);

		JButton btnInserisci = new JButton("Inserisci");

		btnInserisci.setEnabled(false);
		btnInserisci.setBounds(142, 209, 97, 25);
		getContentPane().add(btnInserisci);

		JLabel lblAvvisoD = new JLabel("");
		lblAvvisoD.setForeground(Color.RED);
		lblAvvisoD.setBounds(142, 27, 209, 16);
		getContentPane().add(lblAvvisoD);



		//---------LISTNER----------PER L'ATTIVAZIONE DEL BOTTONE E PER IL CONTROLLO DINAMICO DEGL'ID
		textIDDipendente.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//----CONTROLLI AVVISI ID DUPLICATO----
				lblAvvisoD.setText(ControlloAvvisoDipendente());
				//----CONTROLLI DI ABILITAZIONE BOTTONE----
				if (controllobottone()==false )
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		textNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//----CONTROLLI DI ABILITAZIONE BOTTONE----
				if (controllobottone()==false )
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});
		textCognome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//----CONTROLLI DI ABILITAZIONE BOTTONE----
				if (controllobottone()==false )
					btnInserisci.setEnabled(false);
				else
					btnInserisci.setEnabled(true);
			}
		});


		//------AQUISIZIONE VALORI DELLA VARIABILI DAI COMPONENTI E PASSAGGIO ALLE QUERY TRAMITE BOTTONE ----------------
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InserimentoSuccesso InsSuc= new InserimentoSuccesso();
				//AQUISIZIONE VALORI DIPENDENTE
				IDDipendente=Integer.parseInt(textIDDipendente.getText());	//CASTING DA STRING A INT
				Cellulare=textCellulare.getText();
				Nome=textNome.getText();
				Cognome=textCognome.getText();
				Sesso=(String) cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex());	//ATTENZIONE AL CASTING
				Tipologia=cbTipoDipendente.getSelectedIndex();
				Dipendente d = new Dipendente(IDDipendente, Nome, Cognome, Cellulare, Sesso, Tipologia);	//CREAZIONE OGETTO DA INSERIRE
				dDAOP.salvaDipendente(d);	//INSERIMENTO TUPLA
				InsSuc.setVisible(true);
			
				
				
				
			}
		});
	}


	//----CONTROLLO PER AVVISO DI ID DUPLICATO DIPENDENTE
	public String ControlloAvvisoDipendente() {
		String risultato="";
		String IDD = textIDDipendente.getText();
		int ID =Integer.parseInt(IDD);
		//if(IDD.equals(""))
			//return risultato;
		if (!IDD.equals("") && dDAOP.ControlloDinamicoIdDipendente(ID))
			risultato="ID esistente o non valido!";
		return risultato;
		
	}
	
	
	
	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----
	public boolean controllobottone() {
		boolean risultato=true ;
		//---------CONTROLLI LATO DIPENDENTE----------
		if (textIDDipendente.getText().equals("") || textNome.getText().equals("") || textCognome.getText().equals("") || dDAOP.ControlloDinamicoIdDipendente(Integer.parseInt(textIDDipendente.getText()))) {
			risultato=false;
			System.out.println("CAMPI DIPENDENTE NON COMPILATI O ID ESISTENTE");
		}
		else
			System.out.println("CAMPI DIPENDENTE COMPILATI");
		return risultato;

	}




	public boolean controlloIDDipendente () {
		return dDAOP.ControlloDinamicoIdDipendente(Integer.parseInt(textIDDipendente.getText()));
	}




}
