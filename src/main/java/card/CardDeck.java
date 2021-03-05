package card;

import com.badlogic.gdx.graphics.Texture;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.Random;

public class CardDeck implements card.ICardDeck {
    private final Stack<Card> deck;
    public Texture move1Texture;
    public Texture backUpTexture;

    /**
     * Makes a standard deck consisting of the different available cards.
     */
    public CardDeck() {
        backUpTexture = new Texture("assets/cards/backup.png");
        move1Texture = new Texture("assets/cards/move1.png");
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
        for(Card card : this.deck){
            deckCopy.push(card);
        }
        return deckCopy;
    }
}
