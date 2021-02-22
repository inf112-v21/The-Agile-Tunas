package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static final int PORT = 9090;
    //The port can be any number really, but above 9090 should be good, 8080 or something is internet
    ServerSocket incoming = new ServerSocket(PORT);

    public Server() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);
        System.out.println(" [SERVER] Waiting for client connection...");

        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String date = (new Date()).toString();
        System.out.println("[SERVER] Sending date " + date);
        out.println("[SERVER] sending date " + date);

        System.out.println("[SERVER] Sent Date. Closing");
        client.close();
        listener.close();
    }


    /*
    Socket clientConnection = incoming.accept();


    //Reading from keyboard, might be useful for testing
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in ));

    String userInput = keyboard.readLine();

    BufferedReader receiver = new BufferedReader( new InputStreamReader(clientConnection.getInputStream()));
    PrintWriter sender = new PrintWriter(clientConnection.getOutputStream(), true);

     */





}
