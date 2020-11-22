import java.util.ArrayList;
import java.util.HashSet;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    public Game(Deck deck){
        this.deck = deck;
        players = new ArrayList<>();
    }
    public void addPlayer(Player player){
        this.players.add(player);
    }
    public void addDealer(){
        Player dealer = new Player("Dealer");
        dealer.setDealer();
        this.players.add(dealer);
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void dealCard(Player player){
        player.addCard(deck.deal());
    }
    public boolean checkRoundFinished(){
        ArrayList<Boolean> endedTurn = new ArrayList<>();
        for (Player player : players){
            if (player.getBust() || player.getStuck()){
                endedTurn.add(true);
            } else {
                endedTurn.add(false);
            }
        }
        return !endedTurn.contains(false);
    }

}
