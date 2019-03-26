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
		ButtonGroup gruppo = new ButtonGroup();		
		setBounds(100, 100, 493, 398);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 478, 358);
		getContentPane().add(tabbedPane);
		
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
										gruppo.add(radioAllenatori);
										
												JCheckBox checkAnno1 = new JCheckBox("Anno");
												checkAnno1.setEnabled(false);
												checkAnno1.setBounds(217, 39, 57, 25);
												panel_2.add(checkAnno1);
												
														JRadioButton radioCorsi = new JRadioButton("Infortuni associati a corsi");
														radioCorsi.setBounds(8, 69, 205, 25);
														panel_2.add(radioCorsi);
														gruppo.add(radioCorsi);
														
																JCheckBox checkAnno2 = new JCheckBox("Anno");
																checkAnno2.setEnabled(false);
																checkAnno2.setBounds(217, 69, 57, 25);
																panel_2.add(checkAnno2);
																
																		JYearChooser yearChooser = new JYearChooser();
																		yearChooser.getSpinner().setEnabled(false);
																		yearChooser.setBounds(282, 42, 51, 22);
																		panel_2.add(yearChooser);
																		
																				JYearChooser yearChooser1 = new JYearChooser();
																				yearChooser1.getSpinner().setEnabled(false);
																				yearChooser1.setBounds(282, 72, 51, 22);
																				panel_2.add(yearChooser1);
																				
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
																																										gruppo.add(radioConfronti);
																																										
																																												JButton btnNewButton = new JButton("Avvia");
																																												btnNewButton.setBounds(172, 290, 97, 25);
																																												panel.add(btnNewButton);
																																												
																																												
																																														//----LISTNER----
																																														radioCorsi.addChangeListener(new ChangeListener() {
																																															public void stateChanged(ChangeEvent e) {
																																																if (radioCorsi.isSelected()) {
																																																	checkAnno2.setEnabled(true);
																																																	yearChooser1.setEnabled(true);
																																																} else {
																																																	checkAnno2.setEnabled(false);
																																																	yearChooser1.setEnabled(false);
																																												
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



		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Eventi", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Assoluti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(12, 13, 537, 99);
		panel_1.add(panel_3);

		JRadioButton rdbtnVittorieAssociatiAd = new JRadioButton("Vittorie associati ad allenatori");
		rdbtnVittorieAssociatiAd.setBounds(8, 26, 205, 25);
		panel_3.add(rdbtnVittorieAssociatiAd);

		JCheckBox checkBox_5 = new JCheckBox("Anno");
		checkBox_5.setEnabled(false);
		checkBox_5.setBounds(217, 26, 57, 25);
		panel_3.add(checkBox_5);

		JYearChooser yearChooser_3 = new JYearChooser();
		yearChooser_3.getSpinner().setEnabled(false);
		yearChooser_3.setBounds(274, 29, 51, 22);
		panel_3.add(yearChooser_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_4.setEnabled(false);
		comboBox_4.setBounds(337, 27, 78, 22);
		panel_3.add(comboBox_4);

		JRadioButton rdbtnVittorieAssociatiA = new JRadioButton("Vittorie associati a corsi");
		rdbtnVittorieAssociatiA.setBounds(8, 56, 205, 25);
		panel_3.add(rdbtnVittorieAssociatiA);

		JCheckBox checkBox_6 = new JCheckBox("Anno");
		checkBox_6.setEnabled(false);
		checkBox_6.setBounds(217, 56, 57, 25);
		panel_3.add(checkBox_6);

		JYearChooser yearChooser_4 = new JYearChooser();
		yearChooser_4.getSpinner().setEnabled(false);
		yearChooser_4.setBounds(274, 59, 51, 22);
		panel_3.add(yearChooser_4);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_5.setEnabled(false);
		comboBox_5.setBounds(337, 57, 78, 22);
		panel_3.add(comboBox_5);

		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Podio", "Primo", "Secondo", "Terzo"}));
		comboBox_6.setEnabled(false);
		comboBox_6.setBounds(427, 27, 78, 22);
		panel_3.add(comboBox_6);

		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"Podio", "Primo", "Secondo", "Terzo"}));
		comboBox_7.setEnabled(false);
		comboBox_7.setBounds(427, 57, 78, 22);
		panel_3.add(comboBox_7);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atleti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(37, 128, 406, 193);
		panel_1.add(panel_4);

		JComboBox<Iscritto> comboBox_8 = new JComboBox<Iscritto>();
		comboBox_8.setEnabled(false);
		comboBox_8.setBounds(63, 56, 314, 22);
		panel_4.add(comboBox_8);

		JCheckBox checkBox_7 = new JCheckBox("1\u00B0");
		checkBox_7.setEnabled(false);
		checkBox_7.setBounds(8, 55, 41, 25);
		panel_4.add(checkBox_7);

		JCheckBox checkBox_8 = new JCheckBox("2\u00B0");
		checkBox_8.setEnabled(false);
		checkBox_8.setBounds(8, 85, 41, 25);
		panel_4.add(checkBox_8);

		JComboBox<Iscritto> comboBox_9 = new JComboBox<Iscritto>();
		comboBox_9.setEnabled(false);
		comboBox_9.setBounds(63, 86, 314, 22);
		panel_4.add(comboBox_9);

		JCheckBox checkBox_9 = new JCheckBox("4\u00B0");
		checkBox_9.setEnabled(false);
		checkBox_9.setBounds(8, 145, 41, 25);
		panel_4.add(checkBox_9);

		JComboBox<Iscritto> comboBox_10 = new JComboBox<Iscritto>();
		comboBox_10.setEnabled(false);
		comboBox_10.setBounds(63, 146, 314, 22);
		panel_4.add(comboBox_10);

		JComboBox<Iscritto> comboBox_11 = new JComboBox<Iscritto>();
		comboBox_11.setEnabled(false);
		comboBox_11.setBounds(63, 116, 314, 22);
		panel_4.add(comboBox_11);

		JCheckBox checkBox_10 = new JCheckBox("3\u00B0");
		checkBox_10.setEnabled(false);
		checkBox_10.setBounds(8, 115, 41, 25);
		panel_4.add(checkBox_10);

		JLabel label = new JLabel("Anno:");
		label.setEnabled(false);
		label.setBounds(63, 27, 41, 16);
		panel_4.add(label);

		JYearChooser yearChooser_5 = new JYearChooser();
		yearChooser_5.setStartYear(2000);
		yearChooser_5.setMinimum(2000);
		yearChooser_5.setBounds(103, 21, 51, 22);
		panel_4.add(yearChooser_5);

		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(12, 121, 25, 25);
		panel_1.add(radioButton);

	}
}
