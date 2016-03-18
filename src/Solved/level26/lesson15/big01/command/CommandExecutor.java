package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.Operation;
import solved.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by igormakarychev on 6/2/15.
 */
public class CommandExecutor
{
    private CommandExecutor(){}

    private static Map<Operation, Command> operationCommandMap;

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        operationCommandMap.get(operation).execute();
    }

    static
    {
        operationCommandMap = new HashMap<>();
        operationCommandMap.put(Operation.LOGIN, new LoginCommand());
        operationCommandMap.put(Operation.DEPOSIT, new DepositCommand());
        operationCommandMap.put(Operation.INFO, new InfoCommand());
        operationCommandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        operationCommandMap.put(Operation.EXIT, new ExitCommand());
    }
}
