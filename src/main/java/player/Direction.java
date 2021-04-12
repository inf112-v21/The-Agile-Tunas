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


    /*
    public int getRotation(Direction from, Direction to) {
        switch(from) {
            case NORTH:
                switch(to) {
                    case NORTH:
                        break;
                    case EAST:
                        return TiledMapTileLayer.Cell.ROTATE_90;
                    case WEST:
                        return TiledMapTileLayer.Cell.ROTATE_270;
                    case SOUTH:
                        return TiledMapTileLayer.Cell.ROTATE_180;
                }
            case EAST:
                switch(to) {
                    case NORTH:
                        return TiledMapTileLayer.Cell.ROTATE_270;
                    case EAST:
                        break;
                    case WEST:
                        return TiledMapTileLayer.Cell.ROTATE_180;
                    case SOUTH:
                        return TiledMapTileLayer.Cell.ROTATE_90;
                }
            case WEST:
                switch(to) {
                    case NORTH:
                        return TiledMapTileLayer.Cell.ROTATE_90;
                    case EAST:
                        return TiledMapTileLayer.Cell.ROTATE_180;
                    case WEST:
                        break;
                    case SOUTH:
                        return TiledMapTileLayer.Cell.ROTATE_270;
                }
            case SOUTH:
                switch(to) {
                    case NORTH:
                        return TiledMapTileLayer.Cell.ROTATE_180;
                    case EAST:
                        return TiledMapTileLayer.Cell.ROTATE_270;
                    case WEST:
                        return TiledMapTileLayer.Cell.ROTATE_90;
                    case SOUTH:
                        break;
                }
            default:
                return TiledMapTileLayer.Cell.ROTATE_0;
        }
    }*/
}
