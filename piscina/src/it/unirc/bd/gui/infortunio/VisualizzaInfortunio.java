package it.unirc.bd.gui.infortunio;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.InfortunioDAOP;

public class VisualizzaInfortunio extends JDialog {
	private JTable table;
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	
	
	public VisualizzaInfortunio(LinkedList<String[]> risultato) throws SQLException {
		setResizable(false);
		setTitle("Visualizza Dipendenti");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[5];
		columnsName[0] = "Nome";
		columnsName[1] = "Cognome";
		columnsName[2] = "Matricola FIN";
		columnsName[3] = "Gravità";
		columnsName[4] = "Data";
		model.setColumnIdentifiers(columnsName);
		Object rowData[] = new Object[5]; 		
		for (String[] i:risultato) {
			for (int j=0;j<5;j++)
				rowData[j]=i[j];
			model.addRow(rowData);
			}	
		table.setModel(model);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	


}
