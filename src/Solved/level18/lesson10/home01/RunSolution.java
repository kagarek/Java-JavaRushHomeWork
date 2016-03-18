package solved.level18.lesson10.home01;

/**
 * Created by ima on 2/4/2015.
 */
public class RunSolution
{

    public static void main(String[] args)
    {
         new RunSolution();
    }

    public RunSolution()
    {
        try
        {
            String[] args = {"resources/level18_lesson10_home01.txt"};
            Solution.main(args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
