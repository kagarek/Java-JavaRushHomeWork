package solved.level28.lesson06.home01;

/**
 * Created by igormakarychev on 8/10/15.
 */
public class MyThread extends Thread
{
    static int currentThreadNubmer = 0;

    public MyThread()
    {
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(Runnable target)
    {
        super(target);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(String name)
    {
        super(name);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        if (currentThreadNubmer < 10)
            currentThreadNubmer++;
        else
            currentThreadNubmer = (currentThreadNubmer + 1) % 10;

        this.setPriority(currentThreadNubmer);
    }
}
