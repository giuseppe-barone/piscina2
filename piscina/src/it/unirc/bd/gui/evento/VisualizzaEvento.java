package it.unirc.bd.gui.evento;

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

import it.unirc.bd.dao.beans.Evento;
import it.unirc.bd.dao.beans.EventoDAOP;
import it.unirc.bd.gui.iscritto.InserisciIscritto;



public class VisualizzaEvento extends JDialog {

	private JTable table;
	EventoDAOP eDAOP = new EventoDAOP();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaEvento dialog = new VisualizzaEvento(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaEvento(Vector<Evento> list) {
		setResizable(false);
		setTitle("Visualizza Eventi");
		
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))); //codice per provare come aquisire dati dal click sulla tabella
			//	Integer ID =Integer.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
				Integer ID =(Integer)table.getModel().getValueAt(table.getSelectedRow(),0);
				InserisciEvento modifica = new InserisciEvento(true, eDAOP.getEventoId(ID));
				modifica.setVisible(true);
			}
		});
		load(list);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	}
	private void load(Vector<Evento> list) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[4];
		columnsName[0] = "Id";
		columnsName[1] = "Data";
		columnsName[2] = "Livello";
		columnsName[3] = "Tipo";
		model.setColumnIdentifiers(columnsName);
	
		
		System.out.println(list);
		Object rowData[] = new Object[5]; 
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