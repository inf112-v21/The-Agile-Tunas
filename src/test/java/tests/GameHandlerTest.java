package tests;

import game.GameHandler;
import org.junit.Before;
import org.junit.Test;
import player.Direction;
import player.Player;
import player.Robot;
import static org.junit.Assert.assertEquals;

public class GameHandlerTest extends ApplicationTest {
    GameHandler gameHandler;
    private ApplicationTest game;

    @Before
    public void initializeTest(){
        game = new ApplicationTest();
        gameHandler = new GameHandler();
    }

    @Test
    public void BackUpMovesPlayerBack(){

        gameHandler.create();
        gameHandler.setPlayerPosition(gameHandler.initiatePlayer(1),5,5,Direction.NORTH, 0);
        assertEquals(5,gameHandler.getMyPlayer().getRobot().getPosition().x);
        assertEquals(5,gameHandler.getMyPlayer().getRobot().getPosition().y);
    }


}
