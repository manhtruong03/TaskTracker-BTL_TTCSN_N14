package main.java.views;

import javax.swing.*;

import main.java.dao.DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoView {
	private JFrame frame;
	private JPanel panelMain;
	private JButton btnAddCV;
	private JScrollPane scrollPaneMain;
	private ArrayList<TitlePanel> titlePanels;

	public ToDoView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("ToDoView");
		frame.setResizable(false);
		frame.setBounds(100, 100, 763, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titlePanels = new ArrayList<>();
		frame.getContentPane().setLayout(new BorderLayout());

		// ScrollPanel Main
		scrollPaneMain = new JScrollPane();
		frame.getContentPane().add(scrollPaneMain, BorderLayout.CENTER);

		panelMain = new JPanel();
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		scrollPaneMain.setViewportView(panelMain);

		// AddCV Button
		btnAddCV = new JButton("Việc Cần Làm");
		btnAddCV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog(frame, "Thêm Công Việc");
				if (title != null && !title.isEmpty()) {
					addTitlePanel(title);
				}
			}
		});

		// AddCV Button Container
		JPanel btnContainer = new JPanel();
		btnContainer.add(btnAddCV);
		frame.getContentPane().add(btnContainer, BorderLayout.NORTH);
	}

	private void addTitlePanel(String title) {
		TitlePanel titlePanel = new TitlePanel(title);
		titlePanels.add(titlePanel);
		panelMain.add(titlePanel);
		panelMain.revalidate();
		panelMain.repaint();
	}

	private class TitlePanel extends JPanel {
		private JCheckBox checkBox;
		private JLabel lblTitle;
		private JButton btnDelete;
		private JButton btnAdd;
		private JPanel contentPanel;

		private JButton btnLoadFromUsers;
		private JLabel lblUserInfo;

		public TitlePanel(String title) {
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createLineBorder(Color.BLACK));

			checkBox = new JCheckBox();
			checkBox.setSelected(true);
			lblTitle = new JLabel(title);
			btnDelete = new JButton("Xóa");
			btnAdd = new JButton("Thêm");
			contentPanel = new JPanel();
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			JPanel leftContentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			leftContentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Đặt lề trái
			leftContentPanel.add(contentPanel);

			// Thêm nút và label
			btnLoadFromUsers = new JButton("Thêm Thành Viên");
			lblUserInfo = new JLabel();
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(btnLoadFromUsers, BorderLayout.WEST);
			rightPanel.add(lblUserInfo, BorderLayout.EAST);

			// Sự kiện khi click vào nút "Thêm"
			btnLoadFromUsers.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String email = JOptionPane.showInputDialog(frame, "Nhập email:");
					if (email != null && !email.isEmpty()) {
						searchAndDisplayUser(email);
					}
				}
			});

			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int confirmResult = JOptionPane.showConfirmDialog(panelMain,
							"Bạn có chắc chắn muốn xóa bản ghi này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
					if (confirmResult == JOptionPane.YES_OPTION) {
						titlePanels.remove(TitlePanel.this);
						panelMain.remove(TitlePanel.this);
						panelMain.revalidate();
						panelMain.repaint();
					}
				}
			});

			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String task = JOptionPane.showInputDialog(frame, "Thêm một mục");
					if (task != null && !task.isEmpty()) {
						addTaskPanel(task);
					}
				}
			});
			btnAdd.setPreferredSize(new Dimension(70, 30));
			btnLoadFromUsers.setPreferredSize(new Dimension(170, 30));
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.add(checkBox, BorderLayout.WEST);
			topPanel.add(lblTitle, BorderLayout.CENTER);
			topPanel.add(btnDelete, BorderLayout.EAST);

			add(topPanel, BorderLayout.NORTH);
			add(leftContentPanel, BorderLayout.CENTER);
			JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottomPanel.add(btnAdd);
			bottomPanel.add(rightPanel);
			add(bottomPanel, BorderLayout.SOUTH);
		}

		private void addTaskPanel(String task) {
			TaskPanel taskPanel = new TaskPanel(task);
			contentPanel.add(taskPanel);
			contentPanel.revalidate();
			contentPanel.repaint();
		}

		private void searchAndDisplayUser(String email) {
			// Thực hiện tìm kiếm trong file users.txt
			String userName = searchUserByEmail(email);
			if (userName != null) {
				// Hiển thị tên người dùng lên JLabel
				lblUserInfo.setText("Người thực hiện: " + userName);
				lblUserInfo.setForeground(Color.BLUE); // Optional: Đặt màu chữ là màu xanh
			} else {
				// Hiển thị thông báo nếu không tìm thấy email
				JOptionPane.showMessageDialog(frame, "Không tìm thấy email trong CSDL", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		private String searchUserByEmail(String email) {
			// Đọc thông tin người dùng từ file users.txt
			try {
				BufferedReader br = new BufferedReader(new FileReader(DAO.USER_FILE_PATH));
				String line;
				boolean skipFirstLine = true; // Biến để theo dõi xem đã đọc dòng đầu tiên hay chưa
				while ((line = br.readLine()) != null) {

					if (skipFirstLine) {
						// Bỏ qua dòng đầu tiên
						skipFirstLine = false;
						continue;
					}
					String[] parts = line.split("\\|");
					if (parts[2].trim().equals(email.trim())) {
						return parts[1].trim(); // Trả về tên người dùng nếu tìm thấy email
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Trả về null nếu không tìm thấy email
			return null;
		}

	}

	private class TaskPanel extends JPanel {
		private JCheckBox checkBox;
		private JLabel lblTask;

		public TaskPanel(String task) {
			setLayout(new FlowLayout());

			checkBox = new JCheckBox();
			lblTask = new JLabel(task);
			add(checkBox);
			add(lblTask);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoView window = new ToDoView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
