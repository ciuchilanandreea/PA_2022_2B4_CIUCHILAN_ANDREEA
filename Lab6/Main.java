package com.company;

import com.company.Panels.MainFrame;
import java.io.IOException;

public class Main {

    /**
     * Main function of the project
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        startMyPaint();
    }

    /**
     * Method initiates the application
     * @throws IOException
     */
    public static void startMyPaint() throws IOException {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

    }

}
