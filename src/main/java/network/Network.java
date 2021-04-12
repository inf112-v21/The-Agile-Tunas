package network;



import card.Card;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

// This class is a convenient place to keep things common to both the client and server.
public class Network {
    static public final int tcpPort = 54555;
    static public final int udpPort = 54555;

    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(RegisterName.class);
        kryo.register(String[].class);
        kryo.register(UpdateNames.class);
        kryo.register(GameMessage.class);
        kryo.register(PlayerListMessage.class);
        kryo.register(CardList.class);
        kryo.register(WelcomeMessage.class);
        kryo.register(GameTurnsMessage.class);
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

    static public class WelcomeMessage {
        public String text;
        public int nPlayers;
    }

    static public class PlayerListMessage {
        HashMap<String, Player> playerList;
    }

    static public class CardList {
        ArrayList<Card> cards;
        Player player;
    }

    static public class GameTurnsMessage{
        ArrayList<HashMap<Player,Card>> turns;
    }


}
