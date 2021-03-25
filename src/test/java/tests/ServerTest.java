package tests;


import static org.junit.Assert.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import game.MultiplayerGameHandler;
import network.GameClient;
import network.GameServer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerTest {
    /**
     * Semi-manual test. Insert host and name in the pop-up windows.
     * @throws IOException
     */
    @Test
    public void twoClientsConnectToServer() throws IOException, InterruptedException {
        GameServer server = new GameServer();
        GameClient client1 = new GameClient("localhost","test", new Stage(), new MultiplayerGameHandler());
        GameClient client2 = new GameClient("localhost","test", new Stage(), new MultiplayerGameHandler());

        //Wait for the server to update
        TimeUnit.SECONDS.sleep(3);
        assertEquals(2,server.numberOfPlayers);

    }
}
