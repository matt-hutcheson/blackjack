import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerTest {
    Player player1;
    Player player2;
    Card card;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        player2 = new Player("Skint Bob");
        card = new Card(SuitType.HEARTS, RankType.TEN);
    }
    @Test
    public void HasName(){
        assertEquals("Lucky Bob", this.player1.getName());
    }
    @Test
    public void canAddCardToHand() {
        player1.addCard(this.card);
        assertEquals(card, player1.getCard());
    }
    @Test
    public void canRemoveCardFromHand(){
        player1.addCard(this.card);
        player1.clearHand();
        assertNull(player1.getCard());
    }
}
