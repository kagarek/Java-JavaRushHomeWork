package solved.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {

    public static Integer[] sort(Integer[] array) {
        //implement logic here

        if (array == null) return null;
        for (int i = 0; i < array.length; i++)
            if (array[i] == null) return null;

        Arrays.sort(array);

        final double median;

        if (array.length % 2 == 0)
            median = (array[array.length / 2 - 1] + array[array.length / 2]) / 2d;
        else
            median = array[array.length / 2];

        //System.out.println(median);

        Arrays.sort(array, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result = (int) (Math.abs(o1 - median) - Math.abs(o2 - median));
                return result == 0 ? o1 - o2 : result;
            }
        });

        return array;
    }

    public static void main(String[] args)
    {
        //Integer[] array = new Integer[] {1,2,5,7,8,11,15}; // 7 8 5 11 2 1 15
        Integer[] array = new Integer[] {2, 5, 6, 7, 21, 1};
        //Integer[] array = new Integer[] {3, 4, 7, 6, 5, 2, 1, 11, 5, 48, 49, 56, 92, 94};
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");

        System.out.println();
        Integer[] new_array = sort(array);

        for (int i = 0; i < new_array.length; i++)
            System.out.print(new_array[i] + " ");
    }
}
