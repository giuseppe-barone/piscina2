package it.unirc.bd.gui.iscritto;
import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;

import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.dao.beans.IscrittoDAOP;

public class VisualizzaIscritto extends JDialog{
	IscrittoDAOP iDAOP = new IscrittoDAOP();

	private JTable table;
	/*public static void main(String[] args) {
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
	}*/
	public VisualizzaIscritto(Vector<Iscritto> vettore) {
		setResizable(false);
		setTitle("Visualizza Iscritti");
		setModal(true);
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		load(vettore);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(12, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load(Vector<Iscritto> vettore) {
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
	
		
		
		Object rowData[] = new Object[7]; 
		for (int a=0;a<vettore.size();a++) {
			rowData[0] = vettore.elementAt(a).getIdIscritto();
			rowData[1] = vettore.elementAt(a).getNome();
			rowData[2] = vettore.elementAt(a).getCognome();
			rowData[3] = vettore.elementAt(a).getSesso();
			rowData[4] = vettore.elementAt(a).getCellulare();
			rowData[5] = vettore.elementAt(a).getDataNascita();
			if(vettore.elementAt(a).getMatricolaFIN()==null)
				rowData[6] = null;
			else
				rowData[6] = vettore.elementAt(a).getMatricolaFIN();
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}



