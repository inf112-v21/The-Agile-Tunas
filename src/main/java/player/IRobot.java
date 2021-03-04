package player;

import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    Vector2 getPosition();

    int getID();

    boolean hasVisitedAFlag();

    void visitFlag();

    void moveNorth(int i);

    void moveSouth(int i);

    void moveWest(int i);

    void moveEast(int i);

    void changeDirection(Direction direction);

    void setPowerDown();

    boolean powerDownStatus();

}
