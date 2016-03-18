package solved.level18.lesson10.bonus01;

/**
 * Created by igormakarychev on 2/5/15.
 */
public class RunSolution
{
    public static void main(String[] args)
    {
        try
        {
            String[] argsEncrypt = {"-e", "resources/level18_lesson10_bonus01_InitialFileToEncrypt.txt", "resources/level18_lesson10_bonus01_EncryptedFile.txt"};
            String[] argsDecrypt = {"-d", "resources/level18_lesson10_bonus01_EncryptedFile.txt", "resources/level18_lesson10_bonus01_DecryptedFile.txt"};

            Solution.main(argsEncrypt);
            Solution.main(argsDecrypt);
        }
        catch (Exception e)
        {

        }
    }
}
