public class Game {

    public Game(){

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
