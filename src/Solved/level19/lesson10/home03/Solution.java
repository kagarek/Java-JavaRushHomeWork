package solved.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException
    {

        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));

        String line;

        while ((line=fileReader.readLine())!=null)
        {
            String[] words = line.split(" ");
            int day = Integer.parseInt(words[words.length-3]);
            int month = Integer.parseInt(words[words.length-2]);
            int year = Integer.parseInt(words[words.length-1]);
            String name = "";

            for (int i=0; i < words.length-3;i++)
            {
                name = name + words[i] + " ";
            }

            name = name.substring(0, name.length()-1);

            Calendar cal = new GregorianCalendar(year,month-1,day);
            Date birthday = cal.getTime();

            PEOPLE.add(new Person(name,birthday));
        }

        fileReader.close();

    }

}
