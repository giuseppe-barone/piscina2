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
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InserisciIscritto extends JDialog {
	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNOME;
	private JTextField txtCOGNOME;
	private JTextField txtETA;
	private JTextField txtCELLULARE;
	//-----VARIABILI ISCRITTO DA PASSARE ALLA QUERY------
	private int idIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private int eta;

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
		setAlwaysOnTop(true);
		setBounds(100, 100, 435, 267);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtID = new JTextField();
			txtID.setBounds(64, 16, 116, 22);
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
		txtNOME.setBounds(64, 51, 116, 22);
		contentPanel.add(txtNOME);
		txtNOME.setColumns(10);

		txtCOGNOME = new JTextField();
		txtCOGNOME.setBounds(293, 51, 116, 22);
		contentPanel.add(txtCOGNOME);
		txtCOGNOME.setColumns(10);

		txtETA = new JTextField();
		txtETA.setBounds(64, 88, 116, 22);
		contentPanel.add(txtETA);
		txtETA.setColumns(10);

		txtCELLULARE = new JTextField();
		txtCELLULARE.setBounds(293, 86, 116, 22);
		contentPanel.add(txtCELLULARE);
		txtCELLULARE.setColumns(10);

		JLabel lblETA = new JLabel("Et\u00E0:");
		lblETA.setBounds(12, 91, 56, 16);
		contentPanel.add(lblETA);

		JLabel lblCOGNOME = new JLabel("Cognome:");
		lblCOGNOME.setBounds(208, 54, 73, 16);
		contentPanel.add(lblCOGNOME);

		JLabel lblCELLULLARE = new JLabel("Cellulare");
		lblCELLULLARE.setBounds(208, 88, 73, 22);
		contentPanel.add(lblCELLULLARE);

		JLabel lblSESSO = new JLabel("Sesso:");
		lblSESSO.setBounds(12, 128, 56, 16);
		contentPanel.add(lblSESSO);

		JComboBox cbSESSO = new JComboBox();
		cbSESSO.setBounds(64, 125, 84, 22);
		cbSESSO.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbSESSO.setToolTipText("");
		contentPanel.add(cbSESSO);

		JLabel lblAvviso = new JLabel("pollo");
		lblAvviso.setForeground(Color.RED);
		lblAvviso.setBounds(219, 19, 174, 16);
		contentPanel.add(lblAvviso);

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
		txtETA.addCaretListener(new CaretListener() {
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
				eta = Integer.parseInt(txtETA.getText()); //CASTING DA STRING A INT
				Iscritto i = new Iscritto(idIscritto, nome, cognome, sesso, cellulare, eta);
				iDAOP.salvaIscritto(i);
			}
		});

		btnINSERISCI.setBounds(173, 177, 97, 25);
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
		if(txtID.getText().equals("")||txtNOME.getText().equals("")|| txtCOGNOME.getText().equals("")|| txtETA.getText().equals("") || iDAOP.ControlloDinamicoIdIscritto(Integer.parseInt(txtID.getText()))) {
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
}
