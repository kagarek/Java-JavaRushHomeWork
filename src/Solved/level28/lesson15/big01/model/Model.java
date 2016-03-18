package solved.level28.lesson15.big01.model;

import solved.level28.lesson15.big01.view.View;
import solved.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igormakarychev on 8/26/15.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers)
    {
        if (view == null || providers == null || providers.length == 0)
            throw new IllegalArgumentException();

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city)
    {
        List<Vacancy> vacancyList = new ArrayList<>();

        for (Provider provider : providers)
        {
            vacancyList.addAll(provider.getJavaVacancies(city));
        }

        if (vacancyList.size() > 0) view.update(vacancyList);
    }
}
