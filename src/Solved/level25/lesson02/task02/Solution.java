package solved.level25.lesson02.task02;

import java.util.LinkedList;
import java.util.List;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {

    public static void main(String[] args)
    {
        Car car = new Car();
    }

    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            try
            {
                String[] s_wheels = loadWheelNamesFromDB();
                wheels = new LinkedList<>();
                Wheel[] enum_list = Wheel.values();

                if (s_wheels.length != 4 || enum_list.length != 4) throw new IllegalArgumentException();

                for (int i = 0; i < s_wheels.length; i++)
                {
                    for (int j = 0; j < enum_list.length; j++)
                    {
                        if (s_wheels[i].equals(enum_list[j].toString()))
                            this.wheels.add(Wheel.valueOf(s_wheels[i]));
                    }
                }

                if (wheels.size() != 4) throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException i)
            {
                System.out.print("Это не машина");
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
