package main.java.views.components.dashboard;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import main.java.views.components.PanelCustomStyles;
import main.java.views.components.TextFieldCustomStyles;
import main.java.views.utils.Constant;
import main.java.views.utils.UtilsView;

public class HeaderPanel extends JPanel {
	private JTextField textSearch;
	private JTextField txtHellu;

	/**
	 * Create the panel.
	 */
	public HeaderPanel() {
		final int WINDOW_WIDTH = Constant.WINDOW_WIDTH;
		
		setLayout(null);
		setSize(WINDOW_WIDTH, 50);
		setLocation(0, 0);
		
		
		JPanel header = new PanelCustomStyles(WINDOW_WIDTH, 50, 1, 0,
				new GradientPaint(0, 0, new Color(113, 53, 151), 0, 0, new Color(113, 53, 151)),
				Color.BLACK, 0.0f);
		header.setLayout(null);
		header.setLocation(0, 0);
		
		
		
		JLabel lblNameProgram = new JLabel("TASK TRACKER");
        lblNameProgram.setForeground(new Color(255, 255, 255));
        lblNameProgram.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
        lblNameProgram.setBounds(30, 0, 212, 45);
        header.add(lblNameProgram);
		

		
		textSearch = new TextFieldCustomStyles("Tìm kiếm . . .", 200, 30, 0.3f, 10, 
        		new GradientPaint(0, 0, new Color(255, 255, 255), 0, 0, new Color(255, 255, 255)));
        textSearch.setBorder(BorderFactory.createEmptyBorder(7, 20, 8, 10));
        textSearch.setLocation(950, 10);
        textSearch.setForeground(Color.white);
        textSearch.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        header.add(textSearch);
        
        add(header);
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame mainFrame = new JFrame();
					JPanel contentPanel =  new HeaderPanel();
					
					mainFrame.setSize(contentPanel.getSize());
					mainFrame.setLocationRelativeTo(null);
					mainFrame.setContentPane(contentPanel);
					mainFrame.setVisible(true);
					mainFrame.setBackground(new Color(128, 0, 255));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
