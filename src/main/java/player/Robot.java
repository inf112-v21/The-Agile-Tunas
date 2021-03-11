package player;

import card.CardType;
import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot{
    private Vector2 position;
    private boolean flag = false;
    private Direction direction;
    private boolean powerDown = false;
    private final int robotID;

    /**
     * Creates an instance of a robot.
     * The robot has a starting position and a starting direction.
     * This class controls the robots location as well as updating its positions
     * in accord with the game.
     * @param start_pos The starting position of the robot.
     */
    public Robot(Vector2 start_pos, Direction direction, int robotID) {
        this.position = start_pos;
        this.direction = direction;
        this.robotID = robotID;
    }

    /**
     * @return the current position of the robot.
     */
    @Override
    public Vector2 getPosition() {
        return position;
    }

    /**
     * @return the robot's ID.
     */
    @Override
    public int getID() {
        return robotID;
    }

    /**
     * @return the robot's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return a boolean signaling if the robot has visited a flag.
     */
    @Override
    public boolean hasVisitedAFlag() {
        return flag;
    }

    /**
     * Method to update the robot when visiting a flag.
     */
    @Override
    public void visitFlag() {
        this.flag = true;
    }

    /**
     * Takes a CardType and "moves" the robot by updating the x- and y-position and the direction.
     * @param type
     */
    public void doMove(CardType type) {
        switch(type) {
            case MOVE_ONE:
                switch(direction) {
                    case NORTH: moveNorth(1); return;
                    case WEST: moveWest(1); return;
                    case EAST: moveEast(1); return;
                    case SOUTH: moveSouth(1); return;
                }
            case MOVE_TWO:
                switch(direction) {
                    case NORTH: moveNorth(2); return;
                    case WEST: moveWest(2); return;
                    case EAST: moveEast(2); return;
                    case SOUTH: moveSouth(2); return;
                }
            case MOVE_THREE:
                switch(direction) {
                    case NORTH: moveNorth(3); return;
                    case WEST: moveWest(3); return;
                    case EAST: moveEast(3); return;
                    case SOUTH: moveSouth(3); return;
                }
            case BACK_UP:
                switch(direction) {
                    case NORTH: moveNorth(-1); return;
                    case WEST: moveWest(-1); return;
                    case EAST: moveEast(-1); return;
                    case SOUTH: moveSouth(-1); return;
                }
            case ROTATE_LEFT:
                switch(direction) {
                    case NORTH: changeDirection(Direction.WEST); return;
                    case WEST: changeDirection(Direction.SOUTH); return;
                    case EAST: changeDirection(Direction.NORTH); return;
                    case SOUTH: changeDirection(Direction.EAST); return;
                }
            case ROTATE_RIGHT:
                switch(direction) {
                    case NORTH: changeDirection(Direction.EAST); return;
                    case WEST: changeDirection(Direction.NORTH); return;
                    case EAST: changeDirection(Direction.SOUTH); return;
                    case SOUTH: changeDirection(Direction.WEST); return;
                }
            case U_TURN:
                switch(direction) {
                    case NORTH: changeDirection(Direction.SOUTH); return;
                    case WEST: changeDirection(Direction.EAST); return;
                    case EAST: changeDirection(Direction.WEST); return;
                    case SOUTH: changeDirection(Direction.NORTH); return;
                }
            default:
                System.out.println("Invalid Card type.");
        }
    }

    /**
     * Moves the robot north
     */
    public void moveNorth(int i) {
        if (position.y < 15) {
            position.y += i;
        }
    }

    /**
     * Moves the robot south
     */
    @Override
    public void moveSouth(int i) {
        if (position.y > 0) {
            position.y -= i;
        }
    }

    /**
     * Moves the robot west
     */
    @Override
    public void moveWest(int i) {
        if (position.x > 0) {
            position.x -= i;
        }
    }

    /**
     * Moves the robot east
     */
    @Override
    public void moveEast(int i) {
        if (position.x < 11) {
           position.x += i;
        }
    }

    /**
     * Changes the direction the robot is facing.
     * @param dir the new direction.
     */
    @Override
    public void changeDirection(Direction dir) {
        this.direction = dir;
    }

    /**
     * Sets the robot to power down next round.
     */
    @Override
    public void setPowerDown() {
        powerDown = true;
    }

    /**
     * Returns true if robot is in power down, else false.
     * @return true or false
     */
    @Override
    public boolean powerDownStatus() {
        return powerDown;
    }

}
