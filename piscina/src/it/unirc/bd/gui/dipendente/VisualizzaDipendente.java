package it.unirc.bd.gui.dipendente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.Dipendente;
import it.unirc.bd.dao.beans.DipendenteDAOP;
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;
import it.unirc.bd.gui.iscritto.InserisciIscritto;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;

public class VisualizzaDipendente extends JDialog {
	DipendenteDAOP dDAOP = new DipendenteDAOP();

	private JTable table;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaDipendente dialog = new VisualizzaDipendente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public VisualizzaDipendente(Vector<Dipendente> vector) {
		setResizable(false);
		setTitle("Visualizza Dipendenti");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
				Integer ID = (Integer)table.getModel().getValueAt(table.getSelectedRow(), 0);
				InserisciDipendente modifica = new InserisciDipendente(true, dDAOP.getDipendenteId(ID));
				modifica.setVisible(true);
			}});
			
		load(vector);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load(Vector<Dipendente> vector) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[6];
		columnsName[0] = "Id";
		columnsName[1] = "Nome";
		columnsName[2] = "Cognome";
		columnsName[3] = "Cellulare";
		columnsName[4] = "Sesso";
		columnsName[5] = "Tipologia di Dipendente";
		model.setColumnIdentifiers(columnsName);
		/*Vector<Dipendente> list;
		list=dDAOP.getAll();
		System.out.println(list);*/
		
		Object rowData[] = new Object[6]; 
		for (int a=0;a<vector.size();a++) {
			rowData[0] = vector.elementAt(a).getIdDipendente();
			rowData[1] = vector.elementAt(a).getNome();
			rowData[2] = vector.elementAt(a).getCognome();
			rowData[3] = vector.elementAt(a).getCellulare();
			rowData[4] = vector.elementAt(a).getSesso();
			rowData[5] = vector.elementAt(a).getTipologiaDipendente();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}