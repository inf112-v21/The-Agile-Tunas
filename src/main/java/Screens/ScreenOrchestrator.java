package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenOrchestrator extends Game {

    // Integers representing different screens:
    public static final int MENU = 0;
    public static final int PREFERENCES = 1;
    public static final int APPLICATION = 2;
    public static final int ENDGAME = 3;

    // The different screens:
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;

    /**
     * Instantiate a LoadingScreen, sets the screen to this.
     * LoadingScreen shows the MenuScreen.
     */
    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    /**
     * Changes the screen to the given screen.
     * @param screen, the integer representation of the screen.
     */
    public void changeScreen(int screen) {
        switch(screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen((Screen) menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen((Screen) preferencesScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen((Screen) mainScreen);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);
                this.setScreen((Screen) endScreen);
                break;
        }
    }
}
