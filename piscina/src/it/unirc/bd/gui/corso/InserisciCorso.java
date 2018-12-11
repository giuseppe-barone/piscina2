package it.unirc.bd.gui.corso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.DipendenteDAOP;

import javax.swing.event.CaretEvent;

public class InserisciCorso extends JDialog {
	CorsoDAOP cDAOP =new CorsoDAOP();
	DipendenteDAOP dDAOP =new DipendenteDAOP();
	
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textIdCorso;
	private JTextField textNome;
	private JTextField textIdAllenatore1;
	private JTextField textIdAllenatore2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciCorso dialog = new InserisciCorso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciCorso() {
		setBounds(100, 100, 323, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIdcorso = new JLabel("IdCorso:");
		lblIdcorso.setBounds(12, 13, 49, 16);
		contentPanel.add(lblIdcorso);
		
		textIdCorso = new JTextField();
		textIdCorso.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
			}
		});
		textIdCorso.setBounds(73, 10, 49, 22);
		contentPanel.add(textIdCorso);
		textIdCorso.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 42, 38, 16);
		contentPanel.add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(73, 39, 116, 22);
		contentPanel.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Giorni:");
		lblNewLabel.setBounds(134, 71, 38, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblOra = new JLabel("Ora:");
		lblOra.setBounds(12, 71, 26, 16);
		contentPanel.add(lblOra);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "14", "15", "16", "17", "18", "19"}));
		comboBox.setBounds(73, 68, 49, 22);
		contentPanel.add(comboBox);
		
		JLabel lblIdallenatore = new JLabel("IdAllenatore1:");
		lblIdallenatore.setBounds(12, 100, 81, 16);
		contentPanel.add(lblIdallenatore);
		
		JLabel lblIdallenatore_1 = new JLabel("IdAllenatore2:");
		lblIdallenatore_1.setBounds(12, 132, 81, 16);
		contentPanel.add(lblIdallenatore_1);
		
		textIdAllenatore1 = new JTextField();
		textIdAllenatore1.setColumns(10);
		textIdAllenatore1.setBounds(105, 97, 116, 22);
		contentPanel.add(textIdAllenatore1);
		
		textIdAllenatore2 = new JTextField();
		textIdAllenatore2.setColumns(10);
		textIdAllenatore2.setBounds(105, 129, 116, 22);
		contentPanel.add(textIdAllenatore2);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 161, 97, 25);
		contentPanel.add(btnNewButton);
		
		JComboBox cbGiorni = new JComboBox();
		cbGiorni.setModel(new DefaultComboBoxModel(new String[] {"LUN-MERC-VEN", "MAR-GIO-SAB"}));
		cbGiorni.setBounds(179, 68, 114, 22);
		contentPanel.add(cbGiorni);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(233, 100, 56, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.RED);
		lblX.setBounds(233, 132, 56, 16);
		contentPanel.add(lblX);
		
		JLabel lblAllenatoreGiImpegnato = new JLabel("Allenatore gi\u00E0 impegnato");
		lblAllenatoreGiImpegnato.setBounds(134, 165, 159, 16);
		contentPanel.add(lblAllenatoreGiImpegnato);
	}
	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----RITORNA FALSO SE IL BOTTONE NON SI DEVE ATTIVARE
	
		public boolean controllobottone() {
			boolean risultato=true ;
			//---------CONTROLLI LATO DIPENDENTE----------
			if (textIdCorso.getText().equals("") || textNome.getText().equals("") || textIdAllenatore1.getText().equals("") || textIdAllenatore2.getText().equals("") ) {
				risultato=false;
				System.out.println("CAMPI CORSO INDISPENSABILI NON COMPILATI ");
			}
			else if (cDAOP.ControlloDinamicoIdCorso(Integer.parseInt(textIdCorso.getText()))) {
			risultato=false;
			System.out.println("ID CORSO GIA ESISTENTE");
			}
			//CONTROLLARE SE NON ESISTE L'ALLENATORE
			else if (!dDAOP.ControlloDinamicoIdAllenatore(Integer.parseInt(textIdAllenatore1.getText()))) {
				risultato=false;
				System.out.println("ID ALLENATORE 1 NON ESISTENTE");
				}
			else if (!dDAOP.ControlloDinamicoIdAllenatore(Integer.parseInt(textIdAllenatore2.getText()))) {
				risultato=false;
				System.out.println("ID ALLENATORE 2 NON ESISTENTE");
				}
			//----CONTROLLARE SE UN ALLENATORE è GIA PRESENTE ALL'INTERNO DEI CORSI----
			
			return risultato;

		}
}
