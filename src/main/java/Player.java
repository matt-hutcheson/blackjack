import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Integer> results;

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
        Integer result = 0;
        results.add(result);
        for (Card card : hand) {
            for (Integer aceResult: results){
                if (card.getRank() != RankType.ACE) {
                    results.set(results.indexOf(aceResult), aceResult + card.getValue());
                } else {
                    for (Integer newAceResult: results) {
                        results.set(results.indexOf(newAceResult), newAceResult + card.getValue());
                        Integer newResult = aceResult + 10;
                        results.add(newResult);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getResults(){
        return results;
    }
}
