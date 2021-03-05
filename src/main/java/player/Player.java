package player;

import card.Card;
import inf112.skeleton.app.GameHandler;
import java.util.ArrayList;

public class Player extends GameHandler implements IPlayer {
    private Robot robot;
    private int playerID;
    private ArrayList<Card> program;
    private ArrayList<Card> cardHand;

    /**
     * Sets the instance's robot to the given Robot, the instance's name to the given String.
     * @param robot, this player's robot.
     * @param playerID, this player's ID.
     */
    public Player(Robot robot, int playerID) {
        this.robot = robot;
        this.playerID = playerID;
        this.cardHand = new ArrayList<>();
        this.program = new ArrayList<>();
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

    @Override
    public void addToProgram(Card card) {
        program.add(card);
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
     * @return the player's hand.
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
     * Gets ID of player.
     * @return the ID of the player.
     */
    @Override
    public int getID() {
        return playerID;
    }

}
