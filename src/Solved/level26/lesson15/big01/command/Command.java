package solved.level26.lesson15.big01.command;

import solved.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by igormakarychev on 6/2/15.
 */
interface Command
{
    void execute() throws InterruptOperationException;
}

