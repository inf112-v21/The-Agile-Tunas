package player;

import card.Card;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import java.util.ArrayList;

public class Player implements IPlayer {
    private final Robot robot;
    private final int playerID;
    private ArrayList<Card> program;
    private ArrayList<Card> cardHand;
    public boolean programReady;

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
        this.programReady = false;
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
     * @return list of Player Cells.
     */
    @Override
    public ArrayList<TiledMapTileLayer.Cell> getCells() {
        ArrayList<TiledMapTileLayer.Cell> playerCellList = new ArrayList<>();
        Texture pictureAll = new Texture("assets/player.png");
        TextureRegion[][] pictureOne = new TextureRegion().split(pictureAll, 300, 300);
        TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][0]));
        TiledMapTileLayer.Cell playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][2]));
        TiledMapTileLayer.Cell playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][1]));

        playerCellList.add(0, playerCell);
        playerCellList.add(1, playerWonCell);
        playerCellList.add(2, playerDiedCell);

        return playerCellList;
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

    public void setReady() {
        this.programReady = true;
    }

    public void setNotReady() {
        this.programReady = false;
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
     * @param cards Card to add.
     */
    @Override
    public void addToHand(ArrayList<Card> cards) {
        cardHand.addAll(cards);
    }

    /**
     * Removes given card from Player's hand.
     * @param card Card to remove.
     */
    @Override
    public void removeFromHand(Card card) {
        getCardHand().remove(card);
    }

    /**
     * Clears the player's program.
     */
    @Override
    public void clearCards() {
        program.clear();
        cardHand.clear();
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