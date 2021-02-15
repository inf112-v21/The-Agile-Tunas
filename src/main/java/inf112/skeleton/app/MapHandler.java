package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class MapHandler implements IMapHandler{
    public final TiledMap tiledMap;
    private int NumberOfFlags;
    private final int mapHeight;
    private final int mapWidth;


    public MapHandler(String path) {
        this.tiledMap = new TmxMapLoader().load(path);
        mapHeight = (int) tiledMap.getProperties().get("height");
        mapWidth = (int) tiledMap.getProperties().get("width");

        //Counting the number of Flags
        for (int i = 0; i < getLayer(Layers.FLAGS).getWidth(); i++) {
            for (int j = 0; j < getLayer(Layers.FLAGS).getHeight(); j++) {
                if (getLayer(Layers.FLAGS).getCell(i,j)!=null){
                    NumberOfFlags +=1;
                }
            }
        }
    }

    public TiledMapTileLayer getLayer(Layers layer){
        switch(layer){
            case FLAGS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Flags");
            case HOLES:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Holes");
            case WALLS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Walls");
            case CONVEYORS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("ConveyorBelts");
            case LASERS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Laser");
            case PLAYER:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Player");
            case EXPRESS_CONVEYOR:
                return (TiledMapTileLayer) tiledMap.getLayers().get("ExpressConveyorBelts");
            case GEARS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Gears");
            case START_POSITIONS:
                return (TiledMapTileLayer) tiledMap.getLayers().get("StartPositions");
            default:
                return (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        }

    }

    public void setCell(int x, int y, Layers layer, TiledMapTileLayer.Cell objectInCell){
        this.getLayer(layer).setCell(x,y,objectInCell);
    }

    public TiledMapTileLayer.Cell getCell(int x, int y, Layers layer){
        return this.getLayer(layer).getCell(x,y);
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getNumberOfFlags() {
        return NumberOfFlags;
    }

    public ArrayList<Vector2> getStartingPositions(){
        ArrayList<Vector2> startingPositions = new ArrayList<>();
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if ((getLayer(Layers.START_POSITIONS).getCell(i,j)!=null)){
                    startingPositions.add(new Vector2(i,j));
                }
            }
        }
        return startingPositions;
    }
}
