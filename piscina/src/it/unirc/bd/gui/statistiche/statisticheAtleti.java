package it.unirc.bd.gui.statistiche;

import java.awt.Component;
import java.awt.EventQueue;

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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class statisticheAtleti extends JDialog {
	private IscrittoDAOP iscrittoDAOP=new IscrittoDAOP();
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
		setBounds(100, 100, 584, 409);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 566, 365);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Infortunii", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Avvia");
		btnNewButton.setBounds(225, 300, 97, 25);
		panel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Assoluti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 13, 537, 99);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton radioAllenatori = new JRadioButton("Infortuni associati ad allenatori");
		radioAllenatori.setBounds(8, 26, 205, 25);
		panel_2.add(radioAllenatori);
		gruppo.add(radioAllenatori);
		
		JCheckBox chckbxAnno = new JCheckBox("Anno");
		chckbxAnno.setEnabled(false);
		chckbxAnno.setBounds(217, 26, 57, 25);
		panel_2.add(chckbxAnno);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.getSpinner().setEnabled(false);
		yearChooser.setBounds(274, 29, 51, 22);
		panel_2.add(yearChooser);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox.setBounds(337, 27, 78, 22);
		panel_2.add(comboBox);
		
		JRadioButton radioCorsi = new JRadioButton("Infortuni associati a corsi");
		radioCorsi.setBounds(8, 56, 205, 25);
		panel_2.add(radioCorsi);
		gruppo.add(radioCorsi);
		
		JCheckBox checkBox = new JCheckBox("Anno");
		checkBox.setEnabled(false);
		checkBox.setBounds(217, 56, 57, 25);
		panel_2.add(checkBox);
		
		JYearChooser yearChooser_1 = new JYearChooser();
		yearChooser_1.getSpinner().setEnabled(false);
		yearChooser_1.setBounds(274, 59, 51, 22);
		panel_2.add(yearChooser_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_1.setBounds(337, 57, 78, 22);
		panel_2.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		comboBox_2.setBounds(427, 27, 78, 22);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setEnabled(false);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Esordienti", "Ragazzi", "Cadetti", "Seniores"}));
		comboBox_3.setBounds(427, 57, 78, 22);
		panel_2.add(comboBox_3);
		
		JPanel panelConfronti = new JPanel();
		panelConfronti.setBorder(new TitledBorder(null, "Confronti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfronti.setBounds(35, 141, 514, 144);
		panel.add(panelConfronti);
		panelConfronti.setLayout(null);
		
		JComboBox<Iscritto> comboAtleta1 = new JComboBox<Iscritto>();
		comboAtleta1.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta1.setEnabled(false);
		comboAtleta1.setBounds(63, 23, 314, 22);
		panelConfronti.add(comboAtleta1);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_5.setEnabled(false);
		comboBox_5.setBounds(400, 23, 102, 22);
		panelConfronti.add(comboBox_5);
		
		JCheckBox checkBox_1 = new JCheckBox("1\u00B0");
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(8, 22, 41, 25);
		panelConfronti.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("2\u00B0");
		checkBox_2.setEnabled(false);
		checkBox_2.setBounds(8, 52, 41, 25);
		panelConfronti.add(checkBox_2);
		
		JComboBox<Iscritto> comboAtleta2 = new JComboBox<Iscritto>();
		comboAtleta2.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta2.setEnabled(false);
		comboAtleta2.setBounds(63, 53, 314, 22);
		panelConfronti.add(comboAtleta2);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_7.setEnabled(false);
		comboBox_7.setBounds(400, 53, 102, 22);
		panelConfronti.add(comboBox_7);
		
		JCheckBox checkBox_3 = new JCheckBox("4\u00B0");
		checkBox_3.setEnabled(false);
		checkBox_3.setBounds(8, 112, 41, 25);
		panelConfronti.add(checkBox_3);
		
		JComboBox<Iscritto> comboAtleta4 = new JComboBox<Iscritto>();
		comboAtleta4.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta4.setEnabled(false);
		comboAtleta4.setBounds(63, 113, 314, 22);
		panelConfronti.add(comboAtleta4);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_9.setEnabled(false);
		comboBox_9.setBounds(400, 113, 102, 22);
		panelConfronti.add(comboBox_9);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"Qualsiasi", "Lieve", "Media", "Elevata"}));
		comboBox_10.setEnabled(false);
		comboBox_10.setBounds(400, 83, 102, 22);
		panelConfronti.add(comboBox_10);
		
		JComboBox<Iscritto> comboAtleta3 = new JComboBox<Iscritto>();
		comboAtleta3.setModel(iscrittoDAOP.getAtleticb());
		comboAtleta3.setEnabled(false);
		comboAtleta3.setBounds(63, 83, 314, 22);
		panelConfronti.add(comboAtleta3);
		
		JCheckBox checkBox_4 = new JCheckBox("3\u00B0");
		checkBox_4.setEnabled(false);
		checkBox_4.setBounds(8, 82, 41, 25);
		panelConfronti.add(checkBox_4);
		
		JRadioButton radioConfronti = new JRadioButton("");
		radioConfronti.setBounds(12, 141, 25, 25);
		panel.add(radioConfronti);
		gruppo.add(radioConfronti);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Eventi", null, panel_1, null);
		
		
		//----LISTNER----
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

	}
}
