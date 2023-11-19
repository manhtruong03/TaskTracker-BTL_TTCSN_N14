package main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//public class TrelloCloneApp extends JFrame {
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
//					TrelloCloneApp frame = new TrelloCloneApp();
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
//	public TrelloCloneApp() {
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrelloCloneApp {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton createProjectButton;
    private JList<String> projectList; // Assuming a String list for simplicity
    private DefaultListModel<String> projectListModel;

    public TrelloCloneApp() {
        mainFrame = new JFrame("Trello Clone");
        mainPanel = new JPanel(new BorderLayout());

        createProjectButton = new JButton("Create Project");
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the project creation dialog
                showProjectCreationDialog();
            }
        });

        projectListModel = new DefaultListModel<>();
        projectListModel.add(0, "Project 1");
        projectListModel.add(1, "Project 2");
        projectList = new JList<>(projectListModel);

        // Add components to mainPanel based on whether there are projects
        if (projectListModel.isEmpty()) {
            mainPanel.add(createProjectButton, BorderLayout.CENTER);
        } else {
            mainPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        }

        mainFrame.add(mainPanel);
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        createProjectBoardUI();
    }

    private void showProjectCreationDialog() {
        // Create and display project creation dialog
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TrelloCloneApp();
            }
        });
    }
    
 // Inside the TrelloCloneApp class

    private void createProjectBoardUI() {
        // Assuming mainPanel is the panel where the board should be displayed
        mainPanel.removeAll(); // Clear the existing content

        JPanel boardPanel = new JPanel(new GridLayout(1, 3)); // 3 columns for To Do, Doing, Done

        String[] statuses = {"To Do", "Doing", "Done"};
        for (String status : statuses) {
            JPanel columnPanel = new JPanel(new BorderLayout());
            columnPanel.setBorder(BorderFactory.createTitledBorder(status));

            DefaultListModel<String> taskListModel = new DefaultListModel<>();
            JList<String> taskList = new JList<>(taskListModel);

            JButton addTaskButton = new JButton("Add Task");
            addTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Logic to add a task
                }
            });

            columnPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
            columnPanel.add(addTaskButton, BorderLayout.SOUTH);

            boardPanel.add(columnPanel);
        }

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Call createProjectBoardUI() when a project is created or selected

    
 // Inside the TrelloCloneApp class

    private void setupDragAndDrop(JList<String> list) {
        list.setDragEnabled(true);
        list.setDropMode(DropMode.INSERT);
//        list.setTransferHandler(new StringTransferHandler());
    }

    // Define StringTransferHandler class by extending TransferHandler
    // and override methods for importData and createTransferable

    
 // Inside the TrelloCloneApp class

    private void showTaskDetailsDialog(String taskName) {
        // Create and display task details dialog
        JDialog taskDetailsDialog = new JDialog(mainFrame, "Task Details", true);
        // Add components for task details
        taskDetailsDialog.setSize(new Dimension(300, 200));
        taskDetailsDialog.setVisible(true);
    }

    // Add a MouseListener to each task JList to detect double clicks and open the dialog

}

