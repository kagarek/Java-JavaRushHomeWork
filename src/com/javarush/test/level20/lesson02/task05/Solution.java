package com.javarush.test.level20.lesson02.task05;

/* И еще раз о синхронизации
Реализуйте логику записи в файл и чтения из файла для класса Object
Метод load должен инициализировать объект данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/

import java.io.*;

public class Solution
{
    public static void main(java.lang.String[] args)
    {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = new File("resources/level20_lesson02_task05.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Object object = new Object();
            object.string1 = null;   //string #1
            object.string2 = new String();   //string #2
            object.save(outputStream);
            outputStream.flush();

            Object loadedObject = new Object();
            loadedObject.string1 = new String(); //string #3
            loadedObject.string2 = new String(); //string #4


            loadedObject.load(inputStream);
            //check here that the object variable equals to loadedObject - проверьте тут, что object и loadedObject равны

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

    public static class Object {
        public String string1;
        public String string2;

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод

            PrintWriter printWriter = new PrintWriter(outputStream);

            if (string1 != null)
                printWriter.println(string1.number);
            else
                printWriter.println("empty");

            if (string2 != null)
                printWriter.println(string2.number);
            else
                printWriter.println("empty");

            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод

            /*1) Если у записываемого объекта string1 null, а string2 не null,
                    то у загруженного объекта должно быть точно так же. Проверьте и другие комбинации.
            3) countStrings не должен измениться после выполнения загрузки объектов
                    (вот это реально телепатия, без форума в жизни бы не догадался).
            4) если записать в файл стринги с индексом 3 и 4 (например),
                    то загрузиться должны стринги с этими же индексами, а не, например, 1 и 2*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            java.lang.String s;
            int k = countStrings;

            if ((s = reader.readLine())!=null)
            {
                if (!s.equals("empty"))
                {
                    countStrings = Integer.parseInt(s)-1;
                    string1 = new String();
                }
                else
                {
                    string1 = null;
                }
            }

            if ((s = reader.readLine())!=null)
            {
                if (!s.equals("empty"))
                {
                    countStrings = Integer.parseInt(s)-1;
                    string2 = new String();
                }
                else
                {
                    string2 = null;
                }
            }

            countStrings = k;

            reader.close();
        }
    }

    public static int countStrings;

    public static class String
    {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
