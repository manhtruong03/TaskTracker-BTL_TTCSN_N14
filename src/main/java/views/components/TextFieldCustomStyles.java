package main.java.views.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TextFieldCustomStyles extends JTextField {
	
	private String placeholder;
	private float alpha;
	private int radius;
	private GradientPaint paint;
	
	public TextFieldCustomStyles() {
	}
	
	public TextFieldCustomStyles(String placeholder, int width, int height, float alpha, int radius, GradientPaint paint) {
		setSize(width, height);
		
		this.placeholder = placeholder;
		this.alpha = alpha;
		this.radius = radius;
		this.paint = paint;
		
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
	    
	    g2d.setPaint(paint);

	    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
	    g2d.fill(roundedRectangle);

	    // Reset alpha for drawing text
	    g2d.setComposite(AlphaComposite.SrcOver);
	    
	    if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
	    	g2d.dispose();
            return;
        }
	    
	    // Set font and color for text
	    g2d.setFont(getFont());
	    g2d.setColor(getForeground());

	    // Get the FontMetrics
	    FontMetrics metrics = g2d.getFontMetrics(getFont());
	    int x = (getWidth() - metrics.stringWidth(placeholder)) / 5;
	    int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

	    g2d.drawString(placeholder, x, y);
	    g2d.dispose();
	}

}