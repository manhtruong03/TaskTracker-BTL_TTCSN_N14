package main.java.views.main.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class PanelCustomStyles extends JButton {
	
	private float alpha;
	private int radius;
	private GradientPaint paint;
	private Color color;
	
	public PanelCustomStyles() {
		setSize(1280, 720);
		setBackground(Color.black);
		setOpaque(false);
	}
	
	public PanelCustomStyles(int width, int height, float alpha, int radius, GradientPaint paint, Color color) {
		setSize(width, height);
		
		this.alpha = alpha;
		this.radius = radius;
		this.paint = paint;
	
		if (color != null) {
			setBackground(color);
		}
		setOpaque(false);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Set transparency (alpha) level
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.fill(roundedRectangle);
        
        if (paint != null) {
        	g2d.setPaint(paint);
        }
        g2d.dispose();
    }
}
