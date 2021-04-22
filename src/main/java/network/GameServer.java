package network;


import java.io.IOException;
import java.util.*;

import card.Card;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import game.MultiplayerGameHandler;
import player.Direction;
import player.Player;
import player.Robot;

public class GameServer extends Network{
    private final int MaxPlayers = 2;
    public int numberOfPlayers;
    private final MultiplayerGameHandler game;
    private final Set<Connection> connections;
    private final HashMap<Integer, String> names;
    private final HashMap<String, Player> players;
    private final HashMap<Player, ArrayList<Card>> currentPlayersCards;

    Server server;

    public GameServer() throws IOException{
        connections = new HashSet<>();
        names = new HashMap<>(MaxPlayers);
        players = new HashMap<>(MaxPlayers);
        game = new MultiplayerGameHandler(MaxPlayers);
        numberOfPlayers = 0;
        server = new Server();
        currentPlayersCards = new HashMap<>();

        // Uses a separate class for registering the client and server
        Network.register(server);
        server.addListener(this);

        try {
            server.bind(Network.tcpPort, Network.udpPort);
        } catch (Exception e) {
            System.out.println("Binding of the server failed " + e.getMessage());
        }
        server.start();

    }

    @Override
    public void connected(Connection connection) {
        System.out.println("Trying to connect");
        connection.setTimeout(timeout*12);
        connection.setName("Player " + (connections.size() + 1));
        this.connections.add(connection);
        names.put(numberOfPlayers,"player"+numberOfPlayers);
        WelcomeMessage wm = new WelcomeMessage();
        wm.nPlayers = MaxPlayers;
        wm.text = "ready";
        for (Connection con : connections) { con.sendTCP(wm); }

        System.out.println("Connected");

    }



    @Override
    public void disconnected (Connection c) {
        connections.remove(c);
    }

    @Override
    //Method for receiving messages from clients
    public void received (Connection c, Object object) {
        // We know all connections for this server are actually ChatConnections.
        if (object instanceof GameMessage){
            parseMessage(c,(GameMessage) object);
        }
        else if (object instanceof Network.CardList){
            Network.CardList cards = (Network.CardList) object;
            currentPlayersCards.put(cards.player, cards.cards);
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
                Network.WelcomeMessage wm = new Network.WelcomeMessage();
                wm.text = "Welcome";
                wm.nPlayers = numberOfPlayers;
                server.sendToAllTCP(wm);
                if (numberOfPlayers==MaxPlayers) {
                    sendToAllClients("AllReady "+numberOfPlayers);
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


    public void doTurn(){
        Network.GameTurnsMessage GTM = new Network.GameTurnsMessage();
        // Do a turn for each chosen card
        for (int i = 0; i < 5; i++) {
            ArrayList<tuple> priority = new ArrayList<>();
            //Sort the players based on the priority of their selected card
            for(Player player: currentPlayersCards.keySet()){
                int pri = currentPlayersCards.get(player).get(i).getPriority();
                tuple t = new tuple();
                t.player = player;
                t.priority = pri;
                priority.add(t);
            }
            priority.sort(Comparator.comparingInt(o -> o.priority));
            //Do the turn in order of priority for each player
            for(tuple player: priority){
                GTM.turns.get(i).put(player.player,currentPlayersCards.get(player.player).get(i));
            }
        }
        server.sendToAllTCP(GTM);
    }
    static class tuple{
         Player player;
        int priority;
    }

    public static void main(String[] args) throws IOException {
        GameServer serv = new GameServer();


    }
}
