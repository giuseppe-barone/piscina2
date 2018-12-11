package it.unirc.bd.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 477, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmChiudi = new JMenuItem("Chiudi");
		mnFile.add(mntmChiudi);
		
		JMenu mnIscrittiatleti = new JMenu("Iscritti/Atleti");
		menuBar.add(mnIscrittiatleti);
		
		JMenu mnIscritto = new JMenu("Iscritto");
		mnIscrittiatleti.add(mnIscritto);
		
		JMenuItem mntmInserisciIscritto = new JMenuItem("Nuovo Iscritto");
		mnIscritto.add(mntmInserisciIscritto);
		
		JMenuItem mntmRicercaIscritto = new JMenuItem("Ricerca Iscritto");
		mnIscritto.add(mntmRicercaIscritto);
		
		JMenu mnAtleta = new JMenu("Atleta");
		mnIscrittiatleti.add(mnAtleta);
		
		JMenuItem mntmCreaAtleta = new JMenuItem("Nuovo Atleta");
		mnAtleta.add(mntmCreaAtleta);
		
		JMenuItem mntmCercaAtleta = new JMenuItem("Cerca Atleta");
		mnAtleta.add(mntmCercaAtleta);
		
		JMenu mnInfortunii = new JMenu("Infortunii");
		mnIscrittiatleti.add(mnInfortunii);
		
		JMenuItem mntmInserisciInfortunio = new JMenuItem("Inserisci Infortunio");
		mnInfortunii.add(mntmInserisciInfortunio);
		
		JMenu mnRicercaInfortunio = new JMenu("Ricerca Infortunio");
		mnInfortunii.add(mnRicercaInfortunio);
		
		JMenu mnDipendenti = new JMenu("Dipendenti");
		menuBar.add(mnDipendenti);
		
		JMenuItem mntmNuovoDipendente = new JMenuItem("Nuovo Dipendente");
		mnDipendenti.add(mntmNuovoDipendente);
		
		JMenuItem mntmRicercaDipendente = new JMenuItem("Ricerca Dipendente");
		mnDipendenti.add(mntmRicercaDipendente);
		
		JMenu mnCorsi = new JMenu("Corsi");
		menuBar.add(mnCorsi);
		
		JMenuItem mntmNuovoCorso = new JMenuItem("Nuovo Corso");
		mnCorsi.add(mntmNuovoCorso);
		
		JMenuItem mntmRicercaCorsi = new JMenuItem("Ricerca Corsi");
		mnCorsi.add(mntmRicercaCorsi);
		
		JMenuItem mntmIscrizioneCorso = new JMenuItem("Iscrizione Corso");
		mnCorsi.add(mntmIscrizioneCorso);
		
		JMenu mnEventi = new JMenu("Eventi");
		menuBar.add(mnEventi);
		
		JMenuItem mntmOrganizzaEvento = new JMenuItem("Organizza Evento");
		mnEventi.add(mntmOrganizzaEvento);
		
		JMenuItem mntmVisualizzaEventi = new JMenuItem("Visualizza Eventi");
		mnEventi.add(mntmVisualizzaEventi);
		
		JMenuItem mntmPartecipazioneAdEvento = new JMenuItem("Partecipazione ad Evento");
		mnEventi.add(mntmPartecipazioneAdEvento);
		
		JMenu mnPrenotazioni = new JMenu("Prenotazioni");
		menuBar.add(mnPrenotazioni);
		
		JMenuItem mntmNuovaPrenotazione = new JMenuItem("Nuova Prenotazione");
		mnPrenotazioni.add(mntmNuovaPrenotazione);
		
		JMenuItem mntmCercaeliminaPrenotazione = new JMenuItem("Cerca/Elimina Prenotazione");
		mnPrenotazioni.add(mntmCercaeliminaPrenotazione);
		
		JMenu mnStatistiche = new JMenu("Statistiche");
		menuBar.add(mnStatistiche);
		frame.getContentPane().setLayout(null);
	}
}
