package card;

import java.util.Stack;

public interface ICardDeck {

    void shuffle();

    Card getCard();

    void putBack(Card card);

    Stack<Card> getCopy();

    int size();
}
