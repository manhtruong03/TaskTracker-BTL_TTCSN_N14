package main.java.views.main;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

public class LabelBackground extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField textPassword;

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
		setLocationRelativeTo(null);
		setSize(1280, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		
		JLabel lblBackground = new JLabel("Lable background");
		lblBackground.setLocation(0, 0);
		lblBackground.setSize(1277, 689);
		ImageIcon bg = new ImageIcon("src/assets/images/luke-chesser-eICUFSeirc0-unsplash.jpg");
		lblBackground.setIcon(bg);
		
		JLabel lblUsername = new JLabel("User name: ");
		lblUsername.setBounds(311, 233, 74, 20);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(311, 279, 74, 20);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		textUserName = new JTextField();
		textUserName.setBounds(403, 233, 158, 20);
		textUserName.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(403, 282, 158, 20);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(412, 337, 57, 21);
		contentPane.setLayout(null);
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(textUserName);
		contentPane.add(textPassword);
		contentPane.add(btnLogin);
		contentPane.add(lblBackground);
		
		File fileImage = new File("src/assets/images/milad-fakurian-E8Ufcyxz514-unsplash.jpg");
		setimageLable(lblBackground, fileImage);
		
		JPanel panel = new JPanel();
		panel.setBounds(311, 116, 400, 525);
		contentPane.add(panel);
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
