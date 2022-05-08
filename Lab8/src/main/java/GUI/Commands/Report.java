package GUI.Commands;

import GUI.Application;
import ImportCSV.ImportTool;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Class Report models a report command (generate HTML report)
 */
public class Report extends Command{

    /**
     * Constructor
     */
    public Report(){
        super();
    }

    /**
     * Method executes command
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @Override
    public String execute() throws SQLException, ClassNotFoundException, IOException {
        ImportTool.createReport();
        return "Created HTML report at : " + ImportTool.outputPath;
    }
}
