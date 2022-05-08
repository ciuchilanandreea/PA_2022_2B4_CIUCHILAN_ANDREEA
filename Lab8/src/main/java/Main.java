/**
 * JDBC - Lab 8
 * @author : Ciuchilan Andreea
 */


import Connection.ConnectionType;
import ThreadPool.ThreadPool;
import GUI.Application;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    /**
     * Main class of the program
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        runApplication();
        //singletonConnection();
        //poolConnection();
    }

    /**
     * Method runs application
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void runApplication() throws ClassNotFoundException, IOException {
        Application.getInstance().setConnectionType(ConnectionType.SINGLETON);
        Application.getInstance().run();
    }

    /**
     * Method creates a test using 15 singleton connections
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void singletonConnection() throws ClassNotFoundException, IOException {
        for (int i = 0; i < 15; i++) {
            Application.getInstance().setConnectionType(ConnectionType.SINGLETON);
            Application.getInstance().run();
        }
    }

    /**
     * Method creates a test using a ThreadPool of 15 worker tasks (using Hikari CP)
     */
    public static void poolConnection() {
        ThreadPool threadPool = new ThreadPool(15);
        threadPool.run();
    }

}
