package main.java.views;

import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.java.controllers.ProjectMemberController;
import main.java.controllers.UserController;
import main.java.dao.DAO;
import main.java.models.Project;
import main.java.models.ProjectMember;
import main.java.models.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;

public class TeamView extends JFrame {

	private static final long serialVersionUID = 1L;
	private boolean userEdit = false;
	private JPanel contentPane;
	private JTable tblListMember;

	ProjectMemberController pmc;
	UserController uc = new UserController();

	DefaultTableModel modelTable = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Project project = new Project();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamView frame = new TeamView(project.getId());
					frame.setLocationRelativeTo(null);
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
	public TeamView(String projectID) {
		this.setVisible(true);
		setResizable(false);
		setTitle("Team view");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 40, 1246, 716);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTeamView = new JPanel();
		panelTeamView.setBackground(SystemColor.activeCaption);
		panelTeamView.setBounds(0, 0, 1232, 679);
		contentPane.add(panelTeamView);
		panelTeamView.setLayout(null);

		JLabel lblTitle = new JLabel("Các thành viên trong không gian làm việc");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(292, 24, 647, 45);
		panelTeamView.add(lblTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(117, 124, 1000, 466);
		panelTeamView.add(scrollPane);

		tblListMember = new JTable();
		tblListMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tblListMember);

		// Lấy dữ liệu lên bảng
		designTable(projectID);

		tblListMember.setModel(modelTable);
		
