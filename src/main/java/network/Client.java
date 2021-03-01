package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "92.168.64.2";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader( socket.getInputStream()));
        //Read from keyboard
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        //This goes to the server, true is to flush the buffer
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //Make the connection between client and server stay open
        while(true) {
            System.out.println("> ");
            String command = keyboard.readLine();

            if (command.equals("quit")) break;
            //Sends the info you type to server
            out.println(command);

            //Get response from server
            String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse);

            //JOptionPane.showMessageDialog(null, serverResponse);
        }

        socket.close();
        System.exit(0);


    }




}