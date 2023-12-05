package main.java.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.java.controllers.TaskController;
import main.java.models.Task;

public class TaskView {
	public static void main(String[] args) throws IOException{
		TaskController test = new TaskController();
		test.getListOfTask();
		String[][] array = new String[test.getListOfTask().size()][9];
		for (int i = 0; i < test.getListOfTask().size(); i++) {
            // Lấy ra Object hiện tại
            Task Ta = test.getListOfTask().get(i);

            // Lấy ra tên và tuổi của Object hiện tại
            String id = Ta.getId();
            String name = Ta.getTaskName();
            String description = Ta.getDescription();
            String creationDate = Ta.getCreationDate();
            String startDate = Ta.getStartDate();
            String dueDate = Ta.getDueDate();
            String status = Ta.getStatus();
            String position = Ta.getPosition();
            String projectID = Ta.getProjectID();
            
            // Lưu trữ tên và tuổi vào mảng 2 chiều
            array[i][0] = id;
            array[i][1] = name;
            array[i][2] = description;
            array[i][3] = creationDate;
            array[i][4] = startDate;
            array[i][5] = dueDate;
            array[i][6] = status;
            array[i][7] = position;
            array[i][8] = projectID;
        }
		
		
		frame1 frame1 = new frame1();
		frame2 frame2 = new frame2();
		frame3 frame3 = new frame3();
		frame4 frame4 = new frame4();
		frame4 frame5 = new frame4();
		
		frame1.setVisible(true);
		
		// Create ActionListener and implement actionPerformed()
		JButton btnNewButton = new JButton("Xem chi tiết dự án");
		btnNewButton.setBounds(213, 79, 170, 23);
		frame1.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1.setBounds(25, 207, 89, 23);
		
		JButton btnNewButton_2 = new JButton("Sửa");
		btnNewButton_2.setBounds(181, 207, 89, 23);
		
		JButton btnNewButton_3 = new JButton("Xóa");
		btnNewButton_3.setBounds(319, 207, 89, 23);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			array,
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(0, 36, 434, 140);

		frame2.getContentPane().add(table);
		frame2.getContentPane().add(btnNewButton_1);
		frame2.getContentPane().add(btnNewButton_2);
		frame2.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Hoàn tất");
		btnNewButton_4.setBounds(106, 111, 89, 23);
		
		JButton btnNewButton_5 = new JButton("Không đồng ý");
		btnNewButton_5.setBounds(239, 111, 150, 23);
		
		JTextField textField = new JTextField();
		textField.setBounds(210, 53, 175, 20);
		textField.setColumns(10);
		
		frame3.getContentPane().add(textField);
		frame3.getContentPane().add(btnNewButton_4);
		frame3.getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("MÀN HÌNH XÁC NHẬN SỬA");
		lblNewLabel.setBounds(33, 11, 234, 14);
		
		JTextField txtTaskname = new JTextField();
		txtTaskname.setText("TaskName");
		txtTaskname.setBounds(59, 46, 86, 20);
		txtTaskname.setColumns(10);
		
		JTextField txtStartdate = new JTextField();
		txtStartdate.setText("StartDate");
		txtStartdate.setBounds(59, 170, 86, 20);
		txtStartdate.setColumns(10);
		
		JTextField txtStatus = new JTextField();
		txtStatus.setText("Status");
		txtStatus.setBounds(59, 232, 86, 20);
		txtStatus.setColumns(10);
		
		JTextField txtProjectid = new JTextField();
		txtProjectid.setText("ProjectID");
		txtProjectid.setBounds(59, 77, 86, 20);
		txtProjectid.setColumns(10);
		
		JTextField txtCreationdate = new JTextField();
		txtCreationdate.setText("CreationDate");
		txtCreationdate.setBounds(59, 139, 86, 20);
		txtCreationdate.setColumns(10);
		
		JTextField txtDuedate = new JTextField();
		txtDuedate.setText("Duedate");
		txtDuedate.setBounds(218, 170, 86, 20);
		txtDuedate.setColumns(10);
		
		JTextField txtDescription = new JTextField();
		txtDescription.setText("Description");
		txtDescription.setBounds(59, 108, 86, 20);
		txtDescription.setColumns(10);
		
		JTextField txtPosition = new JTextField();
		txtPosition.setText("Position");
		txtPosition.setBounds(59, 201, 86, 20);
		txtPosition.setColumns(10);
		
		JTextField txtIdBnMun = new JTextField();
		txtIdBnMun.setText("Id bạn muốn sửa");
		txtIdBnMun.setBounds(273, 46, 111, 20);
		txtIdBnMun.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Hoàn tất");
		btnNewButton_6.setBounds(322, 76, 89, 23);
		
		frame4.getContentPane().add(btnNewButton_6);
		frame4.getContentPane().add(txtIdBnMun);
		frame4.getContentPane().add(txtPosition);
		frame4.getContentPane().add(txtDescription);
		frame4.getContentPane().add(txtDuedate);
		frame4.getContentPane().add(txtCreationdate);
		frame4.getContentPane().add(txtProjectid);
		frame4.getContentPane().add(txtStatus);
		frame4.getContentPane().add(txtStartdate);
		frame4.getContentPane().add(txtTaskname);
		frame4.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MÀN HÌNH XÁC NHẬN THÊM");
		lblNewLabel_1.setBounds(33, 11, 234, 14);
		
		JTextField txtTaskname1 = new JTextField();
		txtTaskname1.setText("TaskName");
		txtTaskname1.setBounds(59, 46, 86, 20);
		txtTaskname1.setColumns(10);
		
		JTextField txtStartdate1 = new JTextField();
		txtStartdate1.setText("StartDate");
		txtStartdate1.setBounds(59, 170, 86, 20);
		txtStartdate1.setColumns(10);
		
		JTextField txtStatus1 = new JTextField();
		txtStatus1.setText("Status");
		txtStatus1.setBounds(59, 232, 86, 20);
		txtStatus1.setColumns(10);
		
		JTextField txtProjectid1 = new JTextField();
		txtProjectid1.setText("ProjectID");
		txtProjectid1.setBounds(59, 77, 86, 20);
		txtProjectid1.setColumns(10);
		
		JTextField txtCreationdate1 = new JTextField();
		txtCreationdate1.setText("CreationDate");
		txtCreationdate1.setBounds(59, 139, 86, 20);
		txtCreationdate1.setColumns(10);
		
		JTextField txtDuedate1 = new JTextField();
		txtDuedate1.setText("Duedate");
		txtDuedate1.setBounds(218, 170, 86, 20);
		txtDuedate1.setColumns(10);
		
		JTextField txtDescription1 = new JTextField();
		txtDescription1.setText("Description");
		txtDescription1.setBounds(59, 108, 86, 20);
		txtDescription1.setColumns(10);
		
		JTextField txtPosition1 = new JTextField();
		txtPosition1.setText("Position");
		txtPosition1.setBounds(59, 201, 86, 20);
		txtPosition1.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("Hoàn tất");
		btnNewButton_7.setBounds(322, 76, 89, 23);
		
		frame5.getContentPane().add(btnNewButton_7);
		frame5.getContentPane().add(txtPosition1);
		frame5.getContentPane().add(txtDescription1);
		frame5.getContentPane().add(txtDuedate1);
		frame5.getContentPane().add(txtCreationdate1);
		frame5.getContentPane().add(txtProjectid1);
		frame5.getContentPane().add(txtStatus1);
		frame5.getContentPane().add(txtStartdate1);
		frame5.getContentPane().add(txtTaskname1);
		frame5.getContentPane().add(lblNewLabel_1);

		// Tạo một sự kiện click cho nút đó
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Loại bỏ frame hiện tại
		        frame1.setVisible(false);

		        // Hiển thị frame mới
		        frame2.setVisible(true);
		    }
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame2.setVisible(false);
				frame5.setVisible(true);
			}
			
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Loại bỏ frame hiện tại
		        frame2.setVisible(false);

		        // Hiển thị frame mới
		        frame4.setVisible(true);
		    }
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame2.setVisible(false);
				frame3.setVisible(true);
			}
			
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = textField.getText();
				int x = 0;
				for(Task i : test.getListOfTask()) {
					if(i.getId().equals(text)) {
						x += 1;
						break;
					}
				}
				if(x==1) {
					test.deleteTask(text);
					test.saveTaskController();
					test.updateTaskController();
					frame2.setVisible(true);
					frame3.setVisible(false);
				}
				else {
					textField.setText("Không có task bạn đang tìm");
				}
			}
			
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame2.setVisible(true);
				frame3.setVisible(false);
			}
			
		});
		
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = txtIdBnMun.getText();
				String infor = txtTaskname.getText() + "|" + txtDescription.getText() + "|" + txtCreationdate.getText() + "|" + 
				txtStartdate.getText() + "|" + txtDuedate.getText() + "|" + txtStatus.getText() + "|" + txtPosition.getText()
				+ "|" + txtProjectid.getText();
				int x = 0;
				for(Task i : test.getListOfTask()) {
					if(i.getId().equals(text)) {
						x += 1;
						break;
					}
				}
				if(x==1) {
					test.editTask(txtIdBnMun.getText(), infor);
					test.saveTaskController();
					
					frame2.setVisible(true);
					frame4.setVisible(false);
				}
				else {
					txtIdBnMun.setText("Không có task bạn đang tìm");
				}
			}
			
		});
		
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String infor = txtTaskname.getText() + "|" + txtDescription.getText() + "|" + txtCreationdate.getText() + "|" + 
				txtStartdate.getText() + "|" + txtDuedate.getText() + "|" + txtStatus.getText() + "|" + txtPosition.getText()
				+ "|" + txtProjectid.getText();
				test.addTask(infor);
				test.saveTaskController();
				test.updateTaskController();
				frame2.setVisible(true);
				frame5.setVisible(false);
			}
			
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}