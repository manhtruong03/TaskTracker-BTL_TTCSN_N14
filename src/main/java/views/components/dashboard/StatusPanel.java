package main.java.views.components.dashboard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controllers.DataManipulation;
import main.java.controllers.ProjectController;
import main.java.controllers.TaskController;
import main.java.models.Project;
import main.java.models.Task;
import main.java.views.NavigationListener;
import main.java.views.DragDrop.DragDropHandler;
import main.java.views.components.PanelCustomStyles;
import net.miginfocom.swing.MigLayout;

public class StatusPanel extends JPanel {
	
	private List<Task> tasksByStatus;
	private String statusType;

	public List<Task> getTasksByStatus() {
		return tasksByStatus;
	}

	public void setTasksByStatus(List<Task> tasksByStatus) {
		this.tasksByStatus = tasksByStatus;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	/**
	 * Create the panel.
	 */
	public StatusPanel(List<Task> tasksByStatus, NavigationListener navigationListener) {
		this.tasksByStatus = tasksByStatus;
		this.statusType = tasksByStatus.get(0).getStatus().toUpperCase();
		
		setBackground(new Color(128, 0, 255));
		setSize(290, 720);
		setOpaque(false);
		setLayout(new MigLayout());
		
		JPanel statusPanel = new PanelCustomStyles(275, 50, 1, 35, 
				new GradientPaint(0, 0, new Color(241, 242, 244), 320, 20, new Color(241, 242, 244)),
				Color.BLACK, 0.1f);
		statusPanel.setLayout(new MigLayout("wrap, width 275"));
		
		
		String projectID = tasksByStatus.get(0).getProjectID();
		ProjectController projectController = new ProjectController();
		int indexProject = DataManipulation.getPositionById(projectController.getListOfProject(), projectID);
		Project project = projectController.getListOfProject().get(indexProject);
		DragDropHandler.setupDrop(statusPanel, statusType, project, navigationListener);
		
		JLabel lblStatus = new JLabel(statusType);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 18));
		statusPanel.add(lblStatus, "gapleft 20, gapright 20, gaptop 10, gapbottom 10, span, wrap");
		
		TaskController tc = new TaskController(tasksByStatus);
		for (Task task : tc.getListOfTask()) {
			JPanel taskOverviewPanel =  new TaskOverviewPanel(task);
			DragDropHandler.setupDrag(taskOverviewPanel);
			statusPanel.add(taskOverviewPanel, "gap 2 2 2 2");
			statusPanel.revalidate();
			statusPanel.repaint();
		}
		
		add(statusPanel);
		this.revalidate();
		this.repaint();
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame mainFrame = new JFrame();
					
					mainFrame.setSize(1280, 720);
					mainFrame.setLocationRelativeTo(null);
					TaskController taskController = new TaskController();
					List<Task> taskList = taskController.getListOfTask();
					mainFrame.setContentPane(new StatusPanel(taskList, null));
					mainFrame.setVisible(true);
					mainFrame.setBackground(new Color(128, 0, 255));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
