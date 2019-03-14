package it.unirc.bd.gui.statistiche;

import java.awt.EventQueue;
import java.awt.EventQueue; 
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;  
import java.io.File;
import java.io.IOException;

import java.awt.Color; import javax.swing.JLabel; 
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class quattroTorte {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quattroTorte window = new quattroTorte();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public quattroTorte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 834, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*DefaultPieDataset data1 = new DefaultPieDataset();
		DefaultPieDataset data2 = new DefaultPieDataset();
		DefaultPieDataset data3 = new DefaultPieDataset();
		DefaultPieDataset data4 = new DefaultPieDataset();*/
		JFreeChart chart = new JFreeChart.
		 ChartPanel panel = new ChartPanel(chart);
		 panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true)); 
		 panel.setBounds(10, 11, 400, 282);
		 frame.getContentPane().add(panel, BorderLayout.EAST); 
	}

}
