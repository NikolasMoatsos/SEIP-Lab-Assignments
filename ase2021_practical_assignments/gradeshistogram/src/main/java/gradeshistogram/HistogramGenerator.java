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

public class HistogramGenerator {
	
	
	public int[] readGrades(Scanner input) {
		
		int grades[] = new int[149];
		
		for (int i = 0; i < 149; i++) {
			grades[i] = input.nextInt();
		}
		
		return grades;
	}
	
	
	public void generateHistogram(int[] grades) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		XYSeries data = new XYSeries("grades");
		
		for (int i = 0; i < grades.length; i++) {
			data.add(i, grades[i]);
		}
		
		dataset.addSeries(data);
		
		boolean legend = false;
		boolean tooltips = false; 
		boolean urls = false;
		
		JFreeChart chart = ChartFactory.createXYLineChart("Grades Histogram", "Number of Grade", "Grade", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);
		
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		
		frame.setVisible(true);
		
	}
	
	
	public static void main (String args[]) {
		
		Scanner input = null;
		
		try {
			input = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("No File Found!");
			return;
		}
		
		HistogramGenerator gen = new HistogramGenerator();
		int grades[] = gen.readGrades(input);
		gen.generateHistogram(grades);
	}
}
