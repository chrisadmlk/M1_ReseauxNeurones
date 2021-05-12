package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartPanel;

import Perceptrons.PerceptronMonocouche;
import Perceptrons.PerceptronMultiCouches;
import Perceptrons.PerceptronSimple;
import Tools_Classes.ChartMaker;
import Tools_Classes.DataToUse;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Startup_GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startup_GUI window = new Startup_GUI();
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
	public Startup_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1212, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		ButtonGroup bg = new ButtonGroup();
        
		
		JRadioButton rdbtn201 = new JRadioButton("2.1");
		rdbtn201.setBounds(68, 34, 103, 21);
		frame.getContentPane().add(rdbtn201);
		
		
        ButtonGroup buttonGroupPerceptron = new ButtonGroup();
        
        buttonGroupPerceptron.add(rdbtn201);
        
        JRadioButton rdbtn203_D = new JRadioButton("2.3 (descente gradient)");
        rdbtn203_D.setBounds(68, 68, 190, 21);
        frame.getContentPane().add(rdbtn203_D);
        
        JRadioButton rdbtn203_A = new JRadioButton("2.3 (ADALINE)");
        rdbtn203_A.setBounds(68, 102, 164, 21);
        frame.getContentPane().add(rdbtn203_A);
        
        JRadioButton rdbtn209_D = new JRadioButton("2.9 (descente gradient)");
        rdbtn209_D.setBounds(68, 138, 190, 21);
        frame.getContentPane().add(rdbtn209_D);
        
        JRadioButton rdbtn209_A = new JRadioButton("2.9 (ADALINE)");
        rdbtn209_A.setBounds(68, 175, 103, 21);
        frame.getContentPane().add(rdbtn209_A);
        
        JRadioButton rdbtn210_D = new JRadioButton("2.10 (descente gradient)");
        rdbtn210_D.setBounds(298, 30, 169, 21);
        frame.getContentPane().add(rdbtn210_D);
        
        JRadioButton rdbtn210_A = new JRadioButton("2.10 (ADALINE)");
        rdbtn210_A.setBounds(298, 64, 169, 21);
        frame.getContentPane().add(rdbtn210_A);
        
        JRadioButton rdbtn211_D = new JRadioButton("2.11 (descente gradient)");
        rdbtn211_D.setBounds(298, 98, 169, 21);
        frame.getContentPane().add(rdbtn211_D);
        
        JRadioButton rdbtn211_A = new JRadioButton("2.11 (ADALINE)");
        rdbtn211_A.setBounds(298, 134, 169, 21);
        frame.getContentPane().add(rdbtn211_A);
        
        JRadioButton rdbtn301 = new JRadioButton("3.1");
        rdbtn301.setBounds(298, 171, 103, 21);
        frame.getContentPane().add(rdbtn301);
        
        JRadioButton rdbtn305 = new JRadioButton("3.5");
        rdbtn305.setBounds(564, 34, 103, 21);
        frame.getContentPane().add(rdbtn305);
        
        JRadioButton rdbtn403 = new JRadioButton("4.3");
        rdbtn403.setBounds(564, 68, 103, 21);
        frame.getContentPane().add(rdbtn403);
        
        JRadioButton rdbtn412 = new JRadioButton("4.12");
        rdbtn412.setBounds(564, 102, 103, 21);
        frame.getContentPane().add(rdbtn412);
        
        JRadioButton rdbtn414 = new JRadioButton("4.14");
        rdbtn414.setBounds(564, 138, 103, 21);
        frame.getContentPane().add(rdbtn414);
        
        JRadioButton rdbtn417 = new JRadioButton("4.17");
        rdbtn417.setBounds(564, 175, 103, 21);
        frame.getContentPane().add(rdbtn417);
        
		JRadioButton rdbtnExBasique = new JRadioButton("exemple basique (classement perceptron simple - d g)");
		rdbtnExBasique.setBounds(705, 34, 487, 21);
		frame.getContentPane().add(rdbtnExBasique);
		
		JRadioButton rdbtTree = new JRadioButton("exemple arbres (r\u00E9gression perceptron simple)");
		rdbtTree.setBounds(705, 104, 487, 21);
		frame.getContentPane().add(rdbtTree);

		JRadioButton rdbtnPomme = new JRadioButton("exemple pommes (classement perceptron multicouche - full batch)");
		rdbtnPomme.setBounds(705, 198, 487, 21);
		frame.getContentPane().add(rdbtnPomme);
		
       
		
		JRadioButton rdbtnExBasique2 = new JRadioButton("exemple basique (classement perceptron simple - ADA)");
		rdbtnExBasique2.setBounds(705, 68, 487, 21);
		frame.getContentPane().add(rdbtnExBasique2);
		
		JRadioButton rdbtnPomme2 = new JRadioButton("exemple pommes (classement perceptron multicouche - ADA)");
		rdbtnPomme2.setBounds(705, 226, 487, 21);
		frame.getContentPane().add(rdbtnPomme2);
		

		
	    bg.add(rdbtn201);
	    bg.add(rdbtn203_D);
	    bg.add(rdbtn203_A);
	    bg.add(rdbtn209_D);
	    bg.add(rdbtn209_A);
	    bg.add(rdbtn210_D);
	    bg.add(rdbtn210_A);
	    bg.add(rdbtn211_D);
	    bg.add(rdbtn211_A);
	    bg.add(rdbtn301);
	    bg.add(rdbtn305);
	    bg.add(rdbtn403);
	    bg.add(rdbtn412);
	    bg.add(rdbtn414);
	    bg.add(rdbtn417);
	    bg.add(rdbtnExBasique);
	    bg.add(rdbtnExBasique2);
	    bg.add(rdbtTree);
	    bg.add(rdbtnPomme);
	    bg.add(rdbtnPomme2);

	    
		JButton btnNewButton = new JButton("Go !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double[][] w = new double[1][3];
				DataToUse myData = new DataToUse();
		    	ChartMaker chartMaker = new ChartMaker();	

				if (rdbtn201.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_1.csv");
			    	PerceptronSimple.MiseAuPointPorteLogiqueET_2_1(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn203_D.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_3.csv");
			    	PerceptronSimple.Technique_descente_gradient(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn203_A.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_3.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn209_D.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_9.csv");
			    	PerceptronSimple.Technique_descente_gradient(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn209_A.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_9.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn210_D.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_10.csv");
			    	PerceptronSimple.Technique_descente_gradient(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn210_A.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_10.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn211_D.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_11.csv");
			    	PerceptronSimple.Technique_descente_gradient(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn211_A.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_2_11.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}
				if (rdbtn301.isSelected()) {
			    	myData.LoadDataFromCSV("Datas\\table_3_1.csv");
			    	w = PerceptronMonocouche.UseIt(myData);
			    	chartMaker.addSeries(myData);
			    	for (int i=0; i<w.length; i++) {
			    		chartMaker.addLineWithExplicitW(myData, w[i]);
			    	}
				}
				if (rdbtn305.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\table_3_5.csv");
			    	w = PerceptronMonocouche.UseIt(myData);
			    	for (int i=0; i<w.length; i++) {
			    		chartMaker.addLineWithExplicitW(myData, w[i]);
			    	}
				}
				if (rdbtn403.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\table_4_3.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratFULLBATCH(2, myData);
			    	chartMaker.addMulticoucheBackground(myData, permulti);
			    	w = new double[0][];	// pour qu'il n'y ait que le graphe qui s'affiche 
				}
				if (rdbtn412.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\table_4_12.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratFULLBATCH(5, myData);
			    	chartMaker.addMulticoucheBackground(myData, permulti);
			    	w = new double[0][];
				}
				if (rdbtn414.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\table_4_14.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratADALINE(5, myData);
			    	chartMaker.addMulticoucheBackground(myData, permulti);
			    	w = new double[0][];
				}
				if (rdbtn417.isSelected()) {	// TODO
			    	myData.LoadDataFromCSV("Datas\\table_4_17.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratADALINE(5, myData);
			    	chartMaker.addComplexRegressionLine(myData, permulti);
			    	w = new double[0][];
				}		
				
				
				
				
				
				if (rdbtnExBasique.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\exemple_class_1.csv");
			    	PerceptronSimple.Technique_descente_gradient(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}	
				
				if (rdbtnExBasique2.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\exemple_class_1.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}		

				
				if (rdbtTree.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\trees_reg.csv");
			    	PerceptronSimple.Technique_ADALINE(myData);
			    	chartMaker.addSeries(myData);
			    	chartMaker.addLine(myData);
			    	w[0] = myData.getFinalw();
				}		
				
				
				if (rdbtnPomme.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\pommes_class_2.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratFULLBATCH(10, myData);
			    	chartMaker.addMulticoucheBackground(myData, permulti);
			    	w = new double[0][];
				}		
				
				if (rdbtnPomme2.isSelected()) {	
			    	myData.LoadDataFromCSV("Datas\\pommes_class_2.csv");
			    	chartMaker.addSeries(myData);
			    	PerceptronMultiCouches permulti = new PerceptronMultiCouches();
			    	permulti.stratADALINE(10, myData);
			    	chartMaker.addMulticoucheBackground(myData, permulti);
			    	w = new double[0][];
				}		
				

		

		    	Main_Frame frame = new Main_Frame(chartMaker, myData, w);
		    	frame.setVisible(true);

			}
		});
		btnNewButton.setBounds(328, 244, 85, 21);
		frame.getContentPane().add(btnNewButton);


	}
}
