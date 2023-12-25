package main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.controllers.ProjectController;
import main.java.models.Project;
import main.java.views.components.login.LoginForm;
import main.java.views.screens.Dashboard;
import main.java.views.screens.LoginPanel;
import main.java.views.utils.Constant;

public class TrelloApp extends JFrame implements NavigationListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrelloApp frame = new TrelloApp();
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
	public TrelloApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constant.DEFAULT_WINDOW_SIZE);
		setLocationRelativeTo(null);
		setContentPane(new LoginPanel(this));
	}

	@Override
	public void onLoginSelected() {
		setContentPane(new LoginForm(this));
        revalidate();
        repaint();
	}
	
	@Override
	public void onDashboardSelected(Project project) {
		setContentPane(new Dashboard(project, this));
        revalidate();
        repaint();
	}

}
