package main.java.views.lab.BoxModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class BoxModel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoxModel frame = new BoxModel();
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
	public BoxModel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new MigLayout("debug"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		
		
		// Padding
		JPanel paddingPane = new JPanel();
		paddingPane.setBorder(BorderFactory.createEmptyBorder(100, 70, 30, 0));	// top, left, bottom, right
		paddingPane.add(new JLabel("Padding"));
//		paddingPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(paddingPane);
//		setContentPane(paddingPane);
		

		// Border
		JPanel borderPane = new JPanel();
		borderPane.setBorder(BorderFactory.createLineBorder(Color.orange));
		borderPane.add(new JLabel("Border"));
		contentPane.add(borderPane);
//		setContentPane(borderPane);
		
		
		// Margin
		JPanel marginPane = new JPanel(new MigLayout("debug"));
		marginPane.add(new JLabel("Margin"), "gapleft 0, gaptop 100, gapright 10, gapbottom 10");
		contentPane.add(marginPane);
//		setContentPane(marginPane);
		
		
		// Combining Them
		JButton button = new JButton("Combining - Click Me");
		button.setPreferredSize(new Dimension(100, 50)); // Content size
		button.setBorder(BorderFactory.createEmptyBorder(100, 70, 30, 0)); // Padding
//		button.setBorder(BorderFactory.createLineBorder(Color.black)); // Border

		JPanel panel = new JPanel(new MigLayout("debug"));
		panel.add(button, "gap 150 150 15 15"); // Margins
		contentPane.add(panel);
		
		pack();
	}

}
