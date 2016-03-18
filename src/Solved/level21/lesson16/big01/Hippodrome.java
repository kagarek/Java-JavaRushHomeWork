package solved.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by ima on 3/11/2015.
 */
public class Hippodrome
{
    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public  static ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();

        Horse horse1 = new Horse("Horse 1", 3, 0);
        Horse horse2 = new Horse("Horse 2", 3, 0);
        Horse horse3 = new Horse("Horse 3", 3, 0);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException
    {
        for (int i = 1; i <= 100; i++)
        {
            move();
            print();
            Thread.sleep(500);
        }

    }

    public void move() {
        for (Horse horse : horses)
        {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses)
        {
            horse.print();
        }
        System.out.println();
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);

        for (Horse horse : horses)
        {
            if (horse.getDistance() > winner.getDistance())
            {
                winner = horse;
            }
        }

        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is "+getWinner().getName()+"!");
    }
}
