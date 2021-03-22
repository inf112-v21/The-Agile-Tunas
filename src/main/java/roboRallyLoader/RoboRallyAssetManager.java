package roboRallyLoader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl3.audio.Wav;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class RoboRallyAssetManager {

    public final AssetManager assetManager = new AssetManager();
    //Sounds
    public final String boingSound = "sounds/boingsound.wav";

    //Texttures
    public final String playerImage = "Mech1A_north.png";

    //Music
    public final String testMusic = "music/testmusikk.mp3";

    //Skin
    public final String skin = "skin/glassy-ui.json";

    public void addSounds() {
        assetManager.load(boingSound, Wav.Sound.class);
    }
    public void addImages() {
        assetManager.load(playerImage, Texture.class);
    }
    public void addSkin() {
        SkinLoader.SkinParameter parameters = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
        assetManager.load(skin, Skin.class, parameters);
    }
    public void addMusic() {
        assetManager.load(testMusic, Music.class);
    }

}
