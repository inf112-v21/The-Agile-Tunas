package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class SpriteRenderer extends OrthogonalTiledMapRenderer {

    public SpriteRenderer(TiledMap map, float unitScale, Batch batch) {
        super(map, unitScale, batch);
    }


    @Override
    public void render() {

    }
}
