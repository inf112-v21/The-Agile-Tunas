package network;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import card.Card;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.GameState;
import game.MultiplayerGameHandler;
import network.Network.GameMessage;
import player.Player;

public class GameClient extends Network {
    public Client client;
    public String name;
    private Player player;
    private MultiplayerGameHandler game;
    private int numberOfPlayers;

    public GameClient(String host, String name, final MultiplayerGameHandler game) throws IOException {
        this.client = new Client();
        register(client);
        this.client.start();
        this.client.addListener(this);
        connectToServer(host);
        this.game = game;
        this.name = name;


        GameMessage gm = new GameMessage();
        gm.text = "RegisterName: " + name;
        client.sendTCP(gm);

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally for player " + name);
        cfg.setWindowedMode(700, 900);

        new Lwjgl3Application(game, cfg);

        waitForCardSelection();
    }

    private void connectToServer(String host){
        //jacob = 89.10.163.11
        try {
            client.connect(timeout, host, tcpPort, udpPort);
        } catch (Exception e) {
            System.out.println("Cannot connect to " + host);
            e.printStackTrace();
        }
    }

    private void waitForCardSelection(){
        while (!player.programReady){
        }
        sendCards(player.getProgram());
    }


    //Do nothing when connected
    @Override
    public void connected(Connection connection) {

    }


    @Override
    public void disconnected(Connection connection) {
        game.getScreen().dispose();
    }

    @Override
    public void received(Connection connection, Object object) {

        if (object instanceof GameMessage) {
            GameMessage gameMessage = (GameMessage) object;
            parseMessage(connection, gameMessage);
            return;
        }
        if (object instanceof Network.WelcomeMessage) {
            numberOfPlayers = ((Network.WelcomeMessage) object).nPlayers;
        }
        if (object instanceof Network.PlayerListMessage) {
            player = ((Network.PlayerListMessage) object).playerList.get(name);
            game.playerList.addAll(((Network.PlayerListMessage) object).playerList.values());
        }
        if (object instanceof Network.GameTurnsMessage) {

        }
    }

    /**
     * Parse a message coming in from the server.
     *
     * @param gameMessage The message being sent from the server.
     */
    private void parseMessage(Connection connection, GameMessage gameMessage) {
        String[] message = gameMessage.text.split(" ");
        switch (message[0]) {

            case "Welcome":
                break;
            case "AllReady":
                System.out.println("Everyone is ready");
                numberOfPlayers = Integer.parseInt(message[1]);
                game = new MultiplayerGameHandler(numberOfPlayers);
                break;
            default:
        }
    }

    private void sendCards(ArrayList<Card> cards) {
        Network.CardList currentCards = new Network.CardList();
        currentCards.cards = cards;
        currentCards.player = this.player;
        client.sendTCP(currentCards);
    }

    private void doTurnForAllPlayers(Network.GameTurnsMessage gmt) {

        for (HashMap<Player, Card> turn : gmt.turns) {
            for (Player player : turn.keySet()) {
                game.doMove(player, turn.get(player).getType());
            }
            for (Player player : turn.keySet()){
                game.doConveyorBelts(player);
            }
            for (Player player : turn.keySet()){
                game.doLasers(player);
            }
        }
        game.endPhases();
        game.state = GameState.SETUP;

    }

    public static void main(String[] args) throws IOException {
        String host = "89.10.163.11";
        GameClient cl = new GameClient(host, "jacob", new MultiplayerGameHandler(2));
        //GameClient cl2 = new GameClient("localhost", "local", new MultiplayerGameHandler(2));
    }
}