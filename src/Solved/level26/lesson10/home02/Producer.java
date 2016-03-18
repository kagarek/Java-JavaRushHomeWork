package solved.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by igormakarychev on 6/1/15.
 */
public class Producer implements Runnable {
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        try
        {
            int i = 1;
            while (true)
            {
                System.out.println("Some text for " + i);
                i++;
                currentThread.sleep(500);
            }
        }
        catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}