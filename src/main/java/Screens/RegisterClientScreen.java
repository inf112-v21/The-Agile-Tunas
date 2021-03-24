package Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import network.test.GameClient;

public class RegisterClientScreen extends AbstractScreen {

    private String hostAddress;
    private String nameString;

    public RegisterClientScreen(ScreenOrchestrator screenOrchestrator) {
        super(screenOrchestrator);
    }

    @Override
    public void show() {
        super.show();
        Label host = new Label("Address: ", skin);
        Label name = new Label("Name: ", skin);
        TextField tfAddress = new TextField("",skin);
        TextField tfName = new TextField("",skin);

        tfAddress.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        tfName.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        //Button for returning to the main menu
        final TextButton returnButton = new TextButton("Return", skin, "small");
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenOrchestrator.MULTIPLAYER);
            }
        });

        //Button for returning to the main menu
        final TextButton joinButton = new TextButton("Join", skin, "small");
        joinButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hostAddress = tfAddress.getText();
                nameString = tfName.getText();
                GameClient client = new GameClient(hostAddress, nameString);


            }
        });
        table.add(host);
        table.row().pad(10,0,0,10);
        table.add(tfAddress);
        table.row().pad(10,0,0,10);
        table.add(name);
        table.row().pad(10,0,0,10);
        table.add(tfName);
        table.row().pad(10,0,0,10);
        table.add(joinButton);
        table.row().pad(10,0,0,10);
        table.add(returnButton).colspan(2);
        table.row().pad(10,0,0,10);

    }

}
