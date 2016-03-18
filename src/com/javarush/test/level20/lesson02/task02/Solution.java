package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = new File("resources/level20_lesson02_task02.tmp");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            User user1 = new User();

            user1.setFirstName("Igor");
            user1.setLastName("Makar");
            user1.setCountry(User.Country.UKRAINE);

            Date user1BirthDate = formatter.parse("12/09/1985");
            user1.setBirthDate(user1BirthDate);

            user1.setMale(true);

            User user2 = new User();

            user2.setFirstName("Nat");
            user2.setLastName("Makar");
            user2.setCountry(User.Country.UKRAINE);

            Date user2BirthDate = formatter.parse("24/06/1985");
            user2.setBirthDate(user2BirthDate);
            user2.setMale(false);

            javaRush.users.add(user1);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод

            PrintWriter printWriter = new PrintWriter(outputStream);

            printWriter.println(users.size());

            for (int i = 0; i < users.size(); i++)
            {
                printWriter.println(users.get(i).getFirstName());
                printWriter.println(users.get(i).getLastName());
                printWriter.println(users.get(i).getBirthDate());
                printWriter.println(users.get(i).isMale());
                printWriter.println(users.get(i).getCountry().getDisplayedName());
            }

            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int countUsers = Integer.parseInt(reader.readLine());

            for (int i = 0; i < countUsers; i++)
            {
                User user = new User();

                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());

                String stringBirthDate = reader.readLine();
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date birthDate = formatter.parse(stringBirthDate);
                user.setBirthDate(birthDate);

                user.setMale(Boolean.parseBoolean(reader.readLine()));

                String stringCountry = reader.readLine();

                if ("Ukraine".equals(stringCountry))
                    user.setCountry(User.Country.UKRAINE);
                else
                    if ("Russia".equals(stringCountry))
                        user.setCountry(User.Country.RUSSIA);
                    else user.setCountry(User.Country.OTHER);

                users.add(user);
            }

            reader.close();
        }
    }
}
