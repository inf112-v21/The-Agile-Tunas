package Card;

import java.util.Stack;

public interface ICardDeck {

    void shuffle();

    Card getCard();

    Stack<Card> getCopy();
}
