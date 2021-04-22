package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;



public class ScreenOrchestrator extends Game {

    public static final int MENU = 0;
    public static final int PREFERENCES = 1;
    public static final int APPLICATION = 2;
    public static final int ENDGAME = 3;
    public static final int MULTIPLAYER = 4;
    public static final int RULES = 5;
    public static final int CLIENT = 6;

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private RuleScreen ruleScreen;
    private GamePreferences preferences;
    private MultiplayerScreen multiplayerScreen;
    private RegisterClientScreen registerClientScreen;



    public Music playingSong;

    public final String musicForGame = "Music/Rolemusic_-_pl4y1ng.mp3";



    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        preferences = new GamePreferences();


        setScreen(loadingScreen);


    }

    public void changeScreen(int screen) {
        switch(screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case RULES:
                if(ruleScreen == null) ruleScreen = new RuleScreen(this);
                this.setScreen(ruleScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case MULTIPLAYER:
                if(multiplayerScreen == null) multiplayerScreen = new MultiplayerScreen(this);
                this.setScreen(multiplayerScreen);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
            case CLIENT:
                if (registerClientScreen == null) registerClientScreen = new RegisterClientScreen(this);
                this.setScreen(registerClientScreen);
                break;
        }
    }

    public GamePreferences getPreferences() {
        return this.preferences;
    }


}