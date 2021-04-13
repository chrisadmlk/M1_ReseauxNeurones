package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tools_Classes.DataToUse;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Main_Frame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Main_Frame(DataToUse myData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nb d'it\u00E9rations : " + myData.getNbIter());
		lblNewLabel_2.setBounds(20, 97, 131, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Facteur de correction : " );
		lblNewLabel_2_2.setBounds(20, 143, 230, 13);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("E moy : " + myData.getErrMoy());
		lblNewLabel_2_2_2.setBounds(20, 166, 131, 13);
		contentPane.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nb d'erreurs : "+ myData.getNbErreur());
		lblNewLabel_3.setBounds(20, 120, 176, 13);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();			// TODO : appeler fonction de chartMaker qui renvoit un chart àpd d'un dataToUse
		panel.setBounds(20, 207, 415, 257);
		contentPane.add(panel);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(185, 493, 85, 21);
		contentPane.add(btnRetour);
	}
}
