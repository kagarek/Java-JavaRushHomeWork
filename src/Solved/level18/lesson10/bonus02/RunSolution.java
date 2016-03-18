package solved.level18.lesson10.bonus02;

/**
 * Created by igormakarychev on 2/5/15.
 */
public class RunSolution
{
    public static void main(String[] args)
    {
        try
        {
            String[] arguments = {"-c", "Куртка для лыжников, размер 5555555", "11.12", "30"};
            Solution.main(arguments);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
