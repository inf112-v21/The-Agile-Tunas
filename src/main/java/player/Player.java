package player;

import card.Card;
import card.CardDeck;

import java.util.ArrayList;
import java.util.Stack;

public class Player implements IPlayer {
    private Robot robot;
    //private int robotID;
    private String name;
    //private int playerID;
    private ArrayList<Card> program;
    //private final CardType[] cardHand = new CardType[9];
    private CardDeck cardDeck;

    /**
     * Sets the instance's robot to the given Robot, the instance's name to the given String.
     * @param robot, this player's robot.
     * @param name, this player's name.
     */
    public Player(Robot robot, String name) {
        this.robot = robot;
        this.name = name;
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
    public void setProgram(ArrayList<Card> listOfCards) {       // !!! muligens ikke riktig implementert
        for(Card card : listOfCards) {
            program.add(card);
        }
    }

    /**
     * Returns player's current program.
     *
     * @return ArrayList<CardType>
     */
    @Override
    public ArrayList<Card> getProgram() {
        return program;
    }

    /**
     * Returns the card deck.
     * @return ArrayList<CardType>
     */
    @Override
    public Stack<Card> getDeck() {
        cardDeck = new CardDeck();
        cardDeck.shuffle();
        Stack<Card> deck = cardDeck.getCopy();

        return deck;
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
