package main.java.views.lab.GradientRounded;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GradientRoundedPanel extends JPanel {

    public GradientRoundedPanel() {
        setOpaque(false); // Make panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set transparency (alpha) level
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.2f)); // Adjust the float value for more/less transparency

        // Create a rounded rectangle
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 90, 90); // Adjust corner radius with last two parameters

        // Create a gradient paint
        GradientPaint paint = new GradientPaint(0, 0, Color.BLUE, 0, getHeight(), Color.CYAN); // Adjust colors as needed

        // Apply the paint
        g2d.setPaint(paint);

        // Fill the rounded rectangle with the gradient
        g2d.fill(roundedRectangle);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gradient Rounded Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GradientRoundedPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

