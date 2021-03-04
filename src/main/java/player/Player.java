package player;

import card.Card;
import card.CardDeck;
import inf112.skeleton.app.GameHandler;
import java.util.ArrayList;
import java.util.Stack;

public class Player extends GameHandler implements IPlayer {
    private Robot robot;
    private int robotID;
    private String name;
    private int playerID;
    private ArrayList<Card> program;
    private ArrayList<Card> cardHand;
    private CardDeck cardDeck;
    private Stack<Card> deck;
    private GameHandler game;

    /**
     * Sets the instance's robot to the given Robot, the instance's name to the given String.
     * @param robot, this player's robot.
     * @param name, this player's name.
     */
    public Player(Robot robot, String name, int robotID, int playerID) {
        this.robot = robot;
        this.name = name;
        this.robotID = robotID;
        this.playerID = playerID;
        this.cardHand = new ArrayList<>();
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
    public void setProgram(ArrayList<Card> listOfCards) {
        if (listOfCards.size() == 5) {
            for (Card card : listOfCards) {
                program.add(card);
            }
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
     *
     * @returns the player's hand.
     */
    @Override
    public ArrayList<Card> getCardHand() {
        return cardHand;
    }

    /**
     * Adds given card to the player's hand.
     * @param cards
     */
    @Override
    public void addToHand(ArrayList<Card> cards) {
        cardHand.addAll(cards);
    }

    /**
     * Clears the player's program.
     */
    @Override
    public void clearProgram() {
        program.clear();
    }

    /**
     * Sets the player's robot to power down next round.
     */
    @Override
    public void setPowerDown() {
        robot.setPowerDown();
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
