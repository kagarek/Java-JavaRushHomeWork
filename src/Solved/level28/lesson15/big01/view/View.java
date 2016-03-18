package solved.level28.lesson15.big01.view;

import solved.level28.lesson15.big01.Controller;
import solved.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by igormakarychev on 8/26/15.
 */
public interface View
{
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
