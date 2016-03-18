package solved.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {

        Charset win1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");

        /*String fileName_input = args[0];
        String fileName_output = args[1];*/

        String fileName_input = "resources/level22_lesson13_task03_input.txt";
        String fileName_output = "resources/level22_lesson13_task03_output.txt";

        try (
                FileInputStream fileInputStream = new FileInputStream(fileName_input);
                FileOutputStream fileOutputStream = new FileOutputStream(fileName_output)
            )
        {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);

            String s_utf8 = new String(bytes,utf8);
            String s_win1251 = new String(s_utf8.getBytes(win1251));
            fileOutputStream.write(s_win1251.getBytes());
        }
        catch (Exception e)
        {
        }
    }
}
