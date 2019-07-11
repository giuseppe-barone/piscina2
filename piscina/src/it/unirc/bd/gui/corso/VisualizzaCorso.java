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
import it.unirc.bd.dao.beans.Iscritto;
import it.unirc.bd.gui.iscritto.InserisciIscritto;
import it.unirc.bd.gui.iscritto.VisualizzaIscritto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizzaCorso extends JDialog {
	CorsoDAOP cDAOP = new CorsoDAOP();

	private JTable table;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VisualizzaCorso dialog = new VisualizzaCorso(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public VisualizzaCorso(boolean corso,Vector<String[]> vettoreIscrizioni, Vector<Corso> vettoreCorsi) {
		setModal(true);
		setResizable(false);
		setTitle("Visualizza Corsi/Iscrizioni");
		setBounds(100, 100, 543, 414);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		table = new JTable();
	
		if (corso)
			visCorso(vettoreCorsi);
		else
			load(vettoreIscrizioni);
		
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				
				System.out.println(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))); //codice per provare come aquisire dati dal click sulla tabella
					
					if (corso) {
					Integer IDCORSO =Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(),0));
					InserisciCorso modifica = new InserisciCorso(true, cDAOP.getCorsoFromId(IDCORSO));
					modifica.setVisible(true);
					}
					else {
						Integer IDISCRITTO =Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(),0));
						Integer IDCORSO =Integer.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(),4));
						System.out.println(IDISCRITTO +"    "+ IDCORSO);
					
					}
					
				
				
				
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 525, 366);
		panel.add(pane);
		setContentPane(panel);
	
	}

	//METODO PER LA VISUALIZZAZIONE DI CORSO
	private void visCorso(Vector<Corso> vettore) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[6];
		columnsName[0] = "Id Corso";
		columnsName[1] = "Giorni";
		columnsName[2] = "Ora";
		columnsName[3] = "Tipo";
		columnsName[4] = "Allenatore1";
		columnsName[5] = "Allenatore2";
		model.setColumnIdentifiers(columnsName);



		Object rowData[] = new Object[6]; 
		for (Corso c: vettore) {
			rowData[0] = Integer.toString(c.getIdCorso());
			String giorni=null;
			if (c.getGiorni()==0)
				giorni="LUN-MER-VEN";
			else
				giorni="MAR-GIO-SAB";
			rowData[1] = giorni;
			rowData[2] = Integer.toString(c.getOra());
			rowData[3] = c.getTipo();
			rowData[4] = Integer.toString(c.getAllenatore1());
			rowData[5] = Integer.toString(c.getAllenatore2());
			model.addRow(rowData);
		}
		table.setModel(model);
	}



	//METODO PER LA VISUALIZZAZIONE DI ISCRIZIONE
	private void load(Vector<String[]> vettore) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] columnsName = new Object[7];
		columnsName[0] = "Id Isc.";
		columnsName[1] = "Nome";
		columnsName[2] = "Cognome";
		columnsName[3] = "Data di nascita";
		columnsName[4] = "Id Corso";
		columnsName[5] = "Tipo";
		columnsName[6] = "Ora";
		model.setColumnIdentifiers(columnsName);



		Object rowData[] = new Object[7]; 
		for (int a=0;a<vettore.size();a++) {
			rowData[0] = vettore.elementAt(a)[0];
			rowData[1] = vettore.elementAt(a)[1];
			rowData[2] = vettore.elementAt(a)[2];
			rowData[3] = vettore.elementAt(a)[3];
			rowData[4] = vettore.elementAt(a)[4];
			rowData[5] = vettore.elementAt(a)[5];
			rowData[6] = vettore.elementAt(a)[6];
			model.addRow(rowData);
		}
		table.setModel(model);
	}

}
