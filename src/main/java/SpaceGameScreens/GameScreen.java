package SpaceGameScreens;

import SpaceGame.SpaceGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {


    Texture img;
    float x, y;

    SpaceGame game;

    public GameScreen(SpaceGame game) {
        this.game = game;
    }

    public static final float SPEED = 5;

    @Override
    public void show() {
        img = new Texture("hacker2.jpg");

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += SPEED;
        }

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, x, y);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
