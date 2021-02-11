package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public interface IMapHandler {

    TiledMapTileLayer getLayer(Layers layer);

    void setCell(int x, int y, Layers layer, TiledMapTileLayer.Cell objectInCell);

    TiledMapTileLayer.Cell getCell(int x, int y, Layers layer);

    int getMapHeight();

    int getMapWidth();

    int getNumberOfFlags();

    ArrayList<Vector2> getStartingPositions();
}
