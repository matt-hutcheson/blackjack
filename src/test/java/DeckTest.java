import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {
    Deck playingDeck;


    @Before
    public void before() {
        playingDeck = new Deck();
    }

    @Test
    public void deckShouldStartEmpty() {
        assertEquals(0, playingDeck.countDeck());
    }
    @Test
    public void canPopulateDeckOfCards(){
        this.playingDeck.populate();
        assertEquals(52, this.playingDeck.countDeck());
    }
    @Test
    public void canShuffleDeck(){
        Deck unshuffledDeck;
        unshuffledDeck = new Deck();

        unshuffledDeck.populate();
        this.playingDeck.populate();
        this.playingDeck.shuffle();
        assertNotSame("These decks do not match", this.playingDeck, unshuffledDeck);
        assertEquals(52, this.playingDeck.countDeck());
        assertEquals(52, unshuffledDeck.countDeck());
    }

    @Test
    public void canDealCard(){
        this.playingDeck.populate();
        this.playingDeck.shuffle();
        this.playingDeck.deal();
        assertEquals(51, this.playingDeck.countDeck());
    }

    @Test
    public void checkHighHand__player1() {
        //check highHand
        Card card1 = new Card(SuitType.HEARTS, RankType.TEN);
        Card card2 = new Card(SuitType.CLUBS, RankType.NINE);
        Player player1 = new Player("Lucky Bob");
        Player player2 = new Player("Unlucky Bob");
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Lucky Bob Wins with 10 which beats 9", playingDeck.getHighHand(player1, player2));
    }
    @Test
    public void checkHighHand__player2() {
        //check highHand
        Card card1 = new Card(SuitType.HEARTS, RankType.TWO);
        Card card2 = new Card(SuitType.CLUBS, RankType.NINE);
        Player player1 = new Player("Lucky Bob");
        Player player2 = new Player("Unlucky Bob");
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Unlucky Bob Wins with 9 which beats 2", playingDeck.getHighHand(player1, player2));
    }
    @Test
    public void checkHighHand__draw() {
        //check highHand
        Card card1 = new Card(SuitType.HEARTS, RankType.TEN);
        Card card2 = new Card(SuitType.CLUBS, RankType.TEN);
        Player player1 = new Player("Lucky Bob");
        Player player2 = new Player("Unlucky Bob");
        player1.addCard(card1);
        player2.addCard(card2);
        assertEquals("Draw", playingDeck.getHighHand(player1, player2));
    }
    
}
