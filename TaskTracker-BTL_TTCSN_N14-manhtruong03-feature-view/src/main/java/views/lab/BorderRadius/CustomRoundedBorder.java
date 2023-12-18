package main.java.views.lab.BorderRadius;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Path2D;

public class CustomRoundedBorder extends AbstractBorder {
    private int radius;
    private boolean topLeft, topRight, bottomLeft, bottomRight;

    public CustomRoundedBorder(int radius, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
        this.radius = radius;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Path2D.Float path = new Path2D.Float();
        
        if (topLeft) {
            path.moveTo(x, y + radius);
            path.quadTo(x, y, x + radius, y);
        } else {
            path.moveTo(x, y);
        }

        if (topRight) {
            path.lineTo(x + width - radius - 1, y);
            path.quadTo(x + width - 1, y, x + width - 1, y + radius);
        } else {
            path.lineTo(x + width - 1, y);
        }

        if (bottomRight) {
            path.lineTo(x + width - 1, y + height - radius - 1);
            path.quadTo(x + width - 1, y + height - 1, x + width - radius - 1, y + height - 1);
        } else {
            path.lineTo(x + width - 1, y + height - 1);
        }

        if (bottomLeft) {
            path.lineTo(x + radius, y + height - 1);
            path.quadTo(x, y + height - 1, x, y + height - radius - 1);
        } else {
            path.lineTo(x, y + height - 1);
        }

        path.closePath();
        g2d.draw(path);
        g2d.dispose();
    }
}
