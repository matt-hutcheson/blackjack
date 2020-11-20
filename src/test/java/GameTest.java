import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    Game game;
    Deck playingDeck;
    Player player1;
    Player player2;
    @Before
    public void before(){
        game = new Game();
        player1 = new Player("Lucky Bob");
        player2 = new Player("Unlucky Bob");
        playingDeck = new Deck();
        playingDeck.populate();
        playingDeck.shuffle();
    }

    @Test
    public void checkHighHand__player1() {
        Card card1 = new Card(SuitType.HEARTS, RankType.TEN);
        Card card2 = new Card(SuitType.CLUBS, RankType.NINE);
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Lucky Bob Wins with 10 which beats 9", game.getHighHand(player1, player2));
    }
    @Test
    public void checkHighHand__player2() {
        Card card1 = new Card(SuitType.HEARTS, RankType.TWO);
        Card card2 = new Card(SuitType.CLUBS, RankType.NINE);
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Unlucky Bob Wins with 9 which beats 2", game.getHighHand(player1, player2));
    }
    @Test
    public void checkHighHand__draw() {
        Card card1 = new Card(SuitType.HEARTS, RankType.TEN);
        Card card2 = new Card(SuitType.CLUBS, RankType.TEN);
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Draw", game.getHighHand(player1, player2));
    }
}
