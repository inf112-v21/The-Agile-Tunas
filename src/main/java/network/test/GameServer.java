package network.test;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import card.CardDeck;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import network.test.Network.GameMessage;
import network.test.Network.UpdateNames;

public class GameServer extends Listener{
    private final int MaxPlayers = 2;
    public int numberOfPlayers;

    private final HashMap<Integer, String> players;
    Server server;

    public GameServer () throws IOException{

        players = new HashMap<>(MaxPlayers);
        numberOfPlayers = 0;
        server = new Server() {
            protected Connection newConnection () {
                // Using a custom connection
                return new GameConnection();
            }
        };

        // Uses a separate class for registering the client and server
        Network.register(server);
        server.addListener(this);


        try {
            server.bind(Network.port);
        } catch (IOException e) {
            System.out.println("Binding of the server failed " + e.getMessage());
        }
        server.start();

        // Open a window to provide an easy way to stop the server.
        JFrame frame = new JFrame("Game Server");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed (WindowEvent evt) {
                server.stop();
            }
        });
        frame.getContentPane().add(new JLabel("Close to stop the server."));
        frame.setSize(320, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



    @Override
    public void disconnected (Connection c) {
        GameConnection connection = (GameConnection)c;
        if (connection.name != null) {
            // Announce to everyone that someone left.
            GameMessage gameMessage = new GameMessage();
            gameMessage.text = connection.name + " disconnected.";
            server.sendToAllTCP(gameMessage);
            updateNames();
        }
    }

    @Override
    //Method for receiving messages from clients
    public void received (Connection c, Object object) {
        // We know all connections for this server are actually ChatConnections.
        if (object instanceof GameMessage){
            parseMessage(c,(GameMessage) object);
        }
    }

    //Method for handling incoming messages
    //You can send "Ping" from the client and you will recieve Pong if the server is connected.
    private void parseMessage(Connection connection, GameMessage gm){
        String[] message = gm.text.split(" ");
        switch(message[0]){

            case "RegisterName:":
                System.out.println("Registered player " + message[1]);
                players.put(numberOfPlayers,message[1]);
                numberOfPlayers+=1;
                GameMessage gameMessage = new GameMessage();
                gameMessage.text = "Welcome";
                server.sendToAllTCP(gameMessage);
                if (numberOfPlayers==MaxPlayers) sendToAllClients("AllReady");
                this.startGame();
                break;
            case "Ping":
                GameMessage gameMessage2 = new GameMessage();
                gameMessage2.text = "Pong";
                server.sendToAllTCP(gameMessage2);
        }

    }

    //Sends a message to all connected clients
    private void sendToAllClients(String message){
        GameMessage gameMessage = new GameMessage();
        gameMessage.text = message;
        server.sendToAllTCP(gameMessage);
    }

    void updateNames () {
        // Stores the names of all clients
        Connection[] connections = server.getConnections();
        ArrayList<String> names = new ArrayList<>();
        for (int i = connections.length - 1; i >= 0; i--) {
            GameConnection connection = (GameConnection)connections[i];
            names.add(connection.name);
        }
        // Send the names to everyone.
        UpdateNames updateNames = new UpdateNames();
        updateNames.names = names.toArray(new String[names.size()]);
        server.sendToAllTCP(updateNames);
    }

    // This holds per connection state.
    static class GameConnection extends Connection {
        public String name;
    }
    public void startGame(){
        sendToAllClients("Start");
    }

    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer();
    }

}
