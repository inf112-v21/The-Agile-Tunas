package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class RuleScreen extends AbstractScreen{

    private Label ruleLabel;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Table table;

    public RuleScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("RoboRallyRules/roborallyrules.jpg"));
        sprite = new Sprite(texture);
        sprite.setCenterX(Gdx.graphics.getWidth() >> 1);
        sprite.setCenterY(Gdx.graphics.getHeight() >> 1);

        Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));


        table = new Table();
        table.setFillParent(true);
        TextButton returnToMain = new TextButton("Return", skin);

        table.add(returnToMain).fillX().uniformX();
        table.padTop(texture.getHeight()+120);
        stage.addActor(table);

        returnToMain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MENU);
            }
        });

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(175/255f, 175/255f, 175/255f,1);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

}

