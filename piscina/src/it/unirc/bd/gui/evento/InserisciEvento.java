package it.unirc.bd.gui.evento;

import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

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
	public InserisciEvento(boolean modifica, Evento e) {
		setTitle("Inserisci Evento");
		setBounds(100, 100, 266, 194);
		getContentPane().setLayout(null);

		JComboBox<String> comboLivello = new JComboBox<String>();
		comboLivello.setModel(new DefaultComboBoxModel(new String[] {"Provinciale", "Regionale", "Nazionale"}));
		comboLivello.setBounds(56, 45, 155, 22);
		getContentPane().add(comboLivello);

		JComboBox<String> comboTipo = new JComboBox<String>();
		comboTipo.setModel(eDAOP.ElencoTipi());
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
		buttonInserisci.setBounds(12, 109, 97, 25);
		getContentPane().add(buttonInserisci);

		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setBounds(137, 109, 97, 25);
		getContentPane().add(buttonModifica);

		if (modifica) {
			buttonModifica.setVisible(true);
			buttonInserisci.setVisible(false);
		}
		else {
			buttonModifica.setVisible(false);
			buttonInserisci.setVisible(true);
		}
		//LISTNER
		buttonInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tipo=comboTipo.getItemAt(comboTipo.getSelectedIndex());
				Livello=comboLivello.getItemAt(comboLivello.getSelectedIndex());
				//long l=campoData.getDate().getTime();
				/*
				//INIZIO TRATTAMENTO DATA
				java.util.Date dat=campoData.getDate();
				Data.valueOf(""+(dat.getYear()+1900)+"-"+dat.getMonth()+"-"+dat.getDay()+"");
				//Data.setTime(l);				
				//Data= (Date)campoData.getDate();
				java.util.Date utilDate = campoData.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				Data=sqlDate;
				System.out.println(Data.toString());
				//FINE TRATTAMENTO DATA
				*/
				java.util.Date utilDate=campoData.getDate();
			//	long l1 = utilDate.getTime();
				Data=new Date(utilDate.getTime());
			//	Data=Date.valueOf(textData.getText()) ;
			//	long l2 =Data.getTime();
			//	long l3=l2-l1;
			//	System.out.println("la prima data è: "+l1+"\n la seconda data è: "+l2+"\n la differenza è: "+l3);
				System.out.println(Data.toString());
				System.out.println(Tipo+"\n"+Livello);
				Evento evento=new Evento(null,Data,Livello,Tipo);
				if (eDAOP.salvaEvento(evento))
					JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
				else
					JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
			}
		});
		
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo=comboTipo.getItemAt(comboTipo.getSelectedIndex());
				Livello=comboLivello.getItemAt(comboLivello.getSelectedIndex());
				//long l=campoData.getDate().getTime();
				//INIZIO TRATTAMENTO DATA
				java.util.Date dat=campoData.getDate();
				Data.valueOf(""+(dat.getYear()+1900)+"-"+dat.getMonth()+"-"+dat.getDay()+"");
				//Data.setTime(l);				
				//Data= (Date)campoData.getDate();
				java.util.Date utilDate = campoData.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				Data=sqlDate;
				System.out.println(Data.toString());
				//FINE TRATTAMENTO DATA
				System.out.println(Tipo+"\n"+Livello);
				Evento evento=new Evento(null,Data,Livello,Tipo);
				if (eDAOP.salvaEvento(evento))
					JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
				else
					JOptionPane.showMessageDialog(null, "MODIFICA FALLITA");
			}
			
		});


	}
}
