package main.java.views.main;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class TrelloAppView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrelloAppView frame = new TrelloAppView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrelloAppView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(ConstantView.DEFAULT_WINDOW_SIZE);
		setLocationRelativeTo(null);
//		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));	// top, left, bottom, right
		contentPane.setSize(ConstantView.DEFAULT_WINDOW_SIZE);
		contentPane.setLayout(new MigLayout("debug"));
		
		setContentPane(contentPane);
		
		
//		contentPane.add(new JLabel("HeaderLabel"), "span, alignx right, wrap");
		
		contentPane.add(new ProjectView(), "wrap");
//		contentPane.add(new AbsoluteLayout());
		
		
		File fileImage = new File("src/assets/images/kai-oberhauser-BKAaLmT0tIs-unsplash.jpg");
//		Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		JLabel lblBackground = new JLabel();
		setimageLable(lblBackground, fileImage);
		contentPane.add(lblBackground, "width 1280:1280, height 720:720");
		
	}
	
	private void setimageLable(JLabel imageLabel, File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image resizeImage = originalImage.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(resizeImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
