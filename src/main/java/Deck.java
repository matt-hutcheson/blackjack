import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> playingDeck;
    private Card card;

    public Deck(){
        this.playingDeck = new ArrayList<>();
    }
    public int countDeck(){
        return this.playingDeck.size();
    }

    public void populate() {
        for(SuitType suit :  SuitType.values()) {
            for(RankType rank : RankType.values()) {
                 card = new Card(suit, rank);
                 playingDeck.add(card);
            }
        }
    }
    public void shuffle(){
        Collections.shuffle(playingDeck);
    }
    public Card deal(){
        Card card;
        return card = this.playingDeck.remove(0);
    }

}
