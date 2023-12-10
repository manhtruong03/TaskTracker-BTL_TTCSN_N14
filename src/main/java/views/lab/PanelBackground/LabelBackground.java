package main.java.views.lab.PanelBackground;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.java.views.main.LoginForm;
import main.java.views.main.component.ButtonCustomStyles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class LabelBackground extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField textPassword;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabelBackground frame = new LabelBackground();
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
	public LabelBackground() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		
		JLabel lblBackground = new JLabel("Lable background");
		lblBackground.setLocation(0, 0);
		lblBackground.setSize(1277, 689);
		File fileImage = new File("src/assets/images/milad-fakurian-E8Ufcyxz514-unsplash.jpg");
		setimageLable(lblBackground, fileImage);
		
		
		JPanel loginForm = new LoginForm();
		loginForm.setLocation(798, 70);
		
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
		
		
		JButton btnLogin = new ButtonCustomStyles("LOGIN", 320, 40, 1, 30, 
				new GradientPaint(0, 0, Color.CYAN, 320, 35, Color.MAGENTA));
		btnLogin.setBackground(null);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setLocation(45, 338);
		btnLogin.setBorder(null);
		
		contentPane.setLayout(null);
		loginForm.setLayout(null);
		
		loginForm.add(lblUsername);
		loginForm.add(lblPassword);
		loginForm.add(textUserName);
		loginForm.add(textPassword);
		loginForm.add(btnLogin);
		contentPane.add(loginForm);
		loginForm.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("TASK TRACKER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 55));
		lblNewLabel.setBounds(154, 107, 452, 112);
		contentPane.add(lblNewLabel);
		
		JLabel lblTopic = new JLabel("Phần mềm Quản lý dự án phát triển phần mềm");
		lblTopic.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblTopic.setForeground(new Color(255, 255, 255));
		lblTopic.setBounds(133, 209, 473, 27);
		contentPane.add(lblTopic);
		
//		JTextPane txtpnd = new JTextPane();
//		txtpnd.setForeground(new Color(255, 255, 255));
//		txtpnd.setFont(new Font("Segoe UI", Font.BOLD, 13));
//		txtpnd.setText("giảng viên hướng dẫn: ThS. Lê Anh Thắng\n"
//				+ "Nhóm\t: 14 - 20231IT6121002\n"
//				+ "Thành viên : Trương Công Mạnh \t– 2021601910\n"
//				+ "\t  Nguyễn Minh Quang \t– 2021601956\n"
//				+ "\t  Nguyễn Công Thành \t– 2021601687\n"
//				+ "\t  Nguyễn Bá Thông \t– 2021601170\n"
//				+ "\t  Nguyễn Anh Tiến \t– 2021602014");
//		txtpnd.setBounds(185, 281, 444, 153);
//		txtpnd.setBorder(null);
//		txtpnd.setOpaque(false);
//		contentPane.add(txtpnd);
		
		
		
		JButton btnForgotPassword = new JButton("Forgot password?");
		btnForgotPassword.setSize(135, 35);
		btnForgotPassword.setLocation(142, 469);
		btnForgotPassword.setBackground(null);
		btnForgotPassword.setForeground(new Color(255, 255, 255));
		btnForgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnForgotPassword.setBorder(null);
		btnForgotPassword.setOpaque(false);
		
		loginForm.add(btnForgotPassword);
		
		contentPane.add(lblBackground);
		
	}
	
	private void setimageLable(JLabel imageLabel, File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image resizeImage = originalImage.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(resizeImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
