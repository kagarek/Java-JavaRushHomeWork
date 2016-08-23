package solved.level27.lesson15.big01.kitchen;

/**
 * Created by igormakarychev on 6/21/15.
 */
public enum Dish
{
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    Dish(int duration)
    {
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

    private int duration;

    public static String allDishesToString()
    {
        if (Dish.values().length == 0)
            return "No Dish!";

        String s = Dish.values()[0].toString();

        for (int i = 1; i < Dish.values().length; i++)
        {
            s = s + ", " + Dish.values()[i];
        }

        return s;
    }
}