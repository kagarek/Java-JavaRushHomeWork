package solved.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Igor_Makarychev on 10.11.2015.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String name;

            while (true)
            {
                Message messageNameRequest = new Message(MessageType.NAME_REQUEST);
                connection.send(messageNameRequest);
                Message messageNameResponse = connection.receive();

                if (messageNameResponse.getType() == MessageType.USER_NAME)
                {
                    name = messageNameResponse.getData();
                    if (name != null && !name.equals("") && !connectionMap.containsKey(name)) break;
                }
            }

            connectionMap.put(name, connection);

            Message messageNameAccepted = new Message(MessageType.NAME_ACCEPTED);
            connection.send(messageNameAccepted);

            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> loop : connectionMap.entrySet()) {
                if (!userName.equals(loop.getKey())) {
                    connection.send(new Message(MessageType.USER_ADDED, loop.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            // 10.5.	 Организовать бесконечный цикл, внутрь которого перенести функционал пунктов 10.1-10.4.
            while (true)
            {
                // 10.1.	 Принимать сообщение клиента
                Message incomingMessage = connection.receive();

                // 10.2.	 Если принятое сообщение – это текст (тип TEXT), то
                if (incomingMessage.getType().equals(MessageType.TEXT))
                {
                    // формировать новое
                    // текстовое сообщение путем конкатенации: имени клиента, двоеточия, пробела и
                    // текста сообщения. Например, если мы получили сообщение с текстом "привет чат" от
                    // пользователя "Боб", то нужно сформировать сообщение "Боб: привет чат".
                    Message outgoingMessage = new Message(MessageType.TEXT, userName + ": " + incomingMessage.getData());

                    // 10.3.	 Отправлять сформированное сообщение всем клиентам с помощью метода sendBroadcastMessage.
                    sendBroadcastMessage(outgoingMessage);
                } else
                {
                    // 10.4.	 Если принятое сообщение не является текстом, вывести сообщение об ошибке
                    ConsoleHelper.writeMessage("Ошибка!");
                }
            }
        }

        public void run()
        {
            // 11.1.	Выводить сообщение, что установлено новое соединение с удаленным
            // адресом, который можно получить с помощью метода getRemoteSocketAddress
            ConsoleHelper.writeMessage("Установлено новое соединение с " + socket.getRemoteSocketAddress());
            String name = null;

            try (
                Socket socketTMP = socket;
                Connection connection = new Connection(socketTMP);)
            {
                // 11.3.	Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента
                name = serverHandshake(connection);

                // 11.4.	Рассылать всем участникам чата информацию об имени присоединившегося
                // участника (сообщение с типом USER_ADDED). Подумай, какой метод подойдет для
                // этого лучше всего.
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));

                // 11.5.	Сообщать новому участнику о существующих участниках
                sendListOfUsers(connection, name);

                // 11.6.	Запускать главный цикл обработки сообщений сервером
                serverMainLoop(connection,name);

                //11.7.	Обеспечить закрытие соединения при возникновении исключения
                connection.close();
                socket.close();
            }
            catch (IOException e)
            {

                // 11.8.	Отловить все исключения типа IOException и ClassNotFoundException, вывести в
                // консоль информацию, что произошла ошибка при обмене данными с удаленным адресом
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            }
            catch (ClassNotFoundException e)
            {
                // 11.8.	Отловить все исключения типа IOException и ClassNotFoundException, вывести в
                // консоль информацию, что произошла ошибка при обмене данными с удаленным адресом
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом!");
            }

            // 11.9.	После того как все исключения обработаны, если п.11.3 отработал и возвратил
            // нам имя, мы должны удалить запись для этого имени из connectionMap и разослать
            // всем остальным участникам сообщение с типом USER_REMOVED и сохраненным именем.
            if (name != null)
            {
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,name));
            }

            // 11.10.	Последнее, что нужно сделать в методе run() – вывести сообщение,
            // информирующее что соединение с удаленным адресом закрыто.
            // Наш сервер полностью готов. Попробуй его запустить.
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
        }
    }

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int serverPort = ConsoleHelper.readInt();

        try (
                ServerSocket serverSocket = new ServerSocket(serverPort)
        )
        {
            ConsoleHelper.writeMessage("Сервер запущен.");

            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException io)
        {
            ConsoleHelper.writeMessage("Ошибка!");
        }
        catch (Exception e)
        {
            ConsoleHelper.writeMessage("Ошибка!");
        }
    }

    public static void sendBroadcastMessage(Message message)
    {
        for (ConcurrentHashMap.Entry<String, Connection> entry : connectionMap.entrySet())
        {
            try
            {
                entry.getValue().send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение");
            }
        }
    }
}