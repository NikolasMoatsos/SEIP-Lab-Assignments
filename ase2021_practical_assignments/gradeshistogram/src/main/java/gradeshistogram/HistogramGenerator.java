package gradeshistogram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * <h1>Generate Histogram with Grades</h1>
 * The HistogramGenerator takes as an input a file with Integer grades, from the
 * command line and generates a histogram with the grades' frequency, using the JFreeChart charting library.
 * @author Nikolas Moatsos
 * @since April 2021
 */
public class HistogramGenerator {
	
	/**
	 * This method reads the grades from the file and creates an array with the
	 * grades' frequencies.
	 * @param input This is a file with Integer grades.
	 * @return int[] This is an array with the frequency of every grade.
	 */
	public int[] createFrequencies(Scanner input) {
		
		// Initialize the array.
		int gradesFreq[] = new int[11];
		
		// Compute the frequency of every grade.
		while (input.hasNext()) {
			gradesFreq[input.nextInt()] += 1;
		}
		
		// Return the array.
		return gradesFreq;
	}
	
	/**
	 * This method visualizes an array with the grades' frequencies, with a XYLineChart
	 * from the JFreeChart charting library.
	 * @param gradesFreq This is an array with the frequency of every grade.
	 */
	public void generateHistogram(int[] gradesFreq) {
		
		/*
		 * The XYSeriesCollection object is a set XYSeries series (dataset) that
		 * can be visualized in the same chart.
		 */
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		// The XYSeries that are loaded in the dataset.
		XYSeries data = new XYSeries("grades");
		
		/*
		 * Populating the XYSeries data object from the input Integer array
		 * values.
		 */
		for (int i = 0; i < gradesFreq.length; i++) {
			data.add(i, gradesFreq[i]);
		}
		
		// Add the series to the dataset.
		dataset.addSeries(data);
		
		boolean legend = false; // do not visualize a legend
		boolean tooltips = false; // do not visualize tooltips
		boolean urls = false; // do not visualize urls
		
		// Declare and initialize a createXYLineChart JFreeChart.
		JFreeChart chart = ChartFactory.createXYLineChart("Grades Histogram", "Grade", "Frequency", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);
		
		/*
		 * Initialize a frame for visualizing the chart and attach the
		 * previously created chart.
		 */
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		
		// Make the previously created frame visible.
		frame.setVisible(true);
		
	}
	
	public static void main (String args[]) {
		
		// Initialize the Scanner. 
		Scanner input = null;
		

		// Read the input file from the command line.
		try {
			input = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("No File Found!");
			return;
		}
		
		// Generate the histogram.
		HistogramGenerator gen = new HistogramGenerator();
		int gradesFreq[] = gen.createFrequencies(input);
		gen.generateHistogram(gradesFreq);
	}
}
