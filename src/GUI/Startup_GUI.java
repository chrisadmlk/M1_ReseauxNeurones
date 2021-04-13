package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
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
		frame.setBounds(100, 100, 617, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Go !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// prendre infos des boutons et les passer à la fenêtre suivante
				
			}
		});
		btnNewButton.setBounds(268, 234, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton(" simple");
		rdbtnNewRadioButton.setBounds(189, 48, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("monocouche");
		rdbtnNewRadioButton_1.setBounds(342, 48, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("multicouche");
		rdbtnNewRadioButton_2.setBounds(494, 48, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("classification");
		rdbtnNewRadioButton_3.setBounds(189, 90, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("r\u00E9gression");
		rdbtnNewRadioButton_4.setBounds(342, 90, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Exemples du cours");
		rdbtnNewRadioButton_5.setBounds(189, 168, 151, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("Autres exemples");
		rdbtnNewRadioButton_6.setBounds(342, 168, 174, 21);
		frame.getContentPane().add(rdbtnNewRadioButton_6);
		
		JLabel lblNewLabel = new JLabel("Type de perceptron :");
		lblNewLabel.setBounds(21, 52, 155, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTypeDeTche = new JLabel("Type de t\u00E2che :");
		lblTypeDeTche.setBounds(21, 94, 155, 13);
		frame.getContentPane().add(lblTypeDeTche);
		
		JLabel lblOrigineDesDatas = new JLabel("Origine des datas :");
		lblOrigineDesDatas.setBounds(21, 172, 155, 13);
		frame.getContentPane().add(lblOrigineDesDatas);
		
		
        ButtonGroup buttonGroupPerceptron = new ButtonGroup();
        
        buttonGroupPerceptron.add(rdbtnNewRadioButton);
        buttonGroupPerceptron.add(rdbtnNewRadioButton_1);
        buttonGroupPerceptron.add(rdbtnNewRadioButton_2);
       
        ButtonGroup buttonGroupTache = new ButtonGroup();

        buttonGroupTache.add(rdbtnNewRadioButton_3);
        buttonGroupTache.add(rdbtnNewRadioButton_4);
       
        ButtonGroup buttonGroupOrigine = new ButtonGroup();
        
        buttonGroupTache.add(rdbtnNewRadioButton_5);
        buttonGroupTache.add(rdbtnNewRadioButton_6);
        
        JLabel lblTechnique = new JLabel("Technique");
        lblTechnique.setBounds(21, 135, 155, 13);
        frame.getContentPane().add(lblTechnique);
        
        JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("Descente du gradient");
        rdbtnNewRadioButton_7.setBounds(189, 131, 151, 21);
        frame.getContentPane().add(rdbtnNewRadioButton_7);
        
        JRadioButton rdbtnNewRadioButton_7_1 = new JRadioButton("ADALINE");
        rdbtnNewRadioButton_7_1.setBounds(342, 131, 103, 21);
        frame.getContentPane().add(rdbtnNewRadioButton_7_1);
        
        
		
		
		
	}
}
