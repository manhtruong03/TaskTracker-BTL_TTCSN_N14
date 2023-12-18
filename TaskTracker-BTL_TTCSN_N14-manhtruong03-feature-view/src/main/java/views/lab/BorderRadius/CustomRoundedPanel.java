package main.java.views.lab.BorderRadius;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CustomRoundedPanel extends JPanel {
    private int radius;
    private boolean topLeft, topRight, bottomLeft, bottomRight;

    public CustomRoundedPanel(int radius, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
    	setBackground(Color.GREEN);
    	setPreferredSize(new Dimension(300, 300));
        this.radius = radius;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        setOpaque(false);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBackground(Color.MAGENTA);
        add(btnNewButton, 1);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());

        Area area = new Area(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
        if (!topLeft) {
            area.add(new Area(new Rectangle(0, 0, radius, radius)));
        }
        if (!topRight) {
            area.add(new Area(new Rectangle(getWidth() - radius, 0, radius, radius)));
        }
        if (!bottomLeft) {
            area.add(new Area(new Rectangle(0, getHeight() - radius, radius, radius)));
        }
        if (!bottomRight) {
            area.add(new Area(new Rectangle(getWidth() - radius, getHeight() - radius, radius, radius)));
        }

        g2d.fill(area);
        g2d.dispose();
    }
}

