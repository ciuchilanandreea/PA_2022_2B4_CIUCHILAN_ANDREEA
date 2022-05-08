package CustomExceptions;

/**
 * Exception is thrown when user tries to modify inaccessible table
 */
public class AccessDenied extends Exception{
    /**
     * Constructor
     * @param msg
     */
    public AccessDenied(String msg){
        super(msg);
    }
}
