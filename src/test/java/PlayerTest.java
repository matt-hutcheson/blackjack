import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerTest {
    Player player1;
    Card card;
    Card card2;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        card = new Card(SuitType.HEARTS, RankType.TEN);
        card2 = new Card(SuitType.SPADES, RankType.EIGHT);
    }
    @Test
    public void HasName(){
        assertEquals("Lucky Bob", this.player1.getName());
    }
    @Test
    public void canAddCardToHand() {
        player1.addCard(this.card);
        assertEquals(1, player1.getHand().size());
    }
    @Test
    public void canRemoveCardFromHand(){
        player1.addCard(this.card);
        player1.clearHand();
        assertEquals(0, player1.getHand().size());
    }
    @Test
    public void canGetHandMultipleCards(){
        player1.addCard(card);
        player1.addCard(card2);
        assertEquals(2, player1.getHand().size());
    }
    @Test
    public void canGetCard(){
        player1.addCard(card);
        assertEquals(card, player1.getCard(0));
    }
}
