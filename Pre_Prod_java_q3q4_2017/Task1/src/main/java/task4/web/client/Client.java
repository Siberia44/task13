package task4.web.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Scanner messageInConsole;
    private BufferedReader responseFromServer;
    private PrintWriter requestToServer;
    private Socket socket;

    public static void main(String[] args) {
        Client client = new Client();
        while (true) {
            client.startWorkWithClient();
        }
    }

    public void startWorkWithClient() {
        try {
            sendAndAcceptMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAndAcceptMessage() throws IOException {
        socket = new Socket("localhost", 3000);
        initStreams();
        String messageFromClient = messageInConsole.nextLine();
        requestToServer.println(messageFromClient);
        System.out.println(responseFromServer.readLine());
    }

    private void initStreams() {
        try {
            messageInConsole = new Scanner(System.in);
            responseFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            requestToServer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}