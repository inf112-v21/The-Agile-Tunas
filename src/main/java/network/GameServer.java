package network;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import card.CardDeck;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import game.MultiplayerGameHandler;
import network.Network.GameMessage;
import network.Network.UpdateNames;
import player.Direction;
import player.Player;
import player.Robot;

public class GameServer extends Listener{
    private final int MaxPlayers = 4;
    public int numberOfPlayers;
    private CardDeck deck;
    private MultiplayerGameHandler game;

    private final HashMap<Integer, String> names;
    private final HashMap<String, Player> players;
    Server server;

    public GameServer() throws IOException{

        names = new HashMap<>(MaxPlayers);
        players = new HashMap<>(MaxPlayers);
        deck = new CardDeck();
        game = new MultiplayerGameHandler(2);
        numberOfPlayers = 0;
        server = new Server();

        // Uses a separate class for registering the client and server
        Network.register(server);
        server.addListener(this);

        try {
            server.bind(Network.port);
        } catch (IOException e) {
            System.out.println("Binding of the server failed " + e.getMessage());
        }
        server.start();
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
                names.put(numberOfPlayers,message[1]);
                numberOfPlayers+=1;
                GameMessage gameMessage = new GameMessage();
                gameMessage.text = "Welcome";
                server.sendToAllTCP(gameMessage);
                if (numberOfPlayers==MaxPlayers) {
                    sendToAllClients("AllReady");
                    sendRobotsToAllClients();
                }
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

    private void sendRobotsToAllClients(){
        for (int i = 0; i < numberOfPlayers; i++) {
            Robot bot = new Robot(game.mapHandler.getStartingPositions().get(i), Direction.NORTH,i);
            players.put(names.get(i),new Player(bot,i));
        }
        Network.PlayerListMessage message = new Network.PlayerListMessage();
        message.playerList=players;
        server.sendToAllTCP(message);
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

    private void doTurn(){

    }


}
