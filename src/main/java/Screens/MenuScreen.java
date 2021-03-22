package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;

public class MenuScreen implements Screen {

    private ScreenOrchestrator parent;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion background;



    public MenuScreen(ScreenOrchestrator screenOrchestrator) {
        parent = screenOrchestrator;
        stage = new Stage(new ScreenViewport());






    }



    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);


        Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));

        // Lager knappene
        TextButton singleplayer = new TextButton("Single Player", skin);
        TextButton multiplayer = new TextButton("Multiplayer", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton rules = new TextButton("Rules", skin);
        TextButton exit = new TextButton("Exit", skin);


        //Legger til knappene i formasjon? I de forskjellige plassene
        table.add(singleplayer).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(multiplayer).fillX().uniform();
        table.row().pad(10,0,10,0);
        table.add(preferences).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(rules).fillX().uniform();
        table.row().pad(10,0,10,0);
        table.add(exit).fillX().uniformX();

        // Gjør slik at når vi trykker på knappene så skjer det noe
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        singleplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.APPLICATION);
            }
        });

        multiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MULTIPLAYER);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.PREFERENCES);
            }
        });
        rules.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.RULES);
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
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
        stage.dispose();
    }
}
