package it.unirc.bd.gui.dipendente;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class RicercaDipendente extends JDialog {
	private JTextField txtNome;
	private JTextField txtCognome;

	DipendenteDAOP dDAOP = new DipendenteDAOP();
	
	private String nome="";
	private String cognome="";
	private String sesso="";
	private String tipologia="";
	ButtonGroup TipoRicerca = new ButtonGroup();
	ButtonGroup Sesso = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaDipendente dialog = new RicercaDipendente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaDipendente() {
		setTitle("Ricerca");
		setBounds(100, 100, 427, 300);
		getContentPane().setLayout(null);

		JPanel PannelloGeneralita = new JPanel();
		PannelloGeneralita.setBorder(new TitledBorder(null, "Generalit\u00E0", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PannelloGeneralita.setBounds(117, 13, 275, 123);
		getContentPane().add(PannelloGeneralita);
		PannelloGeneralita.setLayout(null);


		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(65, 31, 43, 16);
		PannelloGeneralita.add(lblNome);
		lblNome.setEnabled(false);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(65, 60, 61, 16);
		PannelloGeneralita.add(lblCognome);
		lblCognome.setEnabled(false);

		txtNome = new JTextField();
		txtNome.setBounds(135, 28, 116, 22);
		PannelloGeneralita.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setEnabled(false);

		txtCognome = new JTextField();
		txtCognome.setBounds(135, 57, 116, 22);
		PannelloGeneralita.add(txtCognome);
		txtCognome.setColumns(10);
		txtCognome.setEnabled(false);

		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setBounds(65, 94, 56, 16);
		PannelloGeneralita.add(lblSesso);
		lblSesso.setEnabled(false);
		
		JCheckBox checkNome = new JCheckBox("");
		checkNome.setBounds(13, 27, 30, 25);
		PannelloGeneralita.add(checkNome);
		
		JCheckBox checkCognome = new JCheckBox("");
		checkCognome.setBounds(13, 56, 30, 25);
		PannelloGeneralita.add(checkCognome);
		
		JCheckBox checkSesso = new JCheckBox("");
		checkSesso.setBounds(13, 90, 22, 25);
		PannelloGeneralita.add(checkSesso);
		
		JComboBox cbSesso = new JComboBox();
		cbSesso.setBounds(135, 91, 82, 22);
		cbSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		PannelloGeneralita.add(cbSesso);

		JRadioButton rdbtnGeneralit = new JRadioButton("Generalit\u00E0");
		rdbtnGeneralit.setBounds(8, 9, 97, 25);
		getContentPane().add(rdbtnGeneralit);
		TipoRicerca.add(rdbtnGeneralit);

		JRadioButton rdbtnTipo = new JRadioButton("Tipologia Dipendente");
		rdbtnTipo.setBounds(8, 148, 149, 25);
		getContentPane().add(rdbtnTipo);
		TipoRicerca.add(rdbtnTipo);

		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(161, 149, 120, 22);
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Segretario", "Tecnico", "Allenatore"}));
		getContentPane().add(cbTipo);
		cbTipo.setEnabled(false);

		JRadioButton rdbtnTutti = new JRadioButton("Tutti");
		rdbtnTutti.setBounds(8, 193, 69, 25);
		getContentPane().add(rdbtnTutti);
		TipoRicerca.add(rdbtnTutti);

		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.setBounds(144, 219, 97, 25);
		getContentPane().add(btnRicerca);

		Component[] generalita = PannelloGeneralita.getComponents(); 

		//--------LISTENER
		rdbtnGeneralit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnGeneralit.isSelected()) {
					for (Component g: generalita)
						g.setEnabled(true);
				}
				else {
					for (Component g : generalita)
						g.setEnabled(false);
				}	
			}
		});
		rdbtnTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnTipo.isSelected()) {
					cbTipo.setEnabled(true);
				}
				else
					cbTipo.setEnabled(false);
			}
		});
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				//MODELLO CHE PASSA I PARAMETRI AL DAOP CHE POI PROVVEDERà A FORMULARE LA QUERY
				if (checkNome.isSelected())
					nome=txtNome.getText();
				else
					nome=null;
				if (checkCognome.isSelected())
					cognome=txtCognome.getText();
				else
					cognome=null;
				if (checkSesso.isSelected())
					sesso=(String)cbSesso.getModel().getElementAt(cbSesso.getSelectedIndex()) ;
				else
					sesso=null;
				System.out.println(nome+" "+ cognome + " " +sesso+" ");
				
				if (rdbtnTipo.isSelected()) {
					tipologia=(String)cbTipo.getModel().getElementAt(cbTipo.getSelectedIndex());
					VisualizzaDipendente visualizza = new VisualizzaDipendente(dDAOP.RicercaPerTipologia(tipologia));
					visualizza.setVisible(true);
				}
				if (rdbtnTutti.isSelected()) {
					VisualizzaDipendente visualizza = new VisualizzaDipendente(dDAOP.getAll());
					visualizza.setVisible(true);
					}
				if (rdbtnGeneralit.isSelected()) {
					VisualizzaDipendente visualizza = new VisualizzaDipendente(dDAOP.RicercaComposta(nome, cognome, sesso));
					visualizza.setVisible(true);
					}
				
			}
		;
		});
}}
