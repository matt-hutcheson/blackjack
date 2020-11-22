import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Player dealer;
    public Game(Deck deck){
        this.deck = deck;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void addDealer(){
        dealer = new Player("Dealer");
        dealer.setDealer();
        this.players.add(dealer);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Player getDealer(){
        return dealer;
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

    public void getResults(){
        for (Player player: players){
            player.calcHandScores();
            player.checkIfBust();
            if (player.getStuck() && !player.getBust()){
                player.getBestScore();
            }
        }
    }
}
