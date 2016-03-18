package solved.level17.lesson10.bonus02;

/**
 * Created by ima on 2/2/2015.
 */
public class RunSolution extends Thread
{
    public static void main(String[] args)
    {
        RunSolution thread1 = new RunSolution();
        RunSolution thread2 = new RunSolution();

        thread1.start();
        thread2.start();
    }

    public void run()
    {
        try
        {
            String[] argsCreate = {"-c",
                    "new name 1", "ж", "15/04/1990",
                    "new name 2", "ж", "17/02/1992"};

            String[] argsUpdate = {"-u",
                    "0", "updated name 1", "м", "15/04/1990",
                    "1", "updated name 2", "ж", "17/02/1992",
                    "3", "updated name 3", "м", "12/09/1985"};

            String[] argsDelete = {"-d",
                    "1",
                    "0",
                    "2",
                    "3"};

            String[] argsInfo = {"-i",
                    "1",
                    "0",
                    "2",
                    "3"};

            Solution.main(argsCreate);
            Solution.main(argsUpdate);
            Solution.main(argsDelete);
            Solution.main(argsInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public RunSolution()
    {

    }
}
