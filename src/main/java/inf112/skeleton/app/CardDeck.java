package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//public class CardDeck<Card> implements ICardDeck{
public class CardDeck implements ICardDeck{
    private final List<CardType> deck;
    //private final List<Card> deck;

    /**
     * Makes a standard card deck in RoboRally (?)
     */
    public CardDeck() {
        List<CardType> cards = new ArrayList<>();
        for(int i = 0; i < 18; i++) {
            if(i < 6) {
                cards.add(CardType.BACK_UP);
                cards.add(CardType.U_TURN);
                cards.add(CardType.MOVE_THREE);
            }
            if(i < 12) {
                cards.add(CardType.MOVE_TWO);
            }
            cards.add(CardType.MOVE_ONE);
            cards.add(CardType.ROTATE_LEFT);
            cards.add(CardType.ROTATE_RIGHT);

        }
        Collections.shuffle(cards);

        this.deck = cards;
    }
}
