package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Class manages the scroller of the application
 */
public class Scroller extends JScrollPane {
    private final MainFrame mainFrame;
    public JTextArea textArea = new CustomTextArea();

    /**
     * Constructor
     * @param mainFrame
     * @throws IOException
     */
    public Scroller(MainFrame mainFrame) throws IOException {
        this.mainFrame = mainFrame;
        init();
    }

    /**
     * Method initiates scroller
     */
    private void init(){
        setViewportView(textArea);
        textArea.setBackground(new Color(1,1,1, (float) 0.01)); //make it transparent
    }
}
