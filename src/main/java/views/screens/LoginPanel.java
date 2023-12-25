package main.java.views.screens;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.views.NavigationListener;
import main.java.views.components.login.LoginForm;
import main.java.views.utils.Constant;
import main.java.views.utils.UtilsView;

public class LoginPanel extends JPanel  {

    /**
     * Create the panel.
     */
    public LoginPanel(NavigationListener navigationListener) {
        final int WINDOW_WIDTH = Constant.WINDOW_WIDTH;
        final int WINDOW_HEIGHT = Constant.WINDOW_HEIGHT;
        
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(null);
        
        JLabel lblNameProgram = new JLabel("TASK TRACKER");
        lblNameProgram.setForeground(new Color(255, 255, 255));
        lblNameProgram.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 55));
        lblNameProgram.setBounds(143, 94, 491, 152);
        this.add(lblNameProgram);
        
        JLabel lblTopic = new JLabel("Phần mềm Quản lý dự án phát triển phần mềm");
        lblTopic.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
        lblTopic.setForeground(new Color(255, 255, 255));
        lblTopic.setBounds(122, 196, 512, 50);
        this.add(lblTopic);
        
        JPanel loginForm = new LoginForm(navigationListener);
        loginForm.setLocation(773, 70);
        this.add(loginForm);
        
        JLabel lblBackground = new JLabel();
        lblBackground.setLocation(0, 0);
        lblBackground.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        File fileImage = new File("src/assets/images/milad-fakurian-E8Ufcyxz514-unsplash.jpg");
        UtilsView.setImageLable(lblBackground, fileImage);
        this.add(lblBackground);
    }

}






























//JTextPane txtpnd = new JTextPane();
//txtpnd.setForeground(new Color(255, 255, 255));
//txtpnd.setFont(new Font("Segoe UI", Font.BOLD, 13));
//txtpnd.setText("giảng viên hướng dẫn: ThS. Lê Anh Thắng\n"
//		+ "Nhóm\t: 14 - 20231IT6121002\n"
//		+ "Thành viên : Trương Công Mạnh \t– 2021601910\n"
//		+ "\t  Nguyễn Minh Quang \t– 2021601956\n"
//		+ "\t  Nguyễn Công Thành \t– 2021601687\n"
//		+ "\t  Nguyễn Bá Thông \t– 2021601170\n"
//		+ "\t  Nguyễn Anh Tiến \t– 2021602014");
//txtpnd.setBounds(185, 281, 444, 153);
//txtpnd.setBorder(null);
//txtpnd.setOpaque(false);
//contentPane.add(txtpnd);
