package solved.level26.lesson15.big01;

import solved.level26.lesson15.big01.command.CommandExecutor;
import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by igormakarychev on 6/1/15.
 */
public class CashMachine
{

    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";

    public static void main(String[] args)
    {
        try
        {
            Locale.setDefault(Locale.GERMANY);
            CommandExecutor.execute(Operation.LOGIN);

            Operation operation;
            do
            {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (!operation.equals(Operation.EXIT));
        }
        catch (InterruptOperationException ioe)
        {
            ConsoleHelper.printExitMessage();
        }
    }
}
