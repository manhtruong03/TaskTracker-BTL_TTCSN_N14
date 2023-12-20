package main.java.views;

import main.java.controllers.UserController;
import main.java.dao.DAO;
import main.java.models.User;

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
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

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

	UserController uc;

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
		panel.setBounds(30, 5, 728, 298);
		panel.setBorder(new TitledBorder(null, "Accout Manager", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		uc = new UserController();

		List<String> titleTable = new ArrayList<String>();
		titleTable.add("ID");
		titleTable.add("Name");
		titleTable.add("Email");
		titleTable.add("Phone Number");
		titleTable.add("Password");
		titleTable.add("Date Join");
		titleTable.add("Avatar");
		titleTable.add("Account Role");
		for (String string : titleTable) {
			tableModel.addColumn(string);
		}
		for (User u : uc.getListOfUser()) {
			String[] fields = { u.getId(), u.getFullName(), u.getEmail(), u.getPhoneNumber(), u.getEncryptedPassword(),
					u.getDateJoined(), u.getAvatar(), u.getAccountRole() };
			tableModel.addRow(fields);
		}

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

		JLabel lblNewLabel = new JLabel("Account Role:");
		String[] roles = { "", "Admin", "User" };
		JComboBox<String> cmb_role = new JComboBox<>(roles);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_fullname.getText();
				String email = txt_email.getText();
				String phone = txt_phone.getText();
				String pass = txt_pass.getText();
				String date = txt_date.getText();
				String ava = txt_avatar.getText();
				String role = (String) cmb_role.getSelectedItem();
				String mess = name + "|" + email + "|" + phone + "|" + pass + "|" + date + "|" + ava + "|" + role;

				uc.addUser(mess);

				DAO.saveData(uc.getListOfUser(), DAO.USER_FILE_PATH, uc.userIDCounter);

				tableModel.setRowCount(0);

				for (User u : uc.getListOfUser()) {
					String[] fields = { u.getId(), u.getFullName(), u.getEmail(), u.getPhoneNumber(),
							u.getEncryptedPassword(), u.getDateJoined(), u.getAvatar(), u.getAccountRole() };
					tableModel.addRow(fields);
				}

				tbl_account.setModel(tableModel);
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
				cmb_role.setSelectedItem("");

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
				String newRole = (String) cmb_role.getSelectedItem();
				String newInfor = newName + "|" + newEmail + "|" + newPhone + "|" + newPass + "|" + newDate + "|"
						+ newAvatar + "|" + newRole;

				String id = (String) tableModel.getValueAt(tbl_account.getSelectedRow(), 0);

				uc.editUser(id, newInfor);

				DAO.saveData(uc.getListOfUser(), DAO.USER_FILE_PATH, uc.userIDCounter);

				tableModel.setRowCount(0);

				for (User u : uc.getListOfUser()) {
					String[] fields = { u.getId(), u.getFullName(), u.getEmail(), u.getPhoneNumber(),
							u.getEncryptedPassword(), u.getDateJoined(), u.getAvatar(), u.getAccountRole() };
					tableModel.addRow(fields);
				}

				tbl_account.setModel(tableModel);

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

						uc.deleteUser(IDToDelete);

						DAO.saveData(uc.getListOfUser(), DAO.USER_FILE_PATH, uc.userIDCounter);

						tableModel.setRowCount(0);

						for (User u : uc.getListOfUser()) {
							String[] fields = { u.getId(), u.getFullName(), u.getEmail(), u.getPhoneNumber(),
									u.getEncryptedPassword(), u.getDateJoined(), u.getAvatar(), u.getAccountRole() };
							tableModel.addRow(fields);
						}

						tbl_account.setModel(tableModel);

					}

					txt_fullname.setText("");
					txt_email.setText("");
					txt_phone.setText("");
					txt_pass.setText("");
					txt_date.setText("");
					txt_avatar.setText("");
					cmb_role.setSelectedItem("");
				}

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDateJoin, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFullName)
								.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 133,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_email, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_fullname, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txt_avatar, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
										.addComponent(txt_date, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
										.addComponent(txt_pass, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
										.addComponent(cmb_role, GroupLayout.PREFERRED_SIZE, 151,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup().addGap(78).addComponent(btnAdd).addGap(57)
								.addComponent(btnReset).addGap(55).addComponent(btnEdit).addGap(61)
								.addComponent(btnDelete)))
				.addContainerGap(245, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(624)));
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
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(cmb_role, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnAdd)
								.addComponent(btnReset).addComponent(btnEdit).addComponent(btnDelete))
						.addContainerGap(23, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(null);
		contentPane.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 341, 728, 245);
		contentPane.add(scrollPane);
		tbl_account = new JTable();
		scrollPane.setViewportView(tbl_account);

		tbl_account.setModel(tableModel);
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
					String role = (String) tableModel.getValueAt(selectedRowIndex, 7);
					// Hiển thị hộp thoại nhập liệu để chỉnh sửa thông tin

					txt_fullname.setText(name);
					txt_email.setText(email);
					txt_phone.setText(phone);
					txt_pass.setText(pass);
					txt_date.setText(date);
					txt_avatar.setText(avatar);
					cmb_role.setSelectedItem(role);
				}
			}
		});

		tbl_account.setModel(tableModel);

	}
}
