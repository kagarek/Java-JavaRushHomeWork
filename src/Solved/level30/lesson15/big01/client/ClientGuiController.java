package solved.level30.lesson15.big01.client;

/**
 * Created by Igor_Makarychev on 17.11.2015.
 */
// 21.1.	Создай класс ClientGuiController унаследованный от Client.
public class ClientGuiController extends Client
{
    // 21.2.	Создай и проинициализируй поле, отвечающее за модель ClientGuiModel model.
    private ClientGuiModel model = new ClientGuiModel();

    // 21.3.	Создай и проинициализируй поле, отвечающее за представление ClientGuiView
    // view. Подумай, что нужно передать в конструктор при инициализации объекта.
    private ClientGuiView view = new ClientGuiView(this);

    // 21.4.	Добавь внутренний класс GuiSocketThread унаследованный от SocketThread.
    // Класс GuiSocketThread должен быть публичным. В нем переопредели следующие методы:
    public class GuiSocketThread extends Client.SocketThread
    {
        protected void processIncomingMessage(String message) {
            // 21.4.1.	void processIncomingMessage(String message) – должен устанавливать новое
            // сообщение у модели и вызывать обновление вывода сообщений у представления.
            model.setNewMessage(message);
            view.refreshMessages();
        }

        protected void informAboutAddingNewUser(String userName)
        {
            // 21.4.2.	void informAboutAddingNewUser(String userName) – должен добавлять нового
            // пользователя в модель и вызывать обновление вывода пользователей у отображения.
            model.addUser(userName);
            view.refreshUsers();
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            // 21.4.3.	void informAboutDeletingNewUser(String userName) – должен удалять
            // пользователя из модели и вызывать обновление вывода пользователей у отображения.
            model.deleteUser(userName);
            view.refreshUsers();
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            // 21.4.4. void notifyConnectionStatusChanged(boolean clientConnected) – должен вызывать
            // аналогичный метод у представления.
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

    // 21.5. Переопредели методы в классе ClientGuiController:
    protected SocketThread getSocketThread() {
        // 21.5.1.	SocketThread getSocketThread() – должен создавать и возвращать объект типа GuiSocketThread.
        return new GuiSocketThread();
    }

    public void run()
    {
        // 21.5.2. void run() – должен получать объект SocketThread через метод getSocketThread()
        // и вызывать у него метод run(). Разберись, почему нет необходимости вызывать
        // метод run в отдельном потоке, как мы это делали для консольного клиента.
        getSocketThread().run();
    }

    // 21.5.3. getServerAddress(), getServerPort(),getUserName(). Они должны вызывать
    // одноименные методы из представления (view).
    protected String getServerAddress() { return view.getServerAddress(); }
    protected int getServerPort() { return view.getServerPort(); }
    protected String getUserName() { return view.getUserName(); }


    // 21.6. Объяви метод ClientGuiModel getModel(), который должен возвращать модель.
    public ClientGuiModel getModel() {return model;}

    // 21.7. Объяви метод main(), который должен создавать новый объект
    // ClientGuiController и вызывать у него метод run().
    public static void main(String[] args)
    {
        new ClientGuiController().run();
    }
}
