package solved.level25.lesson07.home01;

public class TaskManipulator implements Runnable,CustomThreadManipulator {
    Thread thread;
    String threadName;

    @Override
    public void run() {
        System.out.println(threadName);
        try
        {
            while (!thread.isInterrupted())
            {
                Thread.sleep(100);
                System.out.println(threadName);
            }
        }
        catch (InterruptedException e)
        {
        }
    }

    @Override
    public void start(String threadName)
    {
        this.threadName = threadName;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop()
    {
        thread.interrupt();
    }
}
