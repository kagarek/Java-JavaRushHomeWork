package solved.level27.lesson15.big01;

import solved.level27.lesson15.big01.kitchen.Cook;
import solved.level27.lesson15.big01.kitchen.Waitor;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        //new Tablet(5).createOrder();

        Cook cook = new Cook("Amigo1");
        Waitor waitor = new Waitor();
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        cook.addObserver(waitor);
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

        Cook cook2 = new Cook("Amigo2");
        cook2.addObserver(waitor);
        tablet.deleteObserver(cook);
        tablet.addObserver(cook2);
        tablet.createOrder(); //во время второго заказа переводишь системное время на день вперед\назад.

        tablet.deleteObserver(cook2);
        tablet.addObserver(cook);
        tablet.createOrder();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}