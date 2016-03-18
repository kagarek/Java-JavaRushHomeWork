package solved.level15.lesson12.home04;

/**
 * Created by ima on 1/16/2015.
 */
public class Earth implements Planet
{
    private static volatile Earth instance = null;

    private Earth()
    {
    }

    public static Earth getInstance()
    {
        if (instance == null)
        {
            synchronized (Earth.class) {
                if (instance == null)
                {
                    instance = new Earth();
                }
            }
        }

        return instance;
    }
}
