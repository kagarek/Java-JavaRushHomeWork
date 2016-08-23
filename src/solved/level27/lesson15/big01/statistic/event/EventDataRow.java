package solved.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public interface EventDataRow
{
    EventType getType();
    Date getDate();
    int getTime();
}
