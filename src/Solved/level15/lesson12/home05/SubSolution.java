package solved.level15.lesson12.home05;

/**
 * Created by igormakarychev on 1/18/15.
 */
public class SubSolution extends Solution
{
     SubSolution()
    {
    }

     SubSolution(Integer i, Integer s)
    {
        super(i, s);
    }

     SubSolution(String i, String s)
    {
        super(i, s);
    }

    protected SubSolution(Short s)
    {
        super(s);
    }

    protected SubSolution(Double d)
    {
        super(d);
    }

    protected SubSolution(Float f)
    {
        super(f);
    }

    public SubSolution(Object o)
    {
        super(o);
    }

    public SubSolution(Object o1, Object o2)
    {
        super(o1, o2);
    }

    public SubSolution(String s, Number n)
    {
        super(s, n);
    }

    private SubSolution(String s) {}
    private SubSolution(Number n) {}
    private SubSolution(Integer i) {}
}
