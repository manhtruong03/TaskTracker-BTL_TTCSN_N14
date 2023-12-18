package main.java.views.main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class AbsoluteLayout extends JPanel {

	/**
	 * Create the panel.
	 */
	public AbsoluteLayout() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Absolute");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 0, 73, 58);
		add(lblNewLabel);
		
	}

}
