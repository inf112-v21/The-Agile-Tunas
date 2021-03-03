package inf112.skeleton.app;

import card.Card;
import card.CardDeck;
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
import player.Direction;
import player.Player;
import player.Robot;

import java.util.ArrayList;
import java.util.Stack;

public class GameHandler extends InputAdapter implements ApplicationListener {
    // Region Class Variable Initialization:
    private SpriteBatch batch;
    private BitmapFont font;

    OrthographicCamera camera;

    //Programcards
    private Sprite backUp, move1,move2,move3,rotateLeft,rotateRight,uTurn;
    private Texture backUpCard;
    private Texture move1card;
    private Texture move2card;
    private Texture move3card;
    private Texture rotateLeftCard;
    private Texture rotateRightCard;
    private Texture uTurnCard;

    private Vector3 temp;

    // MAP:
    private OrthogonalTiledMapRenderer mapRenderer;
    private MapHandler mapHandler;

    // PLAYER CONFIG:
    private Robot robot;
    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;

    // CARD DECK:
    private CardDeck cardDeck;

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

        camera.position.y = 300;
        camera.update();

        // MAP:
        this.mapHandler = new MapHandler("assets/riskyexchange.tmx");
        // RENDERER:
        mapRenderer = new OrthogonalTiledMapRenderer(mapHandler.tiledMap,(float) 1/6);

        // CARD TEXTURES:
        backUpCard = new Texture("assets/cards/backup.png");
        move1card = new Texture("assets/cards/move1.png");
        move2card = new Texture("assets/cards/move2.png");
        move3card = new Texture("assets/cards/move3.png");
        rotateLeftCard = new Texture("assets/cards/rotateleft.png");
        rotateRightCard = new Texture("assets/cards/rotateright.png");
        uTurnCard = new Texture("assets/cards/uturn.png");

        temp = new Vector3();

        // CARD SPRITES:
        backUp = new Sprite(backUpCard);
        move1 = new Sprite(move1card);
        move2 = new Sprite(move2card);
        move3 = new Sprite(move3card);
        rotateLeft = new Sprite(rotateLeftCard);
        rotateRight = new Sprite(rotateRightCard);
        uTurn = new Sprite(uTurnCard);


        // SPRITES POSITION:
        backUp.setPosition(0,-200);
        move1.setPosition(100,-200);
        move2.setPosition(200,-200);
        move3.setPosition(300,-200);
        rotateLeft.setPosition(400,-200);
        rotateRight.setPosition(500,-200);
        uTurn.setPosition(600,-200);


        // PLAYER CONFIG:
        Texture pictureAll = new Texture("assets/player.png");
        TextureRegion[][] pictureOne = new TextureRegion().split(pictureAll, 300, 300);
        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][0]));
        this.playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][2]));
        this.playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][1]));
        Vector2 playerPosition = mapHandler.getStartingPositions().get(0);
        robot = new Robot(playerPosition, Direction.NORTH);          // Instantiating a player Robot.

        // INPUT:
        Gdx.input.setInputProcessor(this);

        // CARD DECK:
        cardDeck = new CardDeck();
        cardDeck.shuffle();
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

    public CardDeck getDeck() {
        return cardDeck;
    }

    public ArrayList<Card> pullCards(CardDeck deck, int numberOfCards) {
        ArrayList<Card> cardList = new ArrayList<Card>();
        for(int i=0; i < numberOfCards; i++) {
            Card card = deck.getCard();
            cardList.add(card);
        }
        return cardList;
    }

    public void giveCardsToPlayer(Player player) {
        ArrayList<Card> cards = pullCards(getDeck(), 9);
        player.addToHand(cards);
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
        backUp.draw(batch);
        move1.draw(batch);
        move2.draw(batch);
        move3.draw(batch);
        rotateLeft.draw(batch);
        rotateRight.draw(batch);
        uTurn.draw(batch);
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

    @Override public boolean keyTyped(char character) {
        return false;
    }

    // Register touch:
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        temp.set(screenX,screenY,0);
        camera.unproject(temp);

        if(backUp.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on backUp");

        if(move1.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on move1");

        if(move2.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on move2");

        if(move3.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on move3");

        if(rotateLeft.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on rotateLeft");

        if(rotateRight.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on rotateRight");

        if(uTurn.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on uTurn");

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