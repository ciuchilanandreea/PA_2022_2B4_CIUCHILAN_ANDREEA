package GUI.Commands;

import Connection.ConnectionType;
import GUI.Application;

import java.sql.SQLException;
import java.util.List;

/**
 * Class ListWhere models a listWhere command(select * from ... where ...)
 */
public class ListWhere extends Command{
    private String what;
    private String field;
    private String value;

    /**
     * Constructor
     */
    public ListWhere(){
        super();
    }

    /**
     * Method sets parameters
     * @param what
     * @param field
     * @param value
     */
    public void setParameters(String what, String field, String value){
        this.what = what;
        this.field = field;
        this.value = value;
    }

    /**
     * Method executes command
     * @return result
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public String execute() throws ClassNotFoundException, SQLException {
       List<String> result;
       if(Application.getInstance().getConnectionType() == ConnectionType.SINGLETON) {
           result = Application.getInstance().getConnectionProvider().selectWhere(Application.getInstance().getCurrentTable(), what, field, value);
       }else{
           result = Application.getInstance().getConnectionPool().selectWhere(Application.getInstance().getCurrentTable(), what,field,value);
       }
       return String.join("\n",result);
    }
}
