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
        super.create();                             // creates and initialises the variables in GameHandler.

        // PLAYER CONFIG:
        playerList = new ArrayList<>();
        playerList.add(getMyPlayer());

        // Initiating the players in the multiplayer game. Initiates as many players as given in constructor.
        for (int i=0; i<numberOfPlayers; i++) {
            int id = i+1;
            if (id != getMyPlayer().getID()) {
                Player player = initiatePlayer(id);
                playerList.add(player);
            }
        }

        // Starting the first round:
        startRound();

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
                    //this.state = GameState.PHASES;

                }
                //phaseNum = 1;
                break;
            /*
            case PHASES:
                chooseProgram = false;
                while (phaseNum <= 5) {
                    this.doPhase();
                }
                endPhases();
                this.state = GameState.SETUP;
                break;

             */
        }
    }

    /**
     * Stars a round.
     */
    @Override
    public void startRound() {
        // CARD DECK:
        if (getDeck().size() < 9) {
            System.out.println("Card Deck has less than 9 cards. Giving new Card Deck");
            this.cardDeck = new CardDeck();
            getDeck().shuffle();
            giveCardsToPlayer(getMyPlayer());
            showCardHand();
            this.state = GameState.PROGRAMMING;
        }
        getDeck().shuffle();
        giveCardsToPlayer(getMyPlayer());
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
     * Shows the card that have been given to player at the start of a round.
     */
    /*
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
    */

    /**
     * Removes the Card sprites and clears the Player's program.
     */
    /*@Override
    public void clearCards() {
        cardSprites.clear();
        for (Player player : playerList) {
            player.clearCards();
        }
    }*/

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


        // PLAYER:
        for (Player player : playerList) {
            setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
        }
        //setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection());

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