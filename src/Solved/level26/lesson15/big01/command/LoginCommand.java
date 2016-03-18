package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.CashMachine;
import solved.level26.lesson15.big01.ConsoleHelper;
import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by igormakarychev on 6/6/15.
 */
public class LoginCommand implements Command
{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException
    {

        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        while (true)
        {

            String answerCCN = ConsoleHelper.readString();
            String answerPIN = ConsoleHelper.readString();

            if (validCreditCards.containsKey(answerCCN) && validCreditCards.getString(answerCCN).equals(answerPIN))
            {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),answerCCN));
                break;
            }
            else
            {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
