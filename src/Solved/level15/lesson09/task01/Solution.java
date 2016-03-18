package solved.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static
    {
        labels.put(2d,"2d");
        labels.put(3d,"3d");
        labels.put(4d,"4d");
        labels.put(5d,"5d");
        labels.put(6d,"6d");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
