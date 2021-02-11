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
    private Robot robot;
    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;
    private TextureRegion[][] pictureOne;
    private Vector2 playerPosStart;
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
        // 6f because it's in the middle of the viewportWidth.
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

        playerPosStart = new Vector2(5,0);
        // Making new player
        robot = new Robot(playerPosStart);

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
                playerLayer.setCell(playerPosX, playerPosY, playerCell);          // Puts a playerCell on (newX, playerPosY).
                playerLayer.setCell(playerPosX, playerPosY, null);           // Removes playerCell on (playerPosX, playerPosY).
            }
            return true;
        }
        // If the right arrow key is pressed:
        else if (keycode == Input.Keys.RIGHT) {
            if (playerPosX < 11) {
                robot.moveRight();
                playerLayer.setCell(playerPosX, playerPosY, playerCell);     // Puts a playerCell on (newX, playerPosY).
                playerLayer.setCell(playerPosX, playerPosY, null);      // Removes playerCell on (playerPosX, playerPosY).

            }
            return true;
        }
        // If the upwards arrow key is pressed:
        else if (keycode == Input.Keys.UP) {
            if (playerPosY < 15) {
                robot.moveUp();
                playerLayer.setCell(playerPosX, playerPosY, playerCell);         // Puts a playerCell on (playerPosX, newY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY).

            }
            return true;
        }
        // If the downwards arrow key is pressed:
        else if (keycode == Input.Keys.DOWN) {
            if (playerPosY > 0 ) {
                robot.moveDown();
                playerLayer.setCell(playerPosX, playerPosY, playerCell);         // Puts a playerCell on (playerPosX, newY).
                playerLayer.setCell(playerPosX, playerPosY, null);          // Removes playerCell on (playerPosX, playerPosY).

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
        // Player in start position
        playerLayer.setCell((int) playerPosStart.x, (int) playerPosStart.y,playerCell);

        // if player is on a hole
        TiledMapTileLayer.Cell hole = holesLayer.getCell((int) robot.position.x, (int) robot.position.y);

        // if player is on a flag
        TiledMapTileLayer.Cell flag = flagsLayer.getCell((int) robot.position.x, (int) robot.position.y);

        // If player is on a hole change player icon to defeat-icon.
        if (hole != null) {
            mapHandler.setCell((int) robot.position.x, (int) robot.position.y,Layers.PLAYER, playerDiedCell);
            //playerLayer.setCell((int) robot.position.x, (int) robot.position.y,playerDiedCell);
        }
        // If player is on a flag change player icon to victory-icon.
        if (flag != null) {
            playerLayer.setCell((int) robot.position.x, (int) robot.position.y,playerWonCell);
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