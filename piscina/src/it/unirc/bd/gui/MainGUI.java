
package it.unirc.bd.gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.gui.corso.InserisciCorso;
import it.unirc.bd.gui.corso.IscrizioneCorso;
import it.unirc.bd.gui.corso.VisualizzaCorso;
import it.unirc.bd.gui.dipendente.InserisciDipendente;
import it.unirc.bd.gui.dipendente.VisualizzaDipendente;
import it.unirc.bd.gui.evento.InserisciEvento;
import it.unirc.bd.gui.evento.PartecipazioneEvento;
import it.unirc.bd.gui.evento.RicercaEvento;
import it.unirc.bd.gui.evento.VisualizzaEvento;
import it.unirc.bd.gui.infortunio.InserisciInfortunio;
import it.unirc.bd.gui.infortunio.RicercaInfortunio;
import it.unirc.bd.gui.iscritto.InserisciIscritto;
import it.unirc.bd.gui.iscritto.RicercaIscritti;
import it.unirc.bd.gui.iscritto.RicercaIscritto;
import it.unirc.bd.gui.iscritto.VisualizzaAtleti;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;
import it.unirc.bd.gui.prenotazione.InserisciPrenotazione;
import it.unirc.bd.gui.prenotazione.VisualizzaPrenotazione;
import javafx.scene.image.Image;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MainGUI {
	String path = "C:\\Users\\emanuele\\Desktop\reggina.png";
	java.awt.Image img = Toolkit.getDefaultToolkit().createImage(path);
	 IscrittoDAOP iDAOP=new IscrittoDAOP();
	
	private JFrame frmHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmHome.setVisible(true);
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
		frmHome = new JFrame();
		frmHome.setTitle("Home");
		frmHome.setResizable(false);
		frmHome.setBounds(100, 100, 554, 341);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmHome.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmChiudi = new JMenuItem("Chiudi");
		mnFile.add(mntmChiudi);

		JMenu mnIscrittiatleti = new JMenu("Iscritti/Atleti");
		menuBar.add(mnIscrittiatleti);

		JMenu mnIscritto = new JMenu("Iscritto");
		mnIscrittiatleti.add(mnIscritto);

		JMenuItem mntmInserisciIscritto = new JMenuItem("Nuovo Iscritto");
		mntmInserisciIscritto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciIscritto inserisci = new InserisciIscritto(false, null); //METTO FALSE PERCH� NON � UNA MODIFICA e non devo passare nessun'ogetto di tipo Iscritto per eventuale modifica
				inserisci.setVisible(true); 
			}
		});
		mnIscritto.add(mntmInserisciIscritto);

		JMenuItem mntmRicercaIscritto = new JMenuItem("Ricerca Iscritto");
		mntmRicercaIscritto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaIscritti ric =new RicercaIscritti();
				ric.setVisible(true);
			}
		});
		mnIscritto.add(mntmRicercaIscritto);

		JMenuItem mntmVisualizzaIscritto = new JMenuItem("Visualizza Iscritto"); 
		mntmVisualizzaIscritto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizzaIscritto visualizza = new VisualizzaIscritto(iDAOP.getAll());
				visualizza.setVisible(true);
			}
		});
		mnIscritto.add(mntmVisualizzaIscritto);

		JMenu mnAtleta = new JMenu("Atleta");
		mnIscrittiatleti.add(mnAtleta);

		JMenuItem mntmCreaAtleta = new JMenuItem("Nuovo Atleta");
		mnAtleta.add(mntmCreaAtleta);

		JMenuItem mntmCercaAtleta = new JMenuItem("Cerca Atleta");
		mntmCercaAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaIscritto vis =new RicercaIscritto(true);
				vis.setVisible(true);
			}
		});
		mnAtleta.add(mntmCercaAtleta);

		JMenuItem mntmVisualizzaAtleti = new JMenuItem("Visualizza Atleti");
		mntmVisualizzaAtleti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizzaAtleti visualizza = new VisualizzaAtleti();
				visualizza.setVisible(true);
			}
		});
		mnAtleta.add(mntmVisualizzaAtleti);
		
		
		JMenu mnInfortunii = new JMenu("Infortunii");
		mnIscrittiatleti.add(mnInfortunii);

		JMenuItem mntmInserisciInfortunio = new JMenuItem("Inserisci Infortunio");
		mntmInserisciInfortunio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciInfortunio inserisci =  new InserisciInfortunio(false, null);
				inserisci.setVisible(true);
			}
		});
		mnInfortunii.add(mntmInserisciInfortunio);


		JMenuItem mntmVisualizzaInfortunio = new JMenuItem("Visualizza Infortunio");
		mntmVisualizzaInfortunio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VisualizzaInfortunio visualizza =  new VisualizzaInfortunio(null);
			//	visualizza.setVisible(true);
			}
		});
		
		JMenuItem mntmRicercaInfortunio = new JMenuItem("Ricerca Infortunio");
		mntmRicercaInfortunio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		mnInfortunii.add(mntmRicercaInfortunio);
		mnInfortunii.add(mntmVisualizzaInfortunio);

		
		JMenu mnDipendenti = new JMenu("Dipendenti");
		menuBar.add(mnDipendenti);

		JMenuItem mntmNuovoDipendente = new JMenuItem("Nuovo Dipendente");
		mntmNuovoDipendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciDipendente inserisci = new InserisciDipendente(false,null);
				inserisci.setVisible(true);
			}
		});
		mnDipendenti.add(mntmNuovoDipendente);

		JMenuItem mntmRicercaDipendente = new JMenuItem("Ricerca Dipendente");
		mnDipendenti.add(mntmRicercaDipendente);
		
		JMenuItem mntmVisualizzaDipendentei = new JMenuItem("Visualizza Dipendenti");
		mntmVisualizzaDipendentei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VisualizzaDipendente visualizza = new VisualizzaDipendente();
          // visualizza.setVisible(true);
			}
		});
		mnDipendenti.add(mntmVisualizzaDipendentei);
		
		JMenu mnCorsi = new JMenu("Corsi");
		menuBar.add(mnCorsi);

		JMenuItem mntmNuovoCorso = new JMenuItem("Nuovo Corso");
		mntmNuovoCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			InserisciCorso inserisci = new InserisciCorso();
			inserisci.setVisible(true);
			}
		});
		mnCorsi.add(mntmNuovoCorso);

		JMenuItem mntmRicercaCorsi = new JMenuItem("Ricerca Corsi");
		mnCorsi.add(mntmRicercaCorsi);

		JMenuItem mntmIscrizioneCorso = new JMenuItem("Iscrizione Corso");
		mntmIscrizioneCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IscrizioneCorso iscrizione = new IscrizioneCorso();
				iscrizione.setVisible(true);
			}
		});
		mnCorsi.add(mntmIscrizioneCorso);

		JMenuItem mntmVisualizzaCorso = new JMenuItem("Visualizza Corso");
		mntmVisualizzaCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*VisualizzaCorso visualizza = new VisualizzaCorso(null);
				visualizza.setVisible(true);*/
			}
		});
		mnCorsi.add(mntmVisualizzaCorso);
		
		JMenu mnEventi = new JMenu("Eventi");
		menuBar.add(mnEventi);

		JMenuItem mntmOrganizzaEvento = new JMenuItem("Organizza Evento");
		mntmOrganizzaEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciEvento inserisci = new InserisciEvento(false, null);
				inserisci.setVisible(true);
			}
		});
		mnEventi.add(mntmOrganizzaEvento);
		
		JMenuItem mntmRicercaEvento = new JMenuItem("Ricerca Evento");
		mntmRicercaEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaEvento ric=new RicercaEvento();
				ric.setVisible(true);
				
				
			}
		});
		mnEventi.add(mntmRicercaEvento);

		JMenuItem mntmPartecipazioneAdEvento = new JMenuItem("Partecipazione ad Evento");
		mntmPartecipazioneAdEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PartecipazioneEvento partecipa = new PartecipazioneEvento(false);
				partecipa.setVisible(true);
			}
		});
		mnEventi.add(mntmPartecipazioneAdEvento);

		JMenu mnPrenotazioni = new JMenu("Prenotazioni");
		menuBar.add(mnPrenotazioni);

		JMenuItem mntmNuovaPrenotazione = new JMenuItem("Nuova Prenotazione");
		mntmNuovaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciPrenotazione inserisci = new InserisciPrenotazione();
				inserisci.setVisible(true);
			}
		});
		mnPrenotazioni.add(mntmNuovaPrenotazione);

		JMenuItem mntmCercaeliminaPrenotazione = new JMenuItem("Cerca/Elimina Prenotazione");
		mnPrenotazioni.add(mntmCercaeliminaPrenotazione);
		

		JMenu mnStatistiche = new JMenu("Statistiche");
		menuBar.add(mnStatistiche);
		frmHome.getContentPane().setLayout(null);

		JLabel lblProssimeEventiIn = new JLabel("Prossimi eventi in programma:");
		lblProssimeEventiIn.setBounds(12, 13, 180, 16);
		frmHome.getContentPane().add(lblProssimeEventiIn);

		JLabel lblInfermieria = new JLabel("Infermeria:");
		lblInfermieria.setBounds(12, 178, 68, 16);
		frmHome.getContentPane().add(lblInfermieria);

		JPanel panelEvento1 = new JPanel();
		panelEvento1.setBounds(12, 42, 163, 60);
		panelEvento1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panelEvento1);
		panelEvento1.setLayout(null);

		JLabel lblTipoEvento1 = new JLabel("Tipo");
		lblTipoEvento1.setBounds(5, 5, 56, 16);
		panelEvento1.add(lblTipoEvento1);

		JLabel lblDataEvento1 = new JLabel("Data");
		lblDataEvento1.setBounds(5, 20, 56, 16);
		panelEvento1.add(lblDataEvento1);

		JLabel lblLivelloEvento1 = new JLabel("Livello");
		lblLivelloEvento1.setBounds(5, 36, 56, 16);
		panelEvento1.add(lblLivelloEvento1);

		JPanel panelEvento2 = new JPanel();
		panelEvento2.setBounds(187, 42, 163, 60);
		panelEvento2.setLayout(null);
		panelEvento2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panelEvento2);

		JLabel lblTipoEvento2 = new JLabel("Tipo");
		lblTipoEvento2.setBounds(5, 5, 56, 16);
		panelEvento2.add(lblTipoEvento2);

		JLabel lblDataEvento2 = new JLabel("Data");
		lblDataEvento2.setBounds(5, 20, 56, 16);
		panelEvento2.add(lblDataEvento2);

		JLabel lblLivelloEvento2 = new JLabel("Livello");
		lblLivelloEvento2.setBounds(5, 36, 56, 16);
		panelEvento2.add(lblLivelloEvento2);

		JPanel panel = new JPanel();
		panel.setBounds(362, 42, 163, 60);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panel);

		JLabel lblTipoEvento3 = new JLabel("Tipo");
		lblTipoEvento3.setBounds(5, 5, 56, 16);
		panel.add(lblTipoEvento3);

		JLabel lblDataEvento3 = new JLabel("Data");
		lblDataEvento3.setBounds(5, 20, 56, 16);
		panel.add(lblDataEvento3);

		JLabel lblLivelloEvento3 = new JLabel("Livello");
		lblLivelloEvento3.setBounds(5, 36, 56, 16);
		panel.add(lblLivelloEvento3);

		JPanel panelInfortunio1 = new JPanel();
		panelInfortunio1.setBounds(12, 207, 163, 60);
		panelInfortunio1.setLayout(null);
		panelInfortunio1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panelInfortunio1);

		JLabel lblnome1 = new JLabel("Nome");
		lblnome1.setBounds(5, 5, 56, 16);
		panelInfortunio1.add(lblnome1);

		JLabel lblCognome1 = new JLabel("Cognome");
		lblCognome1.setBounds(5, 20, 56, 16);
		panelInfortunio1.add(lblCognome1);

		JLabel lbl = new JLabel("Giorni rimasti:");
		lbl.setBounds(5, 36, 81, 16);
		panelInfortunio1.add(lbl);

		JLabel lblGiorni1 = new JLabel("??");
		lblGiorni1.setBounds(98, 36, 56, 16);
		panelInfortunio1.add(lblGiorni1);

		JPanel panelInfortunio2 = new JPanel();
		panelInfortunio2.setBounds(187, 207, 163, 60);
		panelInfortunio2.setLayout(null);
		panelInfortunio2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panelInfortunio2);

		JLabel lblnome2 = new JLabel("Nome");
		lblnome2.setBounds(5, 5, 56, 16);
		panelInfortunio2.add(lblnome2);

		JLabel lblCognome2 = new JLabel("Cognome");
		lblCognome2.setBounds(5, 20, 56, 16);
		panelInfortunio2.add(lblCognome2);

		JLabel label_2 = new JLabel("Giorni rimasti:");
		label_2.setBounds(5, 36, 81, 16);
		panelInfortunio2.add(label_2);

		JLabel lblGiorni2 = new JLabel("??");
		lblGiorni2.setBounds(98, 36, 56, 16);
		panelInfortunio2.add(lblGiorni2);

		JPanel panelInfortunio3 = new JPanel();
		panelInfortunio3.setBounds(362, 207, 163, 60);
		panelInfortunio3.setLayout(null);
		panelInfortunio3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frmHome.getContentPane().add(panelInfortunio3);

		JLabel lblnome3 = new JLabel("Nome");
		lblnome3.setBounds(5, 5, 56, 16);
		panelInfortunio3.add(lblnome3);

		JLabel lblCognome3 = new JLabel("Cognome");
		lblCognome3.setBounds(5, 20, 56, 16);
		panelInfortunio3.add(lblCognome3);

		JLabel label_6 = new JLabel("Giorni rimasti:");
		label_6.setBounds(5, 36, 81, 16);
		panelInfortunio3.add(label_6);

		JLabel lblGiorni3 = new JLabel("??");
		lblGiorni3.setBounds(98, 36, 56, 16);
		panelInfortunio3.add(lblGiorni3);
		
		JPanel panel_1 = new JPanel();
		ImageIcon icon = new ImageIcon("path"); 
		JLabel label = new JLabel(icon);
		panel_1.setBounds(212, 115, 99, 78);
		panel_1.add(label);
		panel_1.validate();
		frmHome.getContentPane().add(panel_1);
		
	}
}
