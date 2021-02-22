package Network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "192.168.56.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader( socket.getInputStream()));

        String serverResponse = input.readLine();

        JOptionPane.showMessageDialog(null, serverResponse);

        socket.close();
        System.exit(0);


    }


    /*

    String serverIP = "The ip adress";
    int serverPort = 9090;

    Socket s = new Socket(serverIP, serverPort);

    public Client() throws IOException {
    }

    BufferedReader receiver = new BufferedReader( new InputStreamReader(s.getInputStream()));
    PrintWriter sender = new PrintWriter(s.getOutputStream(), true);

    //What I wish:



    while(game is going on) {

    String userInput = keyboard.readLine(); // wait for user to type
    sender.println(userInput);              // send message to server
    String response = receiver.readLine();  // wait for server reply
    System.out.println(response);           // display response for user

    }

     */

}
