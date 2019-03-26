package it.unirc.bd.gui.infortunio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.unirc.bd.dao.beans.InfortunioDAOP;
import it.unirc.bd.gui.iscritto.InserisciIscritto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizzaInfo extends JDialog {
	public InfortunioDAOP infortunioDAOP =new InfortunioDAOP();
	private final JPanel contentPanel = new JPanel();


	
	public VisualizzaInfo(Vector<String[]> risultato) throws SQLException {
		setResizable(false);
		setTitle("Visualizza Dipendenti");
		
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JTable table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 5))); //codice per provare come aquisire dati dal click sulla tabella
				//	Integer ID =Integer.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0));
					Integer ID =Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(),5));
					InserisciInfortunio modifica = new InserisciInfortunio(true, infortunioDAOP.getIDInfo(ID) );
					modifica.setVisible(true);
			}
		});
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[6];		
		columnsName[0] = "Nome";
		columnsName[1] = "Cognome";
		columnsName[2] = "Matricola FIN";
		columnsName[3] = "Gravità";
		columnsName[4] = "Data";
		columnsName[5] = "ID";
		model.setColumnIdentifiers(columnsName);
		Object rowData[] = new Object[6]; 		
		for (String[] i:risultato) {
			for (int j=0;j<6;j++)
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
