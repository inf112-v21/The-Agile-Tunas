package player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public int getRotation(Direction dir) {
        switch(dir) {
            case NORTH:
                return TiledMapTileLayer.Cell.ROTATE_0;
            case EAST:
                return TiledMapTileLayer.Cell.ROTATE_270;
            case SOUTH:
                return TiledMapTileLayer.Cell.ROTATE_180;
            case WEST:
                return TiledMapTileLayer.Cell.ROTATE_90;

        }
        return TiledMapTileLayer.Cell.ROTATE_0;
    }

    public Direction getOppositeDirection(Direction dir) {
        switch(dir) {
            case NORTH:
                return SOUTH;
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;

        }
        return dir;
    }
}
