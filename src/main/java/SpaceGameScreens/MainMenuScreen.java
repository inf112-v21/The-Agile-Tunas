package SpaceGameScreens;

import SpaceGame.SpaceGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.GameHandler;
import org.lwjgl.system.CallbackI;

public class MainMenuScreen implements Screen {
    // Region Class Variable Initialization:
    private Texture texture;

    public static final int EXIT_BUTTON_WIDTH = 200;
    public static final int EXIT_BUTTON_HEIGHT = 200;
    public static final int PLAY_BUTTON_WIDTH = 200;
    public static final int PLAY_BUTTON_HEIGHT = 200;
    public static final int EXIT_BUTTON_Y = 50;
    public static final int PLAY_BUTTON_Y = 250;

    SpaceGame game;

    Texture exitButtonActive;
    Texture exitButtonInActive;
    Texture playButtonActive;
    Texture playButtonInActive;

    // End Region

    /**
     *
     * @param game
     */
    public MainMenuScreen(SpaceGame game) {
        this.game = game;
        playButtonActive = new Texture("playbuttonactive.jpg");
        playButtonInActive = new Texture("playbuttoninactive.jpg");
        exitButtonActive = new Texture("exitbuttonactive.jpg");
        exitButtonInActive = new Texture("exitbuttoninactive.jpg");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1,0,0,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        texture = new Texture("backgroundTest.jpg");

        game.batch.begin();
        game.batch.draw(texture, 0, 0);

        int x = SpaceGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && SpaceGame.HEIGHT - Gdx.input.getY()
        < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        x = SpaceGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if(Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && SpaceGame.HEIGHT - Gdx.input.getY()
                < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                this.dispose();
                //game.setScreen(new GameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
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
