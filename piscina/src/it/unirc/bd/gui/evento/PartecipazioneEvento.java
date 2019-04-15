package it.unirc.bd.gui.evento;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class PartecipazioneEvento extends JDialog {
	IscrittoDAOP iDAOP= new IscrittoDAOP();
	EventoDAOP eDAOP= new EventoDAOP();
	private int MatricolaFin;
	private int idEvento;
	private int posizione;
	private String categoria;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartecipazioneEvento dialog = new PartecipazioneEvento();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	
	
	/*
	 * questa paggina potr� sia modificare che aggiungere una nuova partecipazione 
	 * 
	 * 
	 * */
	public PartecipazioneEvento() {
		boolean modifica=false;  			//DA CANCELLARE  PERCH� SENNO RIMARRA SEMPRE IN INSERIMENTO E MAI IN M0DIFICA
		setTitle("Partecipazione evento");
		setBounds(100, 100, 464, 194);
		getContentPane().setLayout(null);
		
		JComboBox<Evento> comboEvento = new JComboBox<Evento>();
		comboEvento.setModel(eDAOP.ElencoEventiDisponibili(modifica));
		comboEvento.setBounds(73, 10, 361, 22);
		getContentPane().add(comboEvento);
		
		JComboBox<Iscritto> comboAtleta = new JComboBox<Iscritto>();
		comboAtleta.setModel(iDAOP.getAtleticb());
		comboAtleta.setBounds(73, 44, 361, 22);
		getContentPane().add(comboAtleta);
		
		JComboBox<String> comboPosizione = new JComboBox<String>();
		comboPosizione.setModel(new DefaultComboBoxModel(new String[] {"Da disputare", "Prima", "Seconda", "Terza", "Altre"}));
		comboPosizione.setBounds(73, 77, 97, 22);
		getContentPane().add(comboPosizione);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(12, 13, 49, 16);
		getContentPane().add(lblEvento);
		
		JLabel lblAtleta = new JLabel("Atleta:");
		lblAtleta.setBounds(12, 47, 49, 16);
		getContentPane().add(lblAtleta);
		
		JLabel lblNewLabel = new JLabel("Posizione:");
		lblNewLabel.setBounds(12, 80, 64, 16);
		getContentPane().add(lblNewLabel);
		
		JButton btnInserisci = new JButton("Inserisci");
		
		btnInserisci.setBounds(57, 112, 97, 25);
		getContentPane().add(btnInserisci);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(166, 112, 97, 25);
		getContentPane().add(btnModifica);
		
		
		//----LISTNER----
		
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Iscritto i;
				i=iDAOP.getAtleticb().getElementAt(comboAtleta.getSelectedIndex());
				MatricolaFin=i.getMatricolaFIN();
				Evento e =eDAOP.ElencoEventiDisponibili(modifica).getElementAt(comboEvento.getSelectedIndex());
				idEvento=e.getIdEvento();
				posizione=comboPosizione.getSelectedIndex();
				categoria=i.CalcoloCategoria(i);
				Date dataOdierna=new Date(2019,014,16);
				if (e.getData().before(dataOdierna)   && posizione!=0)
					JOptionPane.showMessageDialog(null, "STAI TENTANDO DI INSERIRE UNA POSIZIONE\nNON VALIDA PRIMA CHE L'EVENTO SI SIA SVOLTO!");
				else if (eDAOP.salvaPartecipazione(idEvento, MatricolaFin, posizione, categoria))
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
			}
		});
		
		

	}
}
