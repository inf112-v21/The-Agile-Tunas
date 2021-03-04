package inf112.skeleton.app;

import card.Card;
import card.CardType;
import static org.junit.Assert.*;

import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;

public class CardTest {
    @Test
    public void CardHasCorrectType(){

        for (CardType type : CardType.values()){
            Card card = new Card(type, 1000);
            assertEquals(type,card.getType());
        }
    }

    @Test
    public void CardReturnsCorrectPriority(){
        int priority = 1337;
        Card card = new Card(CardType.MOVE_ONE, priority);

        assertEquals(priority,card.getPriority());
    }



}
