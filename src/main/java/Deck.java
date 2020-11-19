import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> playingDeck;
    private SuitType suit;
    private RankType rank;
    private Card card;
    private Player player1;
    private Player player2;

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
    public String getHighHand(Player player1, Player player2){
        if (player1.getCard().getValue() == player2.getCard().getValue()){
            return "Draw";
        } else if (player1.getCard().getValue() > player2.getCard().getValue()) {
            return player1.getName() + " Wins with " + player1.getCard().getValue() + " which beats " + player2.getCard().getValue();
        } else {
            return player2.getName() + " Wins with " + player2.getCard().getValue() + " which beats " + player1.getCard().getValue();
        }
    }
}
