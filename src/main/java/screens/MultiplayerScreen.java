package screens;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.minlog.Log;
import game.MultiplayerGameHandler;
import network.GameClient;
import network.GameServer;
import java.io.IOException;

public class MultiplayerScreen extends AbstractScreen {

    public MultiplayerScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void show() {

        super.show();
        //Button for returning to the main menu
        final TextButton returnButton = new TextButton("Return", skin, "small");
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MENU);
            }
        });

        //Button for choosing who will be the host.
        final TextButton hostButton = new TextButton("Host", skin, "small");
        hostButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    Log.set(Log.LEVEL_DEBUG);
                    GameServer server = new GameServer();
                    GameClient client = new GameClient("localhost","host", stage, new MultiplayerGameHandler(2));
                    parent.changeScreen(ScreenOrchestrator.ENDGAME);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Button for connecting to the server as a client.
        final TextButton clientButton = new TextButton("Join as Player", skin, "small");
        clientButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.CLIENT);
            }
        });


        table.add(hostButton).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(clientButton).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(returnButton).colspan(2);
    }
}
