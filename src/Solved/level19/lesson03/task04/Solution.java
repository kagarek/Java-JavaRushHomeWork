package solved.level19.lesson03.task04;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("resources/level19_lesson03_task04.txt"));
        PersonScanner adapter = new PersonScannerAdapter(s);
        System.out.println(adapter.read());
        adapter.close();
    }

    public static class PersonScannerAdapter implements PersonScanner
    {
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            String s = "";

            if (scanner.hasNextLine())
            {
                s = scanner.nextLine();
                String[] temp = s.split(" ");

                Calendar calendar = new GregorianCalendar(Integer.parseInt(temp[5]), Integer.parseInt(temp[4])-1, Integer.parseInt(temp[3]));
                Date date = calendar.getTime();

                Person person = new Person(temp[1], temp[2], temp[0], date);

                return person;

            }

            return null;
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }
}
