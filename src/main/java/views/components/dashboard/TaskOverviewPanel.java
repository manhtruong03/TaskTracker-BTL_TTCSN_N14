package main.java.views.components.dashboard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.controllers.TaskController;
import main.java.models.Task;
import main.java.views.components.PanelCustomStyles;
import net.miginfocom.swing.MigLayout;

public class TaskOverviewPanel extends JPanel {
	public String taskID;

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * Create the TaskOverviewPanel.
	 */
	public TaskOverviewPanel(Task task) {
		this.taskID = task.getId();
		
		setOpaque(false);
		setLayout(new MigLayout("", "0[]0", "0[]0"));
		
		
		JPanel taskOverview = new PanelCustomStyles(260, 50, 1, 35, 
				new GradientPaint(0, 0, Color.WHITE, 320, 20, Color.WHITE),
				Color.BLACK, 0.1f);
		taskOverview.setLayout(new MigLayout("width 260!"));
		
		
		JLabel lblTaskName = new JLabel(task.getTaskName());
		lblTaskName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		taskOverview.add(lblTaskName, "gapleft 10, span, wrap");
		
		JLabel lblDueDate = new JLabel(task.getDueDate());
		lblDueDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDueDate.setForeground(Color.WHITE);
		lblDueDate.setBackground(new Color(31, 132, 90));
		lblDueDate.setOpaque(true);
		lblDueDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 1, 5));
		taskOverview.add(lblDueDate, "gapleft 10");
		
		JLabel lblComment = new JLabel("cmt: 2");
		lblComment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		taskOverview.add(lblComment, "gapleft 10");
		
		JLabel lblTodo = new JLabel("todo: 0/3");
		lblTodo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		taskOverview.add(lblTodo, "wrap, gapleft 10");
		
		JLabel lblTaskMember = new JLabel("Member:");
		lblTaskMember.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		taskOverview.add(lblTaskMember, "span, wrap, gapleft 10");
		
		
		taskOverview.revalidate();
		taskOverview.repaint();
		
		add(taskOverview);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame mainFrame = new JFrame();
					
					TaskController tc = new TaskController();
					JPanel contentPanel =  new TaskOverviewPanel(tc.getListOfTask().get(2));
					
					mainFrame.setSize(1280, 720);
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
