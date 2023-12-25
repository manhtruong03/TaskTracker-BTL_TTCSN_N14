package main.java.views.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UtilsView {

	public static void setImageLable(JLabel imageLabel, File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image resizeImage = originalImage.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(resizeImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
