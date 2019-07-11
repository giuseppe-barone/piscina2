package it.unirc.bd.gui.infortunio;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import it.unirc.bd.dao.beans.Infortunio;
import it.unirc.bd.dao.beans.InfortunioDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class InserisciInfortunio extends JDialog {
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	IscrittoDAOP iscrittoDAOP =new IscrittoDAOP();
	//VARIABILI DA PASSARE ALLA QUERY
	private int idInfortunio=0;
	private Date data;
	private int GiorniSosta;
	private int gravita;
	private int MatricolaFIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InserisciInfortunio dialog = new InserisciInfortunio(false,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InserisciInfortunio(Boolean modifica, Infortunio info) {
		setBounds(100, 100, 466, 216);
		getContentPane().setLayout(null);
		{
			JLabel lblData = new JLabel("Data");
			lblData.setBounds(12, 39, 56, 16);
			getContentPane().add(lblData);
		}
		{
			JLabel lblGiorniDiSosta = new JLabel("Giorni di Sosta");
			lblGiorniDiSosta.setBounds(12, 71, 89, 16);
			getContentPane().add(lblGiorniDiSosta);
		}
		{
			JLabel lblGravita = new JLabel("Gravit\u00E0");
			lblGravita.setBounds(12, 103, 56, 16);
			getContentPane().add(lblGravita);
		}

		
			JButton buttonInserisci = new JButton("Inserisci");
			buttonInserisci.setEnabled(true);
			buttonInserisci.setBounds(102, 131, 97, 25);
			getContentPane().add(buttonInserisci);
			
			JLabel lblAtleta = new JLabel("Atleta");
			lblAtleta.setBounds(12, 13, 56, 16);
			getContentPane().add(lblAtleta);
			
			JComboBox<Iscritto> comboMatricola = new JComboBox<Iscritto>();
			comboMatricola.setBounds(102, 10, 329, 22);
			comboMatricola.setModel(iscrittoDAOP.getAtleticb());
			getContentPane().add(comboMatricola);
			
			JSpinner spinnerGiorni = new JSpinner();
			spinnerGiorni.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spinnerGiorni.setBounds(102, 68, 116, 22);
			getContentPane().add(spinnerGiorni);
			
			JComboBox comboGravita = new JComboBox();
			comboGravita.setModel(new DefaultComboBoxModel(new String[] {"Lieve", "Media", "Elevata"}));
			comboGravita.setBounds(102, 100, 130, 22);
			getContentPane().add(comboGravita);
			
			JButton buttonModifica = new JButton("Modifica");
			buttonModifica.setEnabled(true);
			buttonModifica.setBounds(211, 131, 97, 25);
			getContentPane().add(buttonModifica);
			
			JDateChooser campoData = new JDateChooser();
			campoData.setBounds(102, 36, 98, 22);
			getContentPane().add(campoData);
			
			//FINESTRA IN MODALITà MODIFICA
			if (modifica) {
				buttonInserisci.setVisible(false);
				buttonModifica.setVisible(true);

				spinnerGiorni.setValue(Integer.valueOf(info.getGiorniSosta()));
				switch(info.getGravita()) {
				case 1:
					comboGravita.setSelectedIndex(0);
					break;
				case 2:
					comboGravita.setSelectedIndex(1);
					break;
				case 3:
					comboGravita.setSelectedIndex(2);
					break;
				}
				
			}
			else {
				buttonInserisci.setVisible(true);
				buttonModifica.setVisible(false);
			}
			
			//-------------------LISTNER-----------------
			
			
			
			
			buttonInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//Prendere la matricola 
					MatricolaFIN = iscrittoDAOP.getAtleticb().getElementAt(comboMatricola.getSelectedIndex()).getMatricolaFIN();
					java.util.Date utilDate = campoData.getDate();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					data=sqlDate;
					GiorniSosta = (Integer)spinnerGiorni.getValue();   //   Integer.parseInt(txtSosta.getText());
					System.out.println(Integer.toString(GiorniSosta) );
					gravita = comboGravita.getSelectedIndex()+1;
					System.out.println(gravita) ;
					System.out.println("la data è: "+data.toString());
					if (controlloCampiOperazione(comboMatricola.getSelectedItem().toString(), GiorniSosta, data)==0) {
						//DEVO PASSARE IL VALORE MATRICOLAFIN
					Infortunio i = new Infortunio(null, data, GiorniSosta, gravita, MatricolaFIN);
					
					if (iDAOP.salva(i))
						JOptionPane.showMessageDialog(null, "Inserimento Riuscito");
					else
						JOptionPane.showMessageDialog(null, "Inserimento Fallito");
					}
				}
			});
			
			//----BOTTONE MODIFICA
			buttonModifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Prendere la matricola 
					MatricolaFIN = iscrittoDAOP.getAtleticb().getElementAt(comboMatricola.getSelectedIndex()).getMatricolaFIN();
				//	data = Date.valueOf(txtData.getText());
					java.util.Date utilDate = campoData.getDate();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					data=sqlDate;
					GiorniSosta = (Integer)spinnerGiorni.getValue();   //   Integer.parseInt(txtSosta.getText());
					System.out.println(Integer.toString(GiorniSosta) );
					gravita = comboGravita.getSelectedIndex()+1;
					System.out.println(gravita) ;
					if (controlloCampiOperazione(comboMatricola.getSelectedItem().toString(), GiorniSosta, data)==0) {					
					//DEVO PASSARE IL VALORE MATRICOLAFIN
					Infortunio i = new Infortunio(info.getIdInfortunio(), data, GiorniSosta, gravita, MatricolaFIN);
					
					if (iDAOP.modifica(i))
						JOptionPane.showMessageDialog(null, "Modifica Riuscita");
					else
						JOptionPane.showMessageDialog(null, "Modifica Fallita");
				}
				}
			});
			
			
			
	}
	
	//METODO PER IL CONTROLLO DEI CAMPI BISOGNA PASSARE NELLA FIRMA I CAMPI CHE SI VOGLIONO CONTROLLARE (SI POSSONO AGGIUNGERE ANCHE I CAMPI NON OBBLIGATORI SE SI VOGLIONO INSERIE NEL MESSAGGIO DI RIEPILOGO)
			public int controlloCampiOperazione(String nome, int GiorniSosta, java.util.Date data) {	//QUESTO METODO RITORNA 0 SE è CONSENTITO PROCEDERE CON LA QUERY ALTRIMENTI NON DA IL CONSENSO A MANDARE LA QUERY
				int result ;
				if (GiorniSosta==0 || data.equals(null)) {	//SE NON SONO STATI COMPILATI TORNA UN VALORE
					result=1;
					JOptionPane.showMessageDialog(null, "NON SONO STATI COMPILATI TUTTI I CAMPI OBBLIGATORI \n Data dell'infortunio \n Giorni di sosta");
				}
				else {	 //TUTTI I CAMPI SONO STATI COMPILATI, MESSAGGIO DI RIEPILOGO E CONFERMA
					java.sql.Date sqlDate = new java.sql.Date(data.getTime());
				    result= JOptionPane.showConfirmDialog (null, "I dati obbligatori da te inseriti sono:\n Nome: "+nome+";\n Giorni di sosta: "+Integer.toString(GiorniSosta)+";\n Data dell'infortunio: "+sqlDate.toString(),"RIEPILOGO",JOptionPane.YES_NO_OPTION);
				}
				System.out.println("il valore della selezione è: "+Integer.toString(result));
				return result;
			}
	
	
	
}
