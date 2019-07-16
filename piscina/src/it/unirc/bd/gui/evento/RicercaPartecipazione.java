package it.unirc.bd.gui.evento;



import java.awt.EventQueue;
import javax.swing.JDialog;

import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.Partecipazione;
import it.unirc.bd.dao.beans.PartecipazioneDAOP;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;



public class RicercaPartecipazione extends JDialog {
	PartecipazioneDAOP pDAOP=new PartecipazioneDAOP();
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	EventoDAOP eDAOP=new EventoDAOP();
	Vector<Partecipazione> elencoPartecipazioni;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaPartecipazione dialog = new RicercaPartecipazione();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RicercaPartecipazione() {
		setTitle("Ricerca Partecipazione");
		ButtonGroup gruppo = new ButtonGroup();
		setBounds(100, 100, 450, 222);
		getContentPane().setLayout(null);
		
		JRadioButton rbTutti = new JRadioButton("Tutti");
		rbTutti.setSelected(true);
		rbTutti.setBounds(8, 99, 127, 25);
		getContentPane().add(rbTutti);
		gruppo.add(rbTutti);
		
		
		
		JRadioButton rbTipo = new JRadioButton("Tipo");
		gruppo.add(rbTipo);
		
		rbTipo.setBounds(8, 9, 61, 25);
		getContentPane().add(rbTipo);
		
		JDateChooser campoData = new JDateChooser();
		campoData.setEnabled(false);
		campoData.setBounds(65, 73, 98, 22);
		getContentPane().add(campoData);
		
		JRadioButton rbAtleta = new JRadioButton("Atleta");
		gruppo.add(rbAtleta);
		rbAtleta.setBounds(8, 39, 61, 25);
		getContentPane().add(rbAtleta);
		
		JComboBox<Iscritto> comboAtleta = new JComboBox<Iscritto>();
		comboAtleta.setEnabled(false);
		comboAtleta.setModel(iDAOP.getAtleticb());
		comboAtleta.setBounds(76, 43, 344, 22);
		getContentPane().add(comboAtleta);
		
		JComboBox<String> comboTipo = new JComboBox<String>();
		comboTipo.setEnabled(false);
		comboTipo.setModel(eDAOP.ElencoTipi());
		comboTipo.setBounds(77, 10, 344, 22);
		getContentPane().add(comboTipo);
		
		JRadioButton rbData = new JRadioButton("Data");
		gruppo.add(rbData);
		rbData.setBounds(8, 69, 55, 25);
		getContentPane().add(rbData);
		
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String[]> vettore=null;
				
				if(rbTutti.isSelected())
					vettore=pDAOP.getPartecipazioniTutte();
				else if (rbData.isSelected()) {
					Date data=new Date(campoData.getDate().getTime());
					vettore=pDAOP.getPartecipazioniData(data);
				}
				else if (rbAtleta.isSelected()) {
					Iscritto iscritto=iDAOP.getAtleticb().getElementAt(comboAtleta.getSelectedIndex());
					int id =iscritto.getMatricolaFIN();
					vettore =pDAOP.getPartecipazioniFromMatricola(id);
				}
				else {
					String tipo=(String) comboTipo.getSelectedItem();
					vettore =pDAOP.getPartecipazioniFromTipo(tipo);
				}
				VisualizzaPartecipazione visualizza =new VisualizzaPartecipazione(vettore);
				visualizza .setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(129, 133, 97, 25);
		getContentPane().add(btnNewButton);
		
		
		rbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rbTipo.isSelected())
					comboTipo.setEnabled(true);
				else
					comboTipo.setEnabled(false);

			}
		});
		
		rbAtleta.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbAtleta.isSelected())
					comboAtleta.setEnabled(true);
				else
					comboAtleta.setEnabled(false);

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

	}
}
