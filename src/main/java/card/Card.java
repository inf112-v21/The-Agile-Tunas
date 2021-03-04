package card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Card implements ICard {
    private final CardType type;
    private final int priority;
    private Sprite sprite;

    /**
     * Sets the instance's type to the given CardType, and the instance's priority to the given int.
     *
     * @param type
     * @param priority
     */
    public Card(CardType type, int priority, Texture texture){
        this.type = type;
        this.priority = priority;
        this.sprite = new Sprite(texture);
    }

    /**
     * Returns the card's type.
     *
     * @return
     */
    public CardType getType(){
        return type;
    }

    /**
     * Returns the card's priority.
     *
     * @return
     */
    public int getPriority(){
        return priority;
    }

    /**
     * Returns the card's sprite.
     * @return
     */
    public Sprite getSprite() {
        return sprite;
    }
}
