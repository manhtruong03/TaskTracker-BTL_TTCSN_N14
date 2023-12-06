package main.java.views;

import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.java.controllers.UserController;
import main.java.dao.DAO;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;

public class TeamView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblListMember;

	DefaultTableModel modelTable = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamView frame = new TeamView();
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
	public TeamView() {
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

		try {
			// Mở file để đọc
			BufferedReader br = new BufferedReader(new FileReader(
					"D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\listmemberinteam.txt"));

			// Đọc dòng tiêu đề và thêm cột vào mô hình bảng
			List<String> titleTable = new ArrayList<String>();
			titleTable.add("Name");
			titleTable.add("Email");
			titleTable.add("Phone Number");
			titleTable.add("Password");
			titleTable.add("Date Join");
			titleTable.add("Avatar");
			titleTable.add("Delete"); // Thêm cột cho nút xóa
			for (String string : titleTable) {
				modelTable.addColumn(string);
			}

			// Đọc các dòng dữ liệu và thêm vào mô hình bảng
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\\|");
				modelTable.addRow(fields);
			}

			// Đóng file
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		tblListMember.setModel(modelTable);

		// Thêm nút xóa vào cột "Delete"
		TableColumn deleteColumn = tblListMember.getColumnModel().getColumn(modelTable.getColumnCount() - 1);
		deleteColumn.setCellRenderer(new TableButtonRenderer());
		deleteColumn.setCellEditor(new TableButtonEditor(new JCheckBox()));

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
				String email = JOptionPane.showInputDialog(tblListMember, "Nhập email người muốn thêm:", "Thêm mới",
						JOptionPane.QUESTION_MESSAGE);

				if (email != null && !email.isEmpty()) {
					// Kiểm tra xem email có tồn tại trong file không
					if (isEmailExists(email)) {
						// Thêm thông tin mới vào bảng và file
						loadUserInformation(email);
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
	}

	// Hàm kiểm tra xem email có tồn tại trong file không
	private boolean isEmailExists(String emailToCheck) {
		String filePath = "D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\user.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\\|");
				String email = fields[1]; // Giả sử email là ở cột thứ 2

				if (emailToCheck.equals(email)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	// Hàm thêm người dùng mới vào bảng và file
	private void addUserToTable(String emailToAdd, String fullName, String phoneNumber, String encryptedPassword,
			String dateJoined, String avatar) {
		Vector<Object> rowData = new Vector<>();
		rowData.add(fullName);
		rowData.add(emailToAdd);
		rowData.add(phoneNumber);
		rowData.add(encryptedPassword);
		rowData.add(dateJoined);
		rowData.add(avatar);

		modelTable.addRow(rowData);

		// Thêm thông tin mới vào file
		String filePath = "D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\listmemberinteam.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
			bw.newLine();
			// Tạo một dòng mới cho nhân viên mới
			String newLine = "";
			for (int i = 0; i < modelTable.getColumnCount() - 1; i++) { // getColumnCount: đếm số lượng cột trong bảng,
																		// vì bảng có cả nút delete trừ bớt 1
				newLine += modelTable.getValueAt(modelTable.getRowCount() - 1, i);
				if (i < modelTable.getColumnCount() - 2) {
					newLine += "|";
				}
			}
			bw.write(newLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Đọc thông tin nhân viên từ file "user.txt" và thêm vào bảng
	private void loadUserInformation(String emailcheck) {
		String userFilePath = "D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\user.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\\|");
				if (emailcheck.equals(fields[1])) {
					String fullName = fields[0];
					String email = fields[1];
					String phoneNumber = fields[2];
					String encryptedPassword = fields[3];
					String dateJoined = fields[4];
					String avatar = fields[5];

					addUserToTable(emailcheck, fullName, phoneNumber, encryptedPassword, dateJoined, avatar);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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

		public TableButtonEditor(JCheckBox checkBox) {
			super(checkBox);

			deleteButton = new JButton("Delete");
			deleteButton.setOpaque(true);

			// Xử lý sự kiện khi nút xóa được nhấn
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int confirmResult = JOptionPane.showConfirmDialog(tblListMember,
							"Are you sure you want to delete this record?", "Confirm Delete",
							JOptionPane.YES_NO_OPTION);
					if (confirmResult == JOptionPane.YES_OPTION) {
						int selectedRow = tblListMember.getSelectedRow();
						if (selectedRow != -1) {
							// Lấy dữ liệu từ model
							String nameToDelete = (String) modelTable.getValueAt(selectedRow, 0); // Giả sử "Name" là cột đầu tiên
							// Gọi hàm xóa bản ghi từ file
							deleteRecordFromFile(nameToDelete);

							// Xóa bản ghi khỏi model
							modelTable.removeRow(selectedRow); // Đã cập nhật số lượng hàng hiện tại
						}
					}
				}
			});
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

	// Hàm xóa bản ghi từ file
	private void deleteRecordFromFile(String nameToDelete) {
		String filePath = "D:\\ThucTapCoSoNganh\\BTL\\TaskTracker-BTL_TTCSN_N14-main\\src\\assets\\data\\listmemberinteam.txt";
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.contains(nameToDelete)) {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Ghi lại toàn bộ nội dung mới vào file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0; i < lines.size(); i++) {
				bw.write(lines.get(i));

				// Nếu không phải dòng cuối cùng, thêm dòng mới
				if (i < lines.size() - 1) {
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
