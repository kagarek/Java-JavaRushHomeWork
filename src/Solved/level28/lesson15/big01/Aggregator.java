package solved.level28.lesson15.big01;

import solved.level28.lesson15.big01.model.*;
import solved.level28.lesson15.big01.view.HtmlView;

/**
 * Created by igormakarychev on 8/15/15.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        HtmlView htmlView = new HtmlView();

        Provider hhStrategy = new Provider(new HHStrategy());
        Provider mkStrategy = new Provider(new MoikrugStrategy());

        Model model = new Model(htmlView, hhStrategy, mkStrategy);
        Controller controller = new Controller(model);

        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }

}