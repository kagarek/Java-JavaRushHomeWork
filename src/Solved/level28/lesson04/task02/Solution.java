package solved.level28.lesson04.task02;

import java.util.concurrent.ThreadLocalRandom;

/* ThreadLocalRandom
Класс Solution будет использоваться трэдами.
Реализуйте логику всех методов, используйте класс ThreadLocalRandom.
getRandomIntegerBetweenNumbers должен возвращать случайный int между from и to
getRandomDouble должен возвращать случайный double
getRandomLongBetween0AndN должен возвращать случайный long между 0 и n
*/
public class Solution {

    static ThreadLocalRandom threadLocalRandom;

    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        return threadLocalRandom.current().nextInt(from,to);
    }

    public static double getRandomDouble() {
        return threadLocalRandom.current().nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        return threadLocalRandom.current().nextLong(n);
    }
}
