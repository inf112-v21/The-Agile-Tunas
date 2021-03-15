package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import map.MapHandler;

public class Renderer extends OrthogonalTiledMapRenderer {
    private MapHandler mapHandler;

    public Renderer(TiledMap map, float unitScale, Batch batch, MapHandler mapHandler) {
        super(map, unitScale, batch);
        this.map = map;
        this.mapHandler = mapHandler;
    }

    public void render() {

    }

    private void clearMap() {

    }

    public void placeRobots() {

    }
}
