package inf112.skeleton.app;

public class Card {
    private final CardTypes type;
    private final int priority;

    Card(CardTypes type, int priority){
        this.type = type;
        this.priority = priority;
    }

    public CardTypes getType(){
        return type;
    }

    public int getPriority(){
        return priority;
    }

}
