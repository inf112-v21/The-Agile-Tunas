package inf112.skeleton.app;

import Player.Direction;
import Player.Robot;
import com.badlogic.gdx.math.Vector2;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RobotTest {
    private Robot bot;

    @Test
    public void robotMovesUp() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH);
        bot.moveNorth();
        Vector2 expected = new Vector2(0,1);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesDown() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH);
        bot.moveSouth();
        Vector2 expected = new Vector2(0,-1);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesLeft() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH);
        bot.moveWest();
        Vector2 expected = new Vector2(-1,0);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesRight() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH);
        bot.moveEast();
        Vector2 expected = new Vector2(1,0);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotHasCorrectStartingPosition(){
        bot = new Robot(new Vector2(10,10), Direction.NORTH);
        assertEquals(new Vector2(10,10),bot.getPosition());
    }
}
