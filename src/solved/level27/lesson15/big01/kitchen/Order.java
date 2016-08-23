package solved.level27.lesson15.big01.kitchen;

import solved.level27.lesson15.big01.ConsoleHelper;
import solved.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class Order
{
    public Tablet getTablet()
    {
        return tablet;
    }

    private Tablet tablet;

    public List<Dish> getDishes()
    {
        return dishes;
    }

    private List<Dish> dishes;

    public Order(Tablet tablet) throws IOException
    {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
        this.tablet = tablet;
    }

    @Override
    public String toString()
    {
        return dishes.size() == 0 ? "" : "Your order: " + dishes.toString() + " of " + tablet.toString();
    }

    public int getTotalCookingTime()
    {
        int totalDuration = 0;

        for (Dish dish : dishes)
            totalDuration+=dish.getDuration();

        return totalDuration;
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }
}
