package CustomExceptions;

/**
 * Exception is thrown when user doesn't supply the correct number of arguments for inserting into db
 */
public class IllegalNumberOfArgs extends Exception{
    /**
     * Constructor
     * @param msg
     */
    public IllegalNumberOfArgs(String msg){
        super(msg);
    }
}
