package solved.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by igormakarychev on 10/18/15.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
/*
5. Реализация метода run для Consumer:
5.1. Усыпить трэд на 0.5 секунды
5.2. В бесконечном цикле заберите элемент из очереди методом take и выведите в консоль "Processing item.toString()".
*/

        try
        {
            Thread.sleep(500);
            while (true)
            {
                ShareItem si = queue.take();
                System.out.println("Processing "+ si.toString());
            }
        }
        catch (InterruptedException e)
        {
            //e.printStackTrace();
        }
    }
}
