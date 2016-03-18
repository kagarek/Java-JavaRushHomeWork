package solved.level17.lesson10.bonus01;

import java.text.ParseException;

/**
 * Created by igormakarychev on 2/1/15.
 */
public class RunSolution
{
    public static void main(String[] args)
    {
        new RunSolution();
    }

    public RunSolution()
    {
        String[] argsCreate = {"-c", "New person", "м", "24/06/1985"};
        String[] argsUpdate = {"-u", "1", "Update", "ж", "12/09/1985"};
        String[] argsDelete = {"-d", "0"};
        String[] argsInfo = {"-i", "1"};
        try
        {
            Solution.main(argsCreate);
            Solution.main(argsUpdate);
            Solution.main(argsDelete);
            Solution.main(argsInfo);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
