package GUI;

import Connection.ConnectionPool;
import Connection.ConnectionProvider;
import Connection.ConnectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class Application models the application
 */
public class Application {
    private static ConnectionProvider connectionProvider;
    private static ConnectionPool connectionPool;
    private MainFrame mainFrame;
    private static Application application;
    private String currentTable;
    private static List<String> tables = Arrays.asList("cities","countries","continent","directors");
    private static ConnectionType type;

    static {
        try {
            application = new Application();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor
     * @throws ClassNotFoundException
     */
    private Application() throws ClassNotFoundException {
        init();
    };

    /**
     * Method initiates connection type
     * @throws ClassNotFoundException
     */
    private static void init() throws ClassNotFoundException {
        if(type == ConnectionType.SINGLETON) {
            connectionProvider = ConnectionProvider.connect();
        }else if(type == ConnectionType.POOL){
            connectionPool = ConnectionPool.connect();
        }
    }

    /**
     * Method runs application
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void run() throws ClassNotFoundException, IOException {
        init();
        MainFrame frame  = new MainFrame();
        frame.run();
    }

    /**
     * Method returns connection type
     * @return connection type
     */
    public ConnectionType getConnectionType(){
        return type;
    }

    /**
     * Method sets connection type
     * @param connType
     */
    public void setConnectionType(ConnectionType connType){
        type = connType;
    }

    /**
     * Method returns current instance of the application
     * @return application
     * @throws ClassNotFoundException
     */
    public static Application getInstance() throws ClassNotFoundException {
        if(application == null){
            application = new Application();
        }
        return application;
    }

    /**
     * Method returns connection provider (singleton)
     * @return connection provider
     * @throws ClassNotFoundException
     */
    public ConnectionProvider getConnectionProvider() throws ClassNotFoundException {
        return connectionProvider;
    }

    /**
     * Method returns connection pool (pool)
     * @return connection pool
     */
    public ConnectionPool getConnectionPool(){
        return connectionPool;
    }

    /**
     * Method sets current table (who has the focus)
     * @param tableName
     */
    public void setCurrentTable(String tableName){
        currentTable = tableName;
    }

    /**
     * Method returns current table
     * @return tableName
     */
    public String getCurrentTable(){
        return currentTable;
    }

    /**
     * Method returns all tables
     * @return tables
     */
    public static List<String> getTables(){
        return tables;
    }
}
