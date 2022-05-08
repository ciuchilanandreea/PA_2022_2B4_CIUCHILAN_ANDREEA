package GUI.Commands;

import java.util.concurrent.TimeUnit;

/**
 * Class Exit models the exit command
 */
public class Exit extends Command{
    /**
     * Constructor
     */
    public Exit(){
        super();
    }

    /**
     * Method executes command
     * @return null
     */
    @Override
    public String execute(){
        System.exit(1);
        return null;
    }
}
