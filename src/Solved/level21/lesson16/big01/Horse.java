package solved.level21.lesson16.big01;

/**
 * Created by ima on 3/11/2015.
 */
public class Horse
{
    public String name;
    public double speed;
    public double distance;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public String getName()
    {

        return name;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDistance()
    {
        return distance;
    }

    public Horse (String name, double speed, double distance)
    {
        this.name = name;
        this.speed = speed;
        this.distance = distance;

    }

    public void move()
    {
        distance += speed*Math.random();
    }

    public void print()
    {
        for (int i = 0; i < distance; i++)
        {
            System.out.print(".");
        }
        System.out.println(name);
    }
}
