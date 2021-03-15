package Screens;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {



    private ScreenOrchestrator parent;

    public LoadingScreen(ScreenOrchestrator screenOrchestrator) {
        parent = screenOrchestrator;

    }


    @Override
    public void show() {

    }

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
