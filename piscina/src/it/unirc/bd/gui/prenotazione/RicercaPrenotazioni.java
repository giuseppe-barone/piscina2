package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.unirc.bd.dao.beans.Prenotazione;
import it.unirc.bd.dao.beans.PrenotazioneDAOP;
import it.unirc.bd.gui.evento.VisualizzaEvento;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class RicercaPrenotazioni extends JDialog {
	private JTextField txtData;
	private JTextField txtTipo;
	private JTextField txtOra;
	ButtonGroup gruppo = new ButtonGroup();
	
	//PARAMETRI DA PASSARE ALLA QUERY
	private Date data;
	private String tipo;
	private Integer ora;
	
	//OGGETTI DAO
	PrenotazioneDAOP pDAOP = new PrenotazioneDAOP();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RicercaPrenotazioni dialog = new RicercaPrenotazioni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RicercaPrenotazioni() {
		setTitle("Ricerca Prenotazioni");
		setBounds(100, 100, 374, 265);
		getContentPane().setLayout(null);
		
		JLabel lblRicercaPer = new JLabel("Ricerca per:");
		lblRicercaPer.setBounds(26, 13, 76, 28);
		getContentPane().add(lblRicercaPer);
		
		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setText("yy-mm-dd");
		txtData.setBounds(171, 50, 116, 22);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setEnabled(false);
		txtTipo.setBounds(171, 80, 116, 22);
		getContentPane().add(txtTipo);
		txtTipo.setColumns(10);
		
		txtOra = new JTextField();
		txtOra.setEnabled(false);
		txtOra.setBounds(171, 115, 116, 22);
		getContentPane().add(txtOra);
		txtOra.setColumns(10);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(123, 166, 97, 25);
		getContentPane().add(btnCerca);
		
		JRadioButton rbData = new JRadioButton("Data");
		rbData.setBounds(81, 50, 68, 25);
		getContentPane().add(rbData);
		gruppo.add(rbData);
		
		JRadioButton rbTipo = new JRadioButton("Tipo");
		rbTipo.setBounds(81, 81, 68, 25);
		getContentPane().add(rbTipo);
		gruppo.add(rbTipo);
		
		JRadioButton rbOra = new JRadioButton("Ora");
		rbOra.setBounds(81, 114, 68, 25);
		getContentPane().add(rbOra);
		gruppo.add(rbOra);
		
		//--------LISTENER
		rbTipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbTipo.isSelected())
					txtTipo.setEnabled(true);
				else
					txtTipo.setEnabled(false);
			}
		});
		
		rbData.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rbData.isSelected())
					txtData.setEnabled(true);
				else
					txtData.setEnabled(false);
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
		
		//----ACQUISIZIONE DATI DA BOTTONE
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Prenotazione> vettore =new Vector<Prenotazione>();
				if(rbData.isSelected()) {
					data = Date.valueOf(txtData.getText());
					vettore=pDAOP.RicercaPerData(data);
				}
				else
					data=null;
				
				if(rbTipo.isSelected()) {
					tipo = txtTipo.getText();
					vettore=pDAOP.RicercaPerTipo(tipo);
				}
				else
					tipo=null;
				
				/*if(rbOra.isSelected()) {
					ora = txtOra.getText();
					vettore=pDAOP.RicercaPerOrario(ora);
				}
				else
					ora=null;*/
				VisualizzaPrenotazione vis=new VisualizzaPrenotazione(vettore);
				vis.setVisible(true);
			}
		});
	}
}
