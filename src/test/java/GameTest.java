import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    Game game;
    Deck deck;
    Player player1;
    Player player2;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        player2 = new Player("Unlucky Bob");
        deck = new Deck();
        deck.populate();
        deck.shuffle();
        game = new Game(deck);
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

}
