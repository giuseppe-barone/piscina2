package it.unirc.bd.gui.evento;

import java.awt.EventQueue;

import javax.swing.JDialog;

import it.unirc.bd.dao.beans.Partecipazione;
import it.unirc.bd.dao.beans.PartecipazioneDAOP;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class RicercaPartecipazione extends JDialog {
	PartecipazioneDAOP pDAOP=new PartecipazioneDAOP();
	Vector<Partecipazione> elencoPartecipazioni;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaPartecipazione dialog = new RicercaPartecipazione();
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
	public RicercaPartecipazione() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<String[]> v=pDAOP.assembler(pDAOP.getTuttePartecipazioni());
					for (String[] s: v)
						System.out.println(s);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		});
		btnNewButton.setBounds(93, 82, 145, 86);
		getContentPane().add(btnNewButton);

	}
}
