package it.unirc.bd.gui.dipendente;

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

import it.unirc.bd.dao.beans.Allenatore;
import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;

public class VisualizzaAllenatore extends JDialog {
	DipendenteDAOP dDAOP = new DipendenteDAOP();
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaAllenatore dialog = new VisualizzaAllenatore();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaAllenatore() {
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
		Object[] columnsName = new Object[3];
		columnsName[0] = "IdAllenatore";
		columnsName[1] = "Qualifica";
		columnsName[2] = "IdDipendente";
		model.setColumnIdentifiers(columnsName);
		Vector<Allenatore> list;
		list=dDAOP.getAllenatori();
		System.out.println(list);
		Object rowData[] = new Object[3]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdAllenatore();
			rowData[1] = list.elementAt(a).getQualifica();
			rowData[2] = list.elementAt(a).getIdDipendente();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}