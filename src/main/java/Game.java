import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Player dealer;
    private HashMap<String, ArrayList<Player>> results;
    public Game(Deck deck){
        this.deck = deck;
        this.players = new ArrayList<>();
        this.results = new HashMap<>();
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
        if (this.deck.countDeck() > 0) {
            player.addCard(deck.deal());
        } else {
            this.deck.populate();
            this.deck.shuffle();
            System.out.println("Previous deck empty. New deck shuffled and put on deck.");
            player.addCard(deck.deal());
        }
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

    public boolean checkDraw(Player player){
        boolean draw = false;
        if (this.dealer.getBestScore() == player.getBestScore()){
            draw = true;
        }
        return draw;
    }

    public void calcResults(){
        ArrayList<Player> wins = new ArrayList<>();
        ArrayList<Player> draws = new ArrayList<>();
        ArrayList<Player> losses = new ArrayList<>();
        for (int i = 1; i < players.size(); i++){
            if (!players.get(i).getBust()){
                if (players.get(i).getBestScore() > dealer.getBestScore()){
                    wins.add(players.get(i));
                } else if (checkDraw(players.get(i))){
                    draws.add(players.get(i));
                } else {
                    losses.add(players.get(i));
                }
            } else {
                losses.add(players.get(i));
            }
        }
        results.put("Winners", wins);
        results.put("Draws", draws);
        results.put("Losers", losses);
    }

    public HashMap<String, ArrayList<Player>> getResults(){
        return this.results;
    }

    public void dealerTurn(){
        int bestScore = 0;
        for (Player player: players){
            if (player.getBestScore() > bestScore){
                bestScore = player.getBestScore();
            }
        }
        do {
            if (this.dealer.getBestScore() < 16 || this.dealer.getBestScore() < bestScore) {
                dealCard(this.dealer);
            }
            this.dealer.calcHandScores();
            this.dealer.checkIfBust();
            if (this.dealer.getBestScore() >= bestScore && !this.dealer.getBust()) {
                this.dealer.setStuck(true);
            }
        } while (!this.dealer.getStuck() && !this.dealer.getBust());
    }

    public void nextTurnReset(){
        this.results.clear();
        for (Player player: players){
            player.getResults().clear();
            player.setStuck(false);
            player.setBust(false);
            player.clearHand();
            player.getResults().clear();
        }
    }
}
