package main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//public class KanbanBoard extends JFrame {
//
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					KanbanBoard frame = new KanbanBoard();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public KanbanBoard() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//	}
//
//}

import javax.swing.*;

public class KanbanBoard extends JFrame {
    
    public KanbanBoard() {
        setTitle("Kanban Board");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel to hold everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        // Add the columns
        mainPanel.add(createColumn("Upcoming"));
        mainPanel.add(createColumn("In Progress"));
        mainPanel.add(createColumn("Done"));
        
        // Add mainPanel to the JFrame
        add(mainPanel);
        
        setVisible(true);
    }
    
    private JScrollPane createColumn(String title) {
        // Create a panel to hold the column
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setBorder(BorderFactory.createTitledBorder(title));
        
        // Add tasks to the column
        for (int i = 0; i < 10; i++) {
            columnPanel.add(new JLabel("Task " + (i + 1)));
        }
        
        // Make the column scrollable
        JScrollPane scrollPane = new JScrollPane(columnPanel);
        return scrollPane;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KanbanBoard());
    }
}

