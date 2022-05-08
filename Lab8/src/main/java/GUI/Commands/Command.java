package GUI.Commands;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Abstract class Command models a generic command
 */
public abstract class Command {
    private String text;

    /**
     * Constructor
     */
    protected Command() {};

    /**
     * TO BE OVERRIDE
     * Method executes command
     * @return result
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public abstract String execute() throws SQLException, ClassNotFoundException, IOException;
}
