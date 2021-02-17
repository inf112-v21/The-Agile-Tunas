package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements IPlayer {
    private Robot robot;
    //private int robotID;
    private String name;
    //private int playerID;
    //private CardDeck playerDeck;
    private ArrayList<Card> program;


    public Player(Robot robot, String name) {
        //this.playerDeck = new CardDeck(new ArrayList<>());
        this.robot = robot;
        this.name = name;
    }

    public List<Card> deck_Of_Cards() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.MOVE_ONE);
        cards.add(Card.MOVE_TWO);
        cards.add(Card.MOVE_THREE);
        cards.add(Card.BACK_UP);
        cards.add(Card.ROTATE_LEFT);
        cards.add(Card.ROTATE_RIGHT);
        cards.add(Card.U_TURN);

        Collections.shuffle(cards);

        return cards;
    }


    /**
     *
     * @return
     */
    @Override
    public Robot getRobot() {
        return robot;
    }

    /**
     *
     * @param listOfCards
     */
    @Override
    public void setProgram(ArrayList<Card> listOfCards) {
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Card> getProgram() {
        return program;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Card> getDeck() {
        return null;
    }

    /**
     *
     */
    @Override
    public void setPowerDown() {
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
}
