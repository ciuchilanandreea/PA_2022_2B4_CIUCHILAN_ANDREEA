package GUI;

import javax.swing.*;
import java.io.IOException;

/**
 * Class manages the mainframe of the application
 */
public class MainFrame extends JFrame {
    private final static int W = 800, H = 800;
    private Scroller scroller;

    /**
     * Constructor
     * @throws IOException
     */
    public MainFrame() throws IOException {
        super("Shellino");
        init();
    }

    /**
     * Method initiates frame
     * @throws IOException
     */
    private void init() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        scroller = new Scroller(this);
        add(scroller);
        pack();
    }

    /**
     * Method runs frame
     */
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setBounds(0, 0, W, H);
                setVisible(true);
                showPrompt();
            }
        });
    }

    /**
     * Method shows prompt
     */
    private void showPrompt() {
        scroller.textArea.setText(scroller.textArea.getText() + "shellino>");
    }


}
