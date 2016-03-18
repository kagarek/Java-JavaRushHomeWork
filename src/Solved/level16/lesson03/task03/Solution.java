package solved.level16.lesson03.task03;

import java.util.ArrayList;
import java.util.List;

/* Список и нити
В методе main добавить в статический объект list 5 нитей SpecialThread - различных объектов.
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        //Add your code here - добавьте свой код тут

        Thread specialThread1 = new Thread(new SpecialThread());
        Thread specialThread2 = new Thread(new SpecialThread());
        Thread specialThread3 = new Thread(new SpecialThread());
        Thread specialThread4 = new Thread(new SpecialThread());
        Thread specialThread5 = new Thread(new SpecialThread());

        list.add(specialThread1);
        list.add(specialThread2);
        list.add(specialThread3);
        list.add(specialThread4);
        list.add(specialThread5);
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's run method inside SpecialThread");
        }
    }
}
