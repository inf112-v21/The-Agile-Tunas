package player;

import card.Card;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public interface IPlayer {
    Robot getRobot();
    
    ArrayList<TiledMapTileLayer.Cell> getCells();

    void addToProgram(Card card);

    ArrayList<Card> getProgram();

    int getID();

    void clearCards();

    ArrayList<Card> getCardHand();

    void addToHand(ArrayList<Card> cards);

    void removeFromHand(Card card);

}