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

import it.unirc.bd.dao.beans.CorsoDAOP;
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
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class RicercaEvento extends JDialog {
	//OGETTI DAOP
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	EventoDAOP eDAOP =new EventoDAOP();
	CorsoDAOP cDAOP =new CorsoDAOP();
	
	//DATI DA PASSARE ALLA QUERY
	private Date dataprova;
	private String date;
	private Date Data;
	private String Tipo;
	private String Livello;
	private Integer MatricolaFin;
	private boolean isTutti;

	ButtonGroup gruppo = new ButtonGroup();
	private final JPanel contentPanel = new JPanel();

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
		setTitle("Ricerca evento");
		setBounds(100, 100, 423, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//----COMBOBOX ATLETI----
		JComboBox<String> cbbRA = new JComboBox<String>();
		cbbRA.setBounds(12, 137, 368, 22);
		//HO CREATO UN METODO CHE MI CONVERTE IL DefaultComboBoxModel DA OGETTO A STRING
		cbbRA.setModel(AtletaStringcb(iDAOP.getAtleticb()));
		cbbRA.setEnabled(false);
		contentPanel.add(cbbRA);

		JComboBox cbbLivello = new JComboBox();
		cbbLivello.setBounds(82, 70, 116, 22);
		cbbLivello.setModel(new DefaultComboBoxModel(new String[] {"Provinciale", "Regionale", "Nazionale"}));
		cbbLivello.setEnabled(false);
		contentPanel.add(cbbLivello);

		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.setBounds(147, 172, 97, 25);
		contentPanel.add(btnRicerca);

		JPanel panel = new JPanel();
		panel.setBounds(248, 19, -235, 78);
		contentPanel.add(panel);

		JRadioButton rbTipo = new JRadioButton("Tipo");
		rbTipo.setBounds(12, 11, 62, 25);
		contentPanel.add(rbTipo);
		gruppo.add(rbTipo);

		JRadioButton rbData = new JRadioButton("Data");
		rbData.setBounds(12, 41, 62, 25);
		contentPanel.add(rbData);
		gruppo.add(rbData);


		JRadioButton rbLivello = new JRadioButton("Livello");
		rbLivello.setBounds(12, 72, 73, 25);
		contentPanel.add(rbLivello);
		gruppo.add(rbLivello);


		JRadioButton rbRA = new JRadioButton("Atleta");
		rbRA.setBounds(12, 103, 127, 25);
		contentPanel.add(rbRA);
		gruppo.add(rbRA);


		JRadioButton rbTutti = new JRadioButton("Tutti");
		rbTutti.setSelected(true);
		rbTutti.setBounds(12, 168, 67, 25);
		contentPanel.add(rbTutti);
		gruppo.add(rbTutti);
		
		JDateChooser campodata = new JDateChooser();
		campodata.setEnabled(false);
		campodata.setBounds(82, 41, 116, 22);
		contentPanel.add(campodata);
		
		JComboBox comboTipo = new JComboBox();
		comboTipo.setEnabled(false);
		comboTipo.setModel(cDAOP.ElencoTipi());
		comboTipo.setBounds(82, 12, 162, 22);
		contentPanel.add(comboTipo);





		//----LISTNER----
		rbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbTipo.isSelected())
					comboTipo.setEnabled(true);
				else
					comboTipo.setEnabled(false);
			}
		});

		rbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbData.isSelected())
					campodata.setEnabled(true);
				else
					campodata.setEnabled(false);
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
					Data=new Date(campodata.getDate().getTime());
					vettore=eDAOP.RicercaPerData(Data);
				}
				else
					Data=null;
				if (rbTipo.isSelected()) {
					Tipo=(String) comboTipo.getSelectedItem();
					System.out.println(Tipo);
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
				
				System.out.println("la data da te scelta è: "+ dataprova);
				
				/* ERA UNA PROVA PER VEDERE IL RISULTATO PRIMA DI FARE IL COLLEGAMENTO CON LA TABELLA
				for(Evento e : vettore) {
					System.out.println(e.toString());
				}*/


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
