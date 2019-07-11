package it.unirc.bd.gui.evento;

import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class InserisciEvento extends JDialog {
	private String Tipo;
	private String Livello;
	private Date Data;
	EventoDAOP eDAOP=new EventoDAOP();
	CorsoDAOP cDAOP=new CorsoDAOP();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciEvento dialog = new InserisciEvento(false,null);
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
	public InserisciEvento(boolean modifica, Evento ev) {
		setModal(true);
		setTitle("Inserisci Evento");
		setBounds(100, 100, 266, 194);
		getContentPane().setLayout(null);

		JComboBox<String> comboLivello = new JComboBox<String>();
		comboLivello.setModel(new DefaultComboBoxModel(new String[] {"Provinciale", "Regionale", "Nazionale"}));
		comboLivello.setBounds(56, 45, 155, 22);
		getContentPane().add(comboLivello);

		JComboBox<String> comboTipo = new JComboBox<String>();
		comboTipo.setModel(cDAOP.ElencoTipi());	 	
		comboTipo.setBounds(56, 74, 155, 22);
		getContentPane().add(comboTipo);

		JDateChooser campoData = new JDateChooser();
		campoData.setBounds(56, 13, 155, 22);
		getContentPane().add(campoData);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(12, 19, 56, 16);
		getContentPane().add(lblData);

		JLabel lblLivello = new JLabel("Livello:");
		lblLivello.setBounds(12, 48, 50, 16);
		getContentPane().add(lblLivello);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 77, 41, 16);
		getContentPane().add(lblTipo);

		JButton buttonInserisci = new JButton("Inserisci");
		buttonInserisci.setVisible(false);
		buttonInserisci.setBounds(12, 109, 97, 25);
		getContentPane().add(buttonInserisci);

		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setVisible(false);
		buttonModifica.setBounds(137, 109, 97, 25);
		getContentPane().add(buttonModifica);

		if (modifica) {
			buttonModifica.setVisible(true);
			campoData.setDate(ev.getData());
			comboLivello.setSelectedItem(ev.getLivello());
			comboTipo.setSelectedItem(ev.getTipo());
			System.out.println(ev);
			
		}
		else
			buttonInserisci.setVisible(true);

		
		//LISTNER
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tipo=comboTipo.getItemAt(comboTipo.getSelectedIndex());
				Livello=comboLivello.getItemAt(comboLivello.getSelectedIndex());
				java.util.Date utilDate=campoData.getDate();
				Data=new Date(utilDate.getTime());
				System.out.println(Data.toString());
				System.out.println(Tipo+"\n"+Livello);
				Evento evento=new Evento(null,Data,Livello,Tipo);
				if (eDAOP.salvaEvento(evento))
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
				dispose();
				
			}
		});
		
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo=comboTipo.getItemAt(comboTipo.getSelectedIndex());
				Livello=comboLivello.getItemAt(comboLivello.getSelectedIndex());
				//INIZIO TRATTAMENTO DATA
				java.util.Date utilDate = campoData.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				Data=sqlDate;
				System.out.println(Data.toString());
				//FINE TRATTAMENTO DATA
				
				Evento evento=new Evento(ev.getIdEvento(),Data,Livello,Tipo);
				System.out.println(evento);
				System.out.println(evento.getIdEvento());
				
				if (eDAOP.ModificaEvento(evento))
					JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
				else
					JOptionPane.showMessageDialog(null, "MODIFICA FALLITA");
				dispose();
			}
			
		});


	}
}
