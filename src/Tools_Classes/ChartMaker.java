package Tools_Classes;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Perceptrons.PerceptronMultiCouches;

public class ChartMaker {

	XYPlot plot;
	int nbOfDataset;
	
	public ChartMaker() {
		plot = new XYPlot();
		nbOfDataset=0;
	}


	

	public void addSeries(DataToUse passedObject) {
		

		// on crée le data set
		
		double[][] data = passedObject.getRawData();
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		
		if (passedObject.GetColNb() == 2) {
			XYSeries seriesToAdd = new XYSeries("série n"+ nbOfDataset);
			for (int i=0; i<passedObject.GetLineNb(); i++) {
				double x = data[i][0];
				double y = data[i][1];
				System.out.println(x+"  "+y);
				seriesToAdd.add(x, y);
			}
			dataset.addSeries(seriesToAdd);
			
			// on crée la structure d'un plot 
			
			XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);	 
			renderer.setSeriesPaint(0, Color.blue);

			plot.setRenderer(0, renderer);
		
		
		
		plot.setDataset(nbOfDataset, dataset);
		ValueAxis domain1 = new NumberAxis("X");
		ValueAxis range1 = new NumberAxis("Y");
		plot.setDomainAxis(0, domain1);
		plot.setRangeAxis(0, range1);
		plot.mapDatasetToDomainAxis(0, 0);
		plot.mapDatasetToRangeAxis(0, 0);
			
		nbOfDataset++;
		}
		
		
		else if (passedObject.GetColNb() == 3)  {
		
			for (int j = 2; j<passedObject.GetColNb() ;j++) {
				XYSeries seriesToAdd1 = new XYSeries("série a");
				XYSeries seriesToAdd2 = new XYSeries("série b");
				
				for (int i=0; i<passedObject.GetLineNb(); i++) {
					if (data[i][2] != 1) {
						double x = data[i][0];
						double y = data[i][1];
						seriesToAdd1.add(x, y);}
				}
				
				for (int i=0; i<passedObject.GetLineNb(); i++) {
					if (data[i][2] == 1) {
						double x = data[i][0];
						double y = data[i][1];
						seriesToAdd2.add(x, y);}
				}
				
				dataset.addSeries(seriesToAdd1);
				dataset.addSeries(seriesToAdd2);
				
				// on crée la structure d'un plot 
				
				XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);	 
				renderer1.setSeriesPaint(0, Color.blue);
				XYItemRenderer renderer2 = new XYLineAndShapeRenderer(false, true);	 
				renderer2.setSeriesPaint(1, Color.green);
				
				plot.setRenderer(0, renderer1);
				plot.setRenderer(1, renderer2);
			
			}
			
			plot.setDataset(nbOfDataset, dataset);
			ValueAxis domain1 = new NumberAxis("X");
			ValueAxis range1 = new NumberAxis("Y");
			plot.setDomainAxis(0, domain1);
			plot.setRangeAxis(0, range1);
			plot.mapDatasetToDomainAxis(0, 0);
			plot.mapDatasetToRangeAxis(0, 0);
				
