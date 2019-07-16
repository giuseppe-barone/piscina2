
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
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.InfortunioDAOP;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.PartecipazioneDAOP;
import it.unirc.bd.gui.corso.InserisciCorso;
import it.unirc.bd.gui.corso.IscrizioneCorso;
import it.unirc.bd.gui.corso.RicercaCorso;
import it.unirc.bd.gui.corso.VisualizzaCorso;
import it.unirc.bd.gui.dipendente.InserisciDipendente;
import it.unirc.bd.gui.dipendente.RicercaDipendente;
import it.unirc.bd.gui.dipendente.VisualizzaDipendente;
import it.unirc.bd.gui.evento.InserisciEvento;
import it.unirc.bd.gui.evento.PartecipazioneEvento;
import it.unirc.bd.gui.evento.RicercaEvento;
import it.unirc.bd.gui.evento.RicercaPartecipazione;
import it.unirc.bd.gui.evento.VisualizzaEvento;
import it.unirc.bd.gui.infortunio.InserisciInfortunio;
import it.unirc.bd.gui.infortunio.RicercaInfortunio;
import it.unirc.bd.gui.infortunio.VisualizzaInfo;
import it.unirc.bd.gui.iscritto.InserisciIscritto;
import it.unirc.bd.gui.iscritto.RicercaIscritti;
import it.unirc.bd.gui.iscritto.RicercaIscritto;
import it.unirc.bd.gui.iscritto.VisualizzaAtleti;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;
import it.unirc.bd.gui.prenotazione.InserisciPrenotazione;
import it.unirc.bd.gui.prenotazione.RicercaPrenotazioni;
import it.unirc.bd.gui.prenotazione.VisualizzaPrenotazione;
import it.unirc.bd.gui.statistiche.statisticheAtleti;
import javafx.scene.image.Image;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainGUI {
	String path = "C:\\Users\\emanuele\\Desktop\reggina.png";
	java.awt.Image img = Toolkit.getDefaultToolkit().createImage(path);
	 IscrittoDAOP iDAOP=new IscrittoDAOP();
	 private JTable table;
	 InfortunioDAOP infoDAOP =new InfortunioDAOP();
	 PartecipazioneDAOP pDAOP =new PartecipazioneDAOP();
	
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
				InserisciIscritto inserisci = new InserisciIscritto(false, null); //METTO FALSE PERCHè NON è UNA MODIFICA e non devo passare nessun'ogetto di tipo Iscritto per eventuale modifica
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
				VisualizzaInfo visualizza;
				try {
					visualizza = new VisualizzaInfo(infoDAOP.getTutti());
					visualizza.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmRicercaInfortunio = new JMenuItem("Ricerca Infortunio");
		mntmRicercaInfortunio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RicercaInfortunio ricercainfo = new RicercaInfortunio();
					ricercainfo.setVisible(true);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
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
		mntmRicercaDipendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RicercaDipendente ricerca= new RicercaDipendente();
				ricerca.setVisible(true);
			}
		});
		mnDipendenti.add(mntmRicercaDipendente);
		
		JMenu mnCorsi = new JMenu("Corsi");
		menuBar.add(mnCorsi);

		JMenuItem mntmNuovoCorso = new JMenuItem("Nuovo Corso");
		mntmNuovoCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			InserisciCorso inserisci = new InserisciCorso(false, null);
			inserisci.setVisible(true);
			}
		});
		mnCorsi.add(mntmNuovoCorso);

		JMenuItem mntmRicercaCorsi = new JMenuItem("Ricerca Corsi");
		mntmRicercaCorsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RicercaCorso ricerca= new RicercaCorso();
				ricerca.setVisible(true);
			}
		});
		mnCorsi.add(mntmRicercaCorsi);

		JMenuItem mntmIscrizioneCorso = new JMenuItem("Iscrizione Corso");
		mntmIscrizioneCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IscrizioneCorso iscrizione = new IscrizioneCorso();
				iscrizione.setVisible(true);
			}
		});
		mnCorsi.add(mntmIscrizioneCorso);
		
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
		
		JMenuItem mntmRicercaPartecipazione = new JMenuItem("Ricerca Partecipazione");
		mntmRicercaPartecipazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaPartecipazione ricerca =new RicercaPartecipazione();
				ricerca.setVisible(true);
			}
		});
		mnEventi.add(mntmRicercaPartecipazione);
		
		JMenuItem mntmStatistiche = new JMenuItem("Statistiche");
		mntmStatistiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statisticheAtleti statistiche =new statisticheAtleti();
				statistiche.setVisible(true);
			}
		});
		
		JMenu mnPrenotazioni = new JMenu("Prenotazioni");
		menuBar.add(mnPrenotazioni);
		
		JMenuItem mntmNuovaPrenotazione = new JMenuItem("Nuova Prenotazione");
		mntmNuovaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InserisciPrenotazione visualizza=new InserisciPrenotazione(false,null);
				visualizza.setVisible(true);
			}
		});
		mnPrenotazioni.add(mntmNuovaPrenotazione);
		
		JMenuItem mntmRicercaPrenotazioni = new JMenuItem("Ricerca Prenotazioni");
		mntmRicercaPrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RicercaPrenotazioni ricerca=new RicercaPrenotazioni();
				ricerca.setVisible(true);
			}
		});
		mnPrenotazioni.add(mntmRicercaPrenotazioni);
		menuBar.add(mntmStatistiche);
		frmHome.getContentPane().setLayout(null);
		
		JLabel lblAvvisi = new JLabel("Avvisi");
		lblAvvisi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvvisi.setBounds(12, 13, 524, 25);
		frmHome.getContentPane().add(lblAvvisi);

		JPanel panel = new JPanel();
		panel.setBounds(12, 51, 524, 216);
		frmHome.getContentPane().add(panel);
		panel.setLayout(null);
		table = new JTable();
		Vector<String[]> vettore=null;
		if (pDAOP.getPartecipazioniDaAggiornare().size()>=1) {	//QUESTI CONTROLLI MI SERVONO PER MOSTRARE LE PARTECIPAZIONI DA AGGIORNARE
			vettore =pDAOP.getPartecipazioniDaAggiornare();
			lblAvvisi.setText("Ci sono partecipazioni da aggiornare!!");
			lblAvvisi.setForeground(Color.red);
			load(vettore);
		}
		else {
			panel.setVisible(false);
			lblAvvisi.setText("Non ci sono partecipazioni da aggiornare.");
			lblAvvisi.setForeground(Color.green);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 524, 216);
		panel.add(scrollPane);

		
	}
	
	
	
	private void load(Vector<String[]> list) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[7];
		columnsName[0] = "Data";
		columnsName[1] = "Tipo";
		columnsName[2] = "Livello";
		columnsName[3] = "Nome";
		columnsName[4] = "Cognome";
		columnsName[5] = "DataDiNascita";
		columnsName[6] = "Categoria";
		model.setColumnIdentifiers(columnsName);
	
		
		System.out.println(list);
		Object rowData[] = new Object[7]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a)[0];
			rowData[1] = list.elementAt(a)[1];
			rowData[2] = list.elementAt(a)[2];
			rowData[3] = list.elementAt(a)[3];
			rowData[4] = list.elementAt(a)[4];
			rowData[5] = list.elementAt(a)[5];
			rowData[6] = list.elementAt(a)[6];
			model.addRow(rowData);
		}
		table.setModel(model);
	}
	
	
	
}
