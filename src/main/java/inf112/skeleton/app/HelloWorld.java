package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class HelloWorld implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;
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

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        TiledMap map = new TmxMapLoader().load("assets/riskyexchange.tmx");
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

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 12, 16);
        //6f because it's in the middle of the viewportWidth.
        camera.position.x = 6f;
        camera.update();

        mapRenderer = new OrthogonalTiledMapRenderer(map, (float) 1/300);
        mapRenderer.setView(camera);

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