			nbOfDataset++;

		}
			

		else  {
			
			for (int j = 2; j<passedObject.GetColNb() ;j++) {
				XYSeries seriesToAdd = new XYSeries("série : " + (j-1) );
				
				for (int i=0; i<passedObject.GetLineNb(); i++) {
					if (data[i][j] == 1) {
						double x = data[i][0];
						double y = data[i][1];
						seriesToAdd.add(x, y);}
				}

				dataset.addSeries(seriesToAdd);
				
				// on crée la structure d'un plot 
				
				XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);	 
				if (j==2) renderer.setSeriesPaint(j-2, Color.blue);
				if (j==3) renderer.setSeriesPaint(j-2, Color.red);
				if (j==4) renderer.setSeriesPaint(j-2, Color.green);

				plot.setRenderer(j-2, renderer);

			}
			
			plot.setDataset(nbOfDataset, dataset);
			ValueAxis domain1 = new NumberAxis("X");
			ValueAxis range1 = new NumberAxis("Y");
			plot.setDomainAxis(0, domain1);
			plot.setRangeAxis(0, range1);
			plot.mapDatasetToDomainAxis(nbOfDataset, 0);		
			plot.mapDatasetToRangeAxis(nbOfDataset, 0);
				
			nbOfDataset++;
		}
		
	}
	
	
	public void addLine(DataToUse passedObject){  
		
		

		// on crée le data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		double[] w = passedObject.getFinalw();
		XYSeries seriesToAdd = new XYSeries("ligne n" + nbOfDataset);
		
		
		System.out.println("!!!!!!!!!  " + passedObject.getRawData()[1].length );
		double[][] data = getLimitValue (passedObject.getRawData(), passedObject.GetLineNb());
		
		
		// on détermine deux points limites de la droite
		
		double x1=0, y1=0, x2=0, y2=0;
		
		if (w.length == 3) {
			x1 = data[0][0];
			y1 = -1 * (w[0] + w[1]*x1) / w[2];
			x2 = data[1][0];
			y2 = -1 *(w[0] + w[1]*x2) / w[2];
		}
		
		if (w.length == 2) {
			x1 = data[0][0];
			y1 = (w[0] + w[1]*x1) ;
			x2 = data[1][0];
			y2 = (w[0] + w[1]*x2) ;
		}
		
		seriesToAdd.add(x1, y1);
		seriesToAdd.add(x2, y2);
		dataset.addSeries(seriesToAdd);

		
		// Create the line data, renderer, and axis
		XYItemRenderer renderer2 = new XYLineAndShapeRenderer(true, false);	// Lines only

		// Set the line data, renderer, and axis into plot
		plot.setDataset(nbOfDataset, dataset);
		plot.setRenderer(nbOfDataset, renderer2);


		// Map the line to the second Domain and second Range
		plot.mapDatasetToDomainAxis(nbOfDataset, 0);
		plot.mapDatasetToRangeAxis(nbOfDataset, 0);
		
		nbOfDataset++;
	}
	

	
	
	

	public void addLineWithExplicitW(DataToUse passedObject, double[] w){  
		
		

		// on crée le data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries seriesToAdd = new XYSeries("ligne n" + nbOfDataset);
		
		double[][] data = getLimitValue (passedObject.getRawData(), passedObject.GetLineNb());
		
		
		// on détermine deux points limites de la droite
		
		double x1=0, y1=0, x2=0, y2=0;
		
		if (w.length == 3) {
			x1 = data[0][0];
			y1 = -1 * (w[0] + w[1]*x1) / w[2];
			x2 = data[1][0];
			y2 = -1 *(w[0] + w[1]*x2) / w[2];
		}
		
		if (w.length == 2) {
			x1 = data[0][0];
			y1 = (w[0] + w[1]*x1) ;
			x2 = data[1][0];
			y2 = (w[0] + w[1]*x2) ;
		}
		
		seriesToAdd.add(x1, y1);
		seriesToAdd.add(x2, y2);
		dataset.addSeries(seriesToAdd);

		
		// Create the line data, renderer, and axis
		XYItemRenderer renderer2 = new XYLineAndShapeRenderer(true, false);	// Lines only
		ValueAxis domain2 = new NumberAxis("Domain");
		ValueAxis range2 = new NumberAxis("Range");

		// Set the line data, renderer, and axis into plot
		plot.setDataset(nbOfDataset, dataset);
		plot.setRenderer(nbOfDataset, renderer2);

		// Map the line to the second Domain and second Range
		plot.mapDatasetToDomainAxis(nbOfDataset, 0);
		plot.mapDatasetToRangeAxis(nbOfDataset, 0);
		
		
		nbOfDataset++;
		
	}
	

	
	public void addMulticoucheBackground(DataToUse passedObject, PerceptronMultiCouches permulti) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		double increment = 0.06;
		int result=0;
		
		
		// détermination des limites du shmilblick
		double[][] limits = getLimitValue (passedObject.getRawData(), passedObject.GetLineNb());
		double x1 = (limits[0][0]) -1;
		double y1 = (limits[0][1]) -1;
		double x2 = (limits[1][0]) +1;
		double y2 = (limits[1][1]) +1;
		
		System.out.println("$$$$$$$$$ " + x1 + " " + y1 + " " + x2 + " " + x2);

		
		if (passedObject.getCas() == 414) {
			
			System.out.println("ici mgl");
			
			XYSeries seriesToAdd1 = new XYSeries("fond 1");
			XYSeries seriesToAdd2 = new XYSeries("fond 2");
			XYSeries seriesToAdd3 = new XYSeries("fond 3");
			

			for (double x=x1; x<x2; x=x+increment) {
				for (double y=y1; y<y2; y=y+increment) {
					result =  permulti.classThisData(x, y);
//					System.out.println(x + " " + y + " " + result);
					if (result == 1)  seriesToAdd1.add(x, y);
					else if (result == 2) seriesToAdd2.add(x, y);
					else seriesToAdd3.add(x, y);
				}
			}
			
			dataset.addSeries(seriesToAdd1);
			dataset.addSeries(seriesToAdd2);
			dataset.addSeries(seriesToAdd3);
			
			// on crée la structure d'un plot 
			
			XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);	 
			renderer1.setSeriesPaint(10, Color.orange);
			XYItemRenderer renderer2 = new XYLineAndShapeRenderer(false, true);	 
			renderer2.setSeriesPaint(11, Color.green);
			XYItemRenderer renderer3 = new XYLineAndShapeRenderer(false, true);	 
			renderer3.setSeriesPaint(12, Color.blue);
			
			plot.setRenderer(10, renderer1);
			plot.setRenderer(11, renderer2);
			plot.setRenderer(12, renderer3);
		}
		


		else {
			XYSeries seriesToAdd1 = new XYSeries("fond false");
			XYSeries seriesToAdd2 = new XYSeries("fond true");
			

			System.out.println("IIIIIIIIIII " + x1 + " " + x2 + " " + y1 + " " + y2);
			
			for (double x=x1; x<x2; x=x+increment) {
				System.out.println("IIIIIIIIIII");
				
				for (double y=y1; y<y2; y=y+increment) {
					

					
					result =  permulti.classThisData(x, y);
					System.out.println(x + " - " + y + " - " + result);
					if (result == 0)  seriesToAdd1.add(x, y);
					else seriesToAdd2.add(x, y);
				}
			}
			
			dataset.addSeries(seriesToAdd1);
			dataset.addSeries(seriesToAdd2);
			
			// on crée la structure d'un plot 
			
			XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);	 
			renderer1.setSeriesPaint(10, Color.blue);
			XYItemRenderer renderer2 = new XYLineAndShapeRenderer(false, true);	 
			renderer2.setSeriesPaint(11, Color.green);
			
			plot.setRenderer(10, renderer1);
			plot.setRenderer(11, renderer2);
		
		}
		
		plot.setDataset(nbOfDataset, dataset);
		ValueAxis domain1 = new NumberAxis("X");
		ValueAxis range1 = new NumberAxis("Y");
		plot.setDomainAxis(10, domain1);
		plot.setRangeAxis(10, range1);
		plot.mapDatasetToDomainAxis(10, 0);
		plot.mapDatasetToRangeAxis(10, 0);
			

		nbOfDataset++;

	}
			
	
	
	public void addComplexRegressionLine(DataToUse passedObject, PerceptronMultiCouches permulti) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		double increment = 0.02;
	
		// détermination des limites du shmilblick
		double[][] limits = getLimitValue (passedObject.getRawData(), passedObject.GetLineNb());
		double x1 = (limits[0][0]) -1;
		double y1 = (limits[0][1]) -1;
		double x2 = (limits[1][0]) +1;
		double y2 = (limits[1][1]) +1;


		
		XYSeries seriesToAdd1 = new XYSeries("courbe de regression");

		for (double x=x1; x<x2; x=x+increment) {
			seriesToAdd1.add(x, permulti.getYForThisX(x));
		}
		dataset.addSeries(seriesToAdd1);

			
		// on crée la structure d'un plot 
		XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);	 
		renderer1.setSeriesPaint(11, Color.orange);
		plot.setRenderer(11, renderer1);

		plot.setDataset(nbOfDataset, dataset);
		ValueAxis domain1 = new NumberAxis("X");
		ValueAxis range1 = new NumberAxis("Y");
		plot.setDomainAxis(11, domain1);
		plot.setRangeAxis(11, range1);
		plot.mapDatasetToDomainAxis(11, 0);
		plot.mapDatasetToRangeAxis(11, 0);
			

		nbOfDataset++;

	}
	
	
	
	public JFreeChart finalize(String title) {
		
		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		return chart;
	}
	
	
	


	public double[][] getLimitValue (double[][] data, int nbLine){
		
		double[][] limits = new double[2][2];
		
		limits[0][0] = data[0][0];			// min col 1 (=X)
		limits[0][1] = data[0][1]; 			// min col 2 (=Y)
		limits[1][0] = data[0][1];			// max col 1 (=X)
		limits[1][1] = data[0][1];			// max col 2 (=Y)
		

		for (int i=1; i<nbLine; i++) {
			
//			System.out.println("$$$$$$$$$ en entrée : " + data[i][0] + " " + data[i][1]);
			
			if (data[i][0] < limits[0][0])  limits[0][0] = data[i][0];
			if (data[i][1] < limits[0][1])  limits[0][1] = data[i][1];
			if (data[i][0] > limits[1][0])  limits[1][0] = data[i][0];
			if (data[i][1] > limits[1][1])  limits[1][1] = data[i][1];
			
//			System.out.println("$$$$$$$$$ après : " + limits[0][0] + " " + limits[0][1] + " " + limits[1][0] + " " + limits[1][1] + " ");
		}

		return limits;
	}
	
	
	
	
	
	
	
	
	public void reset() {
		plot = new XYPlot();
		nbOfDataset =0;
	}
	
	
	
}
