package game;

import card.Card;
import map.Layers;
import player.Direction;
import player.Player;
import player.Robot;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * The class that handles game logic.
 */
public class MultiplayerGameHandler extends GameHandler {

    // PLAYERS:
    public ArrayList<Player> playerList;
    public int numberOfPlayers;
    private Player player;
    private int playerID;

    //public MultiplayerGameHandler(GameServer server, int playerID, Boolean isHost) {
    public MultiplayerGameHandler(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;             // The number of players is given to constructor when initialising a MultiplayerGameHandler.
        //this.numberOfPlayers = server.getNumberOfPlayers;
        //this.playerID = playerID;

    }

    /**
     * Sets mapHandler, camera, mapRenderer, the icons for the player,
     * the player position, the robot for the player, and input processor.
     */
    @Override
    public void create() {
        super.create();                             // creates and initialises the variables in GameHandler.

        // PLAYER CONFIG:
        playerList = new ArrayList<>();

        // Initiating the players in the multiplayer game. Initiates as many players as given in constructor.
        /*
        for (int i=0; i<numberOfPlayers; i++) {
            this.initiatePlayer(i+1);
        }*/
        Vector2 playerPosition = mapHandler.getStartingPositions().get(playerID);
        this.player = new Player(new Robot(playerPosition, Direction.NORTH, 1), 1);
        for (int i=0; i<numberOfPlayers; i++) {
            if (i != getMyPlayer().getID()) {
                this.initiatePlayer(i+1);
            }
        }

        // Starting the first round:
        startRound();

    }

    /**
     * Creates a new Player with given ID, and gives it a Robot. Also adds the Player to the list of players.
     * @param playerID The Player ID.
     */
    @Override
    public void initiatePlayer(int playerID) {
        Vector2 playerPosition = mapHandler.getStartingPositions().get(playerID-1);                     // The starting position of a player. Corresponds to Player ID.
        Player player = new Player(new Robot(playerPosition, Direction.NORTH, playerID), playerID);     // Creates a new player
        playerList.add(player);
    }

    public Player getPlayer(int playerID) {
        return playerList.get(playerID);
    }

    /**
     * Prepare for the only turn we have so far.
     */
    @Override
    public void startRound() {
        if (cardSprites != null) {
            clearCards();
        }
        // CARD DECK:
        if (getDeck().size() < 9) {
            System.out.println("Card Deck has less than 9 cards. Giving new Card Deck");
            createDeck();
            getDeck().shuffle();
            giveCardsToPlayer(getMyPlayer());
            showCardHand();
            chooseProgram = true;
        }
        getDeck().shuffle();
        giveCardsToPlayer(getMyPlayer());
        showCardHand();
        chooseProgram = true;
    }

    /**
     * Shows the card that have been given to player at the start of a round.
     */
    @Override
    public void showCardHand() {
        // MAKING LIST OF SPRITES FROM PLAYER CARD HAND:
        cardSprites = new ArrayList<>();
        for(int i=0; i<9; i++) {
            Card card = getMyPlayer().getCardHand().get(i);
            Sprite sprite = card.getSprite();
            cardSprites.add(i, sprite);
        }

        // SPRITES POSITION:
        cardSprites.get(0).setPosition(470, 450);
        cardSprites.get(1).setPosition(570, 450);
        cardSprites.get(2).setPosition(470, 300);
        cardSprites.get(3).setPosition(570, 300);
        cardSprites.get(4).setPosition(470, 150);
        cardSprites.get(5).setPosition(570, 150);
        cardSprites.get(6).setPosition(470, 0);
        cardSprites.get(7).setPosition(570, 0);
        cardSprites.get(8).setPosition(570, -150);
    }

    /**
     * Removes the Card sprites and clears the Player's program.
     */
    @Override
    public void clearCards() {
        cardSprites.clear();
        getMyPlayer().clearProgram();
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

        // PLAYER:
        for (Player player : playerList) {
            setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection());
        }

        // DRAW CARD SPRITES ON SCREEN:
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(Sprite sprite : cardSprites) {
            sprite.draw(batch);
        }
        batch.end();

        for (Player player : playerList) {
            if (player.getProgram().size() == 5) {
                doPhase();
            }
        }

        // HOLE AND FLAG CELL:
        for (Player player : playerList) {
            TiledMapTileLayer.Cell hole = getMapHandler().getCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.HOLES);
            TiledMapTileLayer.Cell flag = getMapHandler().getCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.FLAGS);

            // If player is on a hole change player icon to defeat-icon.
            if (hole != null) {
                getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y,Layers.PLAYER, player.getCells().get(2));
            }
            // If player is on a flag change player icon to victory-icon.
            if (flag != null) {
                getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y,Layers.PLAYER, player.getCells().get(1));
            }
        }
    }

    /**
     * touchDown registers when user clicks on the screen, and tests if one of the card sprites
     * in the players card hand have been clicked. If one has been clicked, the card is moved to
     * the place under the map where the program cards are shown.
     *
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

                    System.out.println("Touch on" + card.toString());
                }
            }
            for (Card card : getMyPlayer().getProgram()) {
                this.player.removeFromHand(card);
            }
        }
        return false;
    }
}