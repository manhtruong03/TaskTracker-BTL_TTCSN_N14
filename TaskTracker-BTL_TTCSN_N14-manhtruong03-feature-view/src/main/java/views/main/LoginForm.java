package main.java.views.main;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class LoginForm extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoginForm() {
		setSize(400, 525);
		setBackground(Color.red);
		setOpaque(false);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set transparency (alpha) level
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.4f));
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 60, 60);
        g2d.fill(roundedRectangle);
        g2d.dispose();
    }
}
