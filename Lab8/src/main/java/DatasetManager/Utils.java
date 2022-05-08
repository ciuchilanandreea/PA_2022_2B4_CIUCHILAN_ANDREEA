package DatasetManager;

public class Utils {

    /**
     * Method parses a string(from imported csv) to int
     * MD00067 -> 67
     * @param stringId
     * @return int
     */
    public static int getId(String stringId){
        return Integer.parseInt(stringId.substring(2));
    }
}
