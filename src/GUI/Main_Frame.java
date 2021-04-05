package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Main_Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nb d'it\u00E9rations : ");
		lblNewLabel_2.setBounds(20, 97, 131, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Facteur de correction : ");
		lblNewLabel_2_2.setBounds(20, 143, 230, 13);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("E moy :");
		lblNewLabel_2_2_2.setBounds(20, 166, 131, 13);
		contentPane.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nb d'erreurs : ");
		lblNewLabel_3.setBounds(20, 120, 176, 13);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 207, 415, 257);
		contentPane.add(panel);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(185, 493, 85, 21);
		contentPane.add(btnRetour);
	}
}
