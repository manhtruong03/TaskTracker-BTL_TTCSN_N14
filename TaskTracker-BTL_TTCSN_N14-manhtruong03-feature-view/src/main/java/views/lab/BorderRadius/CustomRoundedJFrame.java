package main.java.views.lab.BorderRadius;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;

public class CustomRoundedJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomRoundedJFrame frame = new CustomRoundedJFrame();
					frame.setVisible(true);
					
					CustomRoundedPanel panel = new CustomRoundedPanel(100, true, false, true, false); // top-left, bottom-left rounded
					panel.setBackground(Color.PINK);
					frame.getContentPane().add(panel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomRoundedJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
	}

}
