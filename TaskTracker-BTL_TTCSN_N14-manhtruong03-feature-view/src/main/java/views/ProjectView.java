package main.java.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.java.controllers.ProjectController;
import main.java.dao.DAO;
import main.java.models.Project;
import main.java.views.main.component.ButtonCustomStyles;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

public class ProjectView extends JFrame {
	private JPanel panelMain;
    private JLabel lblTitle;
    private JPanel panelContent;
    private JButton btnAddProject;
    private ProjectController pc;

    private static final int PANELS_PER_ROW = 6;
    private static final int PANEL_WIDTH = 150;
    private static final int PANEL_HEIGHT = 100;

    public ProjectView() {
    	setResizable(false);
        initComponents();
    }

    private void initComponents() {
    	this.setVisible(true);
        setTitle("Project View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1005, 632);
        setLocationRelativeTo(null);

        // Panel Main with BorderLayout
        panelMain = new JPanel(new BorderLayout());

        // Label Workspace
        lblTitle = new JLabel("Không gian làm việc", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelMain.add(lblTitle, BorderLayout.NORTH);

        // Panel Content with GridBagLayout
        panelContent = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(panelContent);
        panelMain.add(scrollPane, BorderLayout.CENTER);

        // Panel for Button Add Project
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        displayProjectInFile();

        // Button Add Project
        btnAddProject = new JButton("Thêm dự án");
//        btnAddProject = new ButtonCustomStyles("Thêm dự án", 320, 40, 1, 30, 
//				new GradientPaint(0, 0, Color.CYAN, 320, 35, Color.MAGENTA));
        btnAddProject.setPreferredSize(new Dimension(150, 30));
        btnAddProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewProjectProperty();
            }
        });
        btnPanel.add(btnAddProject);

        // Add the button panel to the SOUTH of panelMain
        panelMain.add(btnPanel, BorderLayout.SOUTH);

        // Set content pane
        setContentPane(panelMain);
    }
    
    private void displayProjectInFile() {
    	pc = new ProjectController();
    	List<Project> listPj = pc.getListOfProject();
    	for (Project project : listPj) {
    		JPanel projectPanel = createProjectPanel(project);
			GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = panelContent.getComponentCount() % PANELS_PER_ROW;
            gbc.gridy = panelContent.getComponentCount() / PANELS_PER_ROW;
            panelContent.add(projectPanel, gbc);
            revalidate();
            repaint();
		}
    }

    private void createNewProjectProperty() {
        Project tempP = new Project();
        // Tạo các trường nhập liệu
        JTextField txtProjectName = new JTextField();
        JTextField txtDescription = new JTextField();
        JTextField txtStartDate = new JTextField();
        JTextField txtEndDate = new JTextField();
        JTextField txtStatus = new JTextField();
        JTextField txtBackgroundImage = new JTextField();

        // Tạo mảng chứa các trường nhập liệu
        Object[] message = {
                "Nhập tên dự án:", txtProjectName,
                "Nhập mô tả dự án:", txtDescription,
                "Nhập ngày bắt đầu:", txtStartDate,
                "Nhập ngày kết thúc:", txtEndDate,
                "Nhập trạng thái dự án:", txtStatus,
                "Nhập link ảnh: ", txtBackgroundImage
        };

        // Hiển thị dialog để nhập thông tin dự án
        // Hiển thị hộp thoại nhập liệu
        int option = JOptionPane.showConfirmDialog(null, message, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
	        tempP.setProjectName(txtProjectName.getText());
	        tempP.setDescription(txtDescription.getText());
	        tempP.setStartDate(txtStartDate.getText());
	        tempP.setEndDate(txtEndDate.getText());
	        tempP.setStatus(txtStatus.getText());
	        tempP.setBackGroundImage(txtBackgroundImage.getText());

            if (txtProjectName != null && !tempP.getProjectName().isEmpty()) {
                // Tạo một panel con mới với thông tin dự án
                JPanel projectPanel = createProjectPanel(tempP);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.gridx = panelContent.getComponentCount() % PANELS_PER_ROW;
                gbc.gridy = panelContent.getComponentCount() / PANELS_PER_ROW;
                panelContent.add(projectPanel, gbc);
                revalidate();
                repaint();
             // Save file
                String newP = tempP.getProjectName() + "|" +
                		tempP.getDescription() + "|" +
                		tempP.getStartDate() + "|" +
                		tempP.getEndDate() + "|" +
                		tempP.getStatus() + "|" +
                		tempP.getBackGroundImage();
                
                pc.addProject(newP);
                DAO.saveData(pc.getListOfProject(), DAO.PROJECT_FILE_PATH, pc.projectIDCounter);
            } else {
            	JOptionPane.showMessageDialog(null, "Bạn chưa nhập Project Name", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    }

    private JPanel createProjectPanel(Project project) {
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        projectPanel.setBackground(SystemColor.activeCaption);

        // Label tiêu đề dự án
        JLabel lblProjectTitle = new JLabel(project.getProjectName(), SwingConstants.CENTER);
        lblProjectTitle.setFont(new Font("Tahoma", Font.BOLD, 16));

        projectPanel.add(lblProjectTitle, BorderLayout.CENTER);

        JPanel pBtnViewMembers = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Button Xem Thành viên
        JButton btnViewMembers = new JButton("Xem thành viên");
        btnViewMembers.setPreferredSize(new Dimension(PANEL_WIDTH, 30));
        btnViewMembers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện khi nhấn nút "Xem thành viên"
                // (Bạn có thể thêm mã xử lý tại đây)
            	ProjectView.this.setVisible(false);
            	new TeamView(project.getId());
            }
        });
        pBtnViewMembers.add(btnViewMembers);

        projectPanel.add(pBtnViewMembers, BorderLayout.SOUTH);

        return projectPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProjectView().setVisible(true);
            }
        });
    }
}
