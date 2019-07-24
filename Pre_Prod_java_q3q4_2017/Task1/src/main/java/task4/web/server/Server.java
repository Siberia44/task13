package task4.web.server;

import task4.web.threads.AbstractFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;


public class Server implements Runnable {
    private AbstractFactory abstractFactory;
    private int port;
    private Map<String, AbstractCommand> abstractCommandMap;

    public Server(int port, AbstractFactory abstractFactory,
                  Map<String, AbstractCommand> abstractCommandMap) {
        this.abstractFactory = abstractFactory;
        this.port = port;
        this.abstractCommandMap = abstractCommandMap;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                abstractFactory.createHandleThread(socket, abstractCommandMap).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
