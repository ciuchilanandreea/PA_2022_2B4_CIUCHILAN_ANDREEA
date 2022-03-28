package com.company.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class ControlPanel models the control panel, containing actions such as save, load, reset and exit
 */
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");


    /**
     * Constructor
     * @param frame
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Method initializes panel
     */
    private void init() {
        setLayout(new GridLayout(1, 4));

        //adding buttons
        add(resetBtn);
        add(saveBtn);
        add(loadBtn);
        add(exitBtn);

        //adding triggers for each button (on click)
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    /**
     * Method saves current canvas as .png, at a location specified by the user in a file chooser
     * @param e
     */
    private void save(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            chooser.setDialogTitle("Save to location");
            chooser.setAcceptAllFileFilterUsed(true);
            FolderFilter filter = new FolderFilter(); //filter and accept only folders
            chooser.addChoosableFileFilter(filter);

            int status = chooser.showOpenDialog(null);
            if(status == JFileChooser.APPROVE_OPTION){ //save image
                ImageIO.write(frame.canvas.getImage(), "PNG", new File(chooser.getSelectedFile().getPath()));
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Method loads a .png image from a location specified by the user in a file chooser
     * @param e
     */
    private void load(ActionEvent e) {
        BufferedImage image;
        try {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            chooser.setDialogTitle("Upload an image");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG files", "png");
            //filter and accept only .png files
            chooser.addChoosableFileFilter(filter);

            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                image = ImageIO.read(new File(chooser.getSelectedFile().getPath())); //read image
                frame.canvas.putImage(image); //display
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Method exits the current session
     * @param e
     */
    private void exit(ActionEvent e) {
        System.exit(1);
    }

    /**
     * Method resets the canvas to blank
     * @param e
     */
    private void reset(ActionEvent e) {
        this.frame.canvas.resetCanvas();
    }

}
