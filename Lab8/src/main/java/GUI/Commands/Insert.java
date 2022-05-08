package GUI.Commands;

import Connection.ConnectionType;
import CustomExceptions.AccessDenied;
import CustomExceptions.IllegalNumberOfArgs;
import Entities.City;
import Entities.Director;
import Entities.Country;
import GUI.Application;

import java.sql.SQLException;
import java.util.List;

/**
 * Class Insert models an insert command
 */
public class Insert extends Command {

    private Object object = null;
    private Exception error;

    /**
     * Constructor
     */
    public Insert() {
        super();
    }

    /**
     * Method sets parameter (object fields)
     * @param parameters
     * @throws ClassNotFoundException
     * @throws IllegalNumberOfArgs
     * @throws AccessDenied
     */
    public void setParameters(List<String> parameters) throws ClassNotFoundException, IllegalNumberOfArgs, AccessDenied {
        switch (Application.getInstance().getCurrentTable()) {
            case "countries" -> {
                if (parameters.size() != 5) {
                    error = new IllegalNumberOfArgs("Illegal number of arguments for instance of countries. Required : id title release_date duration score");
                    object = null;
                } else {
                    object = new Country(Integer.parseInt(parameters.get(0)), parameters.get(1), parameters.get(2), Integer.parseInt(parameters.get(3)));
                }
            }
            case "actors" -> {
                if (parameters.size() != 2) {
                    error = new IllegalNumberOfArgs("Illegal number of arguments for instance of actors. Required : id name");
                    object = null;
                } else {
                    object = new City(Integer.parseInt(parameters.get(0)), parameters.get(1));
                }
            }
            case "movieGenre" -> {
                error = new AccessDenied("You don't have permission to write this table! It will be updated automatically!");
                object = null;
            }
            case "directors" -> {
                if (parameters.size() != 2) {
                    error = new IllegalNumberOfArgs("Illegal number of arguments for instance of directors. Required :cp id name");
                    object = null;
                } else {
                    object = new Director(Integer.parseInt(parameters.get(0)), parameters.get(1));
                }
            }
        }
    }

    /**
     * Method executes command
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public String execute() throws ClassNotFoundException, SQLException {
        if (object == null) {
            return error.getMessage();
        }
        if(Application.getInstance().getConnectionType() == ConnectionType.SINGLETON) {
            Application.getInstance().getConnectionProvider().insert(Application.getInstance().getCurrentTable(), object);
        }else{
            Application.getInstance().getConnectionPool().insert(Application.getInstance().getCurrentTable(), object);
        }
        return "Inserted " + object.getClass().getSimpleName().toString() + " to table " + Application.getInstance().getCurrentTable();
    }
}
