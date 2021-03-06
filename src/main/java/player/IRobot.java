package player;

import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    Vector2 getPosition();

    void moveNorth(int i);

    void moveSouth(int i);

    void moveWest(int i);

    void moveEast(int i);

    void changeDirection(Direction direction);

}
