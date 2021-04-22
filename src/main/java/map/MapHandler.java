package map;

import card.CardType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
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
        for (int i = 0; i < getLayer(Layer.FLAGS).getWidth(); i++) {
            for (int j = 0; j < getLayer(Layer.FLAGS).getHeight(); j++) {
                if (getLayer(Layer.FLAGS).getCell(i,j)!=null){
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
    public TiledMapTileLayer getLayer(Layer layer){
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
    public void setCell(int x, int y, Layer layer, TiledMapTileLayer.Cell objectInCell){
        this.getLayer(layer).setCell(x,y,objectInCell);
    }

    /**
     * Gets the Cell in position (x,y) on the Layer layer.
     * @param x
     * @param y
     * @param layer
     * @return a TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getCell(int x, int y, Layer layer){
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
                if ((getLayer(Layer.START_POSITIONS).getCell(i,j)!=null)){
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
        TiledMapTileLayer.Cell cell = this.getCell((int) position.x, (int) position.y, Layer.WALLS);
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

    public ConveyorBelt checkForConveyor(Vector2 position, Direction dir) {
        TiledMapTileLayer.Cell conveyorCell = this.getCell((int) position.x, (int) position.y, Layer.CONVEYORS);
        TiledMapTileLayer.Cell expressConveyorCell = this.getCell((int) position.x, (int) position.y, Layer.EXPRESS_CONVEYOR);
        if (conveyorCell != null) {
            int tileID = conveyorCell.getTile().getId();
            return findConveyorType(dir, tileID);
        }
        else if (expressConveyorCell != null) {
            int tileID = expressConveyorCell.getTile().getId();
            return findConveyorType(dir, tileID);
        }
        return null;
    }
    public ConveyorBelt findConveyorType(Direction dir, int tileID) {
        switch(dir) {
            case NORTH:
                List<Integer> northConveyorIDs = Arrays.asList(49, 57, 65, 69, 42, 43);
                List<Integer> northExpressConveyorIDs = Arrays.asList(13, 26, 27, 73, 77, 84);
                if (northConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.CONVEYOR_NORTH;
                }
                if (northExpressConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.EXPRESS_CONVEYOR_NORTH;
                }
                break;
            case EAST:
                List<Integer> eastConveyorIDs = Arrays.asList(52, 41, 35, 58, 61, 66);
                List<Integer> eastExpressConveyorIDs = Arrays.asList(14, 25, 19, 74, 78, 81);
                if (eastConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.CONVEYOR_EAST;
                }
                if (eastExpressConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.EXPRESS_CONVEYOR_EAST;
                }
                break;
            case SOUTH:
                List<Integer> southConveyorIDs = Arrays.asList(50, 59, 33, 36, 62, 67);
                List<Integer> southExpressConveyorIDs = Arrays.asList(21, 20, 17, 75, 82, 86);
                if (southConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.CONVEYOR_SOUTH;
                }
                if (southExpressConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.EXPRESS_CONVEYOR_SOUTH;
                }
                break;
            case WEST:
                List<Integer> westConveyorIDs = Arrays.asList(51, 34, 44, 60, 68, 70);
                List<Integer> westExpressConveyorIDs = Arrays.asList(18, 28, 22, 76, 83, 85);
                if (westConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.CONVEYOR_WEST;
                }
                if (westExpressConveyorIDs.contains(tileID)) {
                    return ConveyorBelt.EXPRESS_CONVEYOR_WEST;
                }
                break;
        }
        return null;
    }

    public TiledMapTileLayer.Cell getLaser() {
        TiledMapTileLayer wallLayer = getLayer(Layer.WALLS);
        ArrayList<Vector2> laserWallPositions = new ArrayList<>();

        /*
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                TiledMapTileLayer.Cell cell = (getLayer(Layer.WALLS).getCell(i,j);
                if (cell !=null) {
                    if (cell.getTile().getId() == 46) {
                        laserWallPositions.add(new Vector2(i,j));
                    }
                }
            }
        }*/

        Texture pictureAll = new Texture("assets/tiles.png");
        TextureRegion[][] pictureOne = new TextureRegion().split(pictureAll, 300, 300);
        TiledMapTileLayer.Cell laser = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[4][6]));


        return laser;
    }

    public Laser checkForLaser(Vector2 position, Direction dir) {
        TiledMapTileLayer.Cell laserCell = this.getCell((int) position.x, (int) position.y, Layer.LASERS);

        if (laserCell != null) {
            int tileID = laserCell.getTile().getId();
            return findLaserType(dir, tileID);
        }
        return null;
    }

    public Laser findLaserType(Direction dir, int tileID) {
        switch(dir) {
            case NORTH:
                List<Integer> northSingleLaserIDs = Arrays.asList();
                List<Integer> northDoubleLaserIDs = Arrays.asList();
                if (northSingleLaserIDs.contains(tileID)) {
                    return Laser.SINGLE_LASER_N;
                }
                if (northDoubleLaserIDs.contains(tileID)) {
                    return Laser.DOUBLE_LASER_N;
                }
                break;
            case EAST:
                List<Integer> eastSingleLaserIDs = Arrays.asList();
                List<Integer> eastDoubleLaserIDs = Arrays.asList();
                if (eastSingleLaserIDs.contains(tileID)) {
                    return Laser.SINGLE_LASER_E;
                }
                if (eastDoubleLaserIDs.contains(tileID)) {
                    return Laser.DOUBLE_LASER_E;
                }
                break;
            case SOUTH:
                List<Integer> southSingleLaserIDs = Arrays.asList();
                List<Integer> southDoubleLaserIDs = Arrays.asList();
                if (southSingleLaserIDs.contains(tileID)) {
                    return Laser.SINGLE_LASER_S;
                }
                if (southDoubleLaserIDs.contains(tileID)) {
                    return Laser.DOUBLE_LASER_S;
                }
                break;
            case WEST:
                List<Integer> westSingleLaserIDs = Arrays.asList();
                List<Integer> westDoubleLaserIDs = Arrays.asList();
                if (westSingleLaserIDs.contains(tileID)) {
                    return Laser.SINGLE_LASER_W;
                }
                if (westDoubleLaserIDs.contains(tileID)) {
                    return Laser.DOUBLE_LASER_W;
                }
                break;
        }
        return null;
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
