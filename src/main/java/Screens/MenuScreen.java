package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen extends AbstractScreen {

    public MenuScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void show() {
        super.show();
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

}
