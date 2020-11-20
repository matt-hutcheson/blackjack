import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public Card getCard(int index){
        return this.hand.get(index);
    }

    public void clearHand() {
        this.hand.clear();
    }
}
