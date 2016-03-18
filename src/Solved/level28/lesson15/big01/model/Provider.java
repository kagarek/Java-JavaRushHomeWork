package solved.level28.lesson15.big01.model;

import solved.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by igormakarychev on 8/15/15.
 */
public class Provider
{
    private Strategy strategy;

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString)
    {
        return strategy.getVacancies(searchString);
    }
}
