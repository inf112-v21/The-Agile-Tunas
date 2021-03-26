package game;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screens.ScreenOrchestrator;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        //Chose different width and height according to the new map layout
        cfg.setWindowedMode(700, 900);

        //new Lwjgl3Application(new GameHandler(), cfg);

        //new Lwjgl3Application(new RoboRally(), cfg);

        //new Lwjgl3Application(new MultiplayerGameHandler(), cfg);

        new Lwjgl3Application(new ScreenOrchestrator(), cfg);
    }
}