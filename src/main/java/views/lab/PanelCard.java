package main.java.views.lab;

import java.awt.*;
import javax.swing.*;

public class PanelCard extends JPanel {
    public PanelCard(String content, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        add(new JLabel(content));
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}

