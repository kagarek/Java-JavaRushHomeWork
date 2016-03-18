package solved.level25.lesson05.home01;

/*
 В отдельном классе создать нить LoggingStateThread,
 которая будет выводить в консоль все изменения состояния (State) переданной в конструктор нити.
 Нить LoggingStateThread должна сама завершаться после остановки переданной в конструктор нити.
 Метод main не участвует в тестировании.
 */
public class LoggingStateThread extends Thread
{
    Thread logThread;

    public LoggingStateThread(Thread thread)
    {
        this.logThread = thread;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        State state = this.logThread.getState();
        System.out.println(state);

        while (state != State.TERMINATED)
        {
            if (state != this.logThread.getState())
            {
                state = this.logThread.getState();
                System.out.println(state);
            }
        }

    }
}
