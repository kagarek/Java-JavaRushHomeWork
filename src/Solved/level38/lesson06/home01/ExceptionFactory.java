package Solved.level38.lesson06.home01;

/**
 * Created by Igor_Makarychev on 17.05.2016.
 */
public class ExceptionFactory
{
    public static Throwable getException(Enum message)
    {
        if (message == null) return new IllegalArgumentException();
        else
        {
            String msg = message.toString().toLowerCase().replaceAll("_", " ");
            msg = msg.substring(0, 1).toUpperCase() + msg.substring(1);

            if (message instanceof ExceptionApplicationMessage)
                return new Exception(msg);
            else if (message instanceof ExceptionDBMessage)
                return new RuntimeException(msg);
            else if (message instanceof ExceptionUserMessage)
                return new Error(msg);
            else
                return new IllegalArgumentException();
        }
    }
}