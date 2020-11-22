import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Integer> results;
    private Boolean bust;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.results = new ArrayList<>();
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

    public void calcHandScores() {
        results.clear();
        Integer result = 0;
        results.add(result);
        ArrayList<Integer> loopResults = new ArrayList<>(results);
        for (Card card : hand) {
            for (int i=0; i < loopResults.size(); i++){
                results.set(i, results.get(i) + card.getValue());
                if (results.size() > 1) {
                    for (int j = results.size()-1; j > 0; j--) {
                        results.set(j, results.get(j) + card.getValue());
                    }
                }
                if (card.getRank() == RankType.ACE) {
                    int newResult = results.get(results.size()-1) + 10;
                    results.add(newResult);
                }
            }
        }
    }

    public ArrayList<Integer> getResults(){
        return results;
    }

    public Boolean getBust(){
        return this.bust;
    }

    public void checkIfBust(){
        ArrayList<Boolean> busts = new ArrayList<>();
        for (Integer result : results) {
            if (result > 21) {
                busts.add(true);
            } else {
                busts.add(false);
            }
        }
        boolean allEqual = new HashSet<>(busts).size() <= 1;
        if (allEqual){
            this.bust = busts.get(0);
        } else {
            this.bust = false;
        }
    }
}
