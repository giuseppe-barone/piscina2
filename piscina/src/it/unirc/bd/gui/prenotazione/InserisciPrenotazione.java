package it.unirc.bd.gui.prenotazione;

import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.Prenotazione;
import it.unirc.bd.dao.beans.PrenotazioneDAOP;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class InserisciPrenotazione extends JDialog {
	IscrittoDAOP iDAOP =new IscrittoDAOP();
	DipendenteDAOP dDAOP=new DipendenteDAOP();
	PrenotazioneDAOP pDAOP = new PrenotazioneDAOP();
	//VARIABILI DA PASSARE ALLA QUERY
	private int corsia;
	Integer  idPrenotazione=null;
	private int idIscritto;
	private int ora;
	private int idDipendente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciPrenotazione dialog = new InserisciPrenotazione(false,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciPrenotazione(boolean modifica, Prenotazione prenotazione) {
		setModal(true);
		setTitle("Inserimento Prenotazione");
		setBounds(100, 100, 522, 263);
		getContentPane().setLayout(null);

		JLabel lblCorsia = new JLabel("Corsia");
		lblCorsia.setBounds(12, 13, 56, 16);
		getContentPane().add(lblCorsia);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(12, 72, 56, 16);
		getContentPane().add(lblData);

		JLabel lblIdIsciritto = new JLabel("Richiedente");
		lblIdIsciritto.setBounds(12, 107, 68, 16);
		getContentPane().add(lblIdIsciritto);

		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(12, 43, 56, 16);
		getContentPane().add(lblOra);

		JLabel lblIdDipendente = new JLabel("Dipendente");
		lblIdDipendente.setBounds(12, 142, 92, 16);
		getContentPane().add(lblIdDipendente);

		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setVisible(false);
		btnInserisci.setBounds(139, 174, 97, 25);
		getContentPane().add(btnInserisci);
		
		JSpinner listaCorsie = new JSpinner();
		listaCorsie.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		listaCorsie.setBounds(80, 10, 97, 22);
		getContentPane().add(listaCorsie);
		
		JComboBox listaOre = new JComboBox();
		listaOre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listaOre.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "15", "16", "17", "18", "19"}));
		listaOre.setBounds(80, 40, 97, 22);
		getContentPane().add(listaOre);
		
		JComboBox<Iscritto> comboIscritto = new JComboBox();
		comboIscritto.setModel(iDAOP.getIscritticb());
		comboIscritto.setBounds(108, 104, 384, 22);
		getContentPane().add(comboIscritto);
		
		JComboBox<Dipendente> comboDipendente = new JComboBox<Dipendente>();
		Vector<Dipendente> vettore=dDAOP.RicercaPerTipologia(0);
		comboDipendente.setModel(dDAOP.ModelloCombobox(vettore));
		comboDipendente.setBounds(108, 139, 384, 22);
		getContentPane().add(comboDipendente);
		
		JDateChooser calendario = new JDateChooser();
		calendario.setBounds(80, 69, 98, 22);
		getContentPane().add(calendario);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idPrenotazione=prenotazione.getIdPrenotazione();
				corsia = (int) listaCorsie.getValue();
				Date data =  new java.sql.Date(calendario.getDate().getTime()) ;
				Iscritto i=(Iscritto)comboIscritto.getModel().getElementAt(comboIscritto.getSelectedIndex());
				idIscritto = i.getIdIscritto();
				ora = Integer.valueOf((String) listaOre.getSelectedItem());
				Dipendente d= (Dipendente) comboDipendente.getModel().getElementAt(comboDipendente.getSelectedIndex());
				idDipendente= d.getIdDipendente();
				System.out.println("corsia: "+corsia);
				System.out.println(data.toString());
				System.out.println("iscritto "+idIscritto);
				System.out.println("ora: "+ora);
				System.out.println("dipendente: "+idDipendente);
				Prenotazione p = new Prenotazione(idPrenotazione, corsia,data, idIscritto, ora, idDipendente);
				if (controlloCampiOperazione(corsia, ora, data)==0) {
				if(pDAOP.ModificaPrenotazione(p)==true)
					JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
				else
					JOptionPane.showMessageDialog(null, "MODIFICA FALLITA");
				}
				dispose();
			}
		});
		btnModifica.setBounds(263, 174, 97, 25);
		btnModifica.setVisible(false);
		getContentPane().add(btnModifica);
		if (modifica) {
			btnModifica.setVisible(true);
			listaCorsie.setValue(prenotazione.getCorsia());
			listaOre.setSelectedItem(prenotazione.getOra());
			calendario.setDate(prenotazione.getData());
			
		}
		else
			btnInserisci.setVisible(true);
								
		
		
									//LISTNER
		//INSERIMENTO
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				corsia = (int) listaCorsie.getValue();
				Date data =  new java.sql.Date(calendario.getDate().getTime()) ;
				Iscritto i=(Iscritto)comboIscritto.getModel().getElementAt(comboIscritto.getSelectedIndex());
				idIscritto = i.getIdIscritto();
				ora = Integer.valueOf((String) listaOre.getSelectedItem());
				Dipendente d= (Dipendente) comboDipendente.getModel().getElementAt(comboDipendente.getSelectedIndex());
				idDipendente= d.getIdDipendente();
				System.out.println("corsia: "+corsia);
				System.out.println(data.toString());
				System.out.println("iscritto "+idIscritto);
				System.out.println("ora: "+ora);
				System.out.println("dipendente: "+idDipendente);
				Prenotazione p = new Prenotazione();
				p.setCorsia(corsia);
				p.setData(data);
				p.setIdIscritto(idIscritto);
				p.setOra(ora);
				p.setIdDipendente(idDipendente); 
				if (controlloCampiOperazione(corsia, ora, data)==0) {
				if(pDAOP.salva(p)==true)
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
				}
				dispose();	
			}
		});
		
	}
	
	public int controlloCampiOperazione(int corsia, int ora, Date data) {	//QUESTO METODO RITORNA 0 SE è CONSENTITO PROCEDERE CON LA QUERY ALTRIMENTI NON DA IL CONSENSO A MANDARE LA QUERY
		int result ;
		if (data.equals(null)) {	//SE NON CE LA DATA
			result=1;
			JOptionPane.showMessageDialog(null, "NON SONO STATI COMPILATI TUTTI I CAMPI OBBLIGATORI \n Data di prenotazione");
		}
		else {	 //TUTTI I CAMPI SONO STATI COMPILATI, MESSAGGIO DI RIEPILOGO E CONFERMA
		    result= JOptionPane.showConfirmDialog (null, "I dati obbligatori da te inseriti sono:\n Corsia: "+corsia+";\n Ora: "+ora+";\n Data di prenotazione: "+data.toString(),"RIEPILOGO",JOptionPane.YES_NO_OPTION);
		}
		System.out.println("il valore della selezione è: "+Integer.toString(result));
		return result;
	}
	
}
