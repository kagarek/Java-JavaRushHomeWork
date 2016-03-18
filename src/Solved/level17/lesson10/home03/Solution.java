package solved.level17.lesson10.home03;

import java.util.ArrayList;
import java.util.List;

/* Аптека
Реализуй интерфейс Runnable в класах Apteka и Person.
Все нити должны работать пока не isStopped
Логика для Apteka: drugsController должен сделать закупку случайного лекарства (getRandomDrug) в количестве (getRandomCount) и подождать 300 мс
Логика для Person: drugsController должен сделать продажу случайного лекарства (getRandomDrug) в количестве (getRandomCount) и подождать 100 мс
Расставь synchronized там, где это необходимо
*/

public class Solution {
    public volatile static DrugsController drugsController = new DrugsController();
    public volatile static boolean isStopped = false;

    public static void main(String[] args) throws InterruptedException {
        Thread apteka = new Thread(new Apteka());
        Thread man = new Thread(new Person(), "Мужчина");
        Thread woman = new Thread(new Person(), "Женщина");

        apteka.start();
        man.start();
        woman.start();

        Thread.sleep(1000);
        isStopped = true;
    }

    public static class Apteka implements Runnable {

        public void run()
        {
            try
            {
                while (!isStopped)
                {
                    drugsController.buy(getRandomDrug(), getRandomCount());
                    waitAMoment();
                    waitAMoment();
                    waitAMoment();
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    public static class Person implements Runnable {
        public void run()
        {
            try
            {
                while (!isStopped)
                {
                    drugsController.sell(getRandomDrug(), getRandomCount());
                    waitAMoment();
                }
            }
            catch (Exception e)
            {

            }
        }
    }

    public static int getRandomCount() {
        return (int) (Math.random() * 3) + 1;
    }

    public static Drug getRandomDrug() {
        int index = (int) ((Math.random() * 1000) % (drugsController.allDrugs.size()));
        List<Drug> drugs = new ArrayList<Drug>(drugsController.allDrugs.keySet());
        return drugs.get(index);
    }

    private static void waitAMoment() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
