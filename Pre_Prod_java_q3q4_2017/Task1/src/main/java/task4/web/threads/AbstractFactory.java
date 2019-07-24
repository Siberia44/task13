package task4.web.threads;

import task4.web.server.AbstractCommand;

import java.net.Socket;
import java.util.Map;

public interface AbstractFactory {

    Thread createHandleThread(Socket socket, Map<String, AbstractCommand> commands);

}
