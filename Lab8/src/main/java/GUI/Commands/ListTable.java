package GUI.Commands;

import Connection.ConnectionType;
import GUI.Application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Class ListTable models a listTable command (select * from table)
 */
public class ListTable extends Command{

    /**
     * Constructor
     */
    public ListTable(){
        super();
    }

    /**
     * Method executes command
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public String execute() throws ClassNotFoundException, SQLException {
        if(Application.getInstance().getCurrentTable() == null){
            return "ERROR : No table selected!";
        }
        if(Application.getInstance().getConnectionType() == ConnectionType.SINGLETON) {
            return String.join("\n", Application.getInstance().getConnectionProvider().select(Application.getInstance().getCurrentTable()));
        }
        return String.join("\n",Application.getInstance().getConnectionPool().select(Application.getInstance().getCurrentTable()));
    }
}
