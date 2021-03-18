package inf112.skeleton.app;

import Screens.ScreenOrchestrator;
import SpaceGame.SpaceGame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import network.test.GameServer;
import player.Player;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // SpaceGame configuration:
        config.setTitle("SpaceGame");
        config.setResizable(true);
        config.setWindowedMode(SpaceGame.WIDTH, SpaceGame.HEIGHT);

        //Chose different width and height according to the new map layout
        cfg.setWindowedMode(700, 900);


        //new Lwjgl3Application(new ScreenOrchestrator(), cfg);

        new Lwjgl3Application(new SpaceGame(), config);

        //new Lwjgl3Application(new GameHandler(), cfg);





    }
}