package it.unirc.bd.gui.prenotazione;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.Prenotazione;
import it.unirc.bd.dao.beans.PrenotazioneDAOP;


public class VisualizzaPrenotazione extends JDialog {

	private JTable table;
	PrenotazioneDAOP pDAOP = new PrenotazioneDAOP();
	
	
	public VisualizzaPrenotazione(Vector<Prenotazione> list) {
		setResizable(false);
		setTitle("Visualizza Prenotazioni");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		load(list);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load(Vector<Prenotazione> list) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[7];
		columnsName[0] = "Id";
		columnsName[1] = "Corsia";
		columnsName[2] = "Data";
		columnsName[3] = "IdIscritto";
		columnsName[4] = "Tipo Piscina";
		columnsName[5] = "Ora";
		columnsName[6] = "IdDipendente";
		model.setColumnIdentifiers(columnsName);
		//Vector<Prenotazione> list;
		//list=pDAOP.getAll();
		System.out.println(list);
		Object rowData[] = new Object[7]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdPrenotazione();
			rowData[1] = list.elementAt(a).getCorsia();
			rowData[2] = list.elementAt(a).getData();
			rowData[3] = list.elementAt(a).getIdIscritto();
			rowData[4] = list.elementAt(a).getTipoPiscina();
			rowData[5] = list.elementAt(a).getOra();
			rowData[6] = list.elementAt(a).getIdDipendente();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}