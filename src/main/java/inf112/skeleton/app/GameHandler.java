package inf112.skeleton.app;

import card.Card;
import card.CardDeck;
import card.CardType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import map.Layers;
import map.MapHandler;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import player.Direction;
import player.Player;
import player.Robot;

public class GameHandler extends InputAdapter implements ApplicationListener {
    // Region Class Variable Initialization:
    private SpriteBatch batch;
    private BitmapFont font;

    // CAMERA:
    OrthographicCamera camera;
    private Vector3 temp;

    // CARD TEXTURES:
    private Texture backUpCard;
    private Texture move1card;
    private Texture move2card;
    private Texture move3card;
    private Texture rotateLeftCard;
    private Texture rotateRightCard;
    private Texture uTurnCard;

    // SPRITE LIST:
    ArrayList<Sprite> sprites;

    // MAP:
    private OrthogonalTiledMapRenderer mapRenderer;
    private MapHandler mapHandler;

    // PLAYER CONFIG:
    private Player player;
    private Robot robot;
    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;

    // CARD DECK:
    private CardDeck cardDeck;

    // TEMPORARY:
    ArrayList<Card> programCards;

    // End Region

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
        //camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0f);
        camera.position.y = 200;
        camera.update();
        temp = new Vector3();

        // MAP:
        this.mapHandler = new MapHandler("assets/riskyexchange.tmx");

        // RENDERER:
        mapRenderer = new OrthogonalTiledMapRenderer(mapHandler.tiledMap,(float) 1/8);

