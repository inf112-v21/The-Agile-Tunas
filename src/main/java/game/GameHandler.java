package game;

import card.Card;
import card.CardDeck;
import card.CardType;
import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.MapRenderer;
import map.ConveyorBelt;
import map.Layers;
import map.MapHandler;
import player.Direction;
import player.Player;
import player.Robot;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class that handles game logic.
 */
public class GameHandler extends Game implements InputProcessor {
    // Class Variable Initialization:
    SpriteBatch batch;
    BitmapFont font;

    // CAMERA:
    OrthographicCamera camera;
    Vector3 temp;

    // CARD SPRITE LIST:
    ArrayList<Sprite> cardSprites;

    // MAP:
    public OrthogonalTiledMapRenderer mapRenderer;
    public MapHandler mapHandler;

    // PLAYER CONFIG:
    private Player player;

    // CARDS:
    public CardDeck cardDeck;
    public List<CardType> forwardMoves;

    // BOOLEANS:
    public boolean chooseProgram = true;      // needed so that the game only plays for one "round".
    boolean allRobotsReady = false;

    public GameState state;
    public int phaseNum = 0;

    /**
     * Sets mapHandler, camera, mapRenderer, the icons for the player,
     * the player position, the robot for the player, and input processor.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // CAMERA:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.y = 200;
        camera.update();
        temp = new Vector3();

        // MAP:
        this.mapHandler = new MapHandler("assets/riskyexchange.tmx");

        // Prepare for turn:
        createDeck();
        forwardMoves = Arrays.asList(CardType.MOVE_ONE, CardType.MOVE_TWO, CardType.MOVE_THREE, CardType.BACK_UP);

        // INPUT:
        Gdx.input.setInputProcessor(this);

        // RENDERER:
        mapRenderer = new OrthogonalTiledMapRenderer(mapHandler.tiledMap,(float) 1/8);
        //Gdx.graphics.setContinuousRendering(false);
        //Gdx.graphics.requestRendering();

        state = GameState.SETUP;

        this.player = initiatePlayer(1);
    }

    /**
     * Initializes the CardDeck for the Game.
     */
    public void createDeck() {
        this.cardDeck = new CardDeck();
    }

    /**
     * Creates a new Player with given ID, and gives it a Robot. Also adds the Player to the list of players.
     * @param i The Player ID.
     */
    public Player initiatePlayer(int i) {
        Vector2 playerPosition = mapHandler.getStartingPositions().get(i-1);
        Player newPlayer = new Player(new Robot(playerPosition, Direction.NORTH, i), i);
        setPlayerPosition(newPlayer, (int) newPlayer.getRobot().getPosition().x, (int) newPlayer.getRobot().getPosition().y, newPlayer.getRobot().getDirection(), 0);

        return newPlayer;
    }

    public Player getMyPlayer() {
        return this.player;
    }

    /**
     * @return the MapHandler for this game.
     */
    public MapHandler getMapHandler() {
        return mapHandler;
    }

    /**
     * @return the MapRenderer for this game.
     */
    public MapRenderer getMapRenderer() {
        return mapRenderer;
    }

