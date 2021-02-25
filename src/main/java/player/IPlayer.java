package player;

import card.Card;

import java.util.ArrayList;
import java.util.Stack;

public interface IPlayer {
    Robot getRobot();
    // int getRobotID();

    void setProgram(ArrayList<Card> programCards);

    ArrayList<Card> getProgram();

    Stack<Card> getDeck();

    void setPowerDown();

    String getName();
    // int getPlayerID();

    void clearProgram();

    ArrayList<Card> getCardHand();

    void addToHand(ArrayList<Card> cards);
}