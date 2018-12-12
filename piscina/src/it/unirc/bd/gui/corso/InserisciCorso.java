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

import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.DipendenteDAOP;

import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
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
	private JTextField textIdCorso;
	private JTextField textTipo;
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
		setBounds(100, 100, 491, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIdcorso = new JLabel("IdCorso:");
		lblIdcorso.setBounds(12, 13, 49, 16);
		contentPanel.add(lblIdcorso);
		
		textIdCorso = new JTextField();
		textIdCorso.setBounds(73, 10, 49, 22);
		contentPanel.add(textIdCorso);
		textIdCorso.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 42, 38, 16);
		contentPanel.add(lblNome);
		
		textTipo = new JTextField();
		textTipo.setBounds(73, 39, 116, 22);
		contentPanel.add(textTipo);
		textTipo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Giorni:");
		lblNewLabel.setBounds(134, 71, 38, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblOra = new JLabel("Ora:");
		lblOra.setBounds(12, 71, 26, 16);
		contentPanel.add(lblOra);
		
		JComboBox cbOra = new JComboBox();
		cbOra.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "14", "15", "16", "17", "18", "19"}));
		cbOra.setBounds(73, 68, 49, 22);
		contentPanel.add(cbOra);
		
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
		
		JButton btnInserisci = new JButton("Inserisci");
		
		btnInserisci.setEnabled(false);
		btnInserisci.setBounds(12, 161, 97, 25);
		contentPanel.add(btnInserisci);
		
		JComboBox cbGiorni = new JComboBox();
		cbGiorni.setModel(new DefaultComboBoxModel(new String[] {"LUN-MERC-VEN", "MAR-GIO-SAB"}));
		cbGiorni.setBounds(179, 68, 114, 22);
		contentPanel.add(cbGiorni);
		
		JLabel lblAvvisoAll1 = new JLabel("X");
		lblAvvisoAll1.setForeground(Color.RED);
		lblAvvisoAll1.setBounds(233, 100, 159, 16);
		contentPanel.add(lblAvvisoAll1);
		
		JLabel lblAvvisoAll2 = new JLabel("X");
		lblAvvisoAll2.setForeground(Color.RED);
		lblAvvisoAll2.setBounds(233, 132, 159, 16);
		contentPanel.add(lblAvvisoAll2);
		
		JLabel lblAvvisoIdCorso = new JLabel("New label");
		lblAvvisoIdCorso.setBounds(134, 13, 258, 16);
		
		contentPanel.add(lblAvvisoIdCorso);
	
	
		//----LISTNER CONTROLLI DINAMICI----
		textIdCorso.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//CONTROLLO DUPLICAZIONE ID CORSO
				lblAvvisoIdCorso.setText(ControlloAvvisoCorso());
				//CONTROLLO ABILITAZIONE BOTTONE
				if (controllobottone())
					btnInserisci.setEnabled(true);
				else
					btnInserisci.setEnabled(false);
			}
		});
		textTipo.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				//CONTROLLO ABILITAZIONE BOTTONE
				if (controllobottone())
					btnInserisci.setEnabled(true);
				else
					btnInserisci.setEnabled(false);
			}
		});
		textIdAllenatore1.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//CONTROLLI SUL VALORE DELL'ID ALLENATORE (SE è GIà PRESENTE, SE ESISTE L'ID, SE è UGUALE ALL'ALTRO)
				lblAvvisoAll1.setText(ControlloAvvisoAllenatore(textIdAllenatore1.getText()));
				//CONTROLLO ABILITAZIONE BOTTONE
				if (controllobottone())
					btnInserisci.setEnabled(true);
				else
					btnInserisci.setEnabled(false);
			}
		});
		textIdAllenatore2.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//CONTROLLI SUL VALORE DELL'ID ALLENATORE (SE è GIà PRESENTE, SE ESISTE L'ID, SE è UGUALE ALL'ALTRO)
				lblAvvisoAll2.setText(ControlloAvvisoAllenatore(textIdAllenatore2.getText()));
				//CONTROLLO ABILITAZIONE BOTTONE
				if (controllobottone())
					btnInserisci.setEnabled(true);
				else
					btnInserisci.setEnabled(false);
			}
		});
		
		//----AQUISIZIONE DEI DATI DA PASSARE ALLA QUERY PER INSERIRE IL CORSO NEL DB----
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdCorso=Integer.parseInt(textIdCorso.getText());
				Giorni=cbGiorni.getSelectedIndex();	
				//Ora=cbOra.getModel().getElementAt(cbOra.getSelectedIndex());	//ATTENZIONE AL CASTING
				Ora =Integer.parseInt(cbOra.getModel().getElementAt(cbOra.getSelectedIndex()).toString()) ;
				Tipo=textTipo.getText();
				Allenatore1=Integer.parseInt(textIdAllenatore1.getText());
				Allenatore2=Integer.parseInt(textIdAllenatore2.getText());
				Corso c =new Corso(IdCorso, Giorni,Ora,Tipo,Allenatore1,Allenatore2);
				cDAOP.salvaCorso(c);
				
			}
		});
		
		
	
	
	}
	
	//----CONTROLLO PER AVVISO DI ID DUPLICATO CORSO----
		public String ControlloAvvisoCorso() {
			String risultato="";
			String IDC = textIdCorso.getText();
			//-----------QUESTO IF è LA SOLUZIONE A QUEI PROBLEMI STRING=""---------------------------!!!!!!!!!!!!!!
			if (IDC.equals(""))
				return risultato;
			Integer ID =Integer.parseInt(IDC);
			if (!IDC.equals("") && cDAOP.ControlloDinamicoIdCorso(ID))      
				risultato="ID esistente o non valido!";
			return risultato;
			
		}
		
		
		
		
		//----CONTROLLO PER AVVISO DI ID DUPLICATO ALLENATORE
				public String ControlloAvvisoAllenatore(String IDA) {
					String risultato="";
					//CONTROLLO CHE BLOCCA IL METODO QUALORA IL VALORE SIA ASSENTE
					if(IDA.equals(""))
						return risultato;
					//CONTROLLARE CHE I VALORI DEI DUE ALLENATORI NON SIANO UGUALI
					if (textIdAllenatore1.getText().equals(textIdAllenatore2.getText())) {
						risultato="Id allenatori uguali!";
						return risultato;
					}
					//CONTROLLO CHE VERIFICA L'ESISTENZA DELL'ID
					Integer IA =Integer.parseInt(IDA);	//------------IN CASO NON FUNZIONA "ID non esistente!" CAMBIARE DA INTEGER AD INT E POI CREARE UN ALTRA VARIABILE DI TIPO INTEGER DA PASSARE ALL'IF DOPO
					if (!IDA.equals("") && !dDAOP.ControlloDinamicoIdAllenatore(IA))
						risultato="ID non esistente!";
					//CONTROLLO CHE VERIFICA LA PRESENZA DELL'ALLENATORE ALL'INTERNO DI QUALCHE ALTRO CORSO
					if (cDAOP.ControlloPresenzaAllenatore(IA))
						risultato="Allenatore occupato";
					return risultato;
				}
	
	
	
	
	//----CONTROLLO PER L'ABILITAZIONE DEL BOTTONE----RITORNA FALSO SE IL BOTTONE NON SI DEVE ATTIVARE
	//----PROVO A TOGLIERE TUTTI GLI ELSE IF E LI SOSTITUISCO CON DEGL'IF SEMPLICI
		public boolean controllobottone() {
			boolean risultato=true ;
			//---------CONTROLLI COMPILAZIONE CAMPI CORSO----------
			if (textIdCorso.getText().equals("") || textTipo.getText().equals("") || textIdAllenatore1.getText().equals("") || textIdAllenatore2.getText().equals("") ) {
				risultato=false;
				System.out.println("CAMPI CORSO INDISPENSABILI NON COMPILATI ");
			}
			//----CONTROLLO CHE GLI ALLENATORI SAINO DIVERSI TRA LORO----
			else if (textIdAllenatore1.getText().equals(textIdAllenatore2.getText())) {
				risultato=false;
				System.out.println("ID ALLENATORE UGUALI TRA LORO");
			}
			//----CONTROLLO PRECEDENTE ESISTENZA ID CORSO----
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
			//CONTROLLO SE SONO LIBERI ENTRAMBI GLI ALLENATORI
			else if (cDAOP.ControlloPresenzaAllenatore(Integer.parseInt(textIdAllenatore1.getText()))||cDAOP.ControlloPresenzaAllenatore(Integer.parseInt(textIdAllenatore2.getText()))) {
				risultato=false;
				System.out.println("ALLENATORE 1 O 2 OCCUPATO");
				}
			
			//----CONTROLLARE SE UN ALLENATORE è GIA PRESENTE ALL'INTERNO DEI CORSI----
			if (risultato)
				System.out.println("TUTTI I CAMPI SONO VALIDI");
			System.out.println("____________________________________________________________________________");
			
			return risultato;
			
		}
}
