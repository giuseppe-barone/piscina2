package it.unirc.bd.gui.iscritto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class RicercaIscritti extends JDialog {
	//OGGETTI DAOP
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	
	
	//STRINGHE DA COMPORRE
	private String nome="";
	private String cognome="";
	private String sesso="";
	private String categoria="";
	private Date data;
	private boolean datamin;
	private boolean datamag;
	ButtonGroup gruppoData = new ButtonGroup();

	
	
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaIscritti dialog = new RicercaIscritti();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaIscritti() {
		setBounds(100, 100, 323, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(157, 42, 116, 22);
		contentPanel.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setBounds(157, 77, 116, 22);
		textCognome.setColumns(10);
		contentPanel.add(textCognome);
		
		JComboBox comboSesso = new JComboBox();
		comboSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		comboSesso.setBounds(157, 112, 116, 22);
		contentPanel.add(comboSesso);
		
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		comboCategoria.setBounds(157, 200, 116, 22);
		contentPanel.add(comboCategoria);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setEnabled(false);
		lblNome.setBounds(67, 52, 56, 16);
		contentPanel.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setEnabled(false);
		lblCognome.setBounds(67, 86, 56, 16);
		contentPanel.add(lblCognome);
		
		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setEnabled(false);
		lblSesso.setBounds(67, 115, 56, 16);
		contentPanel.add(lblSesso);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setEnabled(false);
		lblCategoria.setBounds(67, 202, 56, 16);
		contentPanel.add(lblCategoria);
		
		JLabel lblData = new JLabel("Data di nascita");
		lblData.setEnabled(false);
		lblData.setBounds(67, 148, 95, 16);
		contentPanel.add(lblData);
		
		JLabel lblSelezionaICriteri = new JLabel("Seleziona i criteri di ricerca");
		lblSelezionaICriteri.setBounds(12, 13, 181, 16);
		contentPanel.add(lblSelezionaICriteri);
		
		JCheckBox checkNome = new JCheckBox("");
		checkNome.setBounds(34, 48, 25, 25);
		contentPanel.add(checkNome);
		
		JCheckBox checkCognome = new JCheckBox("");
		checkCognome.setBounds(34, 82, 25, 25);
		contentPanel.add(checkCognome);
		
		JCheckBox checkSesso = new JCheckBox("");
		checkSesso.setBounds(34, 111, 25, 25);
		contentPanel.add(checkSesso);
		
		JCheckBox checkCategoria = new JCheckBox("");
		checkCategoria.setBounds(34, 199, 25, 25);
		contentPanel.add(checkCategoria);
		
		JCheckBox checkData = new JCheckBox("");
		checkData.setBounds(34, 144, 25, 25);
		contentPanel.add(checkData);
		
		JButton btnAvvia = new JButton("Avvia");
		btnAvvia.setBounds(157, 259, 97, 25);
		contentPanel.add(btnAvvia);
		
		JLabel lblTutti = new JLabel("Tutti");
		lblTutti.setEnabled(false);
		lblTutti.setBounds(67, 263, 95, 16);
		contentPanel.add(lblTutti);
		
		JCheckBox checkTutti = new JCheckBox("");
		checkTutti.setBounds(34, 260, 25, 25);
		contentPanel.add(checkTutti);
		
		JRadioButton radioMin = new JRadioButton("<");
		radioMin.setBounds(157, 173, 42, 25);
		contentPanel.add(radioMin);
		gruppoData.add(radioMin);

		
		JRadioButton radioUg = new JRadioButton("=");
		radioUg.setBounds(197, 173, 42, 25);
		contentPanel.add(radioUg);
		gruppoData.add(radioUg);
		
		JRadioButton radioMag = new JRadioButton(">");
		radioMag.setBounds(240, 173, 42, 25);
		contentPanel.add(radioMag);
		gruppoData.add(radioMag);
		
		textData = new JTextField();
		textData.setColumns(10);
		textData.setBounds(157, 143, 116, 22);
		contentPanel.add(textData);
		
		JLabel lblTuttiGliAtleti = new JLabel("Atleti");
		lblTuttiGliAtleti.setEnabled(false);
		lblTuttiGliAtleti.setBounds(67, 234, 95, 16);
		contentPanel.add(lblTuttiGliAtleti);
		
		JCheckBox checkAtleti = new JCheckBox("");
		checkAtleti.setBounds(34, 231, 25, 25);
		contentPanel.add(checkAtleti);

		
		//LISTNER
		btnAvvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				//MODELLO CHE PASSA I PARAMETRI AL DAOP CHE POI PROVVEDERà A FORMULARE LA QUERY
				if (checkNome.isSelected())
					nome=textNome.getText();
				else
					nome=null;
				if (checkCognome.isSelected())
					cognome=textCognome.getText();
				else
					cognome=null;
				if (checkSesso.isSelected())
					sesso=(String)comboSesso.getModel().getElementAt(comboSesso.getSelectedIndex()) ;
				else
					sesso=null;
				if (checkData.isSelected())
					data=Date.valueOf(textData.getText());
				else
					data=null;
				System.out.println(nome+" "+ cognome + " " +sesso+" " + data);
				if (checkCategoria.isSelected()) {
					categoria=(String)comboCategoria.getModel().getElementAt(comboCategoria.getSelectedIndex());
					iDAOP.RicercaPerCategoria(categoria);
				}
				if (checkAtleti.isSelected())
					iDAOP.getTuttiAtleti();
				if (checkTutti.isSelected())
					iDAOP.getAll();
				
				iDAOP.RicercaComposta(nome, cognome, sesso, data, datamin, datamag);
				
				
			}
		});
		
		
	}
}
