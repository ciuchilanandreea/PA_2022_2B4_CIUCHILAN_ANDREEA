package GUI.Commands;

import GUI.Application;
import ImportCSV.ImportTool;

import java.sql.SQLException;

/**
 * Class Import models an import command (from csv)
 */
public class Import extends Command{
    private String parameter;

    /**
     * Constructor
     */
    public Import(){
        super();
    }

    /**
     * Method sets parameter (csv path)
     * @param parameter
     */
    public void setParameter(String parameter){
        this.parameter = parameter;
    }

    /**
     * Method executes command
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public String execute() throws ClassNotFoundException, SQLException {
        ImportTool importTool = new ImportTool(parameter);
        importTool.setMaxEntries(30);
        importTool.importFromCsv();
        return "Imported from " + parameter;
    }
}
