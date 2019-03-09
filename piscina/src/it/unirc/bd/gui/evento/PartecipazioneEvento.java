package it.unirc.bd.gui.evento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.Partecipazione;
import it.unirc.bd.dao.beans.PartecipazioneDAOP;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;

public class PartecipazioneEvento extends JDialog {
	//VALORI DA PASSARE ALLA QUERY
	int idEvento;
	int MatricolaFin;
	int Posizione;
	String Categoria;
	//OGETTI DAOP
	PartecipazioneDAOP pDAOP=new PartecipazioneDAOP();
	IscrittoDAOP iDAOP=new IscrittoDAOP();
	EventoDAOP eDAOP=new EventoDAOP();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPosizione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PartecipazioneEvento dialog = new PartecipazioneEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PartecipazioneEvento() {
		setTitle("Partecipazione evento");
		setBounds(100, 100, 422, 178);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JLabel lblIdEvento = new JLabel("Id Evento:");
		lblIdEvento.setBounds(12, 13, 58, 16);
		contentPanel.add(lblIdEvento);

		JLabel lblMatricolaFin = new JLabel("Matricola Fin:");
		lblMatricolaFin.setBounds(12, 41, 78, 16);
		contentPanel.add(lblMatricolaFin);



		JLabel lblNewLabel = new JLabel("Posizione:");
		lblNewLabel.setBounds(12, 70, 58, 16);
		contentPanel.add(lblNewLabel);

		txtPosizione = new JTextField();
		txtPosizione.setBounds(101, 67, 66, 22);
		contentPanel.add(txtPosizione);
		txtPosizione.setColumns(10);

		JButton btnInserisci = new JButton("Inserisci");

		btnInserisci.setBounds(92, 102, 97, 25);
		contentPanel.add(btnInserisci);


		//----COMBOBOX CONTENENTI I DATI DEL DB----
		//----USO DEI DefaultComboBoxModel DI TIPO OGETTO perchè così quando scelgo un ogetto all'interno della controllbox il suo 
		//indice sarà uguale all'indice dell'ogetto all'interno del DefaultComboBoxModel e da quest'ultimo potro ricavare i dati di mio interesse
		//con i metodi standard get

		/*QUESTO CAMPO è PARAMETRIZATO STRINGA PERCHè IL SUO MODEL SARà
		 * UN VETTORE DI STRINGHE CHE GLI PASSIAMO CON DENTRO TUTTI I DATI PER IDENTIFICARE UN ATLETA*/

		//----L'OGETTO ISCRITTO HA DI DEFOULT TOSTRING() CHE STAMPA VALORI CHE A ME NON INTERESSANO
		//PER TALE MOTIVO DICHIARO L'OGETTO DI TIPO STRING
		
		JComboBox<String> cbMatricolaFin = new JComboBox<String>();	
		//HO CREATO UN METODO CHE MI CONVERTE IL DefaultComboBoxModel DA OGETTO A STRING
		cbMatricolaFin.setModel(AtletaStringcb(iDAOP.getAtleticb()));
		cbMatricolaFin.setBounds(102, 38, 290, 22);
		contentPanel.add(cbMatricolaFin);

		//----QUESTO CAMPO è LASCIATO PARAMETRIZATO OGETTO PERCHè DI DEFAULT ECLIPS RICHIAMA IL METODO TOSTRING() CHE IO IN EVENTO HO MODIFICATO
		
		JComboBox<Evento> cbIdEvento = new JComboBox<Evento>();
		cbIdEvento.setBounds(102, 10, 290, 22);
		cbIdEvento.setModel(eDAOP.getEventicb());
		contentPanel.add(cbIdEvento);



		//----LISTNER----

		//AQUISIZIONE DATI DA BOTTONE
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//prelevo ID evento copio i risultai della ricerca degli eventi in un vector per risalire all'id di quello selezionato
				Evento evento =getEvento(cbIdEvento.getModel(), cbIdEvento.getSelectedIndex());
				idEvento=evento.getIdEvento();
				System.out.println("Id Evento: "+Integer.toString(idEvento));
				//prelevo MatricolaFin Iscritto copio i risultai della ricerca degli eventi in un vector per risalire all'id di quello selezionato
				Iscritto iscritto =getIscritto(iDAOP.getAtleticb(), cbMatricolaFin.getSelectedIndex());
				MatricolaFin=iscritto.getMatricolaFIN();
				System.out.println("Matricola Fin: "+Integer.toString(MatricolaFin));
				Posizione=Integer.parseInt(txtPosizione.getText());
				System.out.println("Posizione: "+Posizione);
				//----PER LA CATEGORIA BISOGNA METTERE UN CONTROLLO CHE IN BASE ALLA DATA DI NASCITA DELL'ATLETA CI DICA QUANTI ANNI HA E CALCOLI LA CATEGORIA
				Categoria=CalcoloCategoria(iscritto);
				System.out.println("Categoria: "+Categoria);
				//ADESSO CHE HO TUTTI I DATI CREO UN OGETTO E LO PASSO AL DAOP
				Partecipazione p=new Partecipazione(idEvento,MatricolaFin,Posizione,Categoria);
				if (pDAOP.salvaEvento(p))
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
			}
		});




	}



	//----CON QUESTO METODO FACCIO SI CHE NELLA COMBOBOX SI POSSANO VISUALIZZARE I DATI SUFFICENTI PER INDIVIDUARE L'ATLETA


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
	public Evento getEvento(ComboBoxModel<Evento> model, int indice) {
		Evento risultato= new Evento();
		Vector<Evento> vettoreEventi =new Vector<Evento>();
		for (int i=0;i<model.getSize();i++) {
			vettoreEventi.add(model.getElementAt(i));
		}
		risultato=vettoreEventi.get(indice);
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


	//CALOCLO DELLA CATEGORIA IN BASE ALLA DATA DI NASCITA DELL'ATLETA
	
	public String CalcoloCategoria(Iscritto i) {
		String risultato = null;
		int eta = 0;
		String Sesso=i.getSesso();
		boolean isMaschio;
		if (i.getSesso().equals("Maschio"))
			isMaschio=true;
		else
			isMaschio=false;
		//--------------CODICE PER IL CALCOLO DELL'ETA DA INSERIRE A PARTE PER PEPPE----------------
		LocalDate corrente=LocalDate.now();
		Date nascita=i.getDataNascita();
		LocalDate LNascita=nascita.toLocalDate();
		//System.out.println(LNascita.toString());

		//System.out.println(corrente.toString());
		if ((corrente != null) && (nascita != null)) {
          // System.out.println(Period.between(LNascita, corrente).getYears());
           eta=Period.between(LNascita, corrente).getYears();
           
        }
		if (isMaschio) {	//SE è MASCHIO
			if (eta<14)
				risultato="Esordienti";
			else if (eta<17)
				risultato="Ragazzi";
			else if (eta<21)
				risultato="Cadetti";
			else
				risultato="Seniores";
		}
		else {				//SE NON è MASCIO
			if (eta<13)
				risultato="Esordienti";
			else if (eta<15)
				risultato="Ragazzi";
			else if (eta<19)
				risultato="Cadetti";
			else
				risultato="Seniores";
		}
		return risultato;
	}

}
