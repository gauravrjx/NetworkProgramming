package BasicMultithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadpoolMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        BasicThread t1 = new BasicThread("FIRST");
        BasicThread t2 = new BasicThread("SECOND");
        BasicThread t3 = new BasicThread("THIRD");
        BasicThread t4 = new BasicThread("FOURTH");
        BasicThread t5 = new BasicThread("FIFTH");
        BasicThread t6 = new BasicThread("SIXTH");
        BasicThread t7 = new BasicThread("SEVENTH");
        
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.submit(t4);
        executor.submit(t5);
        executor.submit(t6);
        executor.submit(t7);

        executor.shutdown();
    }
}
