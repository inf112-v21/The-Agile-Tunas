package tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.mockito.Mockito;
import com.badlogic.gdx.backends.headless.HeadlessApplication;

public class ApplicationTest {
    public static Application app;

    /**
     *
     */
    @BeforeClass
    public static void initialize(){
        app = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int i, int i1) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }


        });
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @AfterClass
    public static void clean(){
        app.exit();
        app = null;
    }

}
