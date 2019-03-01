package it.unirc.bd.gui.iscritto;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
	ButtonGroup gruppoTipoRicerca = new ButtonGroup();

	
	
	

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
		setTitle("Ricerca");
		setResizable(false);
		setBounds(100, 100, 372, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		comboCategoria.setBounds(125, 236, 116, 22);
		contentPanel.add(comboCategoria);
		
		JLabel lblSelezionaICriteri = new JLabel("Seleziona i criteri di ricerca");
		lblSelezionaICriteri.setBounds(12, 13, 181, 16);
		contentPanel.add(lblSelezionaICriteri);
		
		JButton btnAvvia = new JButton("Cerca");
		btnAvvia.setBounds(257, 319, 97, 25);
		contentPanel.add(btnAvvia);
		
		JRadioButton radioGeneralita = new JRadioButton("");
		
		radioGeneralita.setBounds(22, 40, 24, 25);
		contentPanel.add(radioGeneralita);
		gruppoTipoRicerca.add(radioGeneralita);
		
		JPanel panelGeneralita = new JPanel();
		panelGeneralita.setBorder(new TitledBorder(null, "Generalit\u00E0", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGeneralita.setBounds(45, 41, 277, 185);
		contentPanel.add(panelGeneralita);
		panelGeneralita.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBounds(141, 25, 116, 22);
		panelGeneralita.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(51, 35, 56, 16);
		panelGeneralita.add(lblNome);
		
		JCheckBox checkNome = new JCheckBox("");
		checkNome.setBounds(18, 31, 25, 25);
		panelGeneralita.add(checkNome);
		
		JCheckBox checkCognome = new JCheckBox("");
		checkCognome.setBounds(18, 65, 25, 25);
		panelGeneralita.add(checkCognome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(51, 69, 56, 16);
		panelGeneralita.add(lblCognome);
		
		textCognome = new JTextField();
		textCognome.setBounds(141, 60, 116, 22);
		panelGeneralita.add(textCognome);
		textCognome.setColumns(10);
		
		JComboBox comboSesso = new JComboBox();
		comboSesso.setBounds(141, 95, 116, 22);
		panelGeneralita.add(comboSesso);
		comboSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		
		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setBounds(51, 98, 56, 16);
		panelGeneralita.add(lblSesso);
		
		JCheckBox checkSesso = new JCheckBox("");
		checkSesso.setBounds(18, 94, 25, 25);
		panelGeneralita.add(checkSesso);
		
		JCheckBox checkData = new JCheckBox("");
		checkData.setBounds(18, 127, 25, 25);
		panelGeneralita.add(checkData);
		
		JLabel lblData = new JLabel("Data di nascita");
		lblData.setBounds(51, 131, 95, 16);
		panelGeneralita.add(lblData);
		
		textData = new JTextField();
		textData.setBounds(141, 126, 116, 22);
		panelGeneralita.add(textData);
		textData.setColumns(10);
		
				
				JRadioButton radioUg = new JRadioButton("=");
				radioUg.setBounds(181, 156, 42, 25);
				panelGeneralita.add(radioUg);
				gruppoData.add(radioUg);
				
				JRadioButton radioMin = new JRadioButton("<");
				radioMin.setBounds(141, 156, 42, 25);
				panelGeneralita.add(radioMin);
				gruppoData.add(radioMin);
				
				JRadioButton radioMag = new JRadioButton(">");
				radioMag.setBounds(224, 156, 42, 25);
				panelGeneralita.add(radioMag);
				gruppoData.add(radioMag);
				
				JRadioButton radioCategoria = new JRadioButton("Categoria");
				radioCategoria.setBounds(22, 235, 95, 25);
				contentPanel.add(radioCategoria);
				gruppoTipoRicerca.add(radioCategoria);
				
				JRadioButton radioAtleti = new JRadioButton("Atleti");
				radioAtleti.setBounds(22, 271, 95, 25);
				contentPanel.add(radioAtleti);
				gruppoTipoRicerca.add(radioAtleti);
				
				JRadioButton radioTutti = new JRadioButton("Tutti");
				radioTutti.setBounds(22, 301, 95, 25);
				contentPanel.add(radioTutti);
				gruppoTipoRicerca.add(radioTutti);
				
				Component[] componenti = panelGeneralita.getComponents();
		
		//LISTNER
				radioGeneralita.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						if (radioGeneralita.isSelected()) {
							for (Component c : componenti)
								c.setEnabled(true);
						}
						else {
							for (Component c : componenti)
								c.setEnabled(false);
						}
							
					}
				});
				
				
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
				if (radioCategoria.isSelected()) {
					categoria=(String)comboCategoria.getModel().getElementAt(comboCategoria.getSelectedIndex());
					iDAOP.RicercaPerCategoria(categoria);
				}
				if (radioAtleti.isSelected())
					iDAOP.getTuttiAtleti();
				if (radioTutti.isSelected())
					iDAOP.getAll();
				
				iDAOP.RicercaComposta(nome, cognome, sesso, data, datamin, datamag);
				
				
			}
		});
		
		
	}
}