		try {
			tblListMember.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    int selectedRow = tblListMember.getSelectedRow();
	                    System.out.println("Selected row: " + selectedRow);
	                    // Add your debugging logic here
	                }
	            }
	        });
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lay du lieu len bang: " + e.getMessage());
		}
		

		createJComboBox(projectID);

		// Đặt kích thước ước lượng cho cột ""
		TableColumn ColumnEdit = tblListMember.getColumnModel().getColumn(0); // Số index 0 cho cột ""
		ColumnEdit.setPreferredWidth(100); // Đặt kích thước ước lượng (đơn vị là pixel)
		TableColumn ColumnEdit2 = tblListMember.getColumnModel().getColumn(1);
		ColumnEdit2.setPreferredWidth(150);

		// Tùy chỉnh kích thước của ô trong bảng
		tblListMember.setRowHeight(40); // Đặt chiều cao của từng hàng
		tblListMember.setIntercellSpacing(new Dimension(10, 10)); // Đặt khoảng cách giữa các ô

		JButton btnThem = new JButton("Thêm mới");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiển thị hộp thoại để nhập email
				String email = JOptionPane.showInputDialog(tblListMember, "Nhập Email người muốn thêm:", "Thêm mới",
						JOptionPane.QUESTION_MESSAGE);

				if (email != null && !email.isEmpty()) {
					// Kiểm tra xem email có tồn tại trong file không
					if (isEmailExists(email)) {
						// Thêm thông tin mới vào bảng và fil
						String chucVu = JOptionPane.showInputDialog(tblListMember, "Nhập chức vụ của user trong task:",
								"Thêm mới", JOptionPane.QUESTION_MESSAGE);
						loadUserInformation(projectID, email, Integer.parseInt(chucVu));
						JOptionPane.showMessageDialog(tblListMember, "Thêm mới thành công!", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(tblListMember, "Email không tồn tại!", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(489, 612, 253, 38);
		panelTeamView.add(btnThem);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamView.this.setVisible(false);
				new ProjectView();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(23, 24, 107, 29);
		panelTeamView.add(btnBack);
	}

	private void designTable(String projectID) {
		modelTable = new DefaultTableModel();
		
		pmc = new ProjectMemberController();

		// Đọc dòng tiêu đề và thêm cột vào mô hình bảng
		List<String> titleTable = new ArrayList<String>();
		titleTable.add("Họ tên");
		titleTable.add("Email");
		titleTable.add("Chức vụ");
		titleTable.add("Delete"); // Thêm cột cho nút xóa
		for (String string : titleTable) {
			modelTable.addColumn(string);
		}

		for (ProjectMember pm : pmc.getListOfProjectMember()) {
			if (pm.getProjectID().equals(projectID)) {
				String hoten = "";
				String email = "";
				for (User u : uc.getListOfUser()) {
					if (pm.getUserID().equals(u.getId())) {
						hoten = u.getFullName();
						email = u.getEmail();
					}
				}
				String[] fields = { hoten, email, getRoleName(Integer.parseInt(pm.getRole())) };
				modelTable.addRow(fields);
			}
		}
	}

	private JComboBox<String> createJComboBox(String projectID) {
		// Tạo JComboBox và thêm các chức vụ
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Chủ dự án");
		comboBox.addItem("Quản lý dự án");
		comboBox.addItem("Thành viên");
		comboBox.addItem("Khách");

		// Thiết lập editor cho cột "Chức vụ"
		TableColumn chucVuColumn = tblListMember.getColumnModel().getColumn(2); // Giả sử cột "Chức vụ" là cột thứ 3
		chucVuColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Thêm nút xóa vào cột "Delete"
		TableColumn deleteColumn = tblListMember.getColumnModel().getColumn(modelTable.getColumnCount() - 1);
		deleteColumn.setCellRenderer(new TableButtonRenderer());
		deleteColumn.setCellEditor(new TableButtonEditor(new JCheckBox(), projectID));

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userEdit) {
					JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
					int row = tblListMember.getSelectedRow();
					if (row != -1) {
						String selectedRole = (String) comboBox.getSelectedItem();
						String email = (String) modelTable.getValueAt(row, 1); // Giả sử cột "email" là cột 2
						String memID = "";
						for (User u : uc.getListOfUser()) {
							if (email.equals(u.getEmail())) {
								for (ProjectMember pm : pmc.getListOfProjectMember()) {
									if (u.getId().equals(pm.getUserID()) && projectID.equals(pm.getProjectID())) {
										memID = pm.getId();
										break;
									}
								}
							}
						}

						// Hiển thị hộp thoại xác nhận
						int response = JOptionPane.showConfirmDialog(null,
								"Bạn có muốn sửa Chức vụ người này trong dự án?", "Xác nhận", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);

						// Nếu người dùng chọn "Đồng ý"
						if (response == JOptionPane.YES_OPTION) {
							// Cập nhật chức vụ trong file txt
							updateRoleInFile(memID, selectedRole);

							// Cập nhật chức vụ trong mô hình bảng
							modelTable.setValueAt(selectedRole, row, 2); // Giả sử cột "Chức vụ" là cột thứ 3
						} else {
							// Nếu người dùng chọn "Không", đặt lại giá trị cũ cho JComboBox
							String currentRole = (String) modelTable.getValueAt(row, 2);
							comboBox.setSelectedItem(currentRole);
						}
						userEdit = false; // Đặt lại cờ sau khi xử lý sự kiện
					}
				}
			}
		});

		// Thêm FocusListener vào JComboBox
		comboBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				userEdit = true;
			}
		});

		return comboBox;
	}

	// Hàm chuyển đổi số thành tên chức vụ
	private String getRoleName(int roleNumber) {
		switch (roleNumber) {
		case 1:
			return "Chủ dự án";
		case 2:
			return "Quản lý dự án";
		case 3:
			return "Thành viên";
		case 4:
			return "Khách";
		default:
			return "Không xác định";
		}
	}

	// Hàm chuyển đổi tên chức vụ thành số
	private int getRoleNumber(String roleName) {
		switch (roleName) {
		case "Chủ dự án":
			return 1;
		case "Quản lý dự án":
			return 2;
		case "Thành viên":
			return 3;
		case "Khách":
			return 4;
		default:
			return -1;
		}
	}

	private void updateRoleInFile(String memID, String newRole) {
		File inputFile = new File("src\\assets\\data\\projectMembers.txt");
		File tempFile = new File("src\\assets\\data\\projectMembers_temp.txt");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;
			boolean isFirstLine = true;

			while ((currentLine = reader.readLine()) != null) {
				// Kiểm tra xem có phải là dòng đầu tiên không để tránh thêm dòng mới ở đầu file
				if (!isFirstLine) {
					writer.newLine(); // Thêm dòng mới trước khi viết dòng tiếp theo
				} else {
					isFirstLine = false;
				}

				String[] fields = currentLine.split("\\|");
				if (fields[0].equals(memID)) {
					// Chuyển đổi tên chức vụ thành số và cập nhật dòng
					fields[3] = String.valueOf(getRoleNumber(newRole));
					String updatedLine = String.join("|", fields);
					writer.write(updatedLine);
				} else {
					// Viết lại dòng không thay đổi
					writer.write(currentLine);
				}
			}
			writer.close();
			reader.close();

			// Xóa file cũ và đổi tên file tạm thành file chính
			if (!inputFile.delete()) {
				System.out.println("Không thể xóa file cũ");
				return;
			}
			if (!tempFile.renameTo(inputFile)) {
				System.out.println("Không thể đổi tên file tạm");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Hàm kiểm tra xem ObjectID có tồn tại trong file không
	private boolean isEmailExists(String emailToCheck) {

		for (User u : uc.getListOfUser()) {
			if (u.getEmail().equals(emailToCheck))
				return true;
		}
		return false;
	}

	// Hàm thêm người dùng mới vào bảng và file
	private void addUserToTable(String projectID, String userID, String hoten, String email, int chucVu) {
		pmc = new ProjectMemberController();

		Vector<Object> rowData = new Vector<>();
		rowData.add(hoten);
		rowData.add(email);
		rowData.add(getRoleName(chucVu));

		modelTable.addRow(rowData);

		tblListMember.setModel(modelTable);

		createJComboBox(projectID);

		String npm = userID + "|" + projectID + "|" + chucVu;

		pmc.addProjectMember(npm);

		// Lưu thông tin mới vào file
		DAO.saveData(pmc.getListOfProjectMember(), DAO.PROJECT_MEMBER_FILE_PATH, pmc.memIDCounter);
	}

	// Load thong tin
	private void loadUserInformation(String projectID, String emailCheck, int chucVu) {
		for (User u : uc.getListOfUser()) {
			if (emailCheck.equals(u.getEmail())) {
				addUserToTable(projectID, u.getId(), u.getFullName(), emailCheck, chucVu);
				break;
			}
		}
	}

	// Inner class for rendering button in table cell
	class TableButtonRenderer extends DefaultTableCellRenderer {
		private JButton deleteButton;

		public TableButtonRenderer() {
			deleteButton = new JButton("Delete");
			deleteButton.setOpaque(true);

			// Tùy chỉnh kích thước nút và vị trí
			Dimension d = new Dimension();
			d.setSize(50, 5);
			deleteButton.setPreferredSize(d);
			deleteButton.setHorizontalAlignment(SwingConstants.CENTER);

			deleteButton.setBorderPainted(false);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return deleteButton;
		}
	}

	class TableButtonEditor extends DefaultCellEditor {
		private JButton deleteButton;
		private String label;
		private boolean isClicked;

		public TableButtonEditor(JCheckBox checkBox, String projectID) {
			super(checkBox);

			deleteButton = new JButton("Delete");
			deleteButton.setOpaque(true);

			try {
				// Xử lý sự kiện khi nút xóa được nhấn
				deleteButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int confirmResult = JOptionPane.showConfirmDialog(tblListMember,
								"Are you sure you want to delete this record?", "Confirm Delete",
								JOptionPane.YES_NO_OPTION);
						if (confirmResult == JOptionPane.YES_OPTION) {
							int selectedRow = 0;
							try {
								selectedRow = tblListMember.getSelectedRow();
								System.out.println("selectedRow: " + selectedRow);
								
							} catch (Exception e2) {
								// TODO: handle exception
								System.out.println("erro: " + selectedRow);
							}
							
							if (selectedRow != -1 ) {
								// Lấy dữ liệu từ model
								String email = (String) tblListMember.getValueAt(selectedRow, 1); // Giả sử cột "email" là cột 2
								String memID = "";
								for (User u : uc.getListOfUser()) {
									if (email.equals(u.getEmail())) {
										for (ProjectMember pm : pmc.getListOfProjectMember()) {
											if (u.getId().equals(pm.getUserID()) && projectID.equals(pm.getProjectID())) {
												memID = pm.getId();
												break;
											}
										}
									}
								}
								// Gọi hàm xóa bản ghi từ file
								pmc.deleteProjectMember(memID);
								
								DAO.saveData(pmc.getListOfProjectMember(), DAO.PROJECT_MEMBER_FILE_PATH, pmc.memIDCounter);
								designTable(projectID);
								
								tblListMember.setModel(modelTable);
								createJComboBox(projectID);
							}
						}
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			

		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "" : value.toString();
			return deleteButton;
		}

		@Override
		public boolean stopCellEditing() {
			isClicked = false;
			return super.stopCellEditing();
		}
	}
}
