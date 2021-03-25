package tests;

import card.Card;
import card.CardType;
import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.Before;
import org.junit.Test;


public class CardTest extends ApplicationTest {
    Texture move1Texture;
    private ApplicationTest game;

    @Before
    public void initializeTest(){
        game = new ApplicationTest();
    }


    @Test
    public void cardHasCorrectType(){
        this.move1Texture = new Texture("assets/cards/move1.png");
        for (CardType type : CardType.values()){
            Card card = new Card(type, 1000, move1Texture);  // må endre slik at hvert kort har riktig texture.
            assertEquals(type,card.getType());
        }
    }

    @Test
    public void cardReturnsCorrectPriority(){
        Texture move1Texture = new Texture("assets/cards/move1.png");
        int priority = 1337;
        Card card = new Card(CardType.MOVE_ONE, priority, move1Texture);

        assertEquals(priority,card.getPriority());
    }

    @Test
    public void cardSpriteHasCorrectTexture(){
        Texture move1Texture = new Texture("assets/cards/move1.png");
        Card card = new Card(CardType.MOVE_ONE, 1000, move1Texture);

        assertEquals((new Sprite(move1Texture)).getTexture(), card.getSprite().getTexture());
    }



}
