package com.company.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Class DrawingPanel models the canvas
 */
public class DrawingPanel extends JPanel {

    Ellipse2D.Double shape;
    private final MainFrame frame;
    private final static int W = 500, H = 400; //width & height


    private BufferedImage image;
    public Graphics2D graphics;

    private boolean pressed = false;

    /**
     * Constructor
     *
     * @param frame
     */
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }


    /**
     * Method creates a blank canvas
     */
    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    /**
     * Method initiates panel
     */
    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }


    /**
     * Method resets canvas by painting a full-screen white rectangle
     */
    public void resetCanvas() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
        repaint();
    }
    public BufferedImage getImage() {
        return image;
    }

    public void putImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
        repaint();
    }



}


