package solved.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {

    public static void main(String[] args)
    {
        getTokens("level22.lesson13.task01.", ".");
    }

    public static String [] getTokens(String query, String delimiter) {

        if (query == null || delimiter == null)
        {
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(query,delimiter);
        while (stringTokenizer.hasMoreTokens())
        {
            arrayList.add(stringTokenizer.nextToken());
        }

        String[] result = new String[arrayList.size()];
        arrayList.toArray(result);

        return result;
    }
}
