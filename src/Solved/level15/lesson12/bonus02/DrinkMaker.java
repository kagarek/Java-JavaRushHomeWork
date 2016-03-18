package solved.level15.lesson12.bonus02;

/**
 * Created by ima on 1/20/2015.
 */
public abstract class DrinkMaker
{

    abstract void getRightCup();

    abstract void putIngredient();

    abstract void pour();

    void makeDrink()
    {
        getRightCup();
        putIngredient();
        pour();
    }

}
