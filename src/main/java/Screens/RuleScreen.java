package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RuleScreen extends AbstractScreen{

    private Label ruleLabel;

    public RuleScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void show() {
        super.show();

        final TextButton returnButton = new TextButton("Return", skin, "small");
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MENU);
            }
        });

        ruleLabel = new Label("Rules go here", skin);

        table.add(ruleLabel).colspan(2);
        table.row().pad(10,0,0,10);
        table.add(returnButton).colspan(2);

    }

}
