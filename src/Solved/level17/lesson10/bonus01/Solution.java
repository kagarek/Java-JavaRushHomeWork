package solved.level17.lesson10.bonus01;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут

        String command = args[0];
        String name;
        Sex sex;
        Date birthday;
        int id;
        Person person;

        DateFormat parseDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        if (command.equals("-c"))
        {
            name = args[1];
            sex = args[2] == "м" ? Sex.MALE : Sex.FEMALE;
            birthday = parseDateFormat.parse(args[3]);

            if (sex == Sex.MALE)
            {
                person = Person.createMale(name, birthday);
            }
            else
            {
                person = Person.createFemale(name, birthday);
            }

            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }
        else
        {
            if (command.equals("-u"))
            {
                id = Integer.parseInt(args[1]);
                name = args[2];
                sex = args[3] == "м" ? Sex.MALE : Sex.FEMALE;

                birthday = parseDateFormat.parse(args[4]);

                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(sex);
                allPeople.get(id).setBirthDay(birthday);
            }
            else
            {
                if (command.equals("-d"))
                {
                    id = Integer.parseInt(args[1]);
                    allPeople.get(id).setName(null);
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDay(null);
                }
                else
                {
                    if (command.equals("-i"))
                    {
                        id = Integer.parseInt(args[1]);
                        person = allPeople.get(id);
                        System.out.println(
                                person.getName() + " " +
                                (person.getSex() == Sex.MALE ? "м" : "ж") + " " +
                                outputDateFormat.format(person.getBirthDay()));
                    }
                }
            }
        }
    }
}
