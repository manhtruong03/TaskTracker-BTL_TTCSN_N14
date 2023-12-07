package main.java.views;

import main.java.dao.DAO;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserView extends JFrame {

	DefaultTableModel tableModel = new DefaultTableModel();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_fullname;
	private JTextField txt_email;
	private JTextField txt_phone;
	private JTextField txt_pass;
	private JTextField txt_date;
	private JTextField txt_avatar;
	private JTable tbl_account;
	private int selectedRowIndex;
	private static int maxId = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
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

	public UserView() {
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Accout Manager", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbl_account = new JTable();
		tbl_account.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(25, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(tbl_account, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 728,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tbl_account, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)));

		JLabel lblFullName = new JLabel("Full Name:");

		JLabel lblEmail = new JLabel("Email:");

		JLabel lblPhoneNumber = new JLabel("Phone Number:");

		JLabel lblPassword = new JLabel("Password:");

		JLabel lblDateJoin = new JLabel("Date Join:");

		JLabel lblAvatar = new JLabel("Avatar:");

		txt_fullname = new JTextField();
		txt_fullname.setColumns(10);

		txt_email = new JTextField();
		txt_email.setColumns(10);

		txt_phone = new JTextField();
		txt_phone.setColumns(10);

		txt_pass = new JTextField();
		txt_pass.setColumns(10);

		txt_date = new JTextField();
		txt_date.setColumns(10);

		txt_avatar = new JTextField();
		txt_avatar.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newID = maxId + 1;
				maxId++;
				String id = "NV" + newID;
				String name = txt_fullname.getText();
				String email = txt_email.getText();
				String phone = txt_phone.getText();
				String pass = txt_pass.getText();
				String date = txt_date.getText();
				String ava = txt_avatar.getText();

				Vector<Object> rowData = new Vector<>();
				rowData.add(id);
				rowData.add(name);
				rowData.add(email);
				rowData.add(phone);
				rowData.add(pass);
				rowData.add(date);
				rowData.add(ava);
				tableModel.addRow(rowData);
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DAO.USER_FILE_PATH, true))) {
					bw.newLine();
					// Tạo một dòng mới cho nhân viên mới
					String newLine = "";
					for (int i = 0; i < tableModel.getColumnCount(); i++) { // getColumnCount: đếm số lượng cột trong
																			// bảng,

						newLine += tableModel.getValueAt(tableModel.getRowCount() - 1, i);
						if (i < tableModel.getColumnCount() - 1) {
							newLine += "|";
						}
					}
					bw.write(newLine);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txt_fullname.setText("");
				txt_email.setText("");
				txt_phone.setText("");
				txt_pass.setText("");
				txt_date.setText("");
				txt_avatar.setText("");

			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy thông tin mới từ các trường nhập liệu

				String newName = txt_fullname.getText();
				String newEmail = txt_email.getText();
				String newPhone = txt_phone.getText();
				String newPass = txt_pass.getText();
				String newDate = txt_date.getText();
				String newAvatar = txt_avatar.getText();

				// Cập nhật dữ liệu trong JTable

				tableModel.setValueAt(newName, selectedRowIndex, 1);
				tableModel.setValueAt(newEmail, selectedRowIndex, 2);
				tableModel.setValueAt(newPhone, selectedRowIndex, 3);
				tableModel.setValueAt(newPass, selectedRowIndex, 4);
				tableModel.setValueAt(newDate, selectedRowIndex, 5);
				tableModel.setValueAt(newAvatar, selectedRowIndex, 6);

				// Cập nhật dữ liệu trong file txt
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DAO.USER_FILE_PATH))) {
					for (int row = 0; row < tableModel.getRowCount(); row++) {
						for (int col = 0; col < tableModel.getColumnCount(); col++) {
							bw.write(tableModel.getValueAt(row, col).toString());
							if (col < tableModel.getColumnCount() - 1) {
								bw.write("|");
							}
						}
						bw.newLine();
					}
				} catch (IOException e5) {
					e5.printStackTrace();
				}

				// Reset các trường nhập liệu sau khi sửa
				btnReset.doClick();

			}
		});
		// Thêm vào hàm khởi tạo
		tbl_account.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tbl_account.rowAtPoint(evt.getPoint());
				int col = tbl_account.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = row;
					// Lấy dữ liệu từ model cho dòng đã chọn
					String id = (String) tableModel.getValueAt(selectedRowIndex, 0);
					String name = (String) tableModel.getValueAt(selectedRowIndex, 1);
					String email = (String) tableModel.getValueAt(selectedRowIndex, 2);
					String phone = (String) tableModel.getValueAt(selectedRowIndex, 3);
					String pass = (String) tableModel.getValueAt(selectedRowIndex, 4);
					String date = (String) tableModel.getValueAt(selectedRowIndex, 5);
					String avatar = (String) tableModel.getValueAt(selectedRowIndex, 6);

					// Hiển thị hộp thoại nhập liệu để chỉnh sửa thông tin

					txt_fullname.setText(name);
					txt_email.setText(email);
					txt_phone.setText(phone);
					txt_pass.setText(pass);
					txt_date.setText(date);
					txt_avatar.setText(avatar);
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmResult = JOptionPane.showConfirmDialog(tbl_account,
						"Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (confirmResult == JOptionPane.YES_OPTION) {
					int selectedRow = tbl_account.getSelectedRow();
					if (selectedRow != -1) {
						// Lấy dữ liệu từ model
						String IDToDelete = (String) tableModel.getValueAt(selectedRow, 0); // Giả sử "ID" là cột đầu
																							// tiên
						// xóa bản ghi từ file

						List<String> lines = new ArrayList<>();
						try (BufferedReader br = new BufferedReader(new FileReader(DAO.USER_FILE_PATH))) {
							String line;
							while ((line = br.readLine()) != null) {
								if (!line.contains(IDToDelete)) {
									lines.add(line);
								}
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						// Ghi lại toàn bộ nội dung mới vào file
						try (BufferedWriter bw = new BufferedWriter(new FileWriter(DAO.USER_FILE_PATH))) {
							for (int i = 0; i < lines.size(); i++) {
								bw.write(lines.get(i));

								// Nếu không phải dòng cuối cùng, thêm dòng mới
								if (i < lines.size() - 1) {
									bw.newLine();
								}
							}
						} catch (IOException e3) {
							e3.printStackTrace();
						}

						// Xóa bản ghi khỏi model
						tableModel.removeRow(selectedRow); // Đã cập nhật số lượng hàng hiện tại
						// Reset các trường nhập liệu sau khi sửa
						btnReset.doClick();
					}
				}

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(78).addComponent(btnAdd).addGap(57)
								.addComponent(btnReset).addGap(55).addComponent(btnEdit).addGap(61)
								.addComponent(btnDelete))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDateJoin, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFullName).addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 133,
										GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 295,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_email, GroupLayout.PREFERRED_SIZE, 295,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_fullname, GroupLayout.PREFERRED_SIZE, 295,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(txt_avatar, GroupLayout.PREFERRED_SIZE, 295,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txt_date, GroupLayout.PREFERRED_SIZE, 295,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txt_pass, GroupLayout.PREFERRED_SIZE, 295,
														GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(245, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(20)
						.addGroup(gl_panel
								.createParallelGroup(
										Alignment.TRAILING)
								.addGroup(
										gl_panel.createSequentialGroup()
												.addComponent(txt_fullname, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(9)
												.addComponent(txt_email, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txt_phone, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txt_pass, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txt_date, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(
														txt_avatar, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_panel.createSequentialGroup()
												.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblDateJoin, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblAvatar,
														GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
						.addGap(57)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnAdd)
								.addComponent(btnReset).addComponent(btnEdit).addComponent(btnDelete))
						.addContainerGap(23, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(groupLayout);

		try {
			// Mở file để đọc
			BufferedReader br = new BufferedReader(new FileReader(DAO.USER_FILE_PATH));
			// Đọc dòng tiêu đề và thêm cột vào mô hình bảng
			List<String> titleTable = new ArrayList<String>();
			titleTable.add("ID");
			titleTable.add("Name");
			titleTable.add("Email");
			titleTable.add("Phone Number");
			titleTable.add("Password");
			titleTable.add("Date Join");
			titleTable.add("Avatar");
			for (String string : titleTable) {
				tableModel.addColumn(string);
			}
			// Đọc các dòng dữ liệu và thêm vào mô hình bảng
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\\|");
				tableModel.addRow(fields);
			}

			// Đóng file
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		tbl_account.setModel(tableModel);

	}
}
