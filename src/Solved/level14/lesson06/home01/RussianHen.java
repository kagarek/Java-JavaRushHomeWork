package solved.level14.lesson06.home01;

/**
 * Created by igormakarychev on 1/10/15.
 */
public class RussianHen extends Hen
{
    public int getCountOfEggsPerMonth() {return 1;}

    public String getDescription()
    {
        return super.getDescription() + " Моя страна - "+Country.RUSSIA+". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
