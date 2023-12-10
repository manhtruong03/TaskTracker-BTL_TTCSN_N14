package main.java.views.lab.ImageSelector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageSelector extends JFrame {

    private JLabel imageLabel;

    public ImageSelector() {
        super("Image Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new FlowLayout());

        JButton button = new JButton("Select Image");
        button.addActionListener(e -> selectAndSaveImage());

        imageLabel = new JLabel();

        add(button);
        add(imageLabel);

        setVisible(true);
    }

    private void selectAndSaveImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File destination = new File("src/assets/images/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                displayImage(destination);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void displayImage(File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            BufferedImage circularImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = circularImage.createGraphics();

            g2d.setClip(new Ellipse2D.Float(0, 0, originalImage.getWidth(), originalImage.getHeight()));
            g2d.drawImage(originalImage, 0, 0, null);
            g2d.dispose();

            imageLabel.setIcon(new ImageIcon(circularImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageSelector::new);
    }
}
