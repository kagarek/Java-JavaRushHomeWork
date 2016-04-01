package Solved.level36.lesson04.big01.view;

import Solved.level36.lesson04.big01.controller.Controller;
import Solved.level36.lesson04.big01.model.ModelData;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public interface View
{
    void refresh(ModelData modelData);
    void setController(Controller controller);
}
