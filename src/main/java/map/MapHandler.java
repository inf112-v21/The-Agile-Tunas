package map;

import card.CardType;
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

    /**
     * Checks for a wall in a given direction on a given position.
     *
     * @param position The position we want to check for walls on
     * @param dir The direction we want to check for walls in
     * @return true if there is a wall in the direction, or false if there is not a wall.
     */
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
                    List<Integer> westWallIDs = Arrays.asList(30, 24, 32, 38);
                    if (westWallIDs.contains(tileID)) {
                        return true;
                    }
                    return false;
            }
        }
        return false;
    }

    /**
     * Gets the next position if one does what the given CardType corresponds to, in the given direction, from the given position.
     *
     * @param position The start position
     * @param dir The direction one is facing in
     * @param cardType The CardType which one wants to act after
     * @return The next position
     */
    public Vector2 getNextPosition(Vector2 position, Direction dir, CardType cardType) {
        switch(cardType) {
            case MOVE_ONE:
                switch(dir) {
                    case NORTH: return new Vector2(position.x,position.y+1);
                    case WEST: return new Vector2(position.x-1,position.y);
                    case EAST: return new Vector2(position.x+1,position.y);
                    case SOUTH: return new Vector2(position.x,position.y-1);
                }
            case MOVE_TWO:
                switch(dir) {
                    case NORTH: return new Vector2(position.x,position.y+2);
                    case WEST: return new Vector2(position.x-2,position.y);
                    case EAST: return new Vector2(position.x+2,position.y);
                    case SOUTH: return new Vector2(position.x,position.y-2);
                }
            case MOVE_THREE:
                switch(dir) {
                    case NORTH: return new Vector2(position.x,position.y+3);
                    case WEST: return new Vector2(position.x-3,position.y);
                    case EAST: return new Vector2(position.x+3,position.y);
                    case SOUTH: return new Vector2(position.x,position.y-3);
                }
            case BACK_UP:
                switch(dir) {
                    case NORTH: return new Vector2(position.x,position.y-1);
                    case WEST: return new Vector2(position.x+1,position.y);
                    case EAST: return new Vector2(position.x-1,position.y);
                    case SOUTH: return new Vector2(position.x,position.y+1);
                }
            case ROTATE_LEFT:
            case ROTATE_RIGHT:
            case U_TURN:
                return position;
        }
        return position;
    }

    public boolean canMoveForward(Vector2 from, Direction dir) {
        boolean currentPosHasWall = this.checkForWall(from, dir);

        if (currentPosHasWall) {
            return false;
        }
        else {
            Vector2 nextPosition = getNextPosition(from, dir, CardType.MOVE_ONE);
            boolean nextPosHasWall = this.checkForWall(nextPosition, dir.getOppositeDirection(dir));
            if (nextPosHasWall) {
                return false;
            }
            else {
                return true;
            }
        }
    }
}
