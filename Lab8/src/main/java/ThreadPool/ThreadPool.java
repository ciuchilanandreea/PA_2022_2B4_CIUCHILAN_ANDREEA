package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class manages a thread pool of n parallel worker tasks
 */
public class ThreadPool {
    private int n;

    /**
     * Constructor
     * @param n
     */
    public ThreadPool(int n){
        this.n = n;
    }

    /**
     * Method runs threads
     * Method runs threads
     */
    public void run(){
        ExecutorService executor = Executors.newFixedThreadPool(n);
        for(int i = 0; i<n; i++){
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
        executor.shutdown();
    }
}
