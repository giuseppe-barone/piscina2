package it.unirc.bd.gui.corso;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class IscrizioneCorso extends JDialog {
	private JTextField textIdIscritto;

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
ciao lele
	/**
	 * Create the dialog.
	 */
	public IscrizioneCorso() {
		setTitle("Inserisci Iscrizione");
		setBounds(100, 100, 224, 157);
		getContentPane().setLayout(null);
		
		JLabel lblIdcorso = new JLabel("IdCorso:");
		lblIdcorso.setBounds(12, 13, 49, 16);
		getContentPane().add(lblIdcorso);
		
		JComboBox cbIdCorso = new JComboBox();
		cbIdCorso.setBounds(73, 10, 81, 22);
		getContentPane().add(cbIdCorso);
		
		JLabel lblNewLabel = new JLabel("IdIscritto:");
		lblNewLabel.setBounds(12, 42, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(12, 71, 97, 25);
		getContentPane().add(btnInserisci);
		
		textIdIscritto = new JTextField();
		textIdIscritto.setBounds(73, 39, 116, 22);
		getContentPane().add(textIdIscritto);
		textIdIscritto.setColumns(10);

	}

}
