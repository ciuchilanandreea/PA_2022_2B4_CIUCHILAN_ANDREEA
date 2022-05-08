package GUI.Commands;

import Connection.ConnectionType;
import GUI.Application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Class Save models a save command (save db to path )
 */
public class Save extends Command {

    private String parameter;

    /**
     * Constructor
     */
    public Save() {
        super();
    }

    /**
     * Method sets parameter (path)
     * @param parameter
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Method executes command
     * @return result
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @Override
    public String execute() throws ClassNotFoundException, IOException {
        try {
            Path from;
            if(Application.getInstance().getConnectionType() == ConnectionType.SINGLETON) {
                from = Application.getInstance().getConnectionProvider().destination.toPath();
            }else{
                from = Application.getInstance().getConnectionPool().destination.toPath();
            }
            Files.copy(from, Path.of(parameter), StandardCopyOption.REPLACE_EXISTING);
       }catch (java.nio.file.AccessDeniedException e){
            return e.toString();
        }
        return "Saved database at " + parameter;

    }
}
