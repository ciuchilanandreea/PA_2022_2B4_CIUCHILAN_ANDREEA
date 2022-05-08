package GUI.Commands;

import GUI.Application;

/**
 * Class models a ChangeDirectory command (here, change table)
 */
public class ChangeDirectory extends Command{
    private String parameter;

    /**
     * Constructor
     */
    public ChangeDirectory(){
        super();
    }

    /**
     * Method sets parameter (tableName)
     * @param tableName
     */
    public void setParameter(String tableName){
        this.parameter = tableName;
    }

    /**
     * Method executed command
     * @return result
     * @throws ClassNotFoundException
     */
    public String execute() throws ClassNotFoundException {
        if(Application.getTables().contains(parameter)) {
            Application.getInstance().setCurrentTable(parameter);
            return "Current table : " + parameter;
        }
        return "ERROR : No such table!";
    }
}
