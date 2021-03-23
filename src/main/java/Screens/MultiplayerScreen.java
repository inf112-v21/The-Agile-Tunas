package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.minlog.Log;
import network.test.GameClient;
import network.test.GameServer;
import java.io.IOException;

public class MultiplayerScreen implements Screen {

    private ScreenOrchestrator parent;
    private Stage stage;


    public MultiplayerScreen(ScreenOrchestrator screenOrchestrator) {
        parent = screenOrchestrator;
        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));

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
                    System.out.println("Server initiated");

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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
