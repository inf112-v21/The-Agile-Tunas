package inf112.skeleton.app;

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

public class HelloWorld extends InputAdapter implements ApplicationListener {
    // region Class Variable Initialization:
    private SpriteBatch batch;
    private BitmapFont font;

    // MAP:
    private OrthogonalTiledMapRenderer mapRenderer;
    private MapHandler mapHandler;

    // PLAYER CONFIG:
    private Robot robot;
    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;

    // endregion

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // MAP::
        this.mapHandler = new MapHandler("assets/riskyexchange.tmx");

        // CAMERA:
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 12, 16);
        // 6f because it's in the middle of the viewportWidth.
        camera.position.x = 6f;
        camera.update();

        // RENDERER:
        mapRenderer = new OrthogonalTiledMapRenderer(mapHandler.tiledMap, (float) 1/300);
        mapRenderer.setView(camera);

        // PLAYER CONFIG:
        Texture pictureAll = new Texture("assets/player.png");
        TextureRegion[][] pictureOne = new TextureRegion().split(pictureAll, 300, 300);
        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][0]));
        this.playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][2]));
        this.playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][1]));
        Vector2 playerPosition = mapHandler.getStartingPositions().get(0);
        robot = new Robot(playerPosition);          // Instantiating a player Robot.

        // INPUT:
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyUp(int keycode) {
        // Current player coordinates:
        int playerPosX = (int) robot.position.x;
        int playerPosY = (int) robot.position.y;

        // If the left arrow key is pressed:
        if (keycode == Input.Keys.LEFT) {
            if (playerPosX > 0) {
                robot.moveLeft();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);            // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the right arrow key is pressed:
        else if (keycode == Input.Keys.RIGHT) {
            if (playerPosX < mapHandler.getMapWidth()-1) {
                robot.moveRight();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the upwards arrow key is pressed:
        else if (keycode == Input.Keys.UP) {
            if (playerPosY < mapHandler.getMapHeight()-1) {
                robot.moveUp();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the downwards arrow key is pressed:
        else if (keycode == Input.Keys.DOWN) {
            if (playerPosY > 0 ) {
                robot.moveDown();
                mapHandler.setCell(playerPosX,playerPosY,Layers.PLAYER,null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        return false;
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        // PLAYER:
        mapHandler.setCell((int) robot.position.x, (int) robot.position.y, Layers.PLAYER, playerCell);

        // HOLE AND FLAG CELL:
        TiledMapTileLayer.Cell hole = mapHandler.getCell((int) robot.position.x, (int) robot.position.y, Layers.HOLES);
        TiledMapTileLayer.Cell flag = mapHandler.getCell((int) robot.position.x, (int) robot.position.y, Layers.FLAGS);

        // If player is on a hole change player icon to defeat-icon.
        if (hole != null) {
            mapHandler.setCell((int) robot.position.x, (int) robot.position.y,Layers.PLAYER, playerDiedCell);
            //playerLayer.setCell((int) robot.position.x, (int) robot.position.y,playerDiedCell);
        }
        // If player is on a flag change player icon to victory-icon.
        if (flag != null) {
            mapHandler.setCell((int) robot.position.x, (int) robot.position.y,Layers.PLAYER, playerWonCell);
            //playerLayer.setCell((int) robot.position.x, (int) robot.position.y,playerWonCell);
        }
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