package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RobotTest {
    Robot bot;

    @Test
    public void robotMovesUp() {
        bot = new Robot(new Vector2(0,0));
        bot.moveUp();
        Vector2 expected = new Vector2(0,1);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesDown() {
        bot = new Robot(new Vector2(0,0));
        bot.moveDown();
        Vector2 expected = new Vector2(0,-1);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesLeft() {
        bot = new Robot(new Vector2(0,0));
        bot.moveLeft();
        Vector2 expected = new Vector2(-1,0);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesRight() {
        bot = new Robot(new Vector2(0,0));
        bot.moveRight();
        Vector2 expected = new Vector2(1,0);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotHasCorrectStartingPosition(){
        bot = new Robot(new Vector2(10,10));
        assertEquals(new Vector2(10,10),bot.getPosition());
    }
}
