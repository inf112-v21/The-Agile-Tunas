package Screens;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {

    private ScreenOrchestrator parent;

    /**
     * Loads the MenuScreen.
     *
     * @param screenOrchestrator, parent class
     */
    public LoadingScreen(ScreenOrchestrator screenOrchestrator) {
        parent = screenOrchestrator;
    }

    @Override
    public void show() {
    }

    /**
     * Uses ScreenOrchestrator's changeScreen method to
     * change the screen to MenuScreen.
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        parent.changeScreen(ScreenOrchestrator.MENU);
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