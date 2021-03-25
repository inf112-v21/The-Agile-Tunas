package tests;


import static org.junit.Assert.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.MultiplayerGameHandler;
import network.GameClient;
import network.GameServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerTest extends ApplicationTest{

    @Before
    public void initializeTest(){
        ApplicationTest game = new ApplicationTest();
    }
    /**
     * Test that the clients can connect to the host
     *
     */
    @Test
    public void twoClientsConnectToServer() {
        Gdx.app.postRunnable(() -> {
            GameServer server = null;
            try {
                server = new GameServer();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                GameClient client1 = new GameClient("localhost","test", new Stage(), new MultiplayerGameHandler());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                GameClient client2 = new GameClient("localhost","test", new Stage(), new MultiplayerGameHandler());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Wait for the server to update
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert server != null;
            assertEquals(2,server.numberOfPlayers);
        });


    }
}
