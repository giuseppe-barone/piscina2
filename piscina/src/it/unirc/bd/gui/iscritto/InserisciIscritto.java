package it.unirc.bd.gui.iscritto;

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

import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class InserisciIscritto extends JDialog {
	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCellulare;
	private JTextField txtMatricolaFin;
	//-----VARIABILI ISCRITTO DA PASSARE ALLA QUERY------
	private int idIscritto=0;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private Date dataNascita;
	private Integer matricolaFIN;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciIscritto dialog = new InserisciIscritto(false, null); //METTO FALSE PERCHè NEL TESTING NON VOGLIO APRIRE LA FINESTRA IN MODALITà MODIFICA ISCRITTO PER QUESTO MOTIVO NON PASSO ALCUN OGETTO ISCRITTO
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @return 
	 */
	//Inserendo il parametro Modifica posso utilizzare la stessa finestra per la modifica e non solo per l'inserimento facendo apparire l'apposito tasto MODIFICA
	//Inserendo il parametro Iscritto ho la possibilità di prelevare dall'ogetto iscritto i parametri modificabili e farli visualizzare
	//negli appositi box per facilitarne la modifica da parte dell'utente
	public InserisciIscritto(boolean Modifica, Iscritto iscritto) {
		setModal(true); 
		setResizable(false);
		setTitle("Inserisci");
		setAlwaysOnTop(true);
		setBounds(100, 100, 456, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnModifica = new JButton("Modifica");
		btnModifica.setVisible(false);

		btnModifica.setBounds(286, 129, 97, 25);
		contentPanel.add(btnModifica);


		JLabel lblNOME = new JLabel("Nome:");
		lblNOME.setBounds(12, 16, 56, 16);
		contentPanel.add(lblNOME);

		txtNome = new JTextField();
		txtNome.setBounds(108, 13, 116, 22);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		txtCognome = new JTextField();
		txtCognome.setBounds(307, 13, 116, 22);
		contentPanel.add(txtCognome);
		txtCognome.setColumns(10);

		txtCellulare = new JTextField();
		txtCellulare.setBounds(307, 50, 116, 22);
		contentPanel.add(txtCellulare);
		txtCellulare.setColumns(10);

		txtMatricolaFin = new JTextField();
		
		txtMatricolaFin.setToolTipText("Lasciare vuoto se non \u00E8 un'atleta");
		txtMatricolaFin.setBounds(108, 87, 116, 22);
		contentPanel.add(txtMatricolaFin);
		txtMatricolaFin.setColumns(10);

		JLabel lblDATA = new JLabel("Data Nascita:");
		lblDATA.setBounds(12, 53, 84, 16);
		contentPanel.add(lblDATA);

		JLabel lblCOGNOME = new JLabel("Cognome:");
		lblCOGNOME.setBounds(238, 16, 73, 16);
		contentPanel.add(lblCOGNOME);

		JLabel lblCELLULLARE = new JLabel("Cellulare");
		lblCELLULLARE.setBounds(238, 50, 73, 22);
		contentPanel.add(lblCELLULLARE);

		JLabel lblSESSO = new JLabel("Sesso:");
		lblSESSO.setBounds(236, 90, 56, 16);
		contentPanel.add(lblSESSO);

		JComboBox cbSesso = new JComboBox();
		cbSesso.setBounds(307, 85, 84, 22);
		cbSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		if (Modifica)
			cbSesso.setSelectedItem(iscritto.getSesso());
		cbSesso.setToolTipText("");
		contentPanel.add(cbSesso);

		JLabel lblMatricolaFin = new JLabel("Matricola FIN");
		lblMatricolaFin.setBounds(12, 90, 84, 16);
		contentPanel.add(lblMatricolaFin);

		JButton btnInserisci = new JButton("Inserisci");
		

		JDateChooser campoData = new JDateChooser();
		campoData.setBounds(108, 47, 116, 22);
		contentPanel.add(campoData);


		btnInserisci.addActionListener(new ActionListener() { //INSERIMENTO ISCRITTO
			public void actionPerformed(ActionEvent arg0) { 
				nome = txtNome.getText();
				cognome = txtCognome.getText();
				sesso=(String) cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex());	//CASTING
				cellulare = txtCellulare.getText();

				
				if (controlloCampiOperazione(nome,cognome, campoData.getDate())==0){
				
				
				java.util.Date utilDate = campoData.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				dataNascita=sqlDate;
				//dataNascita = Date.valueOf(txtData.getText());	QUESTA CODICE PRENDE LA DATA DAL TEXTBOX
				System.out.println("acquisisco: IDISCRITTO,NOME, COGNOME, SESSO, CELLULARE, DATADINASCITA");
				//----CONTROLLO PER LA PRESENZA DELLA MATRICOLA FIN
				if(txtMatricolaFin.getText().equals("")||txtMatricolaFin.getText().equals(null)) 
					matricolaFIN=null;

				else 
					matricolaFIN = Integer.parseInt(txtMatricolaFin.getText());
				//System.out.println(Integer.toString(matricolaFIN));
				Iscritto i = new Iscritto(idIscritto, nome, cognome, sesso, cellulare, dataNascita, matricolaFIN);
				System.out.println("-----------------ddddddddddddddddddddddddddd--------------------------------------------");
				if (iDAOP.salvaIscritto(i)) {
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					//---------------------------------------PARTE PER AZZERARE----------------------------------------------------

					txtNome.setText("");
					txtCognome.setText("");
					txtCellulare.setText("");
					txtMatricolaFin.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
				}
				
				
				
				
			}
		});

		btnInserisci.setBounds(177, 129, 97, 25);
		contentPanel.add(btnInserisci);


		btnInserisci.setVisible(false);



		if (Modifica==true) {
			btnModifica.setVisible(true);
			btnInserisci.setVisible(false);
			btnModifica.setEnabled(true);
			
		}
		else{
			btnModifica.setVisible(false);
			btnInserisci.setVisible(true);
			btnModifica.setEnabled(false);
		}

		//MECCANISMO DI MODIFICA
		if (Modifica) {
			txtNome.setText(iscritto.getNome());
			txtCognome.setText(iscritto.getCognome());
			setTitle("Modifica");
			campoData.setDate(iscritto.getDataNascita());
			txtCellulare.setText(iscritto.getCellulare());
			txtMatricolaFin.setText(iscritto.getMatricolaFIN().toString());
		}

		//LISTNER MODIFICA

		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nome = txtNome.getText();
				cognome = txtCognome.getText();
				sesso=(String) cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex());	//CASTING
				cellulare = txtCellulare.getText();
				
				
				
				if (controlloCampiOperazione(nome,cognome, campoData.getDate())==0){
				
				java.util.Date utilDate = campoData.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				dataNascita=sqlDate;
				if (txtMatricolaFin.getText().equals(null))
					matricolaFIN=null;
				else
					matricolaFIN=Integer.valueOf(txtMatricolaFin.getText());
				Iscritto i = new Iscritto(iscritto.getIdIscritto(),nome, cognome, sesso, cellulare,  dataNascita,matricolaFIN);
				if (iDAOP.ModificaIscritto(i)) 
					JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
				else
					JOptionPane.showMessageDialog(null, "MODIFICA NON RIUSCITA");				
				}
			}
		});



	}

	public boolean controlloBottone() {
		boolean risultato=true;
		//---CONTROLLI COMPILAZIONE CORSO---
		if(txtNome.getText().equals("")|| txtCognome.getText().equals("") ) {
			risultato=false;
			System.out.println("CAMPI ISCRITTO NON COMPILATI O ID ESISTENTE");
		}
		else
			System.out.println("CAMPI ISCRITTO COMPILATI");
		return risultato;
	}

	/*
	public void CalcoloEta() {	//CODICE NON UTILIZATO IN QUESTO SCRIPT, è STATO SOLO SALVATO QUI MA UTILIZZATO IN UN AL'TRO SCRIPT
		LocalDate corrente=LocalDate.now();
		Date nascita=dataNascita;
		LocalDate LNascita=nascita.toLocalDate();
		System.out.println(LNascita.toString());

		System.out.println(corrente.toString());
		if ((corrente != null) && (dataNascita != null)) {
			System.out.println(Period.between(LNascita, corrente).getYears());
		}
	}*/






	//METODO PER IL CONTROLLO DEI CAMPI BISOGNA PASSARE NELLA FIRMA I CAMPI CHE SI VOGLIONO CONTROLLARE (SI POSSONO AGGIUNGERE ANCHE I CAMPI NON OBBLIGATORI SE SI VOGLIONO INSERIE NEL MESSAGGIO DI RIEPILOGO)
	public int controlloCampiOperazione(String nome, String cognome, java.util.Date data) {	//QUESTO METODO RITORNA 0 SE è CONSENTITO PROCEDERE CON LA QUERY ALTRIMENTI NON DA IL CONSENSO A MANDARE LA QUERY
		int result ;
		if (nome.equals("") || cognome.equals("") || data.equals(null)) {	//SE NON SONO STATI COMPILATI TORNA UN VALORE
			result=1;
			JOptionPane.showMessageDialog(null, "NON SONO STATI COMPILATI TUTTI I CAMPI OBBLIGATORI \n Data di nascita \n Nome \n Cognome");
		}
		else {	 //TUTTI I CAMPI SONO STATI COMPILATI, MESSAGGIO DI RIEPILOGO E CONFERMA
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			dataNascita=sqlDate;
		    result= JOptionPane.showConfirmDialog (null, "I dati obbligatori da te inseriti sono:\n Nome: "+nome+";\n Cognome: "+cognome+";\n Data di nascita: "+dataNascita.toString(),"RIEPILOGO",JOptionPane.YES_NO_OPTION);
		}
		System.out.println("il valore della selezione è: "+Integer.toString(result));
		return result;
	}







}
