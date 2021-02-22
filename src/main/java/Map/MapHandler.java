package Map;

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

    /**
     * Takes a string which corresponds to the path of a TiledMap,
     * then loads the TiledMap, sets mapHeight and mapWidth, and counts the number of flags.
     * @param path
     */
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

    /**
     * Takes the name of a layer in the enum Layers and returns the corresponding layer from the TiledMap tiledMap.
     * @param layer
     * @return a TiledMapTileLayer
     */
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

    /**
     * Sets the Cell in position (x,y) on the Layer layer to the object objectInCell.
     * @param x
     * @param y
     * @param layer
     * @param objectInCell
     */
    public void setCell(int x, int y, Layers layer, TiledMapTileLayer.Cell objectInCell){
        this.getLayer(layer).setCell(x,y,objectInCell);
    }

    /**
     * Gets the Cell in position (x,y) on the Layer layer.
     * @param x
     * @param y
     * @param layer
     * @return a TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getCell(int x, int y, Layers layer){
        return this.getLayer(layer).getCell(x,y);
    }

    /**
     * Gets the height of the map.
     * @return mapHeight
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * Gets the width of the map.
     * @return mapWidth
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * Gets the number of flags on map.
     * @return NumberOfFlags
     */
    public int getNumberOfFlags() {
        return NumberOfFlags;
    }

    /**
     * Gets the starting positions of the players/robots.
     * @return ArrayList with positions
     */
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
