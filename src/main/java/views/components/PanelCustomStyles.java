package main.java.views.components;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class PanelCustomStyles extends JPanel {
	
	private float alpha;
	private int radius;
	transient  private GradientPaint paint;
	private Color borderColor = Color.BLACK;
	private float strokeWidth = 1.0f;

	public PanelCustomStyles() {
	}

	public PanelCustomStyles(int width, int height, float alpha, int radius, 
			GradientPaint paint, Color borderColor, float strokeWidth) {
		setSize(width, height);

		this.alpha = alpha;
		this.radius = radius;
		this.paint = paint;
		this.borderColor = borderColor;
		this.strokeWidth = strokeWidth;

		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
	    
	    // Adjust the width and height to account for the stroke width
	    int adjust = Math.round(strokeWidth / 2);
	    int width = getWidth() - adjust;
	    int height = getHeight() - adjust;
	    if (strokeWidth > 0.0f) {
	    	width -= 1;
	    	height -= 1;
	    }

	    // Fill the rounded rectangle
	    g2d.setPaint(paint);
	    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(adjust, adjust, width, height, radius, radius);
	    g2d.fill(roundedRectangle);

	    // Draw the border
	    g2d.setColor(borderColor);
	    g2d.setStroke(new BasicStroke(strokeWidth));
	    g2d.draw(roundedRectangle);

	    g2d.dispose();
	}
}
