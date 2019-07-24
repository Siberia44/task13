package task4.web.threads.httpfactory;

import task4.web.server.AbstractCommand;
import task4.web.threads.AbstractFactory;
import task4.web.threads.httpfactory.thread.HttpHandlerThread;

import java.net.Socket;
import java.util.Map;

public class HttpFactory implements AbstractFactory {

    @Override
    public Thread createHandleThread(Socket socket, Map<String, AbstractCommand> commands) {
        return new HttpHandlerThread(socket, commands);
    }


}
