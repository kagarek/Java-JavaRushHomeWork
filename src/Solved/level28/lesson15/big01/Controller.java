package solved.level28.lesson15.big01;

import solved.level28.lesson15.big01.model.Model;

/**
 * Created by igormakarychev on 8/28/15.
 */
public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName)
    {
        model.selectCity(cityName);
    }
}
