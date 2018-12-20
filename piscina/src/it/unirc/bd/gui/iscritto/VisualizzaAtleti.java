package it.unirc.bd.gui.iscritto;

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

import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

public class VisualizzaAtleti extends JDialog {

	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					VisualizzaIscritto dialog = new VisualizzaIscritto();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaAtleti() {
		setResizable(false);
		setTitle("Visualizza Iscritti");
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
		Object[] columnsName = new Object[7];
		columnsName[0] = "Id";
		columnsName[1] = "Nome";
		columnsName[2] = "Cognome";
		columnsName[3] = "Sesso";
		columnsName[4] = "Cellulare";
		columnsName[5] = "Data di Nascita";
		columnsName[6] = "Matricola FIN";
		model.setColumnIdentifiers(columnsName);
		Vector<Iscritto> list; //= new Object[7];
		list=iDAOP.getTuttiAtleti();
		System.out.println(list);
		Object rowData[] = new Object[7]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a).getIdIscritto();
			rowData[1] = list.elementAt(a).getNome();
			rowData[2] = list.elementAt(a).getCognome();
			rowData[3] = list.elementAt(a).getSesso();
			rowData[4] = list.elementAt(a).getCellulare();
			rowData[5] = list.elementAt(a).getDataNascita();
			rowData[6] = list.elementAt(a).getMatricolaFIN();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}
