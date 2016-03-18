package solved.level26.lesson15.big01;

import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by igormakarychev on 6/1/15.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String s = null;
        try
        {
            s = reader.readLine();
        }
        catch (IOException e) {}

        if (s.trim().equalsIgnoreCase(res.getString("operation.EXIT")))
            throw new InterruptOperationException();

        return s;
    }

    public static void printExitMessage()
    {
        writeMessage(res.getString(res.getString("the.end")));
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        String s = readString();

        if (s.length()!=3)
        {
            writeMessage(res.getString("invalid.data"));
            s = askCurrencyCode();
        }

        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String s = readString();
        String[] ss;

        try
        {
            ss = s.split(" ");
            if (ss.length == 2)
            {
                int nom = Integer.parseInt(ss[0]);
                int count = Integer.parseInt(ss[1]);
            }
            else throw new Exception();
        }
        catch (Exception e)
        {
            writeMessage(res.getString("invalid.data"));
            ss = getValidTwoDigits(currencyCode);
        }

        return ss;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.operation"));

        String s = readString();
        Operation operation;

        try
        {
            operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
        }
        catch (Exception e)
        {
            writeMessage(res.getString("invalid.data"));
            operation = askOperation();
        }

        return operation;
    }
}
