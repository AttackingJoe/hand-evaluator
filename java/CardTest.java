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
        Card c1 = new Card('9', 'h');

        assertTrue(c1.getISuit() == 2);
    }

    @Test
    public void testToString() throws Exception {
        Card c1 = new Card('9', 'h');

        String expected = "9h ";
        String actual = c1.toString();

        assertEquals(expected, actual);
    }
}