package it.unirc.bd.gui.iscritto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import javafx.scene.control.ToggleGroup;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

public class RicercaIscritto extends JDialog {
	//OGETTI DAOP
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	EventoDAOP eDAOP=new EventoDAOP();

	//DATI DA PASSARE ALLA QUERY
	private String Categoria;
	private String TG;
	private boolean isNome=false;
	private boolean isCognome=false;
	private int TipoRicercaData;
	private String date;
	private Date Data;
	private String Nome;
	private String Cognome;
	private String Sesso;
	private Integer MatricolaFin;
	private boolean isTutti;

	ButtonGroup gruppo = new ButtonGroup();
	ButtonGroup gruppoData = new ButtonGroup();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtData;
	private JTextField txtNome;
	private JTextField txtCognome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaIscritto dialog = new RicercaIscritto(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaIscritto(boolean isAtleta) {
		setModal(true);
		setBounds(100, 100, 481, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.setBounds(183, 394, 97, 25);
		contentPanel.add(btnRicerca);

		JPanel panel = new JPanel();
		panel.setBounds(248, 19, -235, 78);
		contentPanel.add(panel);

		JRadioButton rbNomeCognome = new JRadioButton("Nome/Cognome");


		rbNomeCognome.setBounds(8, 44, 140, 25);
		contentPanel.add(rbNomeCognome);
		gruppo.add(rbNomeCognome);

		JRadioButton rbData = new JRadioButton("Data");

		rbData.setBounds(8, 170, 67, 25);
		contentPanel.add(rbData);
		gruppo.add(rbData);


		JRadioButton rbTutti = new JRadioButton("Tutti gli Iscritti");
		rbTutti.setBounds(8, 353, 127, 25);
		contentPanel.add(rbTutti);
		gruppo.add(rbTutti);

		JComboBox cbbSesso = new JComboBox();
		cbbSesso.setBounds(84, 292, 106, 22);
		cbbSesso.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		cbbSesso.setEnabled(false);
		contentPanel.add(cbbSesso);

		JRadioButton rbSesso = new JRadioButton("Sesso");

		rbSesso.setBounds(8, 291, 67, 25);
		contentPanel.add(rbSesso);
		gruppo.add(rbSesso);

		JPanel pannelloData = new JPanel();
		pannelloData.setBounds(155, 174, 283, 92);
		pannelloData.setEnabled(false);
		pannelloData.setBorder(new TitledBorder(null, "Ricerca per Data di Nascita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(pannelloData);
		pannelloData.setLayout(null);

		JLabel label_1 = new JLabel("Data:");
		label_1.setEnabled(false);
		label_1.setBounds(12, 60, 31, 16);
		pannelloData.add(label_1);

		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setBounds(57, 57, 116, 22);
		txtData.setText("yy-mm-dd");
		txtData.setColumns(10);
		pannelloData.add(txtData);

		JRadioButton rbPrecedente = new JRadioButton("Precedente");
		rbPrecedente.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbPrecedente.isSelected())
					TipoRicercaData=0;
			}
		});
		rbPrecedente.setEnabled(false);
		rbPrecedente.setBounds(12, 21, 93, 25);
		pannelloData.add(rbPrecedente);
		gruppoData.add(rbPrecedente);

		JRadioButton rbPari = new JRadioButton("Pari");
		rbPari.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbPari.isSelected())
					TipoRicercaData=1;
			}
		});
		rbPari.setSelected(true);
		rbPari.setEnabled(false);
		rbPari.setBounds(110, 23, 51, 25);
		pannelloData.add(rbPari);
		gruppoData.add(rbPari);

		JRadioButton rbSuccessiva = new JRadioButton("Successiva");
		rbSuccessiva.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbSuccessiva.isSelected())
					TipoRicercaData=2;
			}
		});
		rbSuccessiva.setEnabled(false);
		rbSuccessiva.setBounds(177, 21, 93, 25);
		pannelloData.add(rbSuccessiva);
		gruppoData.add(rbSuccessiva);

		JPanel pannelloNomeCognome = new JPanel();
		pannelloNomeCognome.setBounds(155, 48, 283, 103);
		pannelloNomeCognome.setEnabled(false);
		pannelloNomeCognome.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ricerca per Nome/Cognome", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(pannelloNomeCognome);
		pannelloNomeCognome.setLayout(null);

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(85, 27, 116, 22);
		txtNome.setColumns(10);
		pannelloNomeCognome.add(txtNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setEnabled(false);
		lblNome.setBounds(12, 30, 38, 16);
		pannelloNomeCognome.add(lblNome);

		txtCognome = new JTextField();
		txtCognome.setEnabled(false);
		txtCognome.setBounds(85, 62, 116, 22);
		txtCognome.setColumns(10);
		pannelloNomeCognome.add(txtCognome);

		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setEnabled(false);
		lblCognome.setBounds(12, 65, 65, 16);
		pannelloNomeCognome.add(lblCognome);

		JCheckBox cbNome = new JCheckBox("");

		cbNome.setEnabled(false);
		cbNome.setBounds(221, 27, 25, 25);
		pannelloNomeCognome.add(cbNome);

		JCheckBox cbCognome = new JCheckBox("");

		cbCognome.setEnabled(false);
		cbCognome.setBounds(221, 62, 25, 25);
		pannelloNomeCognome.add(cbCognome);

		JLabel lblRicercaPer = new JLabel("Ricerca per:");
		lblRicercaPer.setBounds(8, 19, 77, 16);
		contentPanel.add(lblRicercaPer);
		
		JComboBox<String> cbbTG = new JComboBox<String>();
		
		cbbTG.setEnabled(false);
		cbbTG.setVisible(isAtleta);
		cbbTG.setModel(eDAOP.ElencoTipi());
		cbbTG.setBounds(265, 324, 173, 22);
		contentPanel.add(cbbTG);
		
		JRadioButton rbTG = new JRadioButton("Tipologia di gare a cui ha preso parte:");
		
		rbTG.setBounds(8, 323, 249, 25);
		rbTG.setVisible(isAtleta);
		contentPanel.add(rbTG);
		gruppo.add(rbTG);
		
		JRadioButton rbCategoria = new JRadioButton("Categoria");
		rbCategoria.setVisible(isAtleta);
		rbCategoria.setBounds(233, 291, 83, 25);
		contentPanel.add(rbCategoria);
		gruppo.add(rbCategoria);
		
		JComboBox cbbCategoria = new JComboBox();
		cbbCategoria.setVisible(isAtleta);
		cbbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		cbbCategoria.setEnabled(false);
		cbbCategoria.setBounds(324, 290, 106, 22);
		contentPanel.add(cbbCategoria);



		//----LISTNER----
		rbCategoria.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rbCategoria.isSelected()) {
					cbbCategoria.setEnabled(true);
				}
				else
					cbbCategoria.setEnabled(false);
			}
		});
		
		rbTG.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rbTG.isSelected()) {
					cbbTG.setEnabled(true);
				}
				else
					cbbTG.setEnabled(false);
			}
		});
		
		cbCognome.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cbCognome.isSelected()) {
					txtCognome.setEnabled(true);
					isCognome=true;
				}
				else {
					txtCognome.setEnabled(false);
					isCognome=false;
				}
			}
		});

		cbNome.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cbNome.isSelected()) {
					txtNome.setEnabled(true);
					isNome=true;
				}
				else {
					txtNome.setEnabled(false);
					isNome=false;
				}
			}
		});

		rbNomeCognome.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbNomeCognome.isSelected()) {
					//INIZIO ABILITAZIONE COMPONENTI PANNELLO
					pannelloNomeCognome.setEnabled(true);
					lblNome.setEnabled(true);
					lblCognome.setEnabled(true);
					cbNome.setEnabled(true);
					cbCognome.setEnabled(true);

				}//FINE ABILITAZIONE
				else {
					//INIZIO DISABILITAZIONI COMPONENTI PANNELLO
					pannelloNomeCognome.setEnabled(false);
					lblNome.setEnabled(false);
					lblCognome.setEnabled(false);
					cbNome.setEnabled(false);
					cbCognome.setEnabled(false);
					//FINE DISABILITAZIONE
				}
			}
		});

		rbSesso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbSesso.isSelected())
					cbbSesso.setEnabled(true);
				else
					cbbSesso.setEnabled(false);
			}
		});
		rbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rbData.isSelected()) {
					//INIZIO ABILITAZIONE COMPONENTI PANNELLO
					pannelloData.setEnabled(true);
					Component[] components=  pannelloData.getComponents();
					for (Component component : components) {
						component.setEnabled(true);
					} 
				}//FINE ABILITAZIONE
				else {
					//INIZIO DISABILITAZIONI COMPONENTI PANNELLO
					pannelloData.setEnabled(false);
					Component[] components=  pannelloData.getComponents();
					for (Component component : components) {
						component.setEnabled(false);
					} 
					//FINE DISABILITAZIONE
				}
			}
		});


		//----AQUISIZIONE DEI DATI DA BOTTONE----AGGIUNGERE LA STAMPA DEI VALORI PRELEVATI
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Iscritto> vettore =new Vector<Iscritto>();
				//JOptionPane.showMessageDialog(null, "Inserimento con successo");
				if (rbTutti.isSelected()) {
					isTutti=true;
					if (isAtleta)
					vettore=iDAOP.getTuttiAtleti();
					else
						vettore=iDAOP.getAll();
				}
				else
					isTutti=false;
				if (rbData.isSelected()) {
					Data =Date.valueOf(txtData.getText());
					vettore=iDAOP.RicercaPerData(Data,TipoRicercaData, isAtleta);

				}
				else
					Data=null;
				if (rbNomeCognome.isSelected()) {
					Nome=txtNome.getText();
					Cognome=txtCognome.getText();
					vettore=iDAOP.RicercaPerNomeCognome(Nome, Cognome, isNome, isCognome,isAtleta);
				}
				else {
					Nome="";
					Cognome="";
				}
				if (rbSesso.isSelected()) {
					Sesso = cbbSesso.getModel().getElementAt(cbbSesso.getSelectedIndex()).toString();
					vettore=iDAOP.RicercaPerSesso(Sesso, isAtleta);
				}
				if (rbCategoria.isSelected()) {
					Categoria = cbbCategoria.getModel().getElementAt(cbbCategoria.getSelectedIndex()).toString();
					System.out.println(Categoria);
					vettore=iDAOP.RicercaPerCategoria(Categoria); 
					}
				if (rbTG.isSelected()) {
					TG = cbbTG.getModel().getElementAt(cbbTG.getSelectedIndex()).toString();
					System.out.println(TG);
					vettore=iDAOP.RicercaPerTipoGara(TG); 
				}
				
				//----VISUALIZZAZIONE DEI DATI AQUISITI----
				if (isTutti)
					System.out.println("Hai ricercato tutti gli eventi");
				else	
					System.out.println(Nome+" "+Cognome+" "+Data+" "+Sesso+" "+MatricolaFin);
				System.out.println("TIPO DI RICERCA PER DATA: "+TipoRicercaData);

				//----INVIO DATI ALLA QUERY----

				//VisualizzaIscritto vis=new VisualizzaIscritto(vettore);
				//vis.setVisible(true);

				//System.out.println(eDAOP.getAll(Data, Tipo, Livello, isTutti));
				for(Iscritto i : vettore) {
					System.out.println(i.toString());
				}
				VisualizzaIscritto vis =new VisualizzaIscritto(vettore);
				vis.setVisible(true);				
				dispose();

			}
		});






	}


}
