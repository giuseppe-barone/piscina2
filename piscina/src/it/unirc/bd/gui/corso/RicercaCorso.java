package it.unirc.bd.gui.corso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.dao.beans.EventoDAOP;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class RicercaCorso extends JDialog {
	//OGETTI DAOP
	private EventoDAOP eDAOP=new EventoDAOP();
	private  DipendenteDAOP dDAOP =new DipendenteDAOP();
	private CorsoDAOP cDAOP=new CorsoDAOP();

	//ELEMENTI DA PASSARE ALLA QUERY
	private int Giorni;
	private int Ora;
	private String Tipo;
	private int idAllenatore;


	ButtonGroup gruppo = new ButtonGroup();
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaCorso dialog = new RicercaCorso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaCorso() {
		setModal(true);
		setTitle("Ricerca Corso");
		setBounds(100, 100, 434, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JRadioButton rbGiorni = new JRadioButton("Giorni");

		rbGiorni.setBounds(8, 34, 63, 25);
		contentPanel.add(rbGiorni);
		gruppo.add(rbGiorni);


		JRadioButton rbOra = new JRadioButton("Ora");

		rbOra.setBounds(8, 64, 63, 25);
		contentPanel.add(rbOra);
		gruppo.add(rbOra);


		JRadioButton rbTipo = new JRadioButton("Tipo");

		rbTipo.setBounds(8, 94, 58, 25);
		contentPanel.add(rbTipo);
		gruppo.add(rbTipo);


		JRadioButton rbAllenatore = new JRadioButton("Allenatore");

		rbAllenatore.setBounds(8, 124, 87, 25);
		contentPanel.add(rbAllenatore);
		gruppo.add(rbAllenatore);


		JComboBox cbGiorni = new JComboBox();
		cbGiorni.setModel(new DefaultComboBoxModel(new String[] {"LUN-MER-VEN", "MAR-GIO-SAB"}));
		cbGiorni.setBounds(79, 35, 199, 22);
		cbGiorni.setEnabled(false);
		contentPanel.add(cbGiorni);

		JComboBox<String> cbTipo = new JComboBox<String>();
		cbTipo.setBounds(79, 95, 199, 22);
		cbTipo.setModel(eDAOP.ElencoTipi());
		cbTipo.setEnabled(false);
		contentPanel.add(cbTipo);

		JComboBox<Dipendente> cbAllenatore = new JComboBox<Dipendente>();
		cbAllenatore.setModel(dDAOP.getAllenatorecb());
		cbAllenatore.setBounds(103, 125, 295, 22);
		cbAllenatore.setEnabled(false);
		contentPanel.add(cbAllenatore);

		JButton btnRicerca = new JButton("Ricerca");

		btnRicerca.setBounds(156, 178, 97, 25);
		contentPanel.add(btnRicerca);

		JLabel lblRicercaPer = new JLabel("Ricerca per:");
		lblRicercaPer.setBounds(8, 9, 188, 16);
		contentPanel.add(lblRicercaPer);

		JComboBox txtOra = new JComboBox();
		txtOra.setEnabled(false);
		txtOra.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "14", "15", "16", "17", "18", "19"}));
		txtOra.setBounds(79, 65, 104, 22);
		contentPanel.add(txtOra);

		JRadioButton rbTutti = new JRadioButton("Tutti");
		rbTutti.setSelected(true);
		rbTutti.setBounds(8, 154, 87, 25);
		contentPanel.add(rbTutti);
		gruppo.add(rbTutti);


		//----LISTNER----
		rbGiorni.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rbGiorni.isSelected())
					cbGiorni.setEnabled(true);
				else
					cbGiorni.setEnabled(false);
			}
		});

		rbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbTipo.isSelected())
					cbTipo.setEnabled(true);
				else
					cbTipo.setEnabled(false);
			}
		});

		rbAllenatore.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbAllenatore.isSelected())
					cbAllenatore.setEnabled(true);
				else
					cbAllenatore.setEnabled(false);
			}
		});

		rbOra.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbOra.isSelected())
					txtOra.setEnabled(true);
				else
					txtOra.setEnabled(false);
			}
		});

		//----AQUISIZIONE DEI DATI----

		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Corso> v =new Vector<Corso>();
				if (rbGiorni.isSelected()) {
					Giorni=cbGiorni.getSelectedIndex();
					v=cDAOP.getVectorCorsoFromGiorni(Giorni);
				}
				if (rbOra.isSelected()) {
					Ora =Integer.parseInt(txtOra.getModel().getElementAt(txtOra.getSelectedIndex()).toString()) ;
					v= cDAOP.getVectorCorsoFromOra(Ora);
				}
				if (rbTipo.isSelected()) {
					Tipo = cbTipo.getModel().getElementAt(cbTipo.getSelectedIndex()).toString();
					v=cDAOP.getVectorCorsoFromTipo(Tipo);
				}
				if (rbAllenatore.isSelected()) {
					idAllenatore = dDAOP.getAllenatorecb().getElementAt(cbAllenatore.getSelectedIndex()).getIdDipendente();
					v=cDAOP.getVectorCorsoFromAllenatore1(idAllenatore);
				}
				if (rbTutti.isSelected())
					v=cDAOP.getAll();
				//________________
				VisualizzaCorso vis =new VisualizzaCorso(true,null,v);
				vis.setVisible(true);

			}
		});




	}
}
