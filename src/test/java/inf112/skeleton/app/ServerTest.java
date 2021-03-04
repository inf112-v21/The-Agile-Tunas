package inf112.skeleton.app;


import static org.junit.Assert.*;

import network.test.GameClient;
import network.test.GameServer;
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
        GameServer server = new GameServer(new GameHandler());
        GameClient client1 = new GameClient();
        GameClient client2 = new GameClient();

        //Wait for the server to update
        TimeUnit.SECONDS.sleep(3);
        assertEquals(2,server.numberOfPlayers);

    }
}
