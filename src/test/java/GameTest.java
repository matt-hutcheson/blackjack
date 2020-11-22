import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    Deck deck;
    Player player1;
    Player player2;
    Card cardTen;
    Card cardAce;
    Card cardEight;
    Card cardSix;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        player2 = new Player("Unlucky Bob");
        deck = new Deck();
        deck.populate();
        deck.shuffle();
        game = new Game(deck);
        cardTen = new Card(SuitType.CLUBS, RankType.TEN);
        cardAce = new Card(SuitType.DIAMONDS, RankType.ACE);
        cardEight = new Card(SuitType.HEARTS, RankType.EIGHT);
        cardSix = new Card(SuitType.SPADES, RankType.SIX);
    }
    @Test
    public void canAddPlayerToGame(){
        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());
    }
    @Test
    public void canDealCardToPlayer(){
        game.addPlayer(player1);
        game.dealCard(player1);
        assertEquals(51, deck.countDeck());
        assertEquals(1, player1.getHand().size());
    }
    @Test
    public void canAddDealer(){
        game.addDealer();
        assertTrue(game.getPlayers().get(0).getDealer());
    }
    @Test
    public void canCheckIfAllStuckOrBust__True(){
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.getDealer().setStuck(true);
        player1.setStuck(true);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player2.calcHandScores();
        player2.checkIfBust();
        assertTrue(game.checkRoundFinished());
    }
    @Test
    public void canCheckIfAllStuckOrBust__False(){
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.getPlayers().get(0).setStuck(true);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player2.calcHandScores();
        player2.checkIfBust();
        assertFalse(game.checkRoundFinished());
    }
    @Test
    public void canCheckWinner__Player1Player2(){
        HashMap<String, ArrayList<Player>> results;
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.getDealer().addCard(cardEight);
        game.getDealer().addCard(cardSix);
        game.getDealer().addCard(cardTen);
        player1.addCard(cardAce);
        player1.addCard(cardTen);
        player2.addCard(cardTen);
        player2.addCard(cardEight);
        player1.setStuck(true);
        player2.setStuck(true);
        game.getDealer().calcHandScores();
        player1.calcHandScores();
        player2.calcHandScores();
        game.calcResults();
        assertEquals(player1, game.getResults().get("Winners").get(0));
        assertEquals(player2, game.getResults().get("Winners").get(1));
    }
    @Test
    public void canCheckWinner__Dealer(){
        HashMap<String, ArrayList<Player>> results;
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.getDealer().addCard(cardAce);
        game.getDealer().addCard(cardTen);
        game.getDealer().setStuck(true);
        player1.addCard(cardTen);
        player1.addCard(cardEight);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player1.setStuck(true);
        player2.setStuck(true);
        game.getDealer().calcHandScores();
        player1.calcHandScores();
        player2.calcHandScores();
        game.calcResults();
        assertEquals(player1, game.getResults().get("Losers").get(0));
        assertEquals(player2, game.getResults().get("Losers").get(1));
    }
    @Test
    public void canCheckWinner__Draw(){
        HashMap<String, ArrayList<Player>> results;
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.getDealer().addCard(cardAce);
        game.getDealer().addCard(cardTen);
        game.getDealer().setStuck(true);
        player1.addCard(cardTen);
        player1.addCard(cardAce);
        player2.addCard(cardTen);
        player2.addCard(cardTen);
        player1.setStuck(true);
        player2.setStuck(true);
        game.getDealer().calcHandScores();
        player1.calcHandScores();
        player2.calcHandScores();
        game.calcResults();
        assertEquals(player1, game.getResults().get("Draws").get(0));
        assertEquals(player2, game.getResults().get("Losers").get(0));
    }
    @Test
    public void checkDealerAI(){
        game.addDealer();
        game.addPlayer(player1);
        game.addPlayer(player2);
        player1.addCard(cardTen);
        player1.addCard(cardSix);
        player2.addCard(cardEight);
        player2.addCard(cardTen);
        game.getDealer().addCard(cardSix);
        game.getDealer().addCard(cardEight);
        player1.setStuck(true);
        player2.setStuck(true);
        player1.calcHandScores();
        player2.calcHandScores();
        game.getDealer().calcHandScores();
        game.dealerTurn();
        ArrayList<Boolean> turnEnded = new ArrayList<>();
        turnEnded.add(game.getDealer().getBust());
        turnEnded.add(game.getDealer().getStuck());
        boolean actual = turnEnded.contains(true);
        assertTrue(actual);
    }
}
