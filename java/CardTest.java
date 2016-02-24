import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGetIRank() throws Exception {
        Card c1 = new Card('9', 'h');

        assertTrue(c1.getIRank() == 9);
    }

    @Test
    public void testGetISuit() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}