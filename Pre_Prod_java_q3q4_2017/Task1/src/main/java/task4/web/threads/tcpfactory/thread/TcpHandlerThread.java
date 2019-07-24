package task4.web.threads.tcpfactory.thread;

import task4.web.server.AbstractCommand;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class TcpHandlerThread extends Thread {
    private Socket socket;
    private Map<String, AbstractCommand> commands;

    public TcpHandlerThread(Socket socket, Map<String, AbstractCommand> commands) {
        this.socket = socket;
        this.commands = commands;
    }

    @Override
    public void run() {
        try {
            acceptMessageAndSendResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptMessageAndSendResponse() throws IOException {
        BufferedReader messageFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter messageToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        String word = messageFromClient.readLine();
        AbstractCommand countOfProducts = commands.get(parseCommandForInstructions(word));
        messageToClient.println(countOfProducts.execute(parseCommandForRequest(word)));
    }

    private String parseCommandForInstructions(String command) {
        String[] separate = command.split("=");
        return separate[0];
    }

    private String parseCommandForRequest(String command) {
        String[] separate = command.split("=");
        if (separate.length > 1) {
            return separate[1];
        }
        return null;
    }
}
