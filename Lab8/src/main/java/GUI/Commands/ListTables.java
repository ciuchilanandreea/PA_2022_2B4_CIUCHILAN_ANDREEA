package GUI.Commands;

import Connection.ConnectionType;
import GUI.Application;

import java.sql.SQLException;
import java.util.List;

/**
 * Class ListTables models a listTables command (see all table names)
 */
public class ListTables extends Command{
    private Application application;

    /**
     * Constructor
     * @throws ClassNotFoundException
     */
    public ListTables() throws ClassNotFoundException {
        super();
        application = Application.getInstance();
    }

    /**
     * Method executes command
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String execute() throws SQLException, ClassNotFoundException {
        List<String> tableList;
        if(Application.getInstance().getConnectionType() == ConnectionType.SINGLETON) {
            tableList = application.getConnectionProvider().seeAllTables();
        }else{
            tableList = application.getConnectionPool().seeAllTables();
        }
        String result = String.join(" ",tableList);
        return result;
    }
}
