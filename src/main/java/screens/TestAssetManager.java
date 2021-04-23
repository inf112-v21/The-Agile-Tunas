package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;


public class TestAssetManager {

    public final AssetManager manager = new AssetManager();

    public final String playingSong = "Music/Rolemusic_-_pl4y1ng.mp3";

    public void queueAddMusic(){
        manager.load(playingSong, Music.class);
    }

}
