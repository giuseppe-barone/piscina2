package it.unirc.bd.gui.corso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InserisciCorso extends JDialog {
	CorsoDAOP cDAOP =new CorsoDAOP();
	DipendenteDAOP dDAOP =new DipendenteDAOP();
	//VALORI DA PASSARE ALLA QUERY
	Integer IdCorso=null;
	Integer Giorni=null;
	Integer Ora =null;
	String Tipo=null;
	Integer Allenatore1 =null;
	Integer Allenatore2 =null;
	
	
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciCorso dialog = new InserisciCorso(false, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciCorso(boolean Modifica, Corso corso) {
		setModal(true);
		setBounds(100, 100, 491, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 16, 38, 16);
		contentPanel.add(lblNome);
		
		textTipo = new JTextField();
		textTipo.setBounds(73, 13, 116, 22);
		contentPanel.add(textTipo);
		textTipo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Giorni:");
		lblNewLabel.setBounds(134, 45, 38, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblOra = new JLabel("Ora:");
		lblOra.setBounds(12, 45, 26, 16);
		contentPanel.add(lblOra);
		
		JComboBox cbOra = new JComboBox();
		cbOra.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "14", "15", "16", "17", "18", "19"}));
		cbOra.setBounds(73, 42, 49, 22);
		contentPanel.add(cbOra);
		
		JLabel lblIdallenatore = new JLabel("IdAllenatore1:");
		lblIdallenatore.setBounds(12, 74, 81, 16);
		contentPanel.add(lblIdallenatore);
		
		JLabel lblIdallenatore_1 = new JLabel("IdAllenatore2:");
		lblIdallenatore_1.setBounds(12, 106, 81, 16);
		contentPanel.add(lblIdallenatore_1);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(12, 135, 97, 25);
		contentPanel.add(btnInserisci);
		btnInserisci.setVisible(false);
		
		JComboBox cbGiorni = new JComboBox();
		cbGiorni.setModel(new DefaultComboBoxModel(new String[] {"LUN-MERC-VEN", "MAR-GIO-SAB"}));
		cbGiorni.setBounds(179, 42, 114, 22);
		contentPanel.add(cbGiorni);
		
		JComboBox<Dipendente> cbA1 = new JComboBox<Dipendente>();
		cbA1.setModel(dDAOP.getAllenatorecb());
		cbA1.setBounds(105, 74, 356, 22);
		contentPanel.add(cbA1);
		
		JComboBox<Dipendente> cbA2 = new JComboBox<Dipendente>();
		cbA2.setModel(dDAOP.getAllenatorecb());
		cbA2.setBounds(105, 103, 356, 22);
		contentPanel.add(cbA2);
		
		JButton btnModifica = new JButton("Modifica");
		
		
		
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Giorni=cbGiorni.getSelectedIndex();	
				
				int id=corso.getIdCorso();
				Ora =Integer.parseInt(cbOra.getModel().getElementAt(cbOra.getSelectedIndex()).toString()) ;
				Tipo=textTipo.getText();
				Dipendente dipendente =dDAOP.getAllenatorecb().getElementAt(cbA1.getSelectedIndex());
				Allenatore1=dipendente.getIdDipendente();
				dipendente = dDAOP.getAllenatorecb().getElementAt(cbA2.getSelectedIndex());
				Allenatore2=dipendente.getIdDipendente();
				System.out.println(Integer.toString(Allenatore1) + " " + " " + Integer.toString(Allenatore2));
				
				
			
				if (textTipo.getText().equals("") ) 
					JOptionPane.showMessageDialog(null, "MANCA IL NOME DEL CORSO");
				else {
				Corso c =new Corso(id, Giorni,Ora,Tipo,Allenatore1,Allenatore2);
					if (cDAOP.ModificaCorso(c))			
						JOptionPane.showMessageDialog(null, "MODIFICA RIUSCITA");
					else
						JOptionPane.showMessageDialog(null, "MODIFICA FALLITA");
				}
				
				dispose();
			}
		});
		btnModifica.setBounds(121, 135, 97, 25);
		contentPanel.add(btnModifica);
		btnModifica.setVisible(false);
		
		if (Modifica) {
			btnModifica.setVisible(true);
			textTipo.setText(corso.getTipo());
			cbGiorni.setSelectedIndex(corso.getGiorni());
			cbOra.setSelectedItem(corso.getOra().toString());
			cbA1.setEnabled(false);
		}
		else
			btnInserisci.setVisible(true);
	
	
		//----LISTNER CONTROLLI DINAMICI----
		
		textTipo.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//CONTROLLO ABILITAZIONE BOTTONE
				/*if (controllobottone())
					btnInserisci.setEnabled(true);
				else
					btnInserisci.setEnabled(false);*/
			}
		});
		
		//----AQUISIZIONE DEI DATI DA PASSARE ALLA QUERY PER INSERIRE IL CORSO NEL DB----
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	IdCorso=Integer.parseInt(textIdCorso.getText()); è AUTOINCREMENTALE
				Giorni=cbGiorni.getSelectedIndex();	
				
				//Ora=cbOra.getModel().getElementAt(cbOra.getSelectedIndex());	//ATTENZIONE AL CASTING
				Ora =Integer.parseInt(cbOra.getModel().getElementAt(cbOra.getSelectedIndex()).toString()) ;
				Tipo=textTipo.getText();
				//AQUISIZIONE ID ALLENATORE 1 E 2
				//prelevo MatricolaFin Iscritto copio i risultai della ricerca degli eventi in un vector per risalire all'id di quello selezionato
				Dipendente dipendente =dDAOP.getAllenatorecb().getElementAt(cbA1.getSelectedIndex());
				Allenatore1=dipendente.getIdDipendente();
				dipendente = dDAOP.getAllenatorecb().getElementAt(cbA2.getSelectedIndex());
				Allenatore2=dipendente.getIdDipendente();
				System.out.println(Integer.toString(Allenatore1) + " " + " " + Integer.toString(Allenatore2));
				
				
			
				if (textTipo.getText().equals("") ) 
					JOptionPane.showMessageDialog(null, "MANCA IL NOME DEL CORSO");
				else if (cDAOP.ControlloPresenzaAllenatore(Allenatore1))
					JOptionPane.showMessageDialog(null, "L'ALLENATORE IN PRIMA è IMPEGNATO \n IN UN ALTRO CORSO!");
				else {
				Corso c =new Corso(null, Giorni,Ora,Tipo,Allenatore1,Allenatore2);
					if (cDAOP.salvaCorso(c))
						JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO");
					else
						JOptionPane.showMessageDialog(null, "INSERIMENTO FALLITO");
				}
				dispose();
			}
		});
		
		
	
	
	}		
}
