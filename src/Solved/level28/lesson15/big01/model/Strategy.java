package solved.level28.lesson15.big01.model;

import solved.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by igormakarychev on 8/15/15.
 */
public interface Strategy
{
    List<Vacancy> getVacancies(String searchString);
}