package network;



import card.Card;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

// This class is a convenient place to keep things common to both the client and server.
public class Network {
    static public final int port = 54555;

    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(RegisterName.class);
        kryo.register(String[].class);
        kryo.register(UpdateNames.class);
        kryo.register(GameMessage.class);
        kryo.register(CardPacket.class);
        kryo.register(PlayerListMessage.class);
    }

    static public class RegisterName {
        public String name;
    }

    static public class UpdateNames {
        public String[] names;
    }

    static public class GameMessage {
        public String text;
    }

    static public class PlayerListMessage {
        HashMap<String, Player> playerList;
    }

    static public class CardPacket {
        public ArrayList<Card> cards;
    }

}
