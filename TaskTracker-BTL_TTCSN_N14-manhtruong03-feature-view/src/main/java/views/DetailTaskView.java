package main.java.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.controllers.ProjectMemberController;
import main.java.controllers.TaskController;
import main.java.controllers.TaskMemberController;
import main.java.controllers.UserController;
import main.java.dao.DAO;
import main.java.models.Task;
import main.java.models.TaskMember;
import main.java.models.User;
import main.java.views.main.component.ButtonCustomStyles;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DetailTaskView extends JFrame {

	private JPanel leftPanel, rightPanel;
	private JLabel titleLabel, memberLabel, descriptionLabel;
	private JButton addMemberButton, moveButton;
	private JTextArea descriptionTextArea;
	private JList<String> memberList;
	private JScrollPane scrollPane;

	ProjectMemberController pmc;
	TaskMemberController tmc;
	UserController uc;
	TaskController tc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailTaskView frame = new DetailTaskView("task-1", "proj-1");
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
	public DetailTaskView(String taskID, String projecID) {
		// Set up the frame
		setTitle("DetailTaskView");
		setSize(800, 447);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 2));
		setLocationRelativeTo(null);
		this.setVisible(false);

		// Left panel
		leftPanel = new JPanel();
		leftPanel.setLayout(null);

		// Title
		titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 10, 49, 28);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		leftPanel.add(titleLabel);

		// Member label and icons
		memberLabel = new JLabel("Members");
		memberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		memberLabel.setBounds(10, 41, 68, 13);
		leftPanel.add(memberLabel);

		JPanel memberIconPanel = new JPanel();
		memberIconPanel.setBounds(10, 55, 272, 70);
		memberIconPanel.setLayout(new BoxLayout(memberIconPanel, BoxLayout.X_AXIS));
		leftPanel.add(memberIconPanel);

		// Giả sử bạn có một danh sách các đối tượng taskMem
		pmc = new ProjectMemberController();
		tmc = new TaskMemberController();
		uc = new UserController();
		tc = new TaskController();

		for (TaskMember tm : tmc.getListOfTaskMembers()) {
			if (tm.getTaskID().equals(taskID) && tm.getProjectID().equals(projecID)) {
				for (User user : uc.getListOfUser()) {
					if (user.getId().equals(tm.getUserID())) {
						ImageIcon icon = new ImageIcon(user.getAvatar());
						JLabel label = new JLabel(icon);
						memberIconPanel.add(label);
					}
				}
			}
		}

		// Add member button
		addMemberButton = new JButton();
		addMemberButton.setIcon(new ImageIcon(DetailTaskView.class.getResource("/assets/images/AddNew.png")));
		addMemberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addMemberButton.setBounds(292, 60, 91, 57);
		addMemberButton.addActionListener(e -> {
			// Hiển thị hộp thoại nhập liệu để lấy email của thành viên mới
			tmc = new TaskMemberController();
			 JTextField txtEmail = new JTextField();
			String taskname = "";
			for (TaskMember tm : tmc.getListOfTaskMembers()) {
				if (tm.getTaskID().equals(taskID) && tm.getProjectID().equals(projecID)) {
					for (User user : uc.getListOfUser()) {
						if (user.getId().equals(tm.getUserID())) {
							taskname += user.getFullName() + "\n";
						}
					}
				}
			}
			taskname += "Enter the email of the new member:";
			
			Object[] thongbao = {
					taskname, txtEmail
			};

			int option = JOptionPane.showConfirmDialog(null, thongbao, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				String email = txtEmail.getText();
				String userID = "";
				int count = 0;
				for (TaskMember tm : tmc.getListOfTaskMembers()) {
					if (tm.getTaskID().equals(taskID) && tm.getProjectID().equals(projecID)) {
						for (User user : uc.getListOfUser()) {
							if (user.getEmail().equals(email)) {
								ImageIcon iconadd = new ImageIcon(user.getAvatar());
								JLabel labeladd = new JLabel(iconadd);
								memberIconPanel.add(labeladd);

								userID = user.getId();

								// Cập nhật giao diện
								memberIconPanel.revalidate();
								memberIconPanel.repaint();

								JOptionPane.showMessageDialog(null, "Thêm thành công", "Notification",
										JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
						if (userID.equals("")) {
							count++;
						} else {
							break;
						}
					}
				}
				if (count != 0) {
					JOptionPane.showMessageDialog(null, "Người dùng không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String newMemOfTask = projecID + "|" + userID + "|" + taskID;

					tmc.addTaskMember(newMemOfTask);

					DAO.saveData(tmc.getListOfTaskMembers(), DAO.TASK_MEMBER_FILE_PATH, tmc.taskMemIDCounter);
				}
			}
		});
		leftPanel.add(addMemberButton);

		// Description label and text area
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setBounds(10, 202, 78, 13);
		leftPanel.add(descriptionLabel);

		// Them descrip len man hinh
		String startDes = "";
		for (Task t : tc.getListOfTask()) {
			if (t.getId().equals(taskID)) {
				startDes += t.getDescription();
				break;
			}
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 225, 393, 103);
		leftPanel.add(scrollPane);
		descriptionTextArea = new JTextArea(startDes);
		descriptionTextArea.setLineWrap(true);
		scrollPane.setViewportView(descriptionTextArea);

		// Add a window listener to listen for the WindowClosing event
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String newChange = "";
				for (Task t : tc.getListOfTask()) {
					if (t.getId().equals(taskID)) {
						newChange = t.getTaskName() + "|" + descriptionTextArea.getText() + "|" + t.getCreationDate()
								+ "|" + t.getStartDate() + "|" + t.getDueDate() + "|" + t.getStatus() + "|"
								+ t.getPosition() + "|" + t.getProjectID();
						break;
					}
				}
				tc.editTask(taskID, newChange);
				DAO.saveData(tc.getListOfTask(), DAO.TASK_FILE_PATH, tc.taskIDCounter);
			}
		});

		// Right panel with move button
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		moveButton = new JButton("Move");
		moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String status = "";
				for (Task t : tc.getListOfTask()) {
					if (t.getId().equals(taskID)) {
						status += t.getStatus();
						break;
					}
				}

				// Tạo các trường nhập liệu
				JTextField txtStatus = new JTextField(status);
//		        JTextField txtPosition = new JTextField();

				// Tạo mảng chứa các trường nhập liệu
				Object[] message = { "Nhập trạng thái:", txtStatus,
//		                "Nhập vị trí :", txtPosition
				};

				// Hiển thị dialog để nhập thông tin dự án
				// Hiển thị hộp thoại nhập liệu
				int option = JOptionPane.showConfirmDialog(null, message, "Di chuyển", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {

					String newStatus = txtStatus.getText();
					for (Task t : tc.getListOfTask()) {
						if (t.getId().equals(taskID)) {
							t.setStatus(newStatus);
							break;
						}
					}

					DAO.saveData(tc.getListOfTask(), DAO.TASK_FILE_PATH, tc.taskIDCounter);
				}

			}
		});
		moveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		moveButton.setBounds(127, 51, 108, 40);
		rightPanel.add(moveButton);

		// Add panels to frame
		getContentPane().add(leftPanel);
		getContentPane().add(rightPanel);
	}

}
