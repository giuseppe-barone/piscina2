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

public class InserisciIscritto extends JDialog {
	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNOME;
	private JTextField txtCOGNOME;
	private JTextField txtDATA;
	private JTextField txtCELLULARE;
	private JTextField txtMATRICOLAFIN;
	//-----VARIABILI ISCRITTO DA PASSARE ALLA QUERY------
	private int idIscritto;
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
			InserisciIscritto dialog = new InserisciIscritto();
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
	public InserisciIscritto() {
		setResizable(false);
		setTitle("Inserisci");
		setAlwaysOnTop(true);
		setBounds(100, 100, 456, 273);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtID = new JTextField();
			txtID.setBounds(108, 16, 116, 22);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}

		JLabel lblID = new JLabel("ID: ");
		lblID.setBounds(12, 18, 56, 19);
		contentPanel.add(lblID);

		JLabel lblNOME = new JLabel("Nome:");
		lblNOME.setBounds(12, 54, 56, 16);
		contentPanel.add(lblNOME);

		txtNOME = new JTextField();
		txtNOME.setBounds(108, 51, 116, 22);
		contentPanel.add(txtNOME);
		txtNOME.setColumns(10);

		txtCOGNOME = new JTextField();
		txtCOGNOME.setBounds(307, 51, 116, 22);
		contentPanel.add(txtCOGNOME);
		txtCOGNOME.setColumns(10);

		txtDATA = new JTextField();
		txtDATA.setBounds(108, 88, 116, 22);
		contentPanel.add(txtDATA);
		txtDATA.setColumns(10);

		txtCELLULARE = new JTextField();
		txtCELLULARE.setBounds(307, 88, 116, 22);
		contentPanel.add(txtCELLULARE);
		txtCELLULARE.setColumns(10);

		txtMATRICOLAFIN = new JTextField();
		txtMATRICOLAFIN.setBounds(108, 125, 116, 22);
		contentPanel.add(txtMATRICOLAFIN);
		txtMATRICOLAFIN.setColumns(10);

		JLabel lblDATA = new JLabel("Data Nascita:");
		lblDATA.setBounds(12, 91, 84, 16);
		contentPanel.add(lblDATA);

		JLabel lblCOGNOME = new JLabel("Cognome:");
		lblCOGNOME.setBounds(238, 54, 73, 16);
		contentPanel.add(lblCOGNOME);

		JLabel lblCELLULLARE = new JLabel("Cellulare");
		lblCELLULLARE.setBounds(238, 88, 73, 22);
		contentPanel.add(lblCELLULLARE);

		JLabel lblSESSO = new JLabel("Sesso:");
		lblSESSO.setBounds(236, 128, 56, 16);
		contentPanel.add(lblSESSO);

		JComboBox cbSESSO = new JComboBox();
		cbSESSO.setBounds(307, 123, 84, 22);
		cbSESSO.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbSESSO.setToolTipText("");
		contentPanel.add(cbSESSO);

		JLabel lblAvviso = new JLabel("avviso");
		lblAvviso.setForeground(Color.RED);
		lblAvviso.setBounds(254, 19, 174, 16);
		contentPanel.add(lblAvviso);

		JLabel lblMatricolaFin = new JLabel("Matricola FIN");
		lblMatricolaFin.setBounds(12, 128, 84, 16);
		contentPanel.add(lblMatricolaFin);

		JButton btnINSERISCI = new JButton("Inserisci");
		btnINSERISCI.setEnabled(false);

