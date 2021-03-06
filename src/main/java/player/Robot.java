package player;

import card.CardType;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Robot implements IRobot{
    private ArrayList<Boolean> flags;
    private Vector2 position;
    private Direction direction;
    private final int robotID;
    private final int damage;
    private int hp;
    public boolean isAlive;

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
        this.damage = 10;
        this.hp = 100;
        this.flags = new ArrayList<>();
        this.isAlive = true;
    }

    /**
     * @return the current position of the robot.
     */
    @Override
    public Vector2 getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public ArrayList<Boolean> getFlags() {
        return this.flags;
    }

    /**
     * Sets the flag with given flag number as visited, if possible.
     * @param flagNumber, The number of the flag.
     */
    public void visitFlag(int flagNumber) {
        boolean canVisit = false;

        // We can always visit flag number 1.
        if (flagNumber == 1) {
            canVisit = true;
        }
        else {
            // If all the flags before flag number "flagNumber" have been visited, we can visit the flag
            for (int i = 0; i < flagNumber-1; i++) {
                if (this.getFlags().get(i).equals(true)) {
                    canVisit = true;
                }
                else {
                    canVisit = false;
                }
            }
        }

        if (canVisit && getFlags().get(flagNumber-1) != true) {
            getFlags().remove(flagNumber-1);
            getFlags().add(flagNumber-1, true);
            System.out.println("Visited Flag Number " + flagNumber);
        }
    }

    /**
     * @return the robot's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return The Robot's current Health Points.
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Takes a given damage and subtracts it from the Robot's HP.
     * @param damage The damage taken.
     */
    public void handleDamage(int damage) {
        this.hp = getHp() - damage;
        if (this.hp <= 0) {
            setAsDead();
        }
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
                    case NORTH: moveSouth(1); return;
                    case WEST: moveEast(1); return;
                    case EAST: moveWest(1); return;
                    case SOUTH: moveNorth(1); return;
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
     *
     */
    public void setAsDead() {
        this.isAlive = false;
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
}