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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class HelloWorld extends InputAdapter implements ApplicationListener {
    // region Class Variable Initialization:
    private SpriteBatch batch;
    private BitmapFont font;

    // TiledMap Layers:
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer holesLayer;
    private TiledMapTileLayer flagsLayer;
    private TiledMapTileLayer boardLayer;
    private TiledMapTileLayer startsPositionsLayer;
    private TiledMapTileLayer gearsLayer;

    private TiledMapTileLayer expressConveyorBeltDownLayer;
    private TiledMapTileLayer expressConveyorBeltRightLayer;
    private TiledMapTileLayer conveyorBeltUpLayer;
    private TiledMapTileLayer conveyorBeltDownLayer;
    private TiledMapTileLayer conveyorBeltRightLayer;
    private TiledMapTileLayer conveyorBeltLeftLayer;
    private TiledMapTileLayer conveyorBeltSwingDownLayer;
    private TiledMapTileLayer conveyorBeltSwingLeftLayer;
    private TiledMapTileLayer conveyorBeltSwingRightLayer;

    private TiledMapTileLayer wrenchLayer;
    private TiledMapTileLayer wrenchHammerLayer;
    private TiledMapTileLayer laserbeamLayer;
    private TiledMapTileLayer laserLayer;
    private TiledMapTileLayer wallsLayer;

    private OrthogonalTiledMapRenderer mapRenderer;

    //Player config
    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;
    private TextureRegion[][] pictureOne;
    private Vector2 playerPos;

    // LISA: Player position stored in ArrayList. Can be implemented differently later.
    private ArrayList playerPosition;

    // endregion

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // TILEDMAP:
        TiledMap map = new TmxMapLoader().load("assets/riskyexchange.tmx");

        // region TILEDMAPTILELAYERS:
        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        holesLayer = (TiledMapTileLayer) map.getLayers().get("Holes");
        flagsLayer = (TiledMapTileLayer) map.getLayers().get("Flags");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        startsPositionsLayer = (TiledMapTileLayer) map.getLayers().get("StartPositions");
        gearsLayer = (TiledMapTileLayer) map.getLayers().get("Gears");
        expressConveyorBeltDownLayer = (TiledMapTileLayer) map.getLayers().get("ExpressConveyorBeltDown");
        expressConveyorBeltRightLayer = (TiledMapTileLayer) map.getLayers().get("ExpressConveyorBeltRight");
        conveyorBeltUpLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltUp");
        conveyorBeltDownLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltDown");
        conveyorBeltRightLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltRight");
        conveyorBeltLeftLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltLeft");
        conveyorBeltSwingDownLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltSwingDown");
        conveyorBeltSwingLeftLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltSwingLeft");
        conveyorBeltSwingRightLayer = (TiledMapTileLayer) map.getLayers().get("ConveyorBeltSwingRight");
        wrenchLayer = (TiledMapTileLayer) map.getLayers().get("Wrench");
        wrenchHammerLayer = (TiledMapTileLayer) map.getLayers().get("WrenchHammer");
        laserbeamLayer = (TiledMapTileLayer) map.getLayers().get("Laserbeam");
        laserLayer = (TiledMapTileLayer) map.getLayers().get("Laser");
        wallsLayer = (TiledMapTileLayer) map.getLayers().get("Walls");
        // endregion

        // CAMERA:
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 12, 16);
        //6f because it's in the middle of the viewportWidth.
        camera.position.x = 6f;
        camera.update();

        // RENDERER:
        mapRenderer = new OrthogonalTiledMapRenderer(map, (float) 1/300);
        mapRenderer.setView(camera);

        // PLAYER CONFIG:
        Texture pictureAll = new Texture("assets/player.png");
        pictureOne = new TextureRegion().split(pictureAll,300,300);

        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][0]));
        this.playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][2]));
        this.playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(pictureOne[0][1]));

        playerPos = new Vector2(5,0);

        // INPUT:
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyUp(int keycode) {
        // Current player coordinates:
        int playerPosX = (int) playerPos.x;
        int playerPosY = (int) playerPos.y;

        // If the left arrow key is pressed:
        if (keycode == Input.Keys.LEFT) {
            if (playerPosX > 0) {
                int newX = playerPosX - 1;                                  // Calculating new x-coordinate.
                playerLayer.setCell(newX, playerPosY, playerCell);          // Puts a playerCell on (newX, playerPosY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY).
                playerPos.set(newX,playerPosY);                             // Sets the new position of player.
            }
            return true;
        }
        // If the right arrow key is pressed:
        else if (keycode == Input.Keys.RIGHT) {
            if (playerPosX < 11) {
                int newX = playerPosX + 1;                                  // Calculating new x-coordinate.
                playerLayer.setCell(newX, playerPosY, playerCell);          // Puts a playerCell on (newX, playerPosY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY)
                playerPos.set(newX,playerPosY);                             // Sets the new position of player.
            }
            return true;
        }
        // If the upwards arrow key is pressed:
        else if (keycode == Input.Keys.UP) {
            if (playerPosY < 15) {
                int newY = playerPosY + 1;                                  // Calculating new y-coordinate.
                playerLayer.setCell(playerPosX, newY, playerCell);          // Puts a playerCell on (playerPosX, newY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY)
                playerPos.set(playerPosX, newY);                            // Sets the new position of player.
            }
            return true;
        }
        // If the downwards arrow key is pressed:
        else if (keycode == Input.Keys.DOWN) {
            if (playerPosY > 0 ) {
                int newY = playerPosY - 1;                                  // Calculating new y-coordinate.
                playerLayer.setCell(playerPosX, newY, playerCell);          // Puts a playerCell on (playerPosX, newY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY)
                playerPos.set(playerPosX, newY);                            // Sets the new position of player.
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
        playerLayer.setCell((int) playerPos.x, (int) playerPos.y,playerCell);
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