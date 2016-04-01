package Solved.level36.lesson04.home01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class Service
{
    public List<String> getData() {
        List<String> data = new ArrayList<String>() {{
            add("First string");
            add("Second string");
            add("Third string");
        }};
        return data;
    }
}
