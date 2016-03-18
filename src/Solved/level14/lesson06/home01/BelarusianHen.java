package solved.level14.lesson06.home01;

/**
 * Created by igormakarychev on 1/10/15.
 */
public class BelarusianHen extends Hen
{
    public int getCountOfEggsPerMonth() {return 4;}

    public String getDescription()
    {
        return super.getDescription() + " Моя страна - "+Country.BELARUS+". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