    /**
     * Takes a keycode which corresponds to one of the four arrow keys on the keyboard.
     * Then moves the robot accordingly by calling one the movement-methods in the Robot class,
     * and sets the cell on the position the robot was on before moving, to null.
     *
     * @param keycode of arrow.
     * @return true if a key has been pressed, else false.
     */
    @Override
    public boolean keyUp(int keycode) {
        // Current player coordinates:
        int playerPosX = (int) player.getRobot().getPosition().x;
        int playerPosY = (int) player.getRobot().getPosition().y;

        // If the left arrow key is pressed:
        if (keycode == Input.Keys.LEFT) {
            if (playerPosX > 0) {
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), Direction.WEST)) {
                    player.getRobot().moveWest(1);
                    mapHandler.setCell(playerPosX,playerPosY, Layers.PLAYER,null);            // Removes playerCell on (playerPosX, playerPosY).
                }
            }
            return true;
        }
        // If the right arrow key is pressed:
        else if (keycode == Input.Keys.RIGHT) {
            if (playerPosX < mapHandler.getMapWidth()-1) {
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), Direction.EAST)) {
                    player.getRobot().moveEast(1);
                    mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
                }
            }
            return true;
        }
        // If the upwards arrow key is pressed:
        else if (keycode == Input.Keys.UP) {
            if (playerPosY < mapHandler.getMapHeight()-1) {
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), Direction.NORTH)) {
                    player.getRobot().moveNorth(1);
                    mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
                }
            }
            return true;
        }
        // If the downwards arrow key is pressed:
        else if (keycode == Input.Keys.DOWN) {
            if (playerPosY > 0 ) {
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), Direction.SOUTH)) {
                    player.getRobot().moveSouth(1);
                    mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
                }
            }
            return true;
        }
        else if (keycode == Input.Keys.W) {
            player.getRobot().changeDirection(Direction.NORTH);
            return true;
        }

        else if (keycode == Input.Keys.A) {
            player.getRobot().changeDirection(Direction.WEST);
            return true;
        }

        else if (keycode == Input.Keys.S) {
            player.getRobot().changeDirection(Direction.SOUTH);
            return true;
        }

        else if (keycode == Input.Keys.D) {
            player.getRobot().changeDirection(Direction.EAST);
            return true;
        }

        return false;
    }

    /**
     * Handles the game logic.
     */
    public void gameLogic() {
        switch(state) {
            case SETUP:
                startRound();
                break;
            case PROGRAMMING:
                chooseProgram = true;
                if (getMyPlayer().getProgram().size() == 5) {
                    chooseProgram = false;
                    allRobotsReady = true;

                    if (allRobotsReady) {
                        this.state = GameState.PHASES;
                    }
                }
                phaseNum = 1;
                break;
            case PHASES:
                chooseProgram = false;
                while (phaseNum <= 5) {
                    this.doPhase();
                }
                endPhases();
                this.state = GameState.SETUP;
                break;
        }
    }

    /**
     * Shoot lasers.
     */
    public void doLasers() {
        // TODO: Implement lasers
    }

    /**
     * Move robots on conveyor belts.
     */
    public void doConveyorBelts(Player player) {
        Vector2 position = player.getRobot().getPosition();
        Direction direction = player.getRobot().getDirection();

        TiledMapTileLayer.Cell conveyorCell = getMapHandler().getCell((int) position.x, (int) position.y, Layers.CONVEYORS);

        if (!conveyorCell.equals(null)){
            for (Direction dir : direction.getAllDirections()) {
                ConveyorBelt conveyor = getMapHandler().checkForConveyor(position, dir);
                if (!conveyor.equals(null)) {
                    moveRobot(player, conveyor);
                }
            }
        }
    }

    /**
     * Move the given Player's Robot according to the type of ConveyorBelt.
     * @param player
     * @param conveyor
     */
    private void moveRobot(Player player, ConveyorBelt conveyor) {
        // TODO: Implement conveyor belts
        switch(conveyor) {
            case CONVEYOR_NORTH:
            case CONVEYOR_SOUTH:
            case CONVEYOR_EAST:
            case CONVEYOR_WEST:
            case EXPRESS_CONVEYOR_NORTH:
            case EXPRESS_CONVEYOR_SOUTH:
            case EXPRESS_CONVEYOR_EAST:
            case EXPRESS_CONVEYOR_WEST:
        }
    }

    /**
     * Stars a round.
     */
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

    public void nextPhase() {
        phaseNum++;
    }

    /**
     * Shows the card that have been given to player at the start of a round.
     */
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
     * @return the current game's deck.
     */
    public CardDeck getDeck() {
        if (!cardDeck.equals(null)){
            return cardDeck;
        }
        else
            System.out.println("Empty CardDeck");
        return null;
    }

    /**
     * Does the moves corresponding to the cards in Player's program.
     */
    public void doPhase() {
        Card programCard = getMyPlayer().getProgram().get(phaseNum-1);
        doMove(getMyPlayer(), programCard.getType());
        doConveyorBelts(getMyPlayer());
        doLasers();
        nextPhase();

        //Gdx.graphics.requestRendering();
    }

    /**
     * Ends the phases.
     */
    public void endPhases() {
        if (cardSprites != null) {
            clearCards();
        }
    }

    /**
     * Makes the given Player do a move/action, corresponding to the given Card.
     * @param player The Player we want to move / do action for
     * @param cardType The CardType which the move/action corresponds to
     */
    public void doMove(Player player, CardType cardType) {
        switch(cardType) {
            case ROTATE_LEFT:
                player.getRobot().doMove(CardType.ROTATE_LEFT);
                setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                return;
            case ROTATE_RIGHT:
                player.getRobot().doMove(CardType.ROTATE_RIGHT);
                //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                return;
            case U_TURN:
                player.getRobot().doMove(CardType.U_TURN);
                //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                return;
            case MOVE_ONE:
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), player.getRobot().getDirection())) {
                    getMapHandler().setCell((int) player.getRobot().getPosition().x,(int) player.getRobot().getPosition().y,Layers.PLAYER,null);
                    player.getRobot().doMove(cardType);
                    setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                    //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                }
                return;
            case MOVE_TWO:
                for (int i=0; i<2; i++) {
                    if (getMapHandler().canMoveForward(player.getRobot().getPosition(), player.getRobot().getDirection())) {
                        getMapHandler().setCell((int) player.getRobot().getPosition().x,(int) player.getRobot().getPosition().y,Layers.PLAYER,null);
                        player.getRobot().doMove(CardType.MOVE_ONE);
                        setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                        //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                    }
                }
                return;
            case MOVE_THREE:
                for (int i=0; i<3; i++) {
                    if (getMapHandler().canMoveForward(player.getRobot().getPosition(), player.getRobot().getDirection())) {
                        getMapHandler().setCell((int) player.getRobot().getPosition().x,(int) player.getRobot().getPosition().y,Layers.PLAYER,null);
                        player.getRobot().doMove(CardType.MOVE_ONE);
                        setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                        //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                    }
                }
                return;
            case BACK_UP:
                Direction dir = player.getRobot().getDirection();
                if (getMapHandler().canMoveForward(player.getRobot().getPosition(), dir.getOppositeDirection(dir))) {
                    getMapHandler().setCell((int) player.getRobot().getPosition().x,(int) player.getRobot().getPosition().y,Layers.PLAYER,null);
                    player.getRobot().doMove(CardType.BACK_UP);
                    setPlayerPosition(player, (int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, player.getRobot().getDirection(), 0);
                    //getMapHandler().setCell((int) player.getRobot().getPosition().x, (int) player.getRobot().getPosition().y, Layers.PLAYER, player.getCells().get(0));
                }
                return;
            default:
                System.out.println("Invalid Card type.");
                return;
        }
    }

    /**
     * Checks is given Player is outside the map.
     * @param player
     * @return
     */
    public boolean isOutsideMap(Player player) {
        // TODO: Implement checking if outside map
        return false;
    }

    /**
     * Removes the Card sprites and clears the Player's program.
     */
    public void clearCards() {
        cardSprites.clear();
        getMyPlayer().clearCards();
    }

    /**
     * Given a CardDeck, gets as many Cards as the number of Cards given,
     * from the CardDeck.
     * @param deck The given CardDeck
     * @param numberOfCards The numberOfCards
     * @return an ArrayList of Cards which have been taken from the CardDeck.
     */
    public ArrayList<Card> pullCards(CardDeck deck, int numberOfCards) {
        ArrayList<Card> cardList = new ArrayList<>();
        for(int i=0; i < numberOfCards; i++) {
            Card card = deck.getCard();
            cardList.add(card);
        }
        return cardList;
    }

    /**
     * Pulls 9 cards from current game's CardDeck and adds them to the player's hand.
     * @param player The Player given
     */
    public void giveCardsToPlayer(Player player) {
        ArrayList<Card> cards = pullCards(getDeck(), 9);
        player.addToHand(cards);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();

        for (Sprite sprite : cardSprites) {
            sprite.getTexture().dispose();
        }
    }

    /**
     * Sets the given player to the position given.
     * @param player The Player to set to a position.
     * @param x x-coordinate of the position.
     * @param y y-coordinate of the position.
     */
    public void setPlayerPosition(Player player, int x, int y, Direction dir, int i) {
        int rotation = dir.getRotation(dir);
        getMapHandler().setCell(x, y, Layers.PLAYER, player.getCells().get(i));
        getMapHandler().getCell(x, y, Layers.PLAYER).setRotation(rotation);
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

        gameLogic();

        setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection(), 0);

        // DRAW CARD SPRITES ON SCREEN:
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(Sprite sprite : cardSprites) {
            sprite.draw(batch);
        }
        batch.end();

        // HOLE AND FLAG CELL:
        TiledMapTileLayer.Cell hole = getMapHandler().getCell((int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, Layers.HOLES);
        TiledMapTileLayer.Cell flag = getMapHandler().getCell((int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, Layers.FLAGS);

        // If player is on a hole change player icon to defeat-icon.
        if (hole != null) {
            setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection(),2);
        }
        // If player is on a flag change player icon to victory-icon.
        if (flag != null) {
            setPlayerPosition(getMyPlayer(), (int) getMyPlayer().getRobot().getPosition().x, (int) getMyPlayer().getRobot().getPosition().y, getMyPlayer().getRobot().getDirection(),1);
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
     * @return boolean true or false (find out why!)
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY,0);
        camera.unproject(temp);
        int numberOfProgramCards = getMyPlayer().getProgram().size();

        if (chooseProgram) {
            for (Card card : getMyPlayer().getCardHand()) {
                Sprite sprite = card.getSprite();
                if (sprite.getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5
                        && !getMyPlayer().getProgram().contains(card)) {
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
        Gdx.graphics.requestRendering();
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}