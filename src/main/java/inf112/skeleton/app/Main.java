package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("hello-world");
        //Chose different width and height according to the new map layout
        cfg.setWindowedMode(600, 800);

        new Lwjgl3Application(new GameHandler(), cfg);
    }
}