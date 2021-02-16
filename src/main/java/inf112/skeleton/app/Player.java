package inf112.skeleton.app;

import java.util.ArrayList;

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
