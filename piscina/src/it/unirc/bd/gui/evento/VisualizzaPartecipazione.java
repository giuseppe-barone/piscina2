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
import it.unirc.bd.dao.beans.PartecipazioneDAOP;
import it.unirc.bd.gui.iscritto.InserisciIscritto;



public class VisualizzaPartecipazione extends JDialog {

	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaPartecipazione dialog = new VisualizzaPartecipazione(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VisualizzaPartecipazione(Vector<String[]> list) {
		PartecipazioneDAOP pDAOP=new PartecipazioneDAOP();
		setModal(true);
		setResizable(false);
		setTitle("Visualizza Partecipazioni");
		
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 7))); //codice per provare come aquisire dati dal click sulla tabella
				String idPartecipazione = (String) table.getModel().getValueAt(table.getSelectedRow(), 7);
				String[] splitId = idPartecipazione.split("/");
				System.out.println(splitId[0] + "    " + splitId[1]);
				int idEvento=Integer.valueOf(splitId[0]);
				int MatricolaFin=Integer.valueOf(splitId[1]);
				PartecipazioneEvento modifica=new PartecipazioneEvento(true, pDAOP.getPartecipazione(idEvento, MatricolaFin));
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
	private void load(Vector<String[]> list) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[8];
		columnsName[0] = "Data";
		columnsName[1] = "Tipo";
		columnsName[2] = "Livello";
		columnsName[3] = "Nome";
		columnsName[4] = "Cognome";
		columnsName[5] = "DataDiNascita";
		columnsName[6] = "Categoria";
		columnsName[7] = "ID";
		model.setColumnIdentifiers(columnsName);
	
		

		Object rowData[] = new Object[8]; 
		for (int a=0;a<list.size();a++) {
			rowData[0] = list.elementAt(a)[0];
			rowData[1] = list.elementAt(a)[1];
			rowData[2] = list.elementAt(a)[2];
			rowData[3] = list.elementAt(a)[3];
			rowData[4] = list.elementAt(a)[4];
			rowData[5] = list.elementAt(a)[5];
			rowData[6] = list.elementAt(a)[6];
			rowData[7] = list.elementAt(a)[7];
			model.addRow(rowData);
		}
		table.setModel(model);
	}
}