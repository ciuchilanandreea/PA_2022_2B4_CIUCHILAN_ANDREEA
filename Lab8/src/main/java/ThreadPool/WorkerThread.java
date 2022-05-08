package ThreadPool;

import Connection.ConnectionType;
import GUI.Application;

import java.io.IOException;

/**
 * Class models a worker thread, which runs the application
 */
public class WorkerThread implements Runnable{
    /**
     * Constructor
     */
    public WorkerThread(){ };

    /**
     * Method runs thread
     */
    @Override
    public void run(){
        try {
            Application.getInstance().setConnectionType(ConnectionType.POOL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Application.getInstance().run();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
