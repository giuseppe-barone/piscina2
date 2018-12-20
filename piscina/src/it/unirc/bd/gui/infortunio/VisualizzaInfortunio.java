package it.unirc.bd.gui.infortunio;

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

import it.unirc.bd.dao.beans.Infortunio;
import it.unirc.bd.dao.beans.InfortunioDAOP;


public class VisualizzaInfortunio extends JDialog {

	private JTable table;
	InfortunioDAOP iDAOP = new InfortunioDAOP();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaInfortunio dialog = new VisualizzaInfortunio();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaInfortunio() {
		setResizable(false);
		setTitle("Visualizza Dipendenti");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		load();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load() {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[5];
		columnsName[0] = "Id";
		columnsName[1] = "Data";
		columnsName[2] = "Giorni di Sosta";
		columnsName[3] = "Gravità";
		columnsName[4] = "MatricolaFIN";
		model.setColumnIdentifiers(columnsName);
		Vector<Infortunio> list;
		list=iDAOP.getAll();
		System.out.println(list);
		Object rowData[] = new Object[5]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdInfortunio();
			rowData[1] = list.elementAt(a).getData();
			rowData[2] = list.elementAt(a).getGiorniSosta();
			rowData[3] = list.elementAt(a).getGravita();
			rowData[4] = list.elementAt(a).getMatricolaFIN();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}