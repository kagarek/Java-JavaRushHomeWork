package solved.level14.lesson08.bonus03;

/**
 * Created by igormakarychev on 1/12/15.
 */
public class Singleton
{
    private static Singleton object = new Singleton();

    public static Singleton getInstance() { return object; }

    private Singleton()
    {
    }

}
