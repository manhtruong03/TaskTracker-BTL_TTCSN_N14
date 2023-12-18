package main.java.views.lab.OverlayFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Click Panels");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Transparent overlay
        JPanel overlay = new JPanel();
        overlay.setBackground(new Color(0, 0, 0, 60)); // Black with transparency
        overlay.setOpaque(false);
        overlay.setVisible(false);

        // Listener to hide the overlay
        overlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                overlay.setVisible(false);
                frame.setGlassPane(new JPanel()); // Reset glass pane
            }
        });

        // Create and add panels
        for (int i = 1; i <= 3; i++) {
            JPanel panel = createPanel("Panel " + i, "Button " + i, overlay, frame);
            frame.add(panel);
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPanel(String labelText, String buttonText, JPanel overlay, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel(labelText));
        panel.add(new JButton(buttonText));

        // Listener for panel click
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setGlassPane(overlay);
                overlay.setVisible(true);
            }
        });

        return panel;
    }
}
