package solved.level25.lesson16.big01;

public class BaseObject
{
    double x;
    double y;
    double radius;
    boolean isAlive;

    public void draw(Canvas canvas) {}
    public void move() {}
    public void die() {isAlive = false;}

    public boolean isIntersec(BaseObject o)
    {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }
    public BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRadius()
    {
        return radius;
    }

    public boolean isAlive()
    {
        return isAlive;
    }
}
