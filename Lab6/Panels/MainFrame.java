package com.company.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * com.company.Main frame class models the application, containing a canvas, a control panel and a configuration panel
 */
public class MainFrame extends JFrame{

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    /**
     * Constructor
     */
    public MainFrame(){
        super("My Game");
        init();
    }

    /**
     * Method initializes frame
     */
    private void init(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //creating panels
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);


        //adding panels at corresponding locations
        add(configPanel,BorderLayout.NORTH);
        add(controlPanel,BorderLayout.SOUTH);
        add(canvas,BorderLayout.CENTER);

        pack();

    }

    /**
     * Method returns the config panel
     * @return config panel
     */
    public ConfigPanel getConfigPanel() {
        return configPanel;
    }


    /**
     * Method returns the control panel
     * @return control panel
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }


    /**
     * Method returns the canvas
     * @return canvas
     */
    public DrawingPanel getCanvas(Graphics g) {
        g.drawLine(20, 20, 200, 180);
        return canvas;
    }
}
