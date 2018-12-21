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

public class RicercaEvento extends JDialog {
	//OGETTI DAOP
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	EventoDAOP eDAOP =new EventoDAOP();

	//DATI DA PASSARE ALLA QUERY
	private String date;
	private Date Data;
	private String Tipo;
	private String Livello;
	private Integer MatricolaFin;
	private boolean isTutti;

	ButtonGroup gruppo = new ButtonGroup();
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
		//----COMBOBOX ATLETI----
		JComboBox<String> cbbRA = new JComboBox<String>();	
		//HO CREATO UN METODO CHE MI CONVERTE IL DefaultComboBoxModel DA OGETTO A STRING
		cbbRA.setModel(AtletaStringcb(iDAOP.getAtleticb()));
		cbbRA.setBounds(12, 137, 368, 22);
		cbbRA.setEnabled(false);
		contentPanel.add(cbbRA);

		JComboBox cbbLivello = new JComboBox();
		cbbLivello.setModel(new DefaultComboBoxModel(new String[] {"Provinciale", "Regionale", "Nazionale"}));
		cbbLivello.setBounds(60, 72, 106, 22);
		cbbLivello.setEnabled(false);
		contentPanel.add(cbbLivello);

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

		JRadioButton rbTipo = new JRadioButton("rbTipo");

		rbTipo.setBounds(228, 10, 127, 25);
		contentPanel.add(rbTipo);
		gruppo.add(rbTipo);

		JRadioButton rbData = new JRadioButton("rbData");

		rbData.setBounds(228, 40, 127, 25);
		contentPanel.add(rbData);
		gruppo.add(rbData);


		JRadioButton rbLivello = new JRadioButton("rbLivello");

		rbLivello.setBounds(228, 71, 127, 25);
		contentPanel.add(rbLivello);
		gruppo.add(rbLivello);


		JRadioButton rbRA = new JRadioButton("rbRA");

		rbRA.setBounds(147, 103, 127, 25);
		contentPanel.add(rbRA);
		gruppo.add(rbRA);


		JRadioButton rbTutti = new JRadioButton("rbTutti");

		rbTutti.setBounds(161, 166, 127, 25);
		contentPanel.add(rbTutti);
		gruppo.add(rbTutti);





		//----LISTNER----
		rbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbTipo.isSelected())
					txtTipo.setEnabled(true);
				else
					txtTipo.setEnabled(false);
			}
		});

		rbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbData.isSelected())
					txtData.setEnabled(true);
				else
					txtData.setEnabled(false);
			}
		});

		rbLivello.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbLivello.isSelected())
					cbbLivello.setEnabled(true);
				else
					cbbLivello.setEnabled(false);
			}
		});

		rbRA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbRA.isSelected())
					cbbRA.setEnabled(true);
				else
					cbbRA.setEnabled(false);
			}
		});
		//----AQUISIZIONE DEI DATI DA BOTTONE----AGGIUNGERE LA STAMPA DEI VALORI PRELEVATI
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Evento> vettore =new Vector<Evento>();
				//JOptionPane.showMessageDialog(null, "Inserimento con successo");
				if (rbTutti.isSelected()) {
					isTutti=true;
					vettore=eDAOP.getAll();
				}
				else
					isTutti=false;
				if (rbData.isSelected()) {
					Data =Date.valueOf(txtData.getText());
					vettore=eDAOP.RicercaPerData(Data);
				}
				else
					Data=null;
				if (rbTipo.isSelected()) {
					Tipo=txtTipo.getText();
					vettore=eDAOP.RicercaPerTipo(Tipo);
				}
				else
					Tipo="";
				if (rbLivello.isSelected()) {
					Livello = cbbLivello.getModel().getElementAt(cbbLivello.getSelectedIndex()).toString();
					vettore=eDAOP.RicercaPerLivello(Livello);
				}
				else
					Livello="";
				if (rbRA.isSelected()) {
					Iscritto iscritto =getIscritto(iDAOP.getAtleticb(), cbbRA.getSelectedIndex());
					MatricolaFin=iscritto.getMatricolaFIN();
					vettore=eDAOP.RicercaPerMatricolaFin(MatricolaFin);
				}
				else
					MatricolaFin=null;
				
				//----VISUALIZZAZIONE DEI DATI AQUISITI----
				if (isTutti)
					System.out.println("Hai ricercato tutti gli eventi");
				else	
				System.out.println(Tipo+" "+Data+" "+Livello+" "+MatricolaFin);
				
				//----INVIO DATI ALLA QUERY----
				
				VisualizzaEvento vis=new VisualizzaEvento(vettore);
				vis.setVisible(true);
				
				//System.out.println(eDAOP.getAll(Data, Tipo, Livello, isTutti));
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
