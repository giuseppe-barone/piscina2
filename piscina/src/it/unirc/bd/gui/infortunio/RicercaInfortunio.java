package it.unirc.bd.gui.infortunio;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JRadioButton;

import it.unirc.bd.dao.beans.InfortunioDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;


import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class RicercaInfortunio extends JDialog {
	IscrittoDAOP iscrittoDAOP= new IscrittoDAOP();
	InfortunioDAOP infortunioDAOP =new InfortunioDAOP();
	private Integer MatricolaFin;
	private String Categoria;
	private int Gravita;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaInfortunio dialog = new RicercaInfortunio();
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
	public RicercaInfortunio() throws SQLException{
		setBounds(100, 100, 415, 244);
		getContentPane().setLayout(null);
		
		JRadioButton radioAtleta = new JRadioButton("Atleta");
		radioAtleta.setBounds(8, 28, 83, 25);
		getContentPane().add(radioAtleta);
		
		JRadioButton radioGravita = new JRadioButton("Gravit\u00E0");
		radioGravita.setBounds(8, 58, 69, 25);
		getContentPane().add(radioGravita);
		
		JRadioButton radioCategoria = new JRadioButton("Categoria");
		radioCategoria.setBounds(8, 88, 83, 25);
		getContentPane().add(radioCategoria);
		
		JComboBox<Iscritto> comboAtleta = new JComboBox<Iscritto>();
		comboAtleta.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta.setBounds(99, 29, 286, 22);
		getContentPane().add(comboAtleta);
		
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		comboCategoria.setBounds(99, 89, 286, 22);
		getContentPane().add(comboCategoria);
		
		JComboBox comboGravita = new JComboBox();
		comboGravita.setModel(new DefaultComboBoxModel(new String[] {"Lieve", "Media", "Elevata"}));
		comboGravita.setBounds(99, 59, 286, 22);
		getContentPane().add(comboGravita);
		
		JRadioButton radioTutti = new JRadioButton("Tutti");
		radioTutti.setBounds(8, 118, 127, 25);
		getContentPane().add(radioTutti);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vector<String[]> risultato=null;
				if (radioAtleta.isSelected()) {
					MatricolaFin=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta.getSelectedIndex()).getMatricolaFIN();		 				
					risultato=infortunioDAOP.getIscrittoId(MatricolaFin);
				}
				if (radioGravita.isSelected()) {
					Gravita=comboGravita.getSelectedIndex()+1;
					risultato=infortunioDAOP.getGravita(Gravita);
				}
				if (radioTutti.isSelected()) {
					risultato=infortunioDAOP.getTutti();
				}
				if (radioCategoria.isSelected()) {
					Categoria=(String)comboCategoria.getSelectedItem();
					System.out.println(Categoria);
					risultato=infortunioDAOP.getCategoria(Categoria);
				}
						VisualizzaInfo vis =new VisualizzaInfo(risultato);

						vis.setVisible(true);	
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	vis.setVisible(true);			
				
					
			}
		});
		btnCerca.setBounds(99, 152, 97, 25);
		getContentPane().add(btnCerca);

	}
}
