package main.java.views.lab;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrelloLikeApp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					TrelloLikeApp frame = new TrelloLikeApp();
//					frame.setVisible(true);
					
					JFrame frame = new JFrame("Trello-like App");
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame.getContentPane().setLayout(new FlowLayout()); // FlowLayout for simplicity
			        
			        
			        
			        
			        frame.setGlassPane(new JComponent() {
			        	@Override
			        	protected void paintComponent(Graphics g) {
			        		g.setColor(new Color(0, 0, 0, 150));
			        		g.fillRect(0, 0, getWidth(), getHeight());
			        		super.paintComponent(g);
			        	}
					});
			        
			        

			        // Add your panels here
			        // ...
			     // Inside createAndShowGUI method of TrelloLikeApp class
			        int panelCardWidth = 200;
			        int panelCardHeight = 100;

			        PanelTable todoPanel = new PanelTable("To Do", panelCardWidth, panelCardHeight);
			        for (int i = 0; i < 4; i++) {
			            PanelCard card1 = new PanelCard("Task " + i, panelCardWidth, panelCardHeight);
			            card1.addMouseListener(new MouseAdapter() {
			            	private int index;
			            	public void mousePressed(MouseEvent e) {
			            		frame.getGlassPane().setVisible(true);
			            		Container glassPanel = (Container) frame.getContentPane();
			            		index = todoPanel.getComponentZOrder(card1);
			            		glassPanel.add(card1);
			            	}
			            	
			            	public void mouseReleased(MouseEvent e) {
			            		frame.getGlassPane().setVisible(false);
			            		todoPanel.addPanelCard(card1, index);
			            	}
			            });
			            todoPanel.addPanelCard(card1);
			            
					}
			        
			        PanelCard card2 = new PanelCard("Task test", panelCardWidth, panelCardHeight);
		            todoPanel.add(card2, 1);
		            todoPanel.setComponentZOrder(card2, 3);
		            frame.getContentPane().add(todoPanel);
		            System.out.println(todoPanel.getComponentZOrder(card2));
		            
			        PanelTable todoPanel2 = new PanelTable("Doing", panelCardWidth, panelCardHeight);
			        for (int i = 4; i < 9; i++) {
			            PanelCard card1 = new PanelCard("Task " + i, panelCardWidth, panelCardHeight);
			            todoPanel2.addPanelCard(card1);

			            frame.getContentPane().add(todoPanel2);
					}
			        
			        PanelTable todoPanel3 = new PanelTable("Doing", panelCardWidth, panelCardHeight);
			        frame.getContentPane().add(todoPanel3);

			        // Repeat for other panels and cards


			        frame.pack();
			        frame.setLocationRelativeTo(null);
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
	public TrelloLikeApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		JFrame frame = new JFrame("Trello-like App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout()); // FlowLayout for simplicity

        // Add your panels here
        // ...
     // Inside createAndShowGUI method of TrelloLikeApp class
        int panelCardWidth = 200;
        int panelCardHeight = 100;
        
        
        
        PanelTable todoPanel = new PanelTable("To Do", panelCardWidth, panelCardHeight);
        PanelCard card1 = new PanelCard("Task 1", panelCardWidth, panelCardHeight);
        todoPanel.addPanelCard(card1);

//        for (int i = 0; i < 4; i++) {
//        	PanelTable todoPanel2 = new PanelTable("Doing", panelCardWidth, panelCardHeight);
//            PanelCard card2 = new PanelCard("Task " + i, panelCardWidth, panelCardHeight);
//            todoPanel.addPanelCard(card2);
//
//            
//		}
        frame.getContentPane().add(todoPanel);

        // Repeat for other panels and cards


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
