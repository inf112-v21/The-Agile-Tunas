package Network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "192.168.0.4";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader( socket.getInputStream()));

        String serverResponse = input.readLine();

        JOptionPane.showMessageDialog(null, serverResponse);

        socket.close();
        System.exit(0);

    }
}
