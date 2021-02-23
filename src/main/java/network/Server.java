package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String[] names = {"Jacob", "Robin", "Lisa", "Olesya"};
    private static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the urbane"};
    private static final int PORT = 9090;
    //The port can be any number really, but above 9090 should be good, 8080 or something is internet

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println("[SERVER] Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client!");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }

    }

    static String getRandomName() {
        String name = names[(int) (Math.random()*names.length)];
        String adj = adjs[(int) (Math.random()* adjs.length)];
        return name + " " + adj;
    }

}
