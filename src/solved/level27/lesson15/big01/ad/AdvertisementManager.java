package solved.level27.lesson15.big01.ad;

import solved.level27.lesson15.big01.ConsoleHelper;
import solved.level27.lesson15.big01.statistic.StatisticManager;
import solved.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by igormakarychev on 6/23/15.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<List<Advertisement>> toCompare = new ArrayList<>();

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        int timeToFill = timeSeconds;
        List<Advertisement> resultList = getBestVideo(timeToFill, storage.list(), new ArrayList<Advertisement>());

        if (resultList.isEmpty() || resultList == null) throw new NoVideoAvailableException();

        Collections.sort(resultList, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                if (Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying()) != 0)
                    return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                else
                {
                    long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                    long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                    return Long.compare(oneSecondCost1, oneSecondCost2);
                }
            }
        });

        //Showing videos
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(resultList, getTotalVideoAmount(resultList), getTotalVideoDuration(resultList)));
        showVideos(resultList);
    }

    private List<Advertisement> getBestVideo(int timeToFill, List<Advertisement> sourceList,  List<Advertisement> resultList)
    {
        for (int i = 0; i < sourceList.size(); i++)
        {
            Advertisement ad = sourceList.get(i);
            if (ad.getHits() <= 0)
                continue;
            else
            {

                if (ad.getDuration() <= timeToFill & !resultList.contains(ad))
                {
                    resultList.add(ad);
                    timeToFill = timeToFill - ad.getDuration();
                    getBestVideo(timeToFill, sourceList, resultList);
                    if (!toCompare.contains(resultList))
                    {
                        toCompare.add(new ArrayList<>(resultList));
                        resultList.clear();
                        timeToFill = timeSeconds;
                    } else
                        break;
                } else
                {
                    if (isPossibleToAddAnyVideo(timeToFill, sourceList, resultList))
                        continue;
                    break;
                }
            }
        }

        Collections.sort(toCompare, new Comparator<List<Advertisement>>()
        {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2)
            {
                // 1) сумма денег, полученная от показов, максимальная из всех возможных вариантов
                if (Long.compare(getTotalVideoAmount(o2), getTotalVideoAmount(o1)) != 0)
                    return Long.compare(getTotalVideoAmount(o2), getTotalVideoAmount(o1));
                    // 4) если существуют несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
                else
                    // 4.1) выбрать тот вариант, у которого суммарное время максимальное
                    if (Integer.compare(getTotalVideoDuration(o2), getTotalVideoDuration(o1)) != 0)
                        return Integer.compare(getTotalVideoDuration(o2), getTotalVideoDuration(o1));
                    else
                        // 4.2) если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов
                        return Integer.compare(o1.size(), o2.size());
            }
        });

        List<Advertisement> emptyList = new ArrayList<>();

        return toCompare.isEmpty() ? emptyList : toCompare.get(0);
    }

    private boolean isPossibleToAddAnyVideo(int timeToFill, List<Advertisement> sourceList,  List<Advertisement> resultList)
    {
        for (Advertisement ad : sourceList)
        {
            if (ad.getDuration() <= timeToFill & !resultList.contains(ad) & ad.getHits() > 0)
            {
                return true;
            }
        }

        return false;
    }

    private int getTotalVideoDuration(List<Advertisement> resultList)
    {
        int i = 0;
        for (Advertisement ad : resultList)
        {
            i+=ad.getDuration();
        }
        return i;
    }

    private long getTotalVideoAmount(List<Advertisement> resultList)
    {
        long i = 0L;
        for (Advertisement ad : resultList)
        {
            i+=ad.getAmountPerOneDisplaying();
        }
        return i;
    }

   private void showVideos(List<Advertisement> resultList)
    {
        for (Advertisement advertisement : resultList)
        {
            advertisement.revalidate();
            ConsoleHelper.writeMessage(
                    advertisement.getName() + " is displaying... "
                            + advertisement.getAmountPerOneDisplaying() + ", "
                            + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

        }
    }
}
