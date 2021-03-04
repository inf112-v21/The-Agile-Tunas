package inf112.skeleton.app;

import card.Card;
import card.CardDeck;
import card.CardType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Stack;

import static org.junit.Assert.*;

public class DeckTest {
    private CardDeck deck;

    @Test
    public void DeckHasCorrectNumberOfCards() {
        deck = new CardDeck();
        assertEquals(84, deck.getCopy().size());
    }

    @Test
    public void DeckContains6BackUpCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.BACK_UP)) counter++;
        }
        assertEquals(6, counter);
    }

    @Test
    public void DeckContains6UTurnCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.U_TURN)) counter++;
        }
        assertEquals(6, counter);
    }

    @Test
    public void DeckContains6MoveThreeCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.MOVE_THREE)) counter++;
        }
        assertEquals(6, counter);
    }

    @Test
    public void DeckContains12MoveTwoCards() {
        deck = new CardDeck();
        int counter = 0;

        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.MOVE_TWO)) counter++;
        }
        assertEquals(12, counter);
    }

    @Test
    public void DeckContains18MoveOneCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.MOVE_ONE)) counter++;
        }
        assertEquals(18, counter);
    }

    @Test
    public void DeckContains18RotateRightCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.ROTATE_RIGHT)) counter++;
        }
        assertEquals(18, counter);
    }

    @Test
    public void DeckContains18RotateLeftCards() {
        deck = new CardDeck();
        int counter = 0;
        for (Card card : deck.getCopy()) {
            if (card.getType().equals(CardType.ROTATE_LEFT)) counter++;
        }
        assertEquals(18, counter);
    }

    @Test
    public void DeckIsDifferentAfterShuffled(){
        deck = new CardDeck();
        boolean changed = false;

        Stack<Card> deckCopy = deck.getCopy();
        deck.shuffle();

        for (Card cardShuffled : deck.getCopy()) {
            for (Card card : deckCopy) {
                if (!cardShuffled.getType().equals(card.getType())) {
                    changed = true;
                    break;
                }
            }
        }
        assertTrue(changed);

    }
}
