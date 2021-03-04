package network.test;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import card.CardDeck;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.GameHandler;
import network.test.Network.RegisterName;
import network.test.Network.GameMessage;
import network.test.Network.UpdateNames;

public class GameServer extends Listener{
    private final int MaxPlayers = 2;
    public int numberOfPlayers;
    private final GameHandler game;
    private HashMap<Integer, Robot> robots;
    private HashMap<Integer, String> players;
    private CardDeck deck;
    Server server;

    public GameServer (GameHandler game) throws IOException {
        this.game = game;
        deck = new CardDeck();
        robots = new HashMap<>(MaxPlayers);
        players = new HashMap<>(MaxPlayers);
        numberOfPlayers = 0;
        server = new Server() {
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new ChatConnection();
            }
        };


        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(server);
        server.addListener(this);


        try {
            server.bind(Network.port);
        } catch (IOException e) {
            System.out.println("Binding of the server failed or obtaining the local IP address failed " + e.getMessage());
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
        ChatConnection connection = (ChatConnection)c;
        if (connection.name != null) {
            // Announce to everyone that someone (with a registered name) has left.
            GameMessage gameMessage = new GameMessage();
            gameMessage.text = connection.name + " disconnected.";
            server.sendToAllTCP(gameMessage);
            updateNames();
        }
    }

    @Override
    public void received (Connection c, Object object) {
        // We know all connections for this server are actually ChatConnections.
        ChatConnection connection = (ChatConnection)c;
        if (object instanceof GameMessage){
            parseMessage(c,(GameMessage) object);
        }
    }

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
        }

    }

    private void sendToAllClients(String message){
        GameMessage gameMessage = new GameMessage();
        gameMessage.text = message;
        server.sendToAllTCP(gameMessage);
    }

    


    void updateNames () {
        // Collect the names for each connection.
        Connection[] connections = server.getConnections();
        ArrayList<String> names = new ArrayList<>();
        for (int i = connections.length - 1; i >= 0; i--) {
            ChatConnection connection = (ChatConnection)connections[i];
            names.add(connection.name);
        }
        // Send the names to everyone.
        UpdateNames updateNames = new UpdateNames();
        updateNames.names = (String[])names.toArray(new String[names.size()]);
        server.sendToAllTCP(updateNames);
    }

    // This holds per connection state.
    static class ChatConnection extends Connection {
        public String name;
    }
    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer(new GameHandler());
    }

}
