package main.java.views.screens;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controllers.ProjectController;
import main.java.controllers.TaskController;
import main.java.models.Project;
import main.java.models.Task;
import main.java.utils.CompareByPosition;
import main.java.views.NavigationListener;
import main.java.views.components.dashboard.HeaderPanel;
import main.java.views.components.dashboard.ProjectHeaderPanel;
import main.java.views.components.dashboard.StatusPanel;
import main.java.views.components.dashboard.TaskOverviewPanel;
import main.java.views.utils.Constant;
import main.java.views.utils.UtilsView;
import net.miginfocom.swing.MigLayout;

public class Dashboard extends JPanel {
	
	final int WINDOW_WIDTH = Constant.WINDOW_WIDTH;
	private String projectID;
	private String projectName;
	private List<Task> sourceTaskList;
	private List<Task> taskListByProjectID;
	private List<List<Task>> taskListByStatus;
	NavigationListener navigationListener;

	/**
	 * Constructor 
	 * @param project
	 */
	public Dashboard(Project project, NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
		initializeVariable(project);
		
		setLayout(null);
		setSize(Constant.DEFAULT_WINDOW_SIZE);
		setLocation(0, 0);
		
		JPanel header = new HeaderPanel();
		add(header);
		

		JPanel projectHeader = new ProjectHeaderPanel(projectName);
		add(projectHeader);
		
		int xLocation = 10;
		int yLocation = 110;
        for (List<Task> statusList : taskListByStatus) {
        	Collections.sort(statusList, new CompareByPosition());
        	JPanel statusPanel = new StatusPanel(statusList, navigationListener);
            statusPanel.setLocation(xLocation, yLocation);
            xLocation += 350;
            add(statusPanel);
		}
		
		JLabel lblBackground = new JLabel();
        lblBackground.setLocation(0, 0);
        lblBackground.setSize(Constant.DEFAULT_WINDOW_SIZE);
        File fileImage = new File(project.getBackGroundImage());
        UtilsView.setImageLable(lblBackground, fileImage);
        add(lblBackground);
	}
	
	private void initializeVariable(Project project) {
		this.projectID = project.getId();
		this.projectName = project.getProjectName();
		
		TaskController tc = new TaskController();
		this.sourceTaskList = tc.getListOfTask();
		
		this.taskListByProjectID = new ArrayList<>();
		
        for (Task task : sourceTaskList) {
            String taskProjectID = task.getProjectID();
            if (taskProjectID.equalsIgnoreCase(this.projectID)) {
            	taskListByProjectID.add(task);
            }
        }
        
        Map<String, List<Task>> map = new HashMap<>();

        for (Task task : taskListByProjectID) {
            String statusType = task.getStatus().toUpperCase();
            if (!map.containsKey(statusType)) {
                map.put(statusType, new ArrayList<>());
            }
            map.get(statusType).add(task);
        }

        // Convert map values to List<List<Task>>
        this.taskListByStatus = new ArrayList<>();
        for (List<Task> statusList : map.values()) {
        	taskListByStatus.add(statusList);
        }
	}
	
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame loginFrame = new JFrame();
					
					ProjectController pc = new ProjectController();
					JPanel contentPanel =  new Dashboard(pc.getListOfProject().get(2), null);
					
					loginFrame.setSize(contentPanel.getSize());
					loginFrame.setLocationRelativeTo(null);
					loginFrame.setContentPane(contentPanel);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
