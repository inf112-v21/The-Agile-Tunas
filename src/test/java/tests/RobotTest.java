package tests;

import card.CardType;
import com.badlogic.gdx.math.Vector2;
import org.junit.Test;
import player.Direction;
import player.Robot;

import static org.junit.Assert.assertEquals;

public class RobotTest {
    private Robot bot;

    @Test
    public void robotMovesUp() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH, 1);
        bot.moveNorth(1);
        Vector2 expected = new Vector2(0,1);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesDown() {
        bot = new Robot(new Vector2(10,10), Direction.NORTH, 1);
        bot.moveSouth(1);
        Vector2 expected = new Vector2(10,9);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesLeft() {
        bot = new Robot(new Vector2(10,10), Direction.NORTH, 1);
        bot.moveWest(1);
        Vector2 expected = new Vector2(9,10);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotMovesRight() {
        bot = new Robot(new Vector2(0,0), Direction.NORTH, 1);
        bot.moveEast(1);
        Vector2 expected = new Vector2(1,0);
        assertEquals(expected,bot.getPosition());
    }

    @Test
    public void robotHasCorrectStartingPosition(){
        bot = new Robot(new Vector2(10,10), Direction.NORTH, 1);
        assertEquals(new Vector2(10,10),bot.getPosition());
    }

    @Test
    public void robotMovesCorrectAccordingMoveOne(){
        bot = new Robot( new Vector2(10,10), Direction.NORTH, 1);
        bot.doMove(CardType.MOVE_ONE);
        assertEquals(11,(int)bot.getPosition().y);
    }

    @Test
    public void robotMovesCorrectAccordingToMoveTwo(){
        bot = new Robot( new Vector2(10,10), Direction.EAST, 1);
        bot.doMove(CardType.MOVE_TWO);
        assertEquals(12,(int)bot.getPosition().x);
    }

    @Test
    public void robotMovesCorrectAccordingToMoveThree(){
        bot = new Robot( new Vector2(10,10), Direction.WEST, 1);
        bot.doMove(CardType.MOVE_THREE);
        assertEquals(7,(int)bot.getPosition().x);
    }


    @Test
    public void robotMovesCorrectlyLeft(){
        bot = new Robot( new Vector2(10,10), Direction.NORTH, 1);

        bot.doMove(CardType.ROTATE_LEFT);
        assertEquals(Direction.WEST,bot.getDirection());

        bot.doMove(CardType.ROTATE_LEFT);
        assertEquals(Direction.SOUTH,bot.getDirection());

        bot.doMove(CardType.ROTATE_LEFT);
        assertEquals(Direction.EAST,bot.getDirection());

        bot.doMove(CardType.ROTATE_LEFT);
        assertEquals(Direction.NORTH,bot.getDirection());
    }

    @Test
    public void robotMovesCorrectlyRight(){
        bot = new Robot( new Vector2(10,10), Direction.NORTH, 1);

        bot.doMove(CardType.ROTATE_RIGHT);
        assertEquals(Direction.EAST,bot.getDirection());

        bot.doMove(CardType.ROTATE_RIGHT);
        assertEquals(Direction.SOUTH,bot.getDirection());

        bot.doMove(CardType.ROTATE_RIGHT);
        assertEquals(Direction.WEST,bot.getDirection());

        bot.doMove(CardType.ROTATE_RIGHT);
        assertEquals(Direction.NORTH,bot.getDirection());
    }

    @Test
    public void robotMovesCorrectlyBackwords() {
        bot = new Robot( new Vector2(10,10), Direction.NORTH, 1);
        bot.doMove(CardType.BACK_UP);
        assertEquals(9,(int)bot.getPosition().y);
    }

    @Test
    public void robotDoesUTurn() {
        bot = new Robot( new Vector2(10,10), Direction.NORTH, 1);
        bot.doMove(CardType.U_TURN);
        assertEquals(Direction.SOUTH,bot.getDirection());
    }

}
