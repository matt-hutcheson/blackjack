import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    Player player1;
    Player player2;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        player2 = new Player("Skint Bob");
    }
    @Test
    public void playerHasName(){
        assertEquals("Lucky Bob", this.player1.getName());
    }    
}
