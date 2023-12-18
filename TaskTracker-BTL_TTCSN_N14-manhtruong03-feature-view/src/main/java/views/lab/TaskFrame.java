package main.java.views.lab;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;

public class TaskFrame extends JPanel {

	/**
	 * Create the panel.
	 */
	public TaskFrame() {
		setBounds(100, 100, 287, 203);
		setLayout(new MigLayout("", "[1px][85px][][][][][]", "[1px][21px][][][][]"));
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1, "cell 0 0,grow");
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, "cell 5 4,alignx left,aligny top");
		

	}

}
