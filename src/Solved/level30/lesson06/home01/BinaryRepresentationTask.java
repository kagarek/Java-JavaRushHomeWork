package solved.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by igormakarychev on 10/27/15.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
    final int i;

    public BinaryRepresentationTask(int i)
    {
        this.i = i;
    }

    @Override
    protected String compute()
    {
        if (i <= 1) return String.valueOf(i);

        BinaryRepresentationTask binaryRepresentationTask1 = new BinaryRepresentationTask(i / 2);
        binaryRepresentationTask1.fork();

        BinaryRepresentationTask binaryRepresentationTask2 = new BinaryRepresentationTask(i % 2);

        return binaryRepresentationTask1.join() + binaryRepresentationTask2.compute();
    }
}