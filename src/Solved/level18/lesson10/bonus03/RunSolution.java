package solved.level18.lesson10.bonus03;

/**
 * Created by ima on 2/6/2015.
 */
public class RunSolution
{
    public static void main(String[] args)
    {
        try
        {
            //String[] argumentsUpdate = {"-u", "198478", "bla-bla-bla", "11.12", "30"};
            String[] argumentsDelete = {"-d", "19847984"};

            //Solution.main(argumentsUpdate);
            Solution.main(argumentsDelete);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
