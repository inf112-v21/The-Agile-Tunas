package inf112.skeleton.app;

public class Card implements ICard {
    private final CardType type;
    private final int priority;

    /**
     * Sets the instance's type to the given CardType, and the instance's priority to the given int.
     *
     * @param type
     * @param priority
     */
    Card(CardType type, int priority){
        this.type = type;
        this.priority = priority;
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

}
