package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class models the text area of the application
 */
public class CustomTextArea extends JTextArea {
    private Image image;
    private Font font = new Font("SansSerif", Font.BOLD, 15);

    /**
     * Constructor
     * @throws IOException
     */
    public CustomTextArea() throws IOException {
        init();
    }

    /**
     * Method initiates jtextarea
     * @throws IOException
     */
    private void init() throws IOException {
        image = ImageIO.read(new File("media\\background-large.jpg"));
        addKeyListener(new CustomKeyListener(this));
        setFont(font);


    }

    /**
     * Method paints components in graphics
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(image,0,0,null);
        super.paintComponent(g);
    }
}
