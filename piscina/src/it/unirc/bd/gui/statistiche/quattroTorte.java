package it.unirc.bd.gui.statistiche;

import java.awt.EventQueue;

import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.TableOrder;

public class quattroTorte extends JDialog {
	public quattroTorte(CategoryDataset dataset) {
		setBounds(100, 100, 450, 300);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 380));
		getContentPane().add(chartPanel);
	}
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
		p.setInteriorGap(0.30);
		return chart;
	}

}
