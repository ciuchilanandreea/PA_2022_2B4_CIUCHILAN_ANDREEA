package com.company.Panels;

import javax.swing.*;

/**
 * Class ConfigPanel models the panel containing configurations regarding the drawing process
 */
public class ConfigPanel extends JPanel{

    private final MainFrame frame;
    private JFrame popUp;
    private JSpinner Ynumber;
    private JSpinner Xnumber;
    private JButton button;

    //decision
    private boolean pressed=false;
    /**
     * Constructor
     */
    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    /**
     * Method initializes panel
     */
    private void init(){

        //adding components
        add(new JLabel("Grinde size "));
        add(XSize());
        add(YSize());
        add(CreateButton());

    }

    /**
     * Method creates a spinner for choosing the radius
     * @return spinner
     */
    private JSpinner XSize(){

        JSpinner Xnumber = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        Xnumber.setValue(10); //default value
        return Xnumber;
    }

    /**
     * Method creates a spinner for choosing the sides
     * @return sides
     */
    private JSpinner YSize(){
        Ynumber = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        Ynumber.setValue(10);
        return Ynumber;
    }
    
    private JButton CreateButton(){
        button = new JButton("Create");
        return button;
    }


}
