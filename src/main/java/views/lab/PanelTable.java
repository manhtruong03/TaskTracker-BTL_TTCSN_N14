package main.java.views.lab;

import java.awt.*;
import javax.swing.*;

public class PanelTable extends JPanel {
    private final String name;
    private final int panelCardWidth;
    private final int panelCardHeight;

    public PanelTable(String name, int panelCardWidth, int panelCardHeight) {
        this.name = name;
        this.panelCardWidth = panelCardWidth;
        this.panelCardHeight = panelCardHeight;
        setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
        updateSize();
    }

    private void updateSize() {
        if (getComponentCount() == 0) {
            setPreferredSize(new Dimension(panelCardWidth, panelCardHeight));
            add(new JLabel(name));
        } else {
            removeIfLabel();
            setPreferredSize(new Dimension(panelCardWidth, getComponentCount() * panelCardHeight));
        }
    }

    public void addPanelCard(JPanel card) {
        removeIfLabel();
        add(card);
        updateSize();
        revalidate();
        repaint();
    }
    
    public void addPanelCard(JPanel card, int index) {
        removeIfLabel();
        add(card, index);
        updateSize();
        revalidate();
        repaint();
    }

    private void removeIfLabel() {
        if (getComponentCount() == 1 && getComponent(0) instanceof JLabel) {
            remove(0);
        }
    }
}

