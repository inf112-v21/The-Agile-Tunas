package card;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface ICard {

    CardType getType();

    int getPriority();

    Sprite getSprite();

}
