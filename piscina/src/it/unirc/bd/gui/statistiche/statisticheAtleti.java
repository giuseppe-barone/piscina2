package it.unirc.bd.gui.statistiche;

import java.awt.Component;
import java.awt.EventQueue;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import com.toedter.calendar.JYearChooser;

import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.dao.beans.statisticheDAOP;
import it.unirc.bd.gui.infortunio.VisualizzaInfo;
import it.unirc.bd.gui.iscritto.RicercaIscritti;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.ChangeEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class statisticheAtleti extends JDialog {
	private IscrittoDAOP iscrittoDAOP=new IscrittoDAOP();
	private statisticheDAOP StatisticheDAOP=new statisticheDAOP();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					statisticheAtleti dialog = new statisticheAtleti();
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
	public statisticheAtleti() {
		ButtonGroup gruppoInfortuni = new ButtonGroup();		
		setBounds(100, 100, 499, 427);
		getContentPane().setLayout(null);

		ButtonGroup gruppoEventi = new ButtonGroup();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 478, 367);
		getContentPane().add(tabbedPane);

		//PARTE RELATIVA ALLA SCHEDA EVENTI

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Eventi", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Assoluti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(12, 13, 449, 99);
		panel_1.add(panel_3);

		JRadioButton radioPosizioneAllenatori = new JRadioButton("Posizioni associati ad allenatori");
		radioPosizioneAllenatori.setBounds(8, 26, 205, 25);
		panel_3.add(radioPosizioneAllenatori);
		gruppoEventi.add(radioPosizioneAllenatori);

		JCheckBox checkAnno5 = new JCheckBox("Anno");
		checkAnno5.setEnabled(false);
		checkAnno5.setBounds(217, 26, 57, 25);
		panel_3.add(checkAnno5);

		JYearChooser yearChooser2 = new JYearChooser();
		yearChooser2.getSpinner().setEnabled(false);
		yearChooser2.setBounds(334, 13, 51, 22);
		panel_3.add(yearChooser2);

		JRadioButton radioPosizioneCorsi = new JRadioButton("Posizioni associati a corsi");
		radioPosizioneCorsi.setBounds(8, 56, 205, 25);
		panel_3.add(radioPosizioneCorsi);
		gruppoEventi.add(radioPosizioneCorsi);

		JCheckBox checkAnno6 = new JCheckBox("Anno");
		checkAnno6.setEnabled(false);
		checkAnno6.setBounds(217, 56, 57, 25);
		panel_3.add(checkAnno6);
		
		JLabel lblAnno_1 = new JLabel("Anno");
		lblAnno_1.setBounds(281, 13, 41, 16);
		panel_3.add(lblAnno_1);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atleti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(33, 142, 406, 154);
		panel_1.add(panel_4);

		JComboBox<Iscritto> comboAtleta5 = new JComboBox<Iscritto>();
		comboAtleta5.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta5.setEnabled(false);
		comboAtleta5.setBounds(63, 25, 314, 22);
		panel_4.add(comboAtleta5);

		JCheckBox checkAtE = new JCheckBox("1\u00B0");
		checkAtE.setEnabled(false);
		checkAtE.setBounds(8, 24, 41, 25);
		panel_4.add(checkAtE);

		JCheckBox checkAtF = new JCheckBox("2\u00B0");
		checkAtF.setEnabled(false);
		checkAtF.setBounds(8, 54, 41, 25);
		panel_4.add(checkAtF);

		JComboBox<Iscritto> comboAtleta6 = new JComboBox<Iscritto>();
		comboAtleta6.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta6.setEnabled(false);
		comboAtleta6.setBounds(63, 55, 314, 22);
		panel_4.add(comboAtleta6);

		JCheckBox checkAtH = new JCheckBox("4\u00B0");
		checkAtH.setEnabled(false);
		checkAtH.setBounds(8, 114, 41, 25);
		panel_4.add(checkAtH);

		JComboBox<Iscritto> comboAtleta8 = new JComboBox<Iscritto>();
		comboAtleta8.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta8.setEnabled(false);
		comboAtleta8.setBounds(63, 115, 314, 22);
		panel_4.add(comboAtleta8);

		JComboBox<Iscritto> comboAtleta7 = new JComboBox<Iscritto>();
		comboAtleta7.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta7.setEnabled(false);
		comboAtleta7.setBounds(63, 85, 314, 22);
		panel_4.add(comboAtleta7);

		JCheckBox checkAtG = new JCheckBox("3\u00B0");
		checkAtG.setEnabled(false);
		checkAtG.setBounds(8, 84, 41, 25);
		panel_4.add(checkAtG);

		JRadioButton radioConfrontiPosizioni = new JRadioButton("");
		radioConfrontiPosizioni.setBounds(8, 135, 25, 25);
		panel_1.add(radioConfrontiPosizioni);
		gruppoEventi.add(radioConfrontiPosizioni);
		
		JButton btnNewButton_1 = new JButton("Avvia");
	
		btnNewButton_1.setBounds(185, 305, 97, 25);
		panel_1.add(btnNewButton_1);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Infortunii", null, panel, null);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Assoluti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 13, 449, 119);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JRadioButton radioAllenatori = new JRadioButton("Infortuni associati ad allenatori");

		radioAllenatori.setBounds(8, 39, 205, 25);
		panel_2.add(radioAllenatori);
		gruppoInfortuni.add(radioAllenatori);

		JCheckBox checkAnno1 = new JCheckBox("Anno");
		checkAnno1.setEnabled(false);
		checkAnno1.setBounds(217, 39, 57, 25);
		panel_2.add(checkAnno1);

		JRadioButton radioCorsi = new JRadioButton("Infortuni associati a corsi");
		radioCorsi.setBounds(8, 69, 205, 25);
		panel_2.add(radioCorsi);
		gruppoInfortuni.add(radioCorsi);

		JCheckBox checkAnno2 = new JCheckBox("Anno");
		checkAnno2.setEnabled(false);
		checkAnno2.setBounds(217, 69, 57, 25);
		panel_2.add(checkAnno2);

		JYearChooser yearChooser = new JYearChooser();
		yearChooser.getSpinner().setEnabled(false);
		yearChooser.setBounds(331, 13, 51, 22);
		panel_2.add(yearChooser);

		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setBounds(282, 13, 37, 16);
		panel_2.add(lblAnno);

		JPanel panelConfronti = new JPanel();
		panelConfronti.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atleti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConfronti.setBounds(55, 135, 406, 147);
		panel.add(panelConfronti);
		panelConfronti.setLayout(null);

		JComboBox<Iscritto> comboAtleta1 = new JComboBox<Iscritto>();
		comboAtleta1.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta1.setEnabled(false);
		comboAtleta1.setBounds(63, 23, 314, 22);
		panelConfronti.add(comboAtleta1);

		JCheckBox checkAtA = new JCheckBox("1\u00B0");
		checkAtA.setEnabled(false);
		checkAtA.setBounds(8, 22, 41, 25);
		panelConfronti.add(checkAtA);

		JCheckBox checkAtB = new JCheckBox("2\u00B0");
		checkAtB.setEnabled(false);
		checkAtB.setBounds(8, 52, 41, 25);
		panelConfronti.add(checkAtB);

		JComboBox<Iscritto> comboAtleta2 = new JComboBox<Iscritto>();
		comboAtleta2.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta2.setEnabled(false);
		comboAtleta2.setBounds(63, 53, 314, 22);
		panelConfronti.add(comboAtleta2);

		JCheckBox checkAtD = new JCheckBox("4\u00B0");
		checkAtD.setEnabled(false);
		checkAtD.setBounds(8, 112, 41, 25);
		panelConfronti.add(checkAtD);

		JComboBox<Iscritto> comboAtleta4 = new JComboBox<Iscritto>();
		comboAtleta4.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta4.setEnabled(false);
		comboAtleta4.setBounds(63, 113, 314, 22);
		panelConfronti.add(comboAtleta4);

		JComboBox<Iscritto> comboAtleta3 = new JComboBox<Iscritto>();
		comboAtleta3.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta3.setEnabled(false);
		comboAtleta3.setBounds(63, 83, 314, 22);
		panelConfronti.add(comboAtleta3);

		JCheckBox checkAtC = new JCheckBox();
		checkAtC.setText("3\u00B0");
		checkAtC.setEnabled(false);
		checkAtC.setBounds(8, 82, 41, 25);
		panelConfronti.add(checkAtC);

		JRadioButton radioConfronti = new JRadioButton("");
		radioConfronti.setBounds(22, 135, 25, 25);
		panel.add(radioConfronti);
		gruppoInfortuni.add(radioConfronti);

		JButton btnNewButton = new JButton("Avvia");
		btnNewButton.setBounds(172, 290, 97, 25);
		panel.add(btnNewButton);


		//----LISTNER---- INFORTUNI
		radioCorsi.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (radioCorsi.isSelected()) {
					checkAnno2.setEnabled(true);
					yearChooser.setEnabled(true);
				} else {
					checkAnno2.setEnabled(false);
					yearChooser.setEnabled(false);

				}
			}
		});
		radioAllenatori.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (radioAllenatori.isSelected()) {
					checkAnno1.setEnabled(true);
					yearChooser.setEnabled(true);
				} else {
					checkAnno1.setEnabled(false);
					yearChooser.setEnabled(false);

				}
			}
		});
		radioConfronti.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (radioConfronti.isSelected()) {
					Component[] components=  panelConfronti.getComponents();
					for (Component c: components)
						c.setEnabled(true);
				}
				else {
					Component[] components=  panelConfronti.getComponents();
					for (Component c: components)
						c.setEnabled(false);
				}
			}
		});
		
		//-----LISTENER RADIOBUTTON EVENTI
		radioPosizioneAllenatori.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(radioPosizioneAllenatori.isSelected()) {
					checkAnno5.setEnabled(true);
					yearChooser2.setEnabled(true);
				}
				else {
					checkAnno5.setEnabled(false);
					yearChooser2.setEnabled(false);
				}
			}
		});
		
		radioPosizioneCorsi.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(radioPosizioneCorsi.isSelected()) {
					checkAnno6.setEnabled(true);
					yearChooser2.setEnabled(true);
				}
				else {
					checkAnno6.setEnabled(false);
					yearChooser2.setEnabled(false);
				}
			}
		});
		
		radioConfrontiPosizioni.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(radioConfrontiPosizioni.isSelected()) {
					panel_4.setEnabled(true);
					Component[] components=  panel_4.getComponents();
					for (Component c: components)
						c.setEnabled(true);
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("pollo");
				//checkAnno2


				if (radioCorsi.isSelected()) {
					Integer anno;
					if (checkAnno2.isSelected())
						anno=yearChooser.getValue();
					else
						anno=null;
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					dataset=StatisticheDAOP.getInfortuniCorso(dataset, anno);
					barraVerticale vis =new barraVerticale(dataset);	
					vis.setVisible(true);
				}	

				if (radioAllenatori.isSelected()) {
					Integer anno;
					if (checkAnno1.isSelected())
						anno=yearChooser.getValue();
					else
						anno=null;
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					dataset=StatisticheDAOP.getInfortuniAllenatori(dataset, anno);
					barraVerticale vis =new barraVerticale(dataset);	
					vis.setVisible(true);
				}

				if (radioConfronti.isSelected()){
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					Iscritto AtletaA;
					Iscritto AtletaB;
					Iscritto AtletaC;
					Iscritto AtletaD;
					if (checkAtA.isSelected()) {
						AtletaA=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta1.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttiInfortunioAtleta(dataset, AtletaA);
					}
					if (checkAtB.isSelected()) {
						AtletaB=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta2.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttiInfortunioAtleta(dataset, AtletaB);
					}
					if (checkAtC.isSelected()) {
						AtletaC=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta3.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttiInfortunioAtleta(dataset, AtletaC);
					}
					if (checkAtD.isSelected()) {
						AtletaD=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta4.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttiInfortunioAtleta(dataset, AtletaD);
					}
					quattroTorte vis =new quattroTorte(dataset);
					vis.setVisible(true);

				}
			}
		});
		
	//PARTE RELATIVA ALLE GARE
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radioPosizioneCorsi.isSelected()) {
					Integer anno;
					if (checkAnno6.isSelected())
						anno=yearChooser2.getValue();
					else
						anno=null;
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					dataset=StatisticheDAOP.getPosizioneCorsi(dataset, anno);
					barraVerticale vis =new barraVerticale(dataset);	
					vis.setVisible(true);
				}
				
				if(radioPosizioneAllenatori.isSelected()) {
					Integer anno;
					if (checkAnno5.isSelected())
						anno=yearChooser2.getValue();
					else
						anno=null;
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					dataset=StatisticheDAOP.getPosizioneAllenatori(dataset, anno);
					barraVerticale vis =new barraVerticale(dataset);	
					vis.setVisible(true);
				}
				
				
				if (radioConfrontiPosizioni.isSelected()){
					DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					Iscritto AtletaE;
					Iscritto AtletaF;
					Iscritto AtletaG;
					Iscritto AtletaH;
					if (checkAtE.isSelected()) {
						AtletaE=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta5.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttePosizioniAtleta(dataset, AtletaE);
					}
					if (checkAtF.isSelected()) {
						AtletaF=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta6.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttePosizioniAtleta(dataset, AtletaF);
					}
					if (checkAtG.isSelected()) {
						AtletaG=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta7.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttePosizioniAtleta(dataset, AtletaG);
					}
					if (checkAtH.isSelected()) {
						AtletaH=iscrittoDAOP.getAtleticb().getElementAt(comboAtleta8.getSelectedIndex());
						dataset=StatisticheDAOP.getTuttePosizioniAtleta(dataset, AtletaH);
					}
					quattroTorte vis =new quattroTorte(dataset);
					vis.setVisible(true);

				}
				
				
				
				
			}
		});
		

	}
}
