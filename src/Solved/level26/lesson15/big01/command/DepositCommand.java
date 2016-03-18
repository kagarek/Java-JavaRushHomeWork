package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.CashMachine;
import solved.level26.lesson15.big01.ConsoleHelper;
import solved.level26.lesson15.big01.CurrencyManipulator;
import solved.level26.lesson15.big01.CurrencyManipulatorFactory;
import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by igormakarychev on 6/2/15.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String[] nom_count = ConsoleHelper.getValidTwoDigits(currencyCode);
        int currentNominal = 0;
        int currentBanknotCount = 0;

        try
        {
            currentNominal = Integer.parseInt(nom_count[0]);
            currentBanknotCount = Integer.parseInt(nom_count[1]);
        }
        catch (NumberFormatException e)
        {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }

        currencyManipulator.addAmount(currentNominal, currentBanknotCount);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), currentBanknotCount*currentNominal,currencyCode));
    }
}
