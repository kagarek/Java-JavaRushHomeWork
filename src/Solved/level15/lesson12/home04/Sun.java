package solved.level15.lesson12.home04;

/**
 * Created by igormakarychev on 1/16/15.
 */
public class Sun implements Planet
{
    private static Sun ourInstance = new Sun();

    public static Sun getInstance()
    {
        return ourInstance;
    }

    private Sun()
    {
    }
}
