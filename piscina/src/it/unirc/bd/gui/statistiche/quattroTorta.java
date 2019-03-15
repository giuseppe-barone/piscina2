package it.unirc.bd.gui.statistiche;

import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.PublicCloneable;
import org.jfree.util.TableOrder;

import javax.swing.JButton;
import java.awt.BorderLayout;

public class quattroTorta {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quattroTorta window = new quattroTorta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public quattroTorta(CategoryDataset dataset) {
		//initialize(dataset);
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 380));
        frame.getContentPane().add(chartPanel);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CategoryDataset dataset) {
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*DefaultPieDataset data = new DefaultPieDataset();   
		data.setValue("15",15);    
		data.setValue("2° Posto: " , 15);  
		data.setValue("3° Posto: " , 15);   
		data.setValue("NC: " , 30);     
		JFreeChart chart = ChartFactory.createPieChart("Statistiche: ", data, true, true, false);   
		 ChartPanel panel = new ChartPanel(chart);
		// panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true)); 
		 panel.setBounds(1, 1, 10, 10);  
		 frame.getContentPane().add(panel);   */
		
		
		
		
		//final CategoryDataset dataset = createDataset(); LA COMMENTO PERCHè LO VOGLIO PASSARE IO IL DATASET
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 380));
        frame.getContentPane().add(chartPanel);

		
		
		
		
		
	}
	
	 // private CategoryDataset createDataset() {
	       /* final double[][] data = new double[][] {
	            {3.0, 4.0, 3.0, 5.0},
	            {5.0, 7.0, 6.0, 8.0},
	            {5.0, 7.0, 3.0, 8.0},
	            {1.0, 2.0, 3.0, 4.0}
	        };
	        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
	            "Emanuele ",
	            "Emanuele",
	            data
	        );*/
	
	
	
	
	
	
		   /* PARTE BUONA
		  
		  final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        dataset.addValue(0, "Ciccio", "Primo");
	        dataset.addValue(0, "Ciccio", "Secondo");
	        dataset.addValue(0, "Ciccio", "Terzo");



	        dataset.addValue(9.8, "Pippo", "Primo");
	        dataset.addValue(6.3, "Pippo", "Secondo");
	        dataset.addValue(0.1, "Pippo", "Terzo");


	        
	        dataset.addValue(7.0, "Paolo", "Primo");
	        dataset.addValue(5.2, "Paolo", "Secondo");
	        dataset.addValue(2.8, "Paolo", "Terzo");



	        dataset.addValue(9.5, "Pluto", "Primo");
	        dataset.addValue(1.2, "Pluto", "Secondo");
	        dataset.addValue(4.5, "Pluto", "Terzo");


	      
		  
		  
		 
		  
	        return dataset;
	    }*/
	
	
	  private JFreeChart createChart(final CategoryDataset dataset) {
	        final JFreeChart chart = ChartFactory.createMultiplePieChart(
	            "STATISTICHE",  // chart title
	            dataset,               // dataset
	            TableOrder.BY_ROW,
	            true,                  // include legend
	            true,
	            false
	        );
	        final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
	        final JFreeChart subchart = plot.getPieChart();
	        final PiePlot p = (PiePlot) subchart.getPlot();
	       // p.setLabelGenerator(new StandardPieItemLabelGenerator("{0}"));
	        p.setInteriorGap(0.30);
	        
	        return chart;
	    }

	
	
	  
}
