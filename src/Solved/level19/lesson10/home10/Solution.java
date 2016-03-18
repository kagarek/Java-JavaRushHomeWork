package solved.level19.lesson10.home10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* Исправить ошибку
Программа содержит всего 1 ошибку. Найди и исправь ее
*/

public class Solution {
    {
        System.out.println("it's Solution class");
    }

    public static void main(String... args) throws IOException {
        try (
                FileOutputStream outputStream = new FileOutputStream("resources\\level19_lesson10_home10_output.txt");
                InputStream is = Solution.class.getClassLoader().getResourceAsStream("resources\\level19_lesson10_home10.gif");
        ) {
            ;
            byte[] b = new byte[is.available()];
            outputStream.write(is.read(b));

            int value = 123_456_789;
            System.out.println(value);

            Example result = null;
            String s = "a";
            switch (s) {
                case "a": {
                    result = new Solution().new A();
                    break;
                }
                case "b": {
                    result = new Solution().new B();
                    break;
                }
                case "c": {
                    result = new Solution().new C();
                    break;
                }
            }

            if (result instanceof C) {
                C p = (C) result;
                System.out.println(p.getClass().getSimpleName());
            }

        } catch (IOException e) {
        }
    }

    interface Example {
    }

    class A implements Example {
        {
            System.out.println("it's A class");
        }
    }

    class B implements Example {
        {
            System.out.println("it's B class");
        }
    }

    class C extends A {
        {
            System.out.println("it's C class");
        }
    }
}
