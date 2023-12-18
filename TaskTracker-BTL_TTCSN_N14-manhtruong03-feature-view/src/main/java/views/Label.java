package main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.views.lab.TaskFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Label extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Label frame = new Label();
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
	public Label() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1289, 756);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(446, 407, 279, 284);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(103, 121, 85, 21);
		panel.add(btnNewButton);
		
		JPanel panelTask = new TaskFrame();
		panelTask.setLocation(747, 100);
		getContentPane().add(panelTask);
		
		JPanel panelTask_1 = new TaskFrame();
		panelTask_1.setLocation(91, 423);
		getContentPane().add(panelTask_1);
	}
}
