package inf112.skeleton.app;

import java.util.ArrayList;

public interface IPlayer {

    Robot getRobot();
    // int getRobotID();

    void setProgram(ArrayList<CardTypes> programCards);

    ArrayList<CardTypes> getProgram();

    ArrayList<CardTypes> getDeck();

    void setPowerDown();

    String getName();
    // int getPlayerID();
}
