package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    Vector2 get_position();

    /**
     *
     * @return
     */
    boolean Has_Visited_a_Flag();

    void Visit_Flag();

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

}