        // PLAYER CONFIG:
        Texture pictureAll = new Texture("assets/player.png");
        TextureRegion[][] pictureOne = new TextureRegion().split(pictureAll, 300, 300);
        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][0]));
        this.playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][2]));
        this.playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][1]));
        Vector2 playerPosition = mapHandler.getStartingPositions().get(0);
        robot = new Robot(playerPosition, Direction.NORTH);          // Instantiating a player Robot.
        player = new Player(robot, "Player1", 1, 1);

        // CARD TEXTURES:
        backUpCard = new Texture("assets/cards/backup.png");
        move1card = new Texture("assets/cards/move1.png");
        move2card = new Texture("assets/cards/move2.png");
        move3card = new Texture("assets/cards/move3.png");
        rotateLeftCard = new Texture("assets/cards/rotateleft.png");
        rotateRightCard = new Texture("assets/cards/rotateright.png");
        uTurnCard = new Texture("assets/cards/uturn.png");

        // PLAYER PROGRAM CARDS (temporary):
        programCards = new ArrayList<>();

        // CARD DECK:
        cardDeck = new CardDeck();
        cardDeck.shuffle();
        giveCardsToPlayer(player);

        // MAKING SPRITES:
        sprites = new ArrayList<>();
        for(int i=0; i<9; i++) {
            Card card = player.getCardHand().get(i);
            CardType type = card.getType();
            switch(type) {
                case MOVE_ONE:
                    sprites.add(i, new Sprite(move1card));
                    break;
                case MOVE_TWO:
                    sprites.add(i, new Sprite(move2card));
                    break;
                case MOVE_THREE:
                    sprites.add(i, new Sprite(move3card));
                    break;
                case BACK_UP:
                    sprites.add(i, new Sprite(backUpCard));
                    break;
                case ROTATE_LEFT:
                    sprites.add(i, new Sprite(rotateLeftCard));
                    break;
                case ROTATE_RIGHT:
                    sprites.add(i, new Sprite(rotateRightCard));
                    break;
                case U_TURN:
                    sprites.add(i, new Sprite(uTurnCard));
                    break;
                default:
                    System.out.println("No matching type :)");
            }
        }
        // SPRITES POSITION:
        sprites.get(0).setPosition(470, 450);
        sprites.get(1).setPosition(570, 450);
        sprites.get(2).setPosition(470, 300);
        sprites.get(3).setPosition(570, 300);
        sprites.get(4).setPosition(470, 150);
        sprites.get(5).setPosition(570, 150);
        sprites.get(6).setPosition(470, 0);
        sprites.get(7).setPosition(570, 0);
        sprites.get(8).setPosition(470, -150);

        // INPUT:
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Takes a keycode which corresponds to one of the four arrow keys on the keyboard.
     * Then moves the robot accordingly by calling one the movement-methods in the Robot class,
     * and sets the cell on the position the robot was on before moving, to null.
     *
     * @param keycode
     * @return true if a key has been pressed, else false.
     */
    @Override
    public boolean keyUp(int keycode) {
        // Current player coordinates:
        int playerPosX = (int) robot.getPosition().x;
        int playerPosY = (int) robot.getPosition().y;

        // If the left arrow key is pressed:
        if (keycode == Input.Keys.LEFT) {
            if (playerPosX > 0) {
                robot.moveWest();
                mapHandler.setCell(playerPosX,playerPosY, Layers.PLAYER,null);            // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the right arrow key is pressed:
        else if (keycode == Input.Keys.RIGHT) {
            if (playerPosX < mapHandler.getMapWidth()-1) {
                robot.moveEast();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the upwards arrow key is pressed:
        else if (keycode == Input.Keys.UP) {
            if (playerPosY < mapHandler.getMapHeight()-1) {
                robot.moveNorth();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the downwards arrow key is pressed:
        else if (keycode == Input.Keys.DOWN) {
            if (playerPosY > 0 ) {
                robot.moveSouth();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        return false;
    }

    /**
     * @returns the current game's deck.
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
     * Takes a CardDeck and gets the same number of Cards as numberOfCards parameter
     * from the CardDeck.
     * @param deck
     * @param numberOfCards
     * @returns an ArraList of Cards which have been taken from the CardDeck.
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
     * @param player
     */
    public void giveCardsToPlayer(Player player) {
        ArrayList<Card> cards = pullCards(getDeck(), 9);
        player.addToHand(cards);
        //ArrayList<Card> cardHand = player.getCardHand();
        //System.out.println(cardHand);
    }

    /**
     * Method for drawing a card. Not used atm.
     * @param batch
     * @param sprite
     */
    private void renderCard(SpriteBatch batch, Sprite sprite) {
        //sprite.setPosition(position * 100, -200);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        backUpCard.dispose();
        move1card.dispose();
        move2card.dispose();
        move3card.dispose();
        rotateLeftCard.dispose();
        rotateRightCard.dispose();
        uTurnCard.dispose();

        batch.dispose();
        font.dispose();
    }

    /**
     * Method for rendering.
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        // PLAYER:
        mapHandler.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, Layers.PLAYER, playerCell);

        batch.setProjectionMatrix(camera.combined);
        // DRAW CARDS ON SCREEN:
        batch.begin();
        sprites.get(0).draw(batch);
        sprites.get(1).draw(batch);
        sprites.get(2).draw(batch);
        sprites.get(3).draw(batch);
        sprites.get(4).draw(batch);
        sprites.get(5).draw(batch);
        sprites.get(6).draw(batch);
        sprites.get(7).draw(batch);
        sprites.get(8).draw(batch);
        batch.end();

        // HOLE AND FLAG CELL:
        TiledMapTileLayer.Cell hole = mapHandler.getCell((int) robot.getPosition().x, (int) robot.getPosition().y, Layers.HOLES);
        TiledMapTileLayer.Cell flag = mapHandler.getCell((int) robot.getPosition().x, (int) robot.getPosition().y, Layers.FLAGS);

        // If player is on a hole change player icon to defeat-icon.
        if (hole != null) {
            mapHandler.setCell((int) robot.getPosition().x, (int) robot.getPosition().y,Layers.PLAYER, playerDiedCell);
        }
        // If player is on a flag change player icon to victory-icon.
        if (flag != null) {
            mapHandler.setCell((int) robot.getPosition().x, (int) robot.getPosition().y,Layers.PLAYER, playerWonCell);
        }
    }

    /**
     *
     * @param character
     * @return
     */
    @Override public boolean keyTyped(char character) {
        return false;
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
        int numberOfProgramCards = programCards.size();

        if(sprites.get(0).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(0).setPosition(0, -200);
            }
            else {
                sprites.get(0).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.MOVE_ONE, 1));
            System.out.println("Touch on" + sprites.get(0).toString());
        }
        if(sprites.get(1).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(1).setPosition(0, -200);
            }
            else {
                sprites.get(1).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.MOVE_TWO, 1));
            System.out.println("Touch on" + sprites.get(1).toString());
        }
        if(sprites.get(2).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(2).setPosition(0, -200);
            }
            else {
                sprites.get(2).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.MOVE_THREE, 1));
            System.out.println("Touch on" + sprites.get(2).toString());
        }
        if(sprites.get(3).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(3).setPosition(0, -200);
            }
            else {
                sprites.get(3).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.BACK_UP, 1));
            System.out.println("Touch on" + sprites.get(3).toString());
        }
        if(sprites.get(4).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(4).setPosition(0, -200);
            }
            else {
                sprites.get(4).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.ROTATE_LEFT, 1));
            System.out.println("Touch on" + sprites.get(4).toString());
        }
        if(sprites.get(5).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(5).setPosition(0, -200);
            }
            else {
                sprites.get(5).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.ROTATE_RIGHT, 1));
            System.out.println("Touch on" + sprites.get(5).toString());
        }
        if(sprites.get(6).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(6).setPosition(0, -200);
            }
            else {
                sprites.get(6).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.U_TURN, 1));
            System.out.println("Touch on" + sprites.get(6).toString());
        }
        if(sprites.get(7).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(7).setPosition(0, -200);
            }
            else {
                sprites.get(7).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.U_TURN, 1));
            System.out.println("Touch on" + sprites.get(7).toString());
        }
        if(sprites.get(8).getBoundingRectangle().contains(temp.x,temp.y) && numberOfProgramCards<5) {
            if (numberOfProgramCards == 0) {
                sprites.get(8).setPosition(0, -200);
            }
            else {
                sprites.get(8).setPosition(numberOfProgramCards * 100, -200);
            }

            programCards.add(new Card(CardType.U_TURN, 1));
            System.out.println("Touch on" + sprites.get(8).toString());
        }
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
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}