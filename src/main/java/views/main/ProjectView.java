package main.java.views.main;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class ProjectView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectView() {
		final Dimension HEADER_D = new Dimension(ConstantView.WINDOW_WIDTH, 50);

//		setSize(ConstantView.DEFAULT_WINDOW_SIZE);
		setPreferredSize(HEADER_D);
        setMinimumSize(HEADER_D);
        setMaximumSize(HEADER_D);
        
        JLabel lblNewLabel = new JLabel("Trello");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JTextField txtTmKim = new JTextField();
        txtTmKim.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTmKim.setForeground(new Color(247, 248, 255));
        txtTmKim.setBorder(null);
        txtTmKim.setText("Tìm kiếm");
        txtTmKim.setColumns(10);
        txtTmKim.setSize(200, 32);
		
//		setLayout(new MigLayout("debug", "", ""));
//		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));	// top, left, bottom, right
//		
//		
////		add(new JLabel("Trello"), "span, alignx right, wrap");
//		add(new JLabel("HeaderLabel"), "span, alignx right, wrap");
//		
//		
//		JTextField txtSearch = new JTextField();
//		txtSearch.setSize(200, 32);
//		add(txtSearch, "span, alignx right, wrap");
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 926, Short.MAX_VALUE)
        			.addComponent(txtTmKim, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        			.addGap(106))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        				.addComponent(txtTmKim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        setLayout(groupLayout);
		add(new JLabel("HeaderLabel"));
		
		
//		JTextField txtSearch = new JTextField();
//		txtSearch.setSize(200, 32);
//		add(txtSearch);
	}

}
