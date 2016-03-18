package solved.level15.lesson12.home04;

/**
 * Created by ima on 1/16/2015.
 */
public class Moon implements Planet
{
    private static volatile Moon instance = null;

    private Moon()
    {
    }

    public static Moon getInstance()
    {
        if (instance == null)
        {
            synchronized (Moon.class) {
                if (instance == null)
                {
                    instance = new Moon();
                }
            }
        }

        return instance;
    }
}
