package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot{
    private Vector2 position;
    private boolean flag = false;
    private Direction direction;

    /**
     * Creates an instance of a robot.
     * The robot has a starting position and a starting direction.
     * This class controls the robots location as well as updating its positions
     * in accord with the game.
     * @param start_pos The starting position of the robot.
     */
    public Robot(Vector2 start_pos, Direction direction) {
        this.position = start_pos;
        this.direction = direction;
    }

    /**
     *
     * @return the current position of the robot.
     */
    @Override
    public Vector2 getPosition() {
        return position;
    }

    /**
     *
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
     * Moves the robot north
     */
    public void moveNorth() {
        position.y +=1;
    }

    /**
     * Moves the robot south
     */
    @Override
    public void moveSouth() {
        position.y -= 1;
    }

    /**
     * Moves the robot west
     */
    @Override
    public void moveWest() {
        position.x -=1;
    }

    /**
     * Moves the robot east
     */
    @Override
    public void moveEast() {
        position.x += 1;
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
