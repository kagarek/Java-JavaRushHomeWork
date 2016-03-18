package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.CashMachine;
import solved.level26.lesson15.big01.ConsoleHelper;
import solved.level26.lesson15.big01.CurrencyManipulator;
import solved.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command
{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));

        Collection<CurrencyManipulator> allManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if(allManipulators.size() <= 0)
        {
            ConsoleHelper.writeMessage(res.getString("no.money"));
            return;
        }

        for(CurrencyManipulator currencyManipulator : allManipulators)
            if(currencyManipulator.hasMoney())
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            else
                ConsoleHelper.writeMessage(res.getString("no.money"));

    }
}
