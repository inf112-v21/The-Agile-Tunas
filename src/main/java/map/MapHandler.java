package map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import player.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

        TiledMapTileLayer.Cell cell = this.getCell(2, 15, Layers.WALLS);
        int tileID = cell.getTile().getId();

        System.out.println(tileID);

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

    public boolean checkForWall(Vector2 position, Direction dir) {
        TiledMapTileLayer.Cell cell = this.getCell((int) position.x, (int) position.y, Layers.WALLS);
        if (cell != null) {
            int tileID = cell.getTile().getId();
            switch(dir) {
                case NORTH:
                    List<Integer> northWallIDs = Arrays.asList(31, 24, 16, 45);
                    if (northWallIDs.contains(tileID)) {
                        return true;
                    }
                    return false;
                case EAST:
                    List<Integer> eastWallIDs = Arrays.asList(23, 16, 8, 46);
                    if (eastWallIDs.contains(tileID)) {
                        return true;
                    }
                    return false;
                case SOUTH:
                    List<Integer> southWallIDs = Arrays.asList(8, 32, 29, 37);
                    if (southWallIDs.contains(tileID)) {
                        return true;
                    }
                    return false;
                case WEST:
                    List<Integer> westWallIDs = Arrays.asList(28, 24, 32, 38);
                    if (westWallIDs.contains(tileID)) {
                        return true;
                    }
                    return false;
            }
        }
        return false;
    }
    /*
    public static void main(String[] args){
        MapHandler mh = new MapHandler();
        for (int i = 0; i < mh.mapHeight; i++) {
            for (int j = 0; j < mh.mapWidth; j++) {
                int id = mh.getLayer(Layers.WALLS).getCell(i,j).getTile().getId();
                System.out.println(id);
            }

        }
    }*/
}
