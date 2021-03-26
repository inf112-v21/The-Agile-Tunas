package card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Card implements ICard {
    private final CardType type;
    private final int priority;
    private final Sprite sprite;

    /**
     * Instanse of a Card in the CardDeck.
     * @param type The type of the Card
     * @param priority The Card's priority
     * @param texture The Texture for Card's Sprite.
     */
    public Card(CardType type, int priority, Texture texture){
        this.type = type;
        this.priority = priority;
        this.sprite = new Sprite(texture);
    }

    /**
     * @return the Card's type.
     */
    public CardType getType(){
        return type;
    }

    /**
     * @return the Card's priority.
     */
    public int getPriority(){
        return priority;
    }

    /**
     * @return the Card's sprite.
     */
    public Sprite getSprite() {
        return sprite;
    }
}
