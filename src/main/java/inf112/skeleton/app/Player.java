package inf112.skeleton.app;

import java.util.ArrayList;

public class Player implements IPlayer {
    private Robot robot;
    //private int robotID;
    private String name;
    //private int playerID;
    private ArrayList<CardType> program;
    private final CardType[] cardHand = new CardType[9];

    /**
     * Sets the instance's robot to the given Robot, the instance's name to the given String.
     * @param robot, this player's robot.
     * @param name, this player's name.
     */
    public Player(Robot robot, String name) {
        //this.playerDeck = new CardDeck(new ArrayList<>());
        this.robot = robot;
        this.name = name;
        //deck = deck_Of_Cards();
    }

    /**
     * Returns the Player's Robot.
     * @return Robot
     */
    @Override
    public Robot getRobot() {
        return robot;
    }

    /**
     * Sets the program for the player.
     *
     * @param listOfCards, an ArrayList of type Card.
     */
    @Override
    public void setProgram(ArrayList<CardType> listOfCards) {
    }

    /**
     * Returns player's current program.
     *
     * @return ArrayList<CardType>
     */
    @Override
    public ArrayList<CardType> getProgram() {
        return program;
    }

    /**
     * Returns the card deck.
     * @return ArrayList<CardType>
     */
    @Override
    public ArrayList<CardType> getDeck() {
        return null;
    }

    /**
     * Sets the robot to power down next round.
     */
    @Override
    public void setPowerDown() {
    }

    /**
     * Gets name of player.
     * @return the name of the player.
     */
    @Override
    public String getName() {
        return name;
    }
}
