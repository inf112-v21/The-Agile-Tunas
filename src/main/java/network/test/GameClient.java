package network.test;


import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;
import card.CardDeck;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import network.test.Network.RegisterName;
import network.test.Network.GameMessage;
import network.test.Network.UpdateNames;
import com.esotericsoftware.minlog.Log;

public class GameClient extends Listener{
    GameScreen screen;
    Client client;
    String name;
    private CardDeck deck;

    public GameClient () {
        client = new Client();
        client.start();
        client.addListener(this);
        Network.register(client);



        // Request the host from the user.
        String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
                null, null, "localhost");
        if (input == null || input.trim().length() == 0) System.exit(1);
        final String host = input.trim();


        // Request the user's name.
        input = (String)JOptionPane.showInputDialog(null, "Name:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
                null, "");
        if (input == null || input.trim().length() == 0) System.exit(1);
        name = input.trim();


        screen = new GameScreen(host);

        // This listener is called when the send button is clicked.
        screen.setSendListener(() -> {
            GameMessage gameMessage = new GameMessage();
            gameMessage.text = screen.getSendText();
            client.sendTCP(gameMessage);
        });

        // This listener is called when the chat window is closed.
        screen.setCloseListener(() -> client.stop());
        screen.setVisible(true);

        // We'll do the connect on a new thread so the ChatFrame can show a progress bar.
        // Connecting to localhost is usually so fast you won't see the progress bar.
        new Thread("Connect") {
            public void run () {
                try {
                    client.connect(5000, host, Network.port);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }


   @Override
   public void connected (Connection connection) {

   }

   @Override
   public void disconnected (Connection connection) {
       EventQueue.invokeLater(() -> {
           screen.dispose();
       });
   }

   @Override
   public void received (Connection connection, Object object) {
       if (object instanceof UpdateNames) {
           UpdateNames updateNames = (UpdateNames)object;
           screen.setNames(updateNames.names);
           return;
       }

       if (object instanceof GameMessage) {
           GameMessage gameMessage = (GameMessage)object;
           screen.addMessage(gameMessage.text);
           parseMessage(connection, gameMessage);
           return;
       }
   }

   private void parseMessage(Connection connection, GameMessage gameMessage){
        String[] message = gameMessage.text.split(" ");
        switch(message[0]){

            default:
                return;
        }
   }

    public static void main (String[] args) {
        Log.set(Log.LEVEL_DEBUG);
        new GameClient();
    }
}