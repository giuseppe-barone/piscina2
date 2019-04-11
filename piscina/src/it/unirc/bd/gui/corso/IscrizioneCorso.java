package it.unirc.bd.gui.corso;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import javax.swing.JButton;


import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IscrizioneCorso extends JDialog {
	IscrittoDAOP iDAOP=new IscrittoDAOP();
	CorsoDAOP cDAOP=new CorsoDAOP();
	int IdIscritto;
	int IdCorso;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IscrizioneCorso dialog = new IscrizioneCorso();
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
	public IscrizioneCorso() {
		setTitle("Inserisci Iscrizione");
		setBounds(100, 100, 406, 156);
		getContentPane().setLayout(null);
		
		JLabel lblIdcorso = new JLabel("IdCorso:");
		lblIdcorso.setBounds(12, 13, 49, 16);
		getContentPane().add(lblIdcorso);
		
		JComboBox<Corso> comboCorso = new JComboBox<Corso>();
		comboCorso.setModel(cDAOP.getCorsoCb());
		comboCorso.setBounds(73, 10, 288, 22);
		getContentPane().add(comboCorso);
		
		JLabel lblNewLabel = new JLabel("IdIscritto:");
		lblNewLabel.setBounds(12, 42, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JButton btnInserisci = new JButton("Inserisci");
		
		btnInserisci.setBounds(141, 74, 97, 25);
		getContentPane().add(btnInserisci);
		
		JComboBox<Iscritto> comboIscritto = new JComboBox<Iscritto>();
		comboIscritto.setModel(iDAOP.getIscritticb());
		comboIscritto.setBounds(73, 39, 288, 22);
		getContentPane().add(comboIscritto);
		
		
		
		
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Corso c=cDAOP.getCorsoCb().getElementAt(comboCorso.getSelectedIndex());
				IdCorso=c.getIdCorso();
				Iscritto i =iDAOP.getIscritticb().getElementAt(comboIscritto.getSelectedIndex());
				IdIscritto=i.getIdIscritto();
				
				int indice =  JOptionPane.showConfirmDialog (null, "I dati obbligatori da te inseriti sono:\n ISCRITTO:\n IdIscritto: "+Integer.toString(i.getIdIscritto())+";\n Nominativo: "+i.getNome()+ " " +i.getCognome()+";\n Data di nascita: "+i.getDataNascita().toString()+"\n ___________________________"+ "\n CORSO: \n Id Corso: "+ Integer.toString(c.getIdCorso())+" Tipo: "+c.getTipo(),"RIEPILOGO",JOptionPane.YES_NO_OPTION);
				if (indice==0) {
					if (cDAOP.salvaIscrizioneCorso(IdCorso, IdIscritto)) 
					JOptionPane.showMessageDialog(null, "ISCRIZIONE RIUSCITA");
					else
					JOptionPane.showMessageDialog(null, "ISCRIZIONE FALLITA");
				}
			
			}
		});
		

	}
	
	
	


	
	
	
	
}
