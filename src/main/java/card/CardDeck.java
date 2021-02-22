package card;

import java.util.Collections;
import java.util.Stack;

//public class CardDeck<Card> implements ICardDeck{
public class CardDeck implements ICardDeck {
    private final Stack<Card> deck;

    /**
     * Makes a standard deck consisting of the different available cards.
     */
    public CardDeck() {
        deck = new Stack<>();
        for(int i = 0; i < 18; i++) {
            if(i < 6) {
                deck.push(new Card(CardType.BACK_UP,1000));
                deck.push(new Card(CardType.U_TURN,1000));
                deck.push(new Card(CardType.MOVE_THREE,1000));
            }
            if(i < 12) {
                deck.push(new Card(CardType.MOVE_TWO,1000));
            }
            deck.push(new Card(CardType.MOVE_ONE,1000));
            deck.push(new Card(CardType.ROTATE_LEFT,1000));
            deck.push(new Card(CardType.ROTATE_RIGHT,1000));
        }

    }

    /**
     * Shuffle the deck of cards randomly.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     *
     * @return the card at the top of the deck.
     */
    @Override
    public Card getCard() {
        return deck.pop();
    }

    /**
     *
     * @return a copy of the deck.
     */
    @Override
    public Stack<Card> getCopy() {
        Stack<Card> deckCopy = new Stack<>();
        for(Card card : deck){
            deckCopy.push(card);
        }
        return deckCopy;
    }
}
