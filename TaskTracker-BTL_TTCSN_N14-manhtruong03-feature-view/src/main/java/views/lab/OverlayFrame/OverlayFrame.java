package main.java.views.lab.OverlayFrame;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OverlayFrame extends JFrame {
	
	public OverlayFrame() {
        setTitle("Panel Overlay Example");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        
        setGlassPane(new JComponent() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		g.setColor(new Color(0, 0, 0, 150));
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
        	}
		});
        

        // Create three panels with labels and buttons
        for (int i = 1; i <= 3; i++) {
            JPanel panel = createPanel("Panel " + i, "Button " + i);
            panel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                	setGlassPaneVisible(true);
                    Container glassPanel = (Container) getGlassPane();
                    glassPanel.add(panel);
                }
            });
            add(panel);
        }
    }

    private void setGlassPaneVisible(boolean visible) {
        // Show or hide the glass pane
        getGlassPane().setVisible(visible);

        // Enable or disable input to other components based on the glass pane visibility
        getRootPane().getContentPane().setEnabled(!visible);
    }

    private JPanel createPanel(String labelText, String buttonText) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(labelText));
        JButton button = new JButton(buttonText);
        button.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		setGlassPaneVisible(false);
        		remove(panel);
        	}
        });
        panel.add(button);

        

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OverlayFrame frame = new OverlayFrame();
            frame.setVisible(true);
        });
    }
}

