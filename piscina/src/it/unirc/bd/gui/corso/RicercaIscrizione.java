package it.unirc.bd.gui.corso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RicercaIscrizione extends JDialog {
	ButtonGroup gruppo = new ButtonGroup();
	IscrittoDAOP iDAOP=new IscrittoDAOP();
	CorsoDAOP cDAOP= new CorsoDAOP();
	int idIscritto;
	int idCorso;


	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaIscrizione dialog = new RicercaIscrizione();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaIscrizione() {
		setTitle("Ricerca Iscrizioni ai corsi");
		setBounds(100, 100, 475, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JRadioButton radioTutti = new JRadioButton("Tutte");
		radioTutti.setSelected(true);
		radioTutti.setBounds(12, 72, 65, 25);
		contentPanel.add(radioTutti);

		JRadioButton radioCorso = new JRadioButton("Per tipo corso:");

		radioCorso.setBounds(12, 42, 113, 25);
		contentPanel.add(radioCorso);

		JRadioButton radioIscritto = new JRadioButton("Per iscritto:");

		radioIscritto.setBounds(12, 12, 95, 25);
		contentPanel.add(radioIscritto);

		JButton btnCerca = new JButton("Cerca");

		btnCerca.setBounds(157, 109, 97, 25);
		contentPanel.add(btnCerca);

		JComboBox<Iscritto> comboIscritto = new JComboBox<Iscritto>();
		comboIscritto.setModel(iDAOP.getIscritticb());
		comboIscritto.setEnabled(false);
		comboIscritto.setBounds(129, 13, 307, 22);
		contentPanel.add(comboIscritto);

		JComboBox<Corso> comboCorso = new JComboBox<Corso>();
		comboCorso.setModel(cDAOP.getCorsoCb());
		comboCorso.setEnabled(false);
		comboCorso.setBounds(129, 43, 307, 22);
		contentPanel.add(comboCorso);

		gruppo.add(radioIscritto);
		gruppo.add(radioCorso);
		gruppo.add(radioTutti);

		//LISTNER
		radioCorso.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (radioCorso.isSelected())
					comboCorso.setEnabled(true);
				else
					comboCorso.setEnabled(false);
			}
		});
		radioIscritto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (radioIscritto.isSelected())
					comboIscritto.setEnabled(true);
				else
					comboIscritto.setEnabled(false);
			}
		});
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioTutti.isSelected()) {
					VisualizzaCorso vis =new VisualizzaCorso(cDAOP.getAllIscrizioni());
					vis.setVisible(true);
					//cDAOP.getAllIscrizioni();
				}
				else if (radioCorso.isSelected()) {
					idCorso=cDAOP.getCorsoCb().getElementAt(comboCorso.getSelectedIndex()).getIdCorso();
					VisualizzaCorso vis =new VisualizzaCorso(cDAOP.getIscrizioniFromIdCorso(idCorso));
					vis.setVisible(true);
					//cDAOP.getIscrizioniFromIdCorso(idCorso);
				}
				else if (radioIscritto.isSelected()) {
					idIscritto=iDAOP.getIscritticb().getElementAt(comboIscritto.getSelectedIndex()).getIdIscritto();
					VisualizzaCorso vis =new VisualizzaCorso(cDAOP.getIscrizioniFromIdIscritto(idIscritto));
					vis.setVisible(true);
					//cDAOP.getIscrizioniFromIdIscritto(idIscritto);
				}

			}
		});


	}
}
