package screens;


public class LoadingScreen extends AbstractScreen {

    public LoadingScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void render(float delta) {
        parent.changeScreen(ScreenOrchestrator.MENU);
    }
}
