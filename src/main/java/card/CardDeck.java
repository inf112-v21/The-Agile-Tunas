package card;

import com.badlogic.gdx.graphics.Texture;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CardDeck implements card.ICardDeck {
    private final Stack<Card> deck;



    /**
     * Makes a standard deck consisting of the different available cards.
     */
    public CardDeck() {
        Texture backUpTexture = new Texture("assets/cards/backup.png");
        Texture move1Texture = new Texture("assets/cards/move1.png");
        Texture move2Texture = new Texture("assets/cards/move2.png");
        Texture move3Texture = new Texture("assets/cards/move3.png");
        Texture rotateLeftTexture = new Texture("assets/cards/rotateleft.png");
        Texture rotateRightTexture = new Texture("assets/cards/rotateright.png");
        Texture uTurnTexture = new Texture("assets/cards/uturn.png");

        deck = new Stack<>();
        Random r = new Random();
        for(int i = 0; i < 18; i++) {
            if(i > 11) {
                deck.push(new Card(CardType.BACK_UP, 200 + r.nextInt(100), backUpTexture));
                deck.push(new Card(CardType.U_TURN, 100 + r.nextInt(100), uTurnTexture));
                deck.push(new Card(CardType.MOVE_THREE, 500 + r.nextInt(100), move3Texture));
            }
            if(i > 5) {
                deck.push(new Card(CardType.MOVE_TWO, 400 + r.nextInt(100), move2Texture));
            }
            deck.push(new Card(CardType.MOVE_ONE, 300 + r.nextInt(100), move1Texture));
            deck.push(new Card(CardType.ROTATE_LEFT, 100 + r.nextInt(100), rotateLeftTexture));
            deck.push(new Card(CardType.ROTATE_RIGHT, 100 + r.nextInt(100), rotateRightTexture));
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
     * @return the card at the top of the deck.
     */
    @Override
    public Card getCard() {
        return deck.pop();
    }

    /**
     * Puts the given card back into the deck.
     * @param card Card to put back.
     */
    @Override
    public void putBack(Card card) {
        this.deck.push(card);
    }

    /**
     *
     * @return a copy of the deck.
     */
    @Override
    public Stack<Card> getCopy() {
        Stack<Card> deckCopy = new Stack<>();
        for(Card card : this.deck){
            deckCopy.push(card);
        }
        return deckCopy;
    }

    /**
     * @return the number of Cards in this CardDeck.
     */
    @Override
    public int size() {
        return this.deck.size();
    }
}
