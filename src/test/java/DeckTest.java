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


    
}
