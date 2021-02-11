package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    Vector2 getPosition();

    /**
     *
     * @return
     */
    boolean hasVisitedAFlag();

    void visitFlag();

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

}
