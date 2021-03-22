package inf112.skeleton.app;

import Screens.ScreenOrchestrator;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import network.test.GameServer;
import player.Player;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");


        //Chose different width and height according to the new map layout
        cfg.setWindowedMode(700, 900);


        //new Lwjgl3Application(new GameHandler(), cfg);

        new Lwjgl3Application(new ScreenOrchestrator(), cfg);



    }
}