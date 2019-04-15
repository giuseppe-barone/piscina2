package it.unirc.bd.gui.iscritto;

import java.awt.EventQueue;

import javax.swing.JDialog;



import it.unirc.bd.dao.beans.EventoDAOP;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class RicercaPartecipazioni extends JDialog {
	EventoDAOP eDAOP=new EventoDAOP();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaPartecipazioni dialog = new RicercaPartecipazioni();
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
	public RicercaPartecipazioni() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("AVVIA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<String[]> v =eDAOP.getTuttePartecipazioni();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(104, 59, 210, 117);
		getContentPane().add(btnNewButton);

	}

}
