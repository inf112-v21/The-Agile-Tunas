package player;

import card.Card;
import java.util.ArrayList;

public interface IPlayer {
    Robot getRobot();

    void setProgram(ArrayList<Card> programCards);

    ArrayList<Card> getProgram();

    void setPowerDown();

    String getName();

    void clearProgram();

    ArrayList<Card> getCardHand();

    void addToHand(ArrayList<Card> cards);
}