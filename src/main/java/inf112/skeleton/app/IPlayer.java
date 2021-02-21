package inf112.skeleton.app;

import java.util.ArrayList;

public interface IPlayer {
    Robot getRobot();
    // int getRobotID();

    void setProgram(ArrayList<CardType> programCards);

    ArrayList<CardType> getProgram();

    ArrayList<CardType> getDeck();

    void setPowerDown();

    String getName();
    // int getPlayerID();
}