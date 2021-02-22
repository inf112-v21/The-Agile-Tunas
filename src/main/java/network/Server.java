package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static String[] names = {"Jacob", "Robin", "Lisa", "Olesya"};
    private static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the urbane"};
    private static final int PORT = 9090;
    //The port can be any number really, but above 9090 should be good, 8080 or something is internet

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));



        try {
            while (true) {
                String request = in.readLine();
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else {
                    out.println("Type 'tell me a name' to get a random name");
                }

            }
        } finally {
            out.close();
            in.close();
        }
    }

    private static String getRandomName() {
        String name = names[(int) (Math.random()*names.length)];
        String adj = adjs[(int) (Math.random()* adjs.length)];
        return name + " " + adj;
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
