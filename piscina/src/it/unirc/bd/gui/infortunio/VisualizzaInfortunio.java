package it.unirc.bd.gui.infortunio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.Infortunio;
import it.unirc.bd.dao.beans.InfortunioDAOP;


public class VisualizzaInfortunio extends JDialog {

	private JTable table;
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaInfortunio dialog = new VisualizzaInfortunio(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaInfortunio(Vector<String[]> risultato) throws SQLException {
		setResizable(false);
		setTitle("Visualizza Dipendenti");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		load(risultato);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load(Vector<String[]> risultato) throws SQLException {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[5];
		columnsName[0] = "Nome";
		columnsName[1] = "Cognome";
		columnsName[2] = "Matricola FIN";
		columnsName[3] = "Gravità";
		columnsName[4] = "Data";
		model.setColumnIdentifiers(columnsName);
		System.out.println(risultato.toString());
		Object rowData[] = new Object[5]; 
		
		for (int i =0; i<risultato.size();i++) {
			for (int colonna=0;colonna<5;colonna++) {
				rowData[colonna]=risultato.elementAt(i)[colonna];
			}
		}
		
			model.addRow(rowData);
		
	
		table.setModel(model);
	}
}