package it.unirc.bd.gui.evento;

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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RicercaEvento extends JDialog {
	//OGETTI DAOP
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	EventoDAOP eDAOP =new EventoDAOP();

	//DATI DA PASSARE ALLA QUERY
	private Date Data;
	private String Tipo;
	private String Livello;
	private Integer MatricolaFin;
	private boolean isTutti;


	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JTextField txtData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaEvento dialog = new RicercaEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaEvento() {
		setBounds(100, 100, 410, 292);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JCheckBox cbTutti = new JCheckBox("Cronistoria (Tutti)");


		cbTutti.setBounds(8, 166, 131, 25);
		contentPanel.add(cbTutti);

		JCheckBox cbRA = new JCheckBox("Ricerca per atleta");
		cbRA.setBounds(8, 103, 131, 25);
		contentPanel.add(cbRA);
		//----COMBOBOX ATLETI----
		JComboBox<String> cbbAtleta = new JComboBox<String>();	
		//HO CREATO UN METODO CHE MI CONVERTE IL DefaultComboBoxModel DA OGETTO A STRING
		cbbAtleta.setModel(AtletaStringcb(iDAOP.getAtleticb()));
		cbbAtleta.setBounds(12, 137, 368, 22);
		cbbAtleta.setEnabled(false);
		contentPanel.add(cbbAtleta);

		JComboBox cbbLivello = new JComboBox();
		cbbLivello.setModel(new DefaultComboBoxModel(new String[] {"Provinciale", "Reggionale", "Nazionale"}));
		cbbLivello.setBounds(60, 72, 106, 22);
		cbbLivello.setEnabled(false);
		contentPanel.add(cbbLivello);

		JCheckBox cbTipo = new JCheckBox("");

		cbTipo.setBounds(184, 10, 30, 25);
		contentPanel.add(cbTipo);

		JCheckBox cbData = new JCheckBox("");
		cbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(cbData.isSelected()) {
					txtData.setEnabled(true);
				}
				else
					txtData.setEnabled(false);
			}
		});
		cbData.setBounds(184, 40, 25, 25);
		contentPanel.add(cbData);

		JCheckBox cbLivello = new JCheckBox("");
		cbLivello.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(cbLivello.isSelected()) {
					cbbLivello.setEnabled(true);
				}
				else
					cbbLivello.setEnabled(false);
			}
		});
		cbLivello.setBounds(184, 72, 25, 25);
		contentPanel.add(cbLivello);

		txtTipo = new JTextField();
		txtTipo.setBounds(60, 13, 116, 22);
		txtTipo.setEnabled(false);
		txtTipo.setColumns(10);
		contentPanel.add(txtTipo);

		txtData = new JTextField();
		txtData.setBounds(60, 40, 116, 22);
		txtData.setText("yy-mm-dd");
		txtData.setEnabled(false);
		txtData.setColumns(10);
		contentPanel.add(txtData);

		JLabel label = new JLabel("Tipo:");
		label.setBounds(8, 16, 30, 16);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("Data:");
		label_1.setBounds(8, 43, 37, 16);
		contentPanel.add(label_1);

		JLabel label_2 = new JLabel("Livello:");
		label_2.setBounds(8, 75, 40, 16);
		contentPanel.add(label_2);

		JButton btnRicerca = new JButton("Ricerca");

		btnRicerca.setBounds(161, 196, 97, 25);
		contentPanel.add(btnRicerca);

		JPanel panel = new JPanel();
		panel.setBounds(248, 19, -235, 78);
		contentPanel.add(panel);
		cbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(cbTipo.isSelected()) {
					txtTipo.setEnabled(true);
				}
				else
					txtTipo.setEnabled(false);
			}
		});
		cbTutti.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cbTutti.isSelected()) {
					//ATTIVO LE SELEZIONE
					isTutti=true;
					cbRA.setSelected(false);
					cbTipo.setSelected(false);
					cbData.setSelected(false);
					cbLivello.setSelected(false);
					cbRA.setEnabled(false);
					cbTipo.setEnabled(false);
					cbData.setEnabled(false);
					cbLivello.setEnabled(false);
				}
				else {
					isTutti=false;
					cbRA.setEnabled(true);
					cbTipo.setEnabled(true);
					cbData.setEnabled(true);
					cbLivello.setEnabled(true);
				}

			}
		});
		cbRA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cbRA.isSelected()) {
					cbbAtleta.setEnabled(true);
					cbData.setSelected(false);
					cbTipo.setSelected(false);
					cbLivello.setSelected(false);
					cbData.setEnabled(false);
					cbTipo.setEnabled(false);
					cbLivello.setEnabled(false);
					cbTutti.setEnabled(false);
					cbTutti.setSelected(false);

				}
				else {
					cbbAtleta.setEnabled(false);
					cbData.setEnabled(true);
					cbTipo.setEnabled(true);
					cbLivello.setEnabled(true);
					cbTutti.setEnabled(true);

				}

			}
		});
		//----AQUISIZIONE DEI DATI DA BOTTONE----AGGIUNGERE LA STAMPA DEI VALORI PRELEVATI
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Inserimento con successo");
				if (!isTutti) {
					System.out.println("NON TUTTI");
					if (cbTipo.isSelected()) {
						Tipo=txtTipo.getText();
						System.out.println(Tipo+" ");
					}
					else
						Tipo=null;
					if (cbData.isSelected()) {
						Data = Data.valueOf(txtData.getText());
						System.out.println(Data+" ");
					}
					else
						Data=null;
					if (cbLivello.isSelected()) {
						Livello = cbbLivello.getModel().getElementAt(cbbLivello.getSelectedIndex()).toString();
						System.out.println(Livello+" ");
					}
					else
						Livello=null;
					if (cbRA.isSelected()) {
						Iscritto iscritto =getIscritto(iDAOP.getAtleticb(), cbbAtleta.getSelectedIndex());
						MatricolaFin=iscritto.getMatricolaFIN();
						System.out.println(Integer.toString(MatricolaFin)+" ");
					}
					else
						MatricolaFin=null;
					/*//----INVIO DATI ALLA QUERY----
					Vector<Evento> vettore =new Vector<Evento>();
					vettore=eDAOP.Ricerca(Tipo, Data, Livello, isTutti);
					for(Evento e : vettore) {
						System.out.println(e.toString());
					}*/
				}
				else {
					System.out.println("TUTTI ");
				}
				//----INVIO DATI ALLA QUERY----
				Vector<Evento> vettore =new Vector<Evento>();
				vettore=eDAOP.Ricerca(Tipo, Data, Livello, isTutti);
				for(Evento e : vettore) {
					System.out.println(e.toString());
				}


			}
		});
	}
	public DefaultComboBoxModel<String> AtletaStringcb(DefaultComboBoxModel<Iscritto> a){
		//COPIO IL DEFAULTCOMBOBOX DI TIPO ISCRITTO IN UN DEFAULTCOMBOBOX DI TIPO STRING DOVE LE STRING SARANNO I DATI DELL'ATLETA
		//SE NON AVESSI FATTO COSì ECLIPSE AVREBBE USATO IL METODO .TOSTRING DI ISCRITTO E MI AVREBBE STAMPATO TUTTI I DATI DELL'ISCRITTO
		DefaultComboBoxModel<String> risultato = new DefaultComboBoxModel<String>();
		String rs;
		for (int i=0;i<a.getSize();i++) {
			rs=a.getElementAt(i).toStringAtleta();
			risultato.addElement(rs);
		}
		return risultato;

	}
	//Prelevo evento tramite l'indice della combobox RESTITUISCO L'OGETTO SCELTO
	public Iscritto getIscritto(ComboBoxModel<Iscritto> model, int indice) {
		Iscritto risultato= new Iscritto();
		Vector<Iscritto> vettoreIscritti =new Vector<Iscritto>();
		for (int i=0;i<model.getSize();i++) {
			vettoreIscritti.add(model.getElementAt(i));
		}
		risultato=vettoreIscritti.get(indice);
		return risultato;
	}
}
