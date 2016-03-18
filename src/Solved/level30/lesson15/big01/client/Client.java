package solved.level30.lesson15.big01.client;

import solved.level30.lesson15.big01.Connection;
import solved.level30.lesson15.big01.ConsoleHelper;
import solved.level30.lesson15.big01.Message;
import solved.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by igormakarychev on 11/11/15.
 */
public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread
    {
        public void run()
        {
            // 17.1
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();

            try
            {
                // 17.2
                Socket socket = new Socket(serverAddress,serverPort);
                // 17.3
                connection = new Connection(socket);
                // 17.4
                clientHandshake();
                // 17.5
                clientMainLoop();
            }
            catch (IOException e)
            {
                // 17.6
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e)
            {
                // 17.6
                notifyConnectionStatusChanged(false);
            }
        }

        // 15.1
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        // 15.2
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем "+userName+" присоединился к чату");
        }

        // 15.3
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем "+userName+" покинул чат");
        }

        // 15.4
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            // 15.4.1
            Client.this.clientConnected = clientConnected;
            // 15.4.2
            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }


        // 16.1
        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            // 16.1.1
            while (true)
            {
                Message incomingMessage = connection.receive();
                switch (incomingMessage.getType())
                {
                    case NAME_REQUEST: {
                        // 16.1.2
                        String name = getUserName();
                        Message outgoingMessage = new Message(MessageType.USER_NAME, name);
                        connection.send(outgoingMessage);
                        break;
                    }
                    case NAME_ACCEPTED: {
                        // 16.1.3
                        notifyConnectionStatusChanged(true);
                        return;
                    }
                    default: {
                        // 16.1.4
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }

        // 16.2
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            // 16.2.6
            while (true)
            {
                // 16.2.1
                Message message = connection.receive();

                switch (message.getType()){
                    // 16.2.2
                    case TEXT: processIncomingMessage(message.getData()); break;
                    // 16.2.3
                    case USER_ADDED: informAboutAddingNewUser(message.getData()); break;
                    // 16.2.4
                    case USER_REMOVED: informAboutDeletingNewUser(message.getData()); break;
                    // 16.2.5
                    default: throw new IOException("Unexpected MessageType");
                }
            }
        }
    }

    // 13.1
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес серера:");
        String address = ConsoleHelper.readString();
        return address;
    }

    // 13.2
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        return port;
    }

    // 13.3
    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        String name = ConsoleHelper.readString();
        return name;
    }

    // 13.4
    protected boolean shouldSentTextFromConsole() {
        return true;
    }

    // 13.5
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    // 13.6
    protected void sendTextMessage(String text) {
        Message message = new Message(MessageType.TEXT, text);
        try
        {
            connection.send(message);
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
            clientConnected = false;
        }
    }

    // 14.1
    public void run()
    {
        // 14.1.1
        SocketThread socketThread = getSocketThread();
        // 14.1.2
        socketThread.setDaemon(true);
        // 14.1.3
        socketThread.start();
        // 14.1.4
        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("Ошибка! Выполнение программы прервано.");
            return;
        }

        // 14.1.5
        if (clientConnected) ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

        // 14.1.6
        while (clientConnected)
        {
            String input = ConsoleHelper.readString();

            if (input.equals("exit")) clientConnected = false;
            else
            {
                // 14.1.7
                if (shouldSentTextFromConsole()) sendTextMessage(input);
            }
        }
    }

    public static void main(String[] args)
    {
        // 14.2
        Client client = new Client();
        client.run();
    }
}
