package network;



import java.io.IOException;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.MultiplayerGameHandler;
import network.Network.GameMessage;
import player.Player;

public class GameClient extends Listener{
    public Client client;
    public String name;
    private Player player;
    private Stage stage;
    private MultiplayerGameHandler game;

    public GameClient (String host, String name, Stage stage, final MultiplayerGameHandler game) throws IOException {
        client = new Client();
        client.start();
        client.addListener(this);
        Network.register(client);
        this.name = name;
        this.stage =stage;
        this.game = game;

        // We'll do the connect on a new thread so the ChatFrame can show a progress bar.

        client.connect(5000, host, Network.port);
        GameMessage gm = new GameMessage();
        gm.text = "RegisterName: " + name;
        client.sendTCP(gm);

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally for player "+ name);
        cfg.setWindowedMode(700, 900);

        new Lwjgl3Application(game, cfg);
    }


    //Do nothing when connected
   @Override
   public void connected (Connection connection) {

   }


   @Override
   public void disconnected (Connection connection) {
        game.getScreen().dispose();
   }

   @Override
   public void received (Connection connection, Object object) {

       if (object instanceof GameMessage) {
           GameMessage gameMessage = (GameMessage)object;
           parseMessage(connection, gameMessage);
           return;
       }
       if (object instanceof Network.PlayerListMessage){
           player = ((Network.PlayerListMessage) object).playerList.get(name);
           game.playerList.addAll(((Network.PlayerListMessage) object).playerList.values());
       }
   }

    /**
     * Parse a message coming in from the server.
     * @param gameMessage The message being sent from the server.
     */
   private void parseMessage(Connection connection, GameMessage gameMessage){
        String[] message = gameMessage.text.split(" ");
        switch(message[0]){

            case "Welcome":
                break;
            case "AllReady":
                System.out.println("Everyone is ready");
                break;
            default:
        }
   }
}