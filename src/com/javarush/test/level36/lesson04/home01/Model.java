package com.javarush.test.level36.lesson04.home01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class Model
{
    private Service service = new Service();

    public List<String> getStringDataList() {
        return service.getData();
    }
    }
