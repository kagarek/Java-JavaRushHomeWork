package solved.level27.lesson15.big01.statistic.event;

import solved.level27.lesson15.big01.kitchen.Dish;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class CookedOrderEventDataRow implements EventDataRow
{
    private Date currentDate;
    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs)
    {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = new ArrayList<>(cookingDishs);
        this.currentDate = new GregorianCalendar().getTime();
    }

    @Override
    public EventType getType() { return EventType.COOKED_ORDER; }

    @Override
    public Date getDate()
    {
        return currentDate;
    }

    @Override
    public int getTime()
    {
        return cookingTimeSeconds;
    }

    public String getCookName() { return cookName; }
}
