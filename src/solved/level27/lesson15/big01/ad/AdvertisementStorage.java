package solved.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igormakarychev on 6/23/15.
 */
class AdvertisementStorage
{
    public List<Advertisement> list()
    {
        return videos;
    }

    public void add(Advertisement advertisement)
    {
        videos.add(advertisement);
    }

    private final List<Advertisement> videos = new ArrayList<>();

    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance()
    {
        return ourInstance;
    }

    private AdvertisementStorage()
    {
        //Original
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));  //10 min
        //Original

        /* Test from Diana
        Advertisement a1 = new Advertisement(new Object(), "TestVideo1", 101, 2, 25 * 60); //1 displaying 51 cent
        Advertisement a2 = new Advertisement(new Object(), "TestVideo2", 202, 1, 10 * 60);
        videos.add(a1);
        a1.revalidate();
        videos.add(a2);
        */

        /* Test from info.javarush.ru
        videos.add(new Advertisement(new Object(), "100-60", 100, 1, 60));
        videos.add(new Advertisement(new Object(), "100-59", 100, 1, 59));
        videos.add(new Advertisement(new Object(), "50-36", 50, 1, 36));
        videos.add(new Advertisement(new Object(), "50-24", 50, 1, 24));
        videos.add(new Advertisement(new Object(), "50-12", 30, 1, 12));
        videos.add(new Advertisement(new Object(), "50-12", 10, 1, 12));
        videos.add(new Advertisement(new Object(), "50-12", 10, 1, 12));
        */
    }
}
