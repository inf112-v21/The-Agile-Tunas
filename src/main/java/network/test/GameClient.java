package network.test;



import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import card.Card;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import network.test.Network.RegisterName;
import network.test.Network.GameMessage;
import network.test.Network.UpdateNames;
import com.esotericsoftware.minlog.Log;

public class GameClient extends Listener{
    Client client;
    String name;
    private ArrayList<Card> deck;

    public GameClient (String host, String name) {
        client = new Client();
        client.start();
        client.addListener(this);
        Network.register(client);
        this.name = name;

        // We'll do the connect on a new thread so the ChatFrame can show a progress bar.
        // Connecting to localhost is usually so fast you won't see the progress bar.
        new Thread("Connect") {
            public void run () {
                try {
                    client.connect(5000, host, Network.port);
                    GameMessage gm = new GameMessage();
                    gm.text = "RegisterName: " + name;
                    if (client.isConnected()) System.out.println("Connected to server");
                    client.sendTCP(gm);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }


    //Do nothing when connected
   @Override
   public void connected (Connection connection) {

   }

   @Override
   public void disconnected (Connection connection) {

   }

   @Override
   public void received (Connection connection, Object object) {

       if (object instanceof GameMessage) {
           GameMessage gameMessage = (GameMessage)object;
           parseMessage(connection, gameMessage);
           return;
       }
   }

   private void parseMessage(Connection connection, GameMessage gameMessage){
        String[] message = gameMessage.text.split(" ");
        switch(message[0]){

            case "Welcome":

                break;
            case "AllReady":

                System.out.println("Everyone is ready");
                break;
            case "Pong":

            default:
                return;
        }
   }



}