 package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import Perceptrons.PerceptronSimple;
import Tools_Classes.ChartMaker;
import Tools_Classes.DataToUse;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Main_Frame extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */

	public Main_Frame(ChartMaker chartMaker, DataToUse myData, double[][]w) {
		
		
		
    	ChartPanel panel = new ChartPanel(chartMaker.finalize("magnique titre"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 666);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnRetour.setBounds(245, 580, 111, 28);
		contentPane.add(btnRetour);

		
		
		
		if (w.length==1) {
		
			JLabel lblNewLabel_2 = new JLabel("Nb d'it\u00E9rations : " + myData.getNbIter());
			lblNewLabel_2.setBounds(20, 31, 131, 13);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_2_2 = new JLabel("Facteur de correction : " + myData.getFacteurCorr() );
			lblNewLabel_2_2.setBounds(20, 77, 230, 13);
			contentPane.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_2_2_2 = new JLabel("E moy : " + myData.getErrMoy());
			lblNewLabel_2_2_2.setBounds(20, 100, 131, 13);
			contentPane.add(lblNewLabel_2_2_2);
			
			JLabel lblNewLabel_3 = new JLabel("Nb d'erreurs : "+ myData.getNbErreur());
			lblNewLabel_3.setBounds(20, 54, 176, 13);
			contentPane.add(lblNewLabel_3);
			
			JLabel lblW =null;
			if (myData.getCas() == 211) lblW = new JLabel("y =  " + w[0][0] + " * x + " + w[0][1] + " (équation issues des wi)" );
			else lblW = new JLabel("w : " + w[0][0] + " / " + w[0][1] + " / " + w[0][2]  );
			lblW.setBounds(20, 123, 568, 13);
			contentPane.add(lblW);
		}
		
		
		else if (w.length==3){
			
			JLabel lblNewLabel_2 = new JLabel("Classe 1 (w) : " + w[0][0] + " / " + w[0][1] + " / " + w[0][2] );
			lblNewLabel_2.setBounds(20, 31, 511, 13);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_2_2 = new JLabel("Classe 2 (w) : " + w[1][0] + " / " + w[1][1] + " / " + w[1][2] );
			lblNewLabel_2_2.setBounds(20, 77, 511, 13);
			contentPane.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_2_2_3 = new JLabel("Classe 3 (w) : " + w[2][0] + " / " + w[2][1] + " / " + w[2][2]);
			lblNewLabel_2_2_3.setBounds(20, 100, 131, 13);
			
		}
		
		

		
		else if (w.length==4){
			
			JLabel lblNewLabel_2 = new JLabel("Symbole 1 : (nbIter) " + w[0][0] + " / (err moy) " + w[0][1]);
			lblNewLabel_2.setBounds(20, 31, 505, 13);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_2_2 = new JLabel("Symbole 2 : (nbIter) " + w[1][0] + " / (err moy) " + w[1][1]);
			lblNewLabel_2_2.setBounds(20, 54, 505, 13);
			contentPane.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_2_2_2 = new JLabel("Symbole 3 : (nbIter) " + w[2][0] + " / (err moy) " + w[2][1]);
			lblNewLabel_2_2_2.setBounds(20, 77, 505, 13);
			contentPane.add(lblNewLabel_2_2_2);
			
			JLabel lblNewLabel_3 = new JLabel("Symbole 4 : (nbIter) " + w[3][0] + " / (err moy) " + w[3][1]);
			lblNewLabel_3.setBounds(20, 100, 466, 13); 
			contentPane.add(lblNewLabel_3);
			
			return;
		}
		
		
		panel.setBounds(10, 168, 589, 393);
		contentPane.add(panel);
		

		
	}
	
	
}
