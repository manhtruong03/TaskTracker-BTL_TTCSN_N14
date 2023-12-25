package main.java.views.components.login;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import main.java.controllers.ProjectController;
import main.java.controllers.UserController;
import main.java.models.User;
import main.java.views.NavigationListener;
import main.java.views.components.ButtonCustomStyles;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JPanel {
	
	private JTextField textUserName;
	private JTextField textPassword;
	private JPasswordField passwordField;
	private JLabel lblLoginError;
	NavigationListener navigationListener;

	/**
	 * Create the panel.
	 */
	public LoginForm(NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
		setSize(400, 525);
		setBackground(Color.red);
		setOpaque(false);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(new Color(247, 248, 250));
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblLogin.setBounds(147, 46, 130, 52);
		
		JLabel lblUsername = new JLabel("User name: ");
		lblUsername.setForeground(new Color(247, 248, 250));
		lblUsername.setBounds(45, 135, 104, 20);
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setForeground(new Color(247, 248, 250));
		lblPassword.setBounds(45, 229, 90, 20);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textUserName.setBounds(45, 165, 320, 40);
		textUserName.setColumns(10);
		textUserName.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		textPassword.setBounds(45, 258, 320, 40);
		textPassword.setColumns(10);
		textPassword.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
		
		lblLoginError = new JLabel();
		lblLoginError.setForeground(Color.RED);
		lblLoginError.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoginError.setBounds(45, 309, 320, 20);
		
		
		JButton btnLogin = new ButtonCustomStyles("LOGIN", 320, 40, 1, 30, 
				new GradientPaint(0, 0, Color.CYAN, 320, 35, Color.MAGENTA));
		btnLogin.addActionListener(e -> verifyAccount());
		btnLogin.setBackground(null);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setLocation(45, 339);
		btnLogin.setBorder(null);
		
		JButton btnForgotPassword = new JButton("Forgot password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnForgotPassword.setSize(135, 35);
		btnForgotPassword.setLocation(142, 469);
		btnForgotPassword.setBackground(null);
		btnForgotPassword.setForeground(new Color(255, 255, 255));
		btnForgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnForgotPassword.setBorder(null);
		btnForgotPassword.setOpaque(false);
		
		this.setLayout(null);
		
		this.add(lblUsername);
		this.add(lblPassword);
		this.add(textUserName);
		this.add(textPassword);
		this.add(lblLoginError);
		this.add(btnLogin);
		this.add(lblLogin);
		this.add(btnForgotPassword);

	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set transparency (alpha) level
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.4f));
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 60, 60);
        g2d.fill(roundedRectangle);
        g2d.dispose();
    }
	
	public void verifyAccount() {
		String userName = textUserName.getText().trim();
		String password = textPassword.getText().trim();
		
		UserController uc = new UserController();
		for (User usr : uc.getListOfUser()) {
			String userEmail = usr.getEmail();
			String userPhoneNumber = usr.getPhoneNumber();
			String userPassword = usr.getEncryptedPassword();
			
			boolean flagUserName = (userEmail.equals(userName) || userPhoneNumber.equals(userName)) ? true : false;
			boolean flagPassword = (userPassword.equals(password)) ? true : false;
			
			if (flagUserName && flagPassword) {
				if (navigationListener != null) {
//		            navigationListener.onLoginSelected();
					ProjectController pc = new ProjectController();
					navigationListener.onDashboardSelected(pc.getListOfProject().get(2));
		        }
			}
		}
		
		lblLoginError.setText("Sai tài khoản hoặc mật khẩu!");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame loginFrame = new JFrame();
//					JPanel loginPanel =  new LoginForm(navigationListener);
//					
//					loginFrame.setSize(loginPanel.getSize());
//					loginFrame.setLocationRelativeTo(null);
//					loginFrame.setContentPane(loginPanel);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
