package main.java.views.lab.MigLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

//public class MigLayoutExample extends JFrame {
//
//    public MigLayoutExample() {
//        initUI();
//    }
//
//    private void initUI() {
//        setLayout(new MigLayout("fill, insets 0, debug")); // Fill the frame and no insets
//        setSize(800, 600);
//        setLocationRelativeTo(null);
//
//        // Mimicking CSS Absolute Positioning
//        // Position a label at x=50px, y=50px from the top-left corner of the frame
//        JLabel label1 = new JLabel("1 Absolute Position");
//        add(label1, "pos 0px 0px");
//        JLabel label2 = new JLabel("2 Absolute Position");
//        add(label2, "pos 800px 600px");
//        JLabel label3 = new JLabel("3 Absolute Position");
//        add(label3, "dock South");
//
//        // Mimicking CSS Relative Positioning
//        // Place a button right next to label1
//        JButton button1 = new JButton("Relative to Label");
//        add(button1, "cell 2 0, pos 50px 50px");
//
//        pack();
//        setTitle("MigLayout Example");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            MigLayoutExample ex = new MigLayoutExample();
//            ex.setVisible(true);
//        });
//    }
//}


import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class MigLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MigLayoutExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("MigLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new MigLayout());

        // Simulating CSS Absolute Positioning
        JButton button1 = new JButton("Button 1");
        frame.add(button1, "pos 50px 50px"); // Position at x=50px, y=50px

        // Simulating CSS Relative Positioning
        JButton button2 = new JButton("Button 2");
        frame.add(button2, "wrap"); // Places button2 below button1

        // Simulating CSS Fixed Positioning (as best as possible)
        // In Swing, this would more likely be a separate, undocked panel
        JButton button3 = new JButton("Button 3");
        frame.add(button3, "dock north"); // Docking to the top of the frame

        // Simulating CSS Sticky Positioning
        // Not directly possible, but you can use a combination of docking and layering

        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}


