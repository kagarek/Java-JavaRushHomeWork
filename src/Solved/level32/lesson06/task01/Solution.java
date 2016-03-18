package solved.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args)
    {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Random r = new Random();

        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        boolean d = false;
        boolean cUC = false;
        boolean cLC = false;

        for (int i = 0; i < 8; i++)
        {
            int k = r.nextInt(alphabet.length());

            if (k < 10) d = true;
            if (k > 9 && k < 36) cUC = true;
            if (k > 35) cLC = true;

            byteArrayOutputStream.write(alphabet.charAt(k));
        }

        if (!d || !cLC || !cUC)
        {
            byteArrayOutputStream = getPassword();
        }

        return byteArrayOutputStream;
    }
}
