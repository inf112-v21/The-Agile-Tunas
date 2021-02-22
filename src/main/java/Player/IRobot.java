package Player;

import com.badlogic.gdx.math.Vector2;
import Player.Player;

public interface IRobot {

    Vector2 getPosition();

    /**
     *
     * @return
     */
    boolean hasVisitedAFlag();

    void visitFlag();

    void moveNorth();

    void moveSouth();

    void moveWest();

    void moveEast();

    void changeDirection(Direction direction);

}
