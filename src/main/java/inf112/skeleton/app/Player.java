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
    private ArrayList<CardTypes> program;
    private final List<CardTypes> deck;
    private final CardTypes[] cardHand = new CardTypes[9];



    public Player(Robot robot, String name) {
        //this.playerDeck = new CardDeck(new ArrayList<>());
        this.robot = robot;
        this.name = name;
        deck = deck_Of_Cards();
    }

    public List<CardTypes> deck_Of_Cards() {
        List<CardTypes> cards = new ArrayList<>();
        for(int i = 0; i < 18; i++) {
            if(i < 6) {
                cards.add(CardTypes.BACK_UP);
                cards.add(CardTypes.U_TURN);
                cards.add(CardTypes.MOVE_THREE);
            }
            if(i < 12) {
                cards.add(CardTypes.MOVE_TWO);
            }
            cards.add(CardTypes.MOVE_ONE);
            cards.add(CardTypes.ROTATE_LEFT);
            cards.add(CardTypes.ROTATE_RIGHT);

        }
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
    public void setProgram(ArrayList<CardTypes> listOfCards) {
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<CardTypes> getProgram() {
        return program;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<CardTypes> getDeck() {
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
