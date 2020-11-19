public class Player {
    private String name;
    private Card hand;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public Card getCard(){
        return this.hand;
    }
    public void addCard(Card card){
        this.hand = card;
    }

    public void clearHand() {
        this.hand = null;
    }
}
