package task4.web.threads.httpfactory.thread;

import task4.exception.ImageAbsenceException;
import task4.web.server.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public class HttpHandlerThread extends Thread {
    private Socket socket;
    private Map<String, AbstractCommand> commands;

    public HttpHandlerThread(Socket socket, Map<String, AbstractCommand> commands) {
        this.socket = socket;
        this.commands = commands;
    }

    @Override
    public void run() {
        try {
            getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageAbsenceException e) {
            System.out.print("");
        }
    }

    private void getInfo() throws IOException {
        try {
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream printWriter = socket.getOutputStream();
                String request = br.readLine();
                if (request.startsWith("GET /shop")) {
                    System.out.println("Client: " + request);
                    printWriter.write("HTTP/1.1 200 OK".getBytes());
                    printWriter.write(System.lineSeparator().getBytes());
                    String information = getCommandFromMap(request).execute(request);
                    String result = getResponseHeader(information) + information;
                    printWriter.write(result.getBytes());
                    printWriter.flush();
                }
            }
        } catch (NullPointerException e) {
            throw new ImageAbsenceException();
        }
    }

    private String getResponseHeader(String information) {
        return "HTTP/1.1 200 OK" + System.lineSeparator() +
                "Content-Type: text/html" + System.lineSeparator() +
                "Content-Length: " + information.length() +
                System.lineSeparator() + "Connection: close" + System.lineSeparator() + System.lineSeparator();
    }

    private AbstractCommand getCommandFromMap(String key) {
        Optional<String> optional = commands.keySet().stream()
                .filter(key::matches)
                .findFirst();
        return commands.get(optional.orElse(null));
    }
}
