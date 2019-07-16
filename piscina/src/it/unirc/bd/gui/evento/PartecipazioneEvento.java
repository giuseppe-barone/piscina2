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
import it.unirc.bd.dao.beans.Partecipazione;
import it.unirc.bd.dao.beans.PartecipazioneDAOP;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class PartecipazioneEvento extends JDialog {
	IscrittoDAOP iDAOP= new IscrittoDAOP();
	EventoDAOP eDAOP= new EventoDAOP();
	PartecipazioneDAOP pDAOP= new PartecipazioneDAOP();
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
					PartecipazioneEvento dialog = new PartecipazioneEvento(false,null);
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
	 * questa paggina potrà sia modificare che aggiungere una nuova partecipazione 
	 * 
	 * 
	 * */
	public PartecipazioneEvento(boolean modifica, Partecipazione partecipazione) {
		setTitle("Partecipazione evento");
		setBounds(100, 100, 464, 194);
		getContentPane().setLayout(null);
		
		JComboBox<Evento> comboEvento = new JComboBox<Evento>();
		comboEvento.setModel(eDAOP.ElencoEventiDisponibili());
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
		btnInserisci.setVisible(false);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModifica.setBounds(166, 112, 97, 25);
		getContentPane().add(btnModifica);
		btnModifica.setVisible(false);
		
		if (modifica)
			btnModifica.setVisible(true);
		else
			btnInserisci.setVisible(true);
		
		//----LISTNER----
		
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Iscritto i;
				i=iDAOP.getAtleticb().getElementAt(comboAtleta.getSelectedIndex());
				MatricolaFin=i.getMatricolaFIN();
				Evento e =eDAOP.ElencoEventiDisponibili().getElementAt(comboEvento.getSelectedIndex());
				idEvento=e.getIdEvento();
				posizione=comboPosizione.getSelectedIndex();
				categoria=i.CalcoloCategoria(i);
			
				java.util.Date dataOdierna = new java.util.Date();
				
			    System.out.println(dataOdierna.toString()+ " LA DATA DI OGGI");
				
				Partecipazione p =new Partecipazione();
				p.setCategoria(categoria);
				p.setIdEvento(idEvento);
				p.setMatricolaFin(MatricolaFin);
				p.setPosizione(posizione);
				if (e.getData().after(dataOdierna)   && posizione!=0)
					JOptionPane.showMessageDialog(null, "STAI TENTANDO DI INSERIRE UNA POSIZIONE\nNON VALIDA PRIMA CHE L'EVENTO SI SIA SVOLTO!");
				else if (pDAOP.salvaPartecipazione(p))
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
			}
		});
		
		

	}
}
