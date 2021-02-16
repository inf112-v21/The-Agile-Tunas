package inf112.skeleton.app;

import java.util.ArrayList;

public interface IPlayer {

    Robot getRobot();
    // int getRobotID();

    void setProgram(ArrayList<Card> programCards);

    ArrayList<Card> getProgram();

    ArrayList<Card> getDeck();

    void setPowerDown();

    String getName();
    // int getPlayerID();
}
