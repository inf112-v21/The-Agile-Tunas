package map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import player.Direction;

import java.util.ArrayList;

public interface IMapHandler {

    TiledMapTileLayer getLayer(Layer layer);

    void setCell(int x, int y, Layer layer, TiledMapTileLayer.Cell objectInCell);

    TiledMapTileLayer.Cell getCell(int x, int y, Layer layer);

    int getMapHeight();

    int getMapWidth();

    int getNumberOfFlags();

    boolean checkForWall(Vector2 position, Direction dir);

    ArrayList<Vector2> getStartingPositions();
}
