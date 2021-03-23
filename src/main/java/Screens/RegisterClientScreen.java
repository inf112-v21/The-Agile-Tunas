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

public class RegisterClientScreen implements Screen {

    private ScreenOrchestrator parent;
    private Stage stage;

    public RegisterClientScreen(ScreenOrchestrator screenOrchestrator) {
        this.parent = screenOrchestrator;
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
        Label host = new Label("Address: ", skin);
        Label name = new Label("Name: ", skin);
        TextField tfAddress = new TextField("",skin);
        TextField tfName = new TextField("",skin);

        tfAddress.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String input = tfAddress.getText();
                System.out.println(input);
            }
        });

        tfName.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String input = tfName.getText();
                System.out.println(input);
            }
        });

        //Button for returning to the main menu
        final TextButton returnButton = new TextButton("Return", skin, "small");
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MULTIPLAYER);
            }
        });

        table.add(host);
        table.row().pad(10,0,0,10);
        table.add(tfAddress);
        table.row().pad(10,0,0,10);
        table.add(name);
        table.row().pad(10,0,0,10);
        table.add(tfName);
        table.row().pad(10,0,0,10);
        table.add(returnButton).colspan(2);
        table.row().pad(10,0,0,10);

    }

    @Override
    public void render(float v) {
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