		//---LISTENER----PER L'ATTIVAZIONE DEL BOTTONE PER IL CONTROLLO DEGLI ID-----
		txtID.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI AVVISI ID DUPLICATO---
				lblAvviso.setText(ControlloAvvisoIscritto());
				//---CONTROLLI DI ABILITAZIONE BOTTONE---
				if(controlloBottone()==false)
					btnINSERISCI.setEnabled(false);
				else
					btnINSERISCI.setEnabled(true);
			}
		});
		txtNOME.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI ABILITAZIONE BOTTONE----
				if(controlloBottone()==false)
					btnINSERISCI.setEnabled(false);
				else
					btnINSERISCI.setEnabled(true);
			}
		});
		txtCOGNOME.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI ABILITAZIONE BOTTONE----
				if(controlloBottone()==false)
					btnINSERISCI.setEnabled(false);
				else
					btnINSERISCI.setEnabled(true);
			}
		});
		txtDATA.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//---CONTROLLI ABILITAZIONE BOTTONE----
				if(controlloBottone()==false)
					btnINSERISCI.setEnabled(false);
				else
					btnINSERISCI.setEnabled(true);
			}
		});



		btnINSERISCI.addActionListener(new ActionListener() { //INSERIMENTO ISCRITTO
			public void actionPerformed(ActionEvent arg0) {
				idIscritto=Integer.parseInt(txtID.getText()); //CASTING DA STRING A INT
				nome = txtNOME.getText();
				cognome = txtCOGNOME.getText();
				sesso=(String) cbSESSO.getModel().getElementAt(cbSESSO.getSelectedIndex());	//ATTENZIONE AL CASTING
				cellulare = txtCELLULARE.getText();
				dataNascita = dataNascita.valueOf(txtDATA.getText());
				System.out.println("acquisisco: IDISCRITTO,NOME, COGNOME, SESSO, CELLULARE, DATADINASCITA");
				//----CONTROLLO PER LA PRESENZA DELLA MATRICOLA FIN
				if(txtMATRICOLAFIN.getText().equals("")||txtMATRICOLAFIN.getText().equals(null))
					matricolaFIN=null;
				
				else
					matricolaFIN = Integer.parseInt(txtMATRICOLAFIN.getText());
				//System.out.println(Integer.toString(matricolaFIN));
				Iscritto i = new Iscritto(idIscritto, nome, cognome, sesso, cellulare, dataNascita, matricolaFIN);
				System.out.println("-----------------ddddddddddddddddddddddddddd--------------------------------------------");
				if (iDAOP.salvaIscritto(i)) {
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					//---------------------------------------PARTE PER AZZERARE----------------------------------------------------
					txtDATA.setText("");
					txtID.setText("");
					txtNOME.setText("");
					txtCOGNOME.setText("");
					txtCELLULARE.setText("");
					txtMATRICOLAFIN.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");

			}
		});

		btnINSERISCI.setBounds(172, 175, 97, 25);
		contentPanel.add(btnINSERISCI);





	}
	//---CONTROLLO PER AVVISO DI ID DUPLICATO O ESISTENTE
	public String ControlloAvvisoIscritto() {
		String risultato="";
		String IDI = txtID.getText();
		if(IDI.equals("")) {
			System.out.println("NON C'E NESSUN VALORE");
			return risultato;
		}
		Integer ID=Integer.parseInt(IDI);
		if(!IDI.equals("")&&iDAOP.ControlloDinamicoIdIscritto(ID)) {
			System.out.println("ID ESISTENTE");
			risultato="ID esistente";
		}

		return risultato;
	}
	public boolean controlloBottone() {
		boolean risultato=true;
		//---CONTROLLI COMPILAZIONE CORSO---
		if(txtID.getText().equals("")||txtNOME.getText().equals("")|| txtCOGNOME.getText().equals("")|| txtDATA.getText().equals("") || iDAOP.ControlloDinamicoIdIscritto(Integer.parseInt(txtID.getText()))) {
			risultato=false;
			System.out.println("CAMPI ISCRITTO NON COMPILATI O ID ESISTENTE");
		}
		else
			System.out.println("CAMPI ISCRITTO COMPILATI");
		return risultato;
	}
	public boolean controlloIDIscritto() {
		return iDAOP.ControlloDinamicoIdIscritto(Integer.parseInt(txtID.getText()));
	}
	
	public void CalcoloEta() {
		LocalDate corrente=LocalDate.now();
		Date nascita=dataNascita.valueOf(txtDATA.getText());
		LocalDate LNascita=nascita.toLocalDate();
		System.out.println(LNascita.toString());

		System.out.println(corrente.toString());
		if ((corrente != null) && (dataNascita != null)) {
           System.out.println(Period.between(LNascita, corrente).getYears());
        }
	}
}
