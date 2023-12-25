package main.java.views.components.dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controllers.ProjectController;
import main.java.models.Project;
import main.java.views.components.PanelCustomStyles;
import main.java.views.utils.Constant;

public class ProjectHeaderPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectHeaderPanel(String projectName) {
		final int WINDOW_WIDTH = Constant.WINDOW_WIDTH;
		
		setLayout(null);
		setSize(WINDOW_WIDTH, 50);
		setLocation(0, 50);
		setOpaque(false);
		
		
		JPanel projectHeader = new PanelCustomStyles(WINDOW_WIDTH, 60, 0.24f, 0, null, Color.BLACK, 0f);
		projectHeader.setLayout(null);
		projectHeader.setLocation(0, 0);
		add(projectHeader);
		
		
		JLabel lblProjectName = new JLabel(projectName);
		lblProjectName.setForeground(new Color(255, 255, 255));
		lblProjectName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblProjectName.setBounds(30, 0, 700, 45);
        projectHeader.add(lblProjectName);
	}

}
