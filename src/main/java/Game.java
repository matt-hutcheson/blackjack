import java.util.ArrayList;

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
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public void dealCard(Player player){
        player.addCard(deck.deal());
    }

}
