package game;

import card.Card;
import card.CardDeck;
import map.Layer;
import player.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

/**
 * The class that handles game logic.
 */
public class MultiplayerGameHandler extends GameHandler {

    // PLAYERS:
    public ArrayList<Player> playerList;
    public int numberOfPlayers;
    public int playerID;

    //public MultiplayerGameHandler(GameServer server, int playerID, Boolean isHost) {
    //public MultiplayerGameHandler(int numberOfPlayers, int playerID) {
    public MultiplayerGameHandler(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;             // The number of players should be given to constructor when initialising a MultiplayerGameHandler.
        //this.numberOfPlayers = server.getNumberOfPlayers;
        this.playerID = 1;
        //this.playedID = playerID;
    }

    /**
     * Sets mapHandler, camera, mapRenderer, the icons for the player,
     * the player position, the robot for the player, and input processor.
     */
    @Override
    public void create() {
        super.setup();                             // creates and initialises the variables in GameHandler.

        // Initiating the players in the multiplayer game. Initiates as many players as given in constructor.
        playerList = new ArrayList<>();
        for (int i=0; i < numberOfPlayers; i++) {
            playerList.add(initiatePlayer(i+1));
        }
        this.player = playerList.get(0);

        // Starting the first round:
        this.startRound();
    }

    /**
     * Gets the Player with given player ID.
     * @param playerID, The player ID of the Player.
     * @return a Player
     */
    public Player getPlayer(int playerID) {
        return playerList.get(playerID-1);
    }

    /**
     * Handles the game logic.
     */
    @Override
    public void gameLogic() {
        switch(state) {
            case SETUP:
                startRound();
                break;
            case PROGRAMMING:
                if (!getMyPlayer().programReady) {
                    chooseProgram = true;
                }

                if (getMyPlayer().getProgram().size() == 5) {
                    chooseProgram = false;
                    getMyPlayer().setReady();
                }
                break;
        }
    }

    /**
     * Starts a round.
     */
    public void startRound() {
        // CARD DECK:
        if (getDeck().size() < 9 * playerList.size()) {
            System.out.println("Card Deck has less than 9 cards. Giving new Card Deck");
            this.cardDeck = new CardDeck();
        }
        getDeck().shuffle();
        for (Player player : playerList) {
            giveCardsToPlayer(player);
        }
        showCardHand();
        this.state = GameState.PROGRAMMING;
    }

    /**
     * Ends the phases.
     */
    @Override
    public void endPhases() {
        if (cardSprites != null) {
            this.clearCards();
        }
        getMyPlayer().setNotReady();
    }

    /**
     * Method for rendering.
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();
        getMapRenderer().setView(camera);
        getMapRenderer().render();

        this.gameLogic();


        // PLAYERS:
        for (Player player : playerList) {
            setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
        }

        // DRAW CARD SPRITES ON SCREEN:
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(Sprite sprite : cardSprites) {
            sprite.draw(batch);
        }
        batch.end();

        // HOLE AND FLAG CELL:
        for (Player player : playerList) {
            TiledMapTileLayer.Cell hole = getMapHandler().getCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layer.HOLES);
            TiledMapTileLayer.Cell flag = getMapHandler().getCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layer.FLAGS);

            // If player is on a hole change player icon to defeat-icon.
            if (hole != null) {
                setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection(), 2);
            }
            // If player is on a flag change player icon to victory-icon.
            if (flag != null) {
                setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection(), 1);
            }
        }
    }

    /**
     * touchDown registers when user clicks on the screen, and tests if one of the card sprites
     * in the players card hand have been clicked. If one has been clicked, the card is moved to
     * the place under the map where the program cards are shown.*
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return boolean true or false
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY,0);
        camera.unproject(temp);
        int numberOfProgramCards = getMyPlayer().getProgram().size();

        if (chooseProgram) {
            for (Card card : getMyPlayer().getCardHand()) {
                Sprite sprite = card.getSprite();
                if (sprite.getBoundingRectangle().contains(temp.x, temp.y) && numberOfProgramCards<5
                        && !getMyPlayer().getProgram().contains(card) && chooseProgram) {
                    if (numberOfProgramCards == 0) {
                        sprite.setPosition(0, -200);
                    }
                    else {
                        sprite.setPosition(numberOfProgramCards * 100, -200);
                    }
                    getMyPlayer().addToProgram(card);

                    System.out.println("Touch on: " + card.toString());
                }
            }
            for (Card card : getMyPlayer().getProgram()) {
                getMyPlayer().removeFromHand(card);
            }
        }
        return false;
    }
}