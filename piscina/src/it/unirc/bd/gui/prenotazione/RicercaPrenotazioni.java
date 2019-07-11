package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.Prenotazione;
import it.unirc.bd.dao.beans.PrenotazioneDAOP;
import it.unirc.bd.gui.evento.VisualizzaEvento;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class RicercaPrenotazioni extends JDialog {
	ButtonGroup gruppo = new ButtonGroup();
	
	//PARAMETRI DA PASSARE ALLA QUERY
	private Date data;
	private String tipo;
	private Integer ora;
	
	//OGGETTI DAO
	PrenotazioneDAOP pDAOP = new PrenotazioneDAOP();
	IscrittoDAOP iDAOP=new IscrittoDAOP();
	DipendenteDAOP dDAOP =new DipendenteDAOP();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaPrenotazioni dialog = new RicercaPrenotazioni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaPrenotazioni() {
		setModal(true);
		setTitle("Ricerca Prenotazioni");
		setBounds(100, 100, 490, 265);
		getContentPane().setLayout(null);
		
		JLabel lblRicercaPer = new JLabel("Ricerca per:");
		lblRicercaPer.setBounds(26, 13, 76, 28);
		getContentPane().add(lblRicercaPer);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(134, 180, 97, 25);
		getContentPane().add(btnCerca);
		
		JRadioButton rbData = new JRadioButton("Data");
		rbData.setSelected(true);
		rbData.setBounds(8, 50, 68, 25);
		getContentPane().add(rbData);
		gruppo.add(rbData);
		
		JRadioButton rbIscritto = new JRadioButton("Iscritto");
		rbIscritto.setBounds(8, 84, 68, 25);
		getContentPane().add(rbIscritto);
		gruppo.add(rbIscritto);
		
		JRadioButton rbDipendente = new JRadioButton("Dipendente");
		rbDipendente.setBounds(8, 114, 94, 25);
		getContentPane().add(rbDipendente);
		gruppo.add(rbDipendente);
		
		JDateChooser campoData = new JDateChooser();
		campoData.setBounds(116, 50, 116, 22);
		getContentPane().add(campoData);
		
		JComboBox<Iscritto> comboIscritto = new JComboBox<Iscritto>();
		comboIscritto.setEnabled(false);
		comboIscritto.setModel(iDAOP.getIscritticb());
		comboIscritto.setBounds(116, 80, 344, 22);
		getContentPane().add(comboIscritto);
		
		JComboBox<Dipendente> comboDipendente = new JComboBox<Dipendente>();
		Vector<Dipendente> vettoredip=dDAOP.RicercaPerTipologia(0);
		comboDipendente.setModel(dDAOP.ModelloCombobox(vettoredip));
		comboDipendente.setEnabled(false);
		comboDipendente.setBounds(115, 115, 345, 22);
		getContentPane().add(comboDipendente);
		
		JRadioButton rbTutti = new JRadioButton("Tutti");
		rbTutti.setBounds(8, 146, 127, 25);
		getContentPane().add(rbTutti);
		gruppo.add(rbTutti);
		
		//--------LISTENER
		rbIscritto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbIscritto.isSelected())
					comboIscritto.setEnabled(true);
				else
					comboIscritto.setEnabled(false);
			}
		});
		
		rbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbData.isSelected())
					campoData.setEnabled(true);
				else
					campoData.setEnabled(false);
			}
		});
		
		rbDipendente.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbDipendente.isSelected())
					comboDipendente.setEnabled(true);
				else
					comboDipendente.setEnabled(false);
			}
		});
		
		//----ACQUISIZIONE DATI DA BOTTONE
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Prenotazione> vettore =new Vector<Prenotazione>();
				if(rbData.isSelected()) {
					data=new Date (campoData.getDate().getTime());
					vettore=pDAOP.RicercaPerData(data);
				}
				
				if(rbIscritto.isSelected()) {
					Iscritto iscritto=iDAOP.getIscritticb().getElementAt(comboIscritto.getSelectedIndex());
					int idIscritto=iscritto.getIdIscritto();
					vettore=pDAOP.RicercaIdIscritto(idIscritto);
				}
				
				
				if(rbDipendente.isSelected()) {
					Dipendente dipendente = vettoredip.get(comboDipendente.getSelectedIndex());
					int id=dipendente.getIdDipendente();
					vettore=pDAOP.RicercaIdDipendente(id);
				}
				if (rbTutti.isSelected())
					vettore=pDAOP.getAll();
				
				VisualizzaPrenotazione vis=new VisualizzaPrenotazione(vettore);
				vis.setVisible(true);
			}
		});
	}
}
