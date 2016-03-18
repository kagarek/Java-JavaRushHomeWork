package solved.level14.lesson06.home01;

/**
 * Created by igormakarychev on 1/10/15.
 */
public abstract class Hen extends Solution.HenFactory
{
    public abstract int getCountOfEggsPerMonth();

    public String getDescription() {return "Я курица.";}
}
