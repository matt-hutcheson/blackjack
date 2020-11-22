import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    Deck deck;
    Player player1;
    Player player2;
    Card cardTen;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        player2 = new Player("Unlucky Bob");
        deck = new Deck();
        deck.populate();
        deck.shuffle();
        game = new Game(deck);
        cardTen = new Card(SuitType.CLUBS, RankType.TEN);
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
        game.getPlayers().get(0).setStuck(true);
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

}
