package GUI;

import CustomExceptions.AccessDenied;
import CustomExceptions.IllegalNumberOfArgs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class manages input/output transactions in the application
 */
public class CustomKeyListener extends KeyAdapter {
    private final int ENTER = KeyEvent.VK_ENTER;
    private final int BACKSPACE = KeyEvent.VK_BACK_SPACE;
    private final CustomTextArea textArea;
    private final static String prompt = "shellino>";


    /**
     * Constructor
     * @param textArea
     */
    public CustomKeyListener(CustomTextArea textArea) {
        super();
        this.textArea = textArea;
    }

    /**
     * Method manages what happens when a key is pressed (enter or backspace)
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == ENTER) {
            String command = getCommand();
            String result = null;
            try {
                result = Utils.processCommand(command);
            } catch (SQLException | ClassNotFoundException | IOException | AccessDenied | IllegalNumberOfArgs throwables) {
                throwables.printStackTrace();
            }
            textArea.append("\n");
            textArea.append(result);
            textArea.append("\n");
            textArea.append(prompt);
        }
    }

    /**
     * Method manages what happens when key is pressed (sets cursor)
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == ENTER) {
            textArea.setCaretPosition(textArea.getCaretPosition() - 1);
        }
    }

    /**
     * Method parses text and gets the last typed command
     * @return command
     */
    private String getCommand() {
        String fullText = textArea.getText();
        int startIndex = fullText.lastIndexOf('>') + 1;
        String input;
        if (textArea.getText().lastIndexOf("\n") == -1) {
            input = fullText.substring(startIndex, fullText.length());
            textArea.setText(textArea.getText());
        } else {
            input = fullText.substring(startIndex, fullText.length() - 1);
            textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
        }
        return input;
    }




}
