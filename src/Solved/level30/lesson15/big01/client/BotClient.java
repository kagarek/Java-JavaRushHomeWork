package solved.level30.lesson15.big01.client;

import solved.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

//18.1.	Создай новый класс BotClient в пакете client. Он должен быть унаследован от Client.
public class BotClient extends Client
{
    private static int nextBotId = 0;

    // 18.2.	В классе BotClient создай внутренний класс BotSocketThread унаследованный от
    // SocketThread. Класс BotSocketThread должен быть публичным.
    public class BotSocketThread extends Client.SocketThread
    {

        //19.1.	Переопредели метод clientMainLoop():
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            // 19.1.1.	С помощью метода sendTextMessage() отправь сообщение с текстом
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            // 19.1.2.	Вызови реализацию clientMainLoop() родительского класса.
            super.clientMainLoop();
        }

        // 19.2.	Переопредели метод processIncomingMessage(String message).
        protected void processIncomingMessage(String message) {

            // 19.2.1.	Вывести в консоль текст полученного сообщения message.
            ConsoleHelper.writeMessage(message);


            try
            {
                // 19.2.2.	Получить из message имя отправителя и текст сообщения. Они разделены ": ".
                String[] data = message.split(": ");
                String senderName = data[0]; //message.substring(0, message.indexOf(": "));
                String senderMessage = data[1]; //message.substring(message.indexOf(": ")+2, message.length());

                // Ответ должен содержать имя клиента, который прислал запрос и ожидает ответ,
                // например, если Боб отправил запрос "время", мы должны отправить ответ "Информация для Боб: 12:30:47".
                String outgoingMessage = "Информация для " + senderName + ": ";

                // Указанный выше формат используй для создания объекта SimpleDateFormat. Для
                // получения текущей даты необходимо использовать класс Calendar и метод getTime().
                Date date = new GregorianCalendar().getTime();

                // 19.2.3.	Отправить ответ в зависимости от текста принятого сообщения. Если текст сообщения:
                switch (senderMessage) {
                    // "дата" – отправить сообщение содержащее текущую дату в формате "d.MM.YYYY";
                    case "дата" : sendTextMessage(outgoingMessage + new SimpleDateFormat("d.MM.YYYY").format(date)); break;
                    // "день" – в формате"d";
                    case "день" : sendTextMessage(outgoingMessage + new SimpleDateFormat("d").format(date)); break;
                    // "месяц" - "MMMM";
                    case "месяц" : sendTextMessage(outgoingMessage + new SimpleDateFormat("MMMM").format(date)); break;
                    // "год" - "YYYY";
                    case "год" : sendTextMessage(outgoingMessage + new SimpleDateFormat("YYYY").format(date)); break;
                    // "время" - "H:mm:ss";
                    case "время" : sendTextMessage(outgoingMessage + new SimpleDateFormat("H:mm:ss").format(date)); break;
                    // "час" - "H";
                    case "час" : sendTextMessage(outgoingMessage + new SimpleDateFormat("H").format(date)); break;
                    // "минуты" - "m";
                    case "минуты" : sendTextMessage(outgoingMessage + new SimpleDateFormat("m").format(date)); break;
                    // "секунды" - "s".
                    case "секунды" : sendTextMessage(outgoingMessage + new SimpleDateFormat("s").format(date)); break;
                    default: break;
                }
            }
            catch (Exception e) {}
        }
    }

    // 18.3.	Переопредели методы:
    // 18.3.1.	getSocketThread(). Он должен создавать и возвращать объект класса BotSocketThread.
    protected SocketThread getSocketThread() { return new BotSocketThread(); }

    // 18.3.2.	shouldSentTextFromConsole(). Он должен всегда возвращать false. Мы не хотим,
    // чтобы бот отправлял текст введенный в консоль.
    protected boolean shouldSentTextFromConsole() { return false; }

    // 18.3.3.	getUserName(), метод должен генерировать новое имя бота, например:
    // date_bot_XX, где XX – любое число от 0 до 99. Этот метод должен возвращать
    // каждый раз новое значение, на случай, если на сервере захотят
    // зарегистрироваться несколько ботов, у них должны быть разные имена.
    protected String getUserName() {
        String newName = "date_bot_" + nextBotId;
        if (nextBotId == 99) nextBotId = 0;
        else nextBotId++;
        return newName;
    }

    // 18.4.	Добавь метод main. Он должен создавать новый объект BotClient и вызывать у
    // него метод run().
    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
