package it.unirc.bd.gui.evento;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VisualizzaEvento extends JDialog {
	private JTable table;
	EventoDAOP eDAOP = new EventoDAOP();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaEvento dialog = new VisualizzaEvento();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaEvento() {
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
		Object[] columnsName = new Object[4];
		columnsName[0] = "Id";
		columnsName[1] = "Data";
		columnsName[2] = "Livello";
		columnsName[3] = "Tipo";
		model.setColumnIdentifiers(columnsName);
		Vector<Evento> list;
		list=eDAOP.getAll();
		System.out.println(list);
		Object rowData[] = new Object[4]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdEvento();
			rowData[1] = list.elementAt(a).getData();
			rowData[2] = list.elementAt(a).getLivello();
			rowData[3] = list.elementAt(a).getTipo();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}