package Solved.level36.lesson04.home01;

import java.util.List;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class Controller
{
    private Model model = new Model();

    public List<String> onDataListShow() {
        return model.getStringDataList();
    }
}