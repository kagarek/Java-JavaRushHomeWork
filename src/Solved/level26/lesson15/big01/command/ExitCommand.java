package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.CashMachine;
import solved.level26.lesson15.big01.ConsoleHelper;
import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit");
    
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (answer.equalsIgnoreCase(res.getString("yes")))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
