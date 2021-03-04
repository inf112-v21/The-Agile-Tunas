package inf112.skeleton.app;

import card.Card;
import card.CardDeck;
import card.CardType;
import static org.junit.Assert.*;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.Test;

public class CardTest {
    Texture move1Texture;

    @Test
    public void cardHasCorrectType(){
        GameHandler gm = new GameHandler();
        CardDeck deck = new CardDeck();

        //this.move1Texture = deck.move1Texture;
        //this.move1Texture = new Texture("assets/cards/move1.png");
        for (CardType type : CardType.values()){
            //Texture txt = deck.move1Texture;
            Card card = new Card(type, 1000, gm.cardDeck.move1Texture);  // må endre slik at hvert kort har riktig texture.
            assertEquals(type,card.getType());
        }
    }

    @Test
    public void cardReturnsCorrectPriority(){
        CardDeck deck = new CardDeck();
        //Texture move1Texture = new Texture("assets/cards/move1.png");
        int priority = 1337;
        Card card = new Card(CardType.MOVE_ONE, priority, deck.move1Texture);

        assertEquals(priority,card.getPriority());
    }

    @Test
    public void cardSpriteHasCorrectTexture(){
        CardDeck deck = new CardDeck();
        //Texture move1Texture = new Texture("assets/cards/move1.png");
        Card card = new Card(CardType.MOVE_ONE, 1000, deck.move1Texture);

        assertEquals((new Sprite(move1Texture)), card.getSprite().getTexture());
    }



}
