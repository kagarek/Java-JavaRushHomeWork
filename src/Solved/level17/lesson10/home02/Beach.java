package solved.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

import java.util.ArrayList;
import java.util.Collections;

public class Beach implements Comparable<Beach> {
    private volatile String name;      //название
    private volatile float distance;   //расстояние
    private volatile int quality;    //качество

    public static void main(String[] args)
    {
        Beach beach1=new Beach("beach1",44,3);
        Beach beach2=new Beach("beach2",19,2);
        Beach beach3=new Beach("beach3",30,3);
        Beach beach4=new Beach("beach4",20,6);
        Beach beach5=new Beach("beach5",22,2);

        ArrayList<Beach> beaches = new ArrayList<Beach>();

        beaches.add(beach1);
        beaches.add(beach2);
        beaches.add(beach3);
        beaches.add(beach4);
        beaches.add(beach5);

        for (Beach x : beaches)
            System.out.println(x.getName() + ", distance = " + x.getDistance() + ", quality =  "+ x.getQuality());

        /*
        beach1, distance = 44.0, quality =  3
        beach2, distance = 19.0, quality =  2
        beach3, distance = 30.0, quality =  3
        beach4, distance = 20.0, quality =  6
        beach5, distance = 22.0, quality =  2
        */

        System.out.println();

        Collections.sort(beaches);

        for (Beach x : beaches)
            System.out.println(x.getName() + ", distance = " + x.getDistance() + ", quality =  "+ x.getQuality());

        /*
        beach4, distance = 20.0, quality =  6
        beach3, distance = 30.0, quality =  3
        beach1, distance = 44.0, quality =  3
        beach2, distance = 19.0, quality =  2
        beach5, distance = 22.0, quality =  2
        */
    }

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public int compareTo(Beach o)
    {
        if (o == null) throw new NullPointerException();
        int distanceParam = (int) (this.getDistance() - o.getDistance());
        int qualityParam = this.getQuality() - o.getQuality();
        return -100 * qualityParam + distanceParam;
    }
}
