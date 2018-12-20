package it.unirc.bd.gui.corso;

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

import it.unirc.bd.dao.beans.Corso;
import it.unirc.bd.dao.beans.CorsoDAOP;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;

public class VisualizzaCorso extends JDialog {
	CorsoDAOP cDAOP = new CorsoDAOP();

	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					VisualizzaCorso dialog = new VisualizzaCorso();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaCorso() {
		setResizable(false);
		setTitle("Visualizza Corsi");
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
		Object[] columnsName = new Object[6];
		columnsName[0] = "idCorso";
		columnsName[1] = "Giorni";
		columnsName[2] = "Ora";
		columnsName[3] = "Tipo";
		columnsName[4] = "Allenatore 1";
		columnsName[5] = "Allenatore 2";
		model.setColumnIdentifiers(columnsName);
		Vector<Corso> list;
		list=cDAOP.getAll();
		System.out.println(list);
		Object rowData[] = new Object[6]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdCorso();
			rowData[1] = list.elementAt(a).getGiorni();
			rowData[2] = list.elementAt(a).getOra();
			rowData[3] = list.elementAt(a).getTipo();
			rowData[4] = list.elementAt(a).getAllenatore1();
			rowData[5] = list.elementAt(a).getAllenatore2();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}
