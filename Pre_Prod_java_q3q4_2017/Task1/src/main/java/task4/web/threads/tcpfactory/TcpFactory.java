package task4.web.threads.tcpfactory;

import task4.web.server.AbstractCommand;
import task4.web.threads.AbstractFactory;
import task4.web.threads.tcpfactory.thread.TcpHandlerThread;

import java.net.Socket;
import java.util.Map;

public class TcpFactory implements AbstractFactory {

    @Override
    public Thread createHandleThread(Socket socket, Map<String, AbstractCommand> commands) {
        return new TcpHandlerThread(socket, commands);
    }
}
