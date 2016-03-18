package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.CashMachine;
import solved.level26.lesson15.big01.ConsoleHelper;
import solved.level26.lesson15.big01.CurrencyManipulator;
import solved.level26.lesson15.big01.CurrencyManipulatorFactory;
import solved.level26.lesson15.big01.exception.InterruptOperationException;
import solved.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true)
        {
            try
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String answer = ConsoleHelper.readString();
                int amount = Integer.parseInt(answer);

                if (currencyManipulator.isAmountAvailable(amount))
                {
                    Map<Integer,Integer> withdrawMap = currencyManipulator.withdrawAmount(amount);
                    StringBuilder stringBuilder = new StringBuilder();

                    for (Map.Entry<Integer,Integer> map : withdrawMap.entrySet())
                        stringBuilder.append("\t" + map.getKey() + " - " + map.getValue());

                    ConsoleHelper.writeMessage(stringBuilder.toString());
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(answer), currencyCode));
                    break;
                }
                else
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
            catch (NumberFormatException nfe)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            catch (NotEnoughMoneyException nem)
            {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
