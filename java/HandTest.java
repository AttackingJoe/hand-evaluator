import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Created by lordstevex on 2/22/16.
 */
public class HandTest {

    @Test
    public void testGetCards() throws Exception {

    }

    @org.junit.Test
    public void testSetCards() throws Exception {

    }

    @org.junit.Test
    public void testIsProperFormat() throws Exception {

    }

    @org.junit.Test
    public void testIsFlush() throws Exception {
        Hand handIsFlush = new Hand("Ac5c7c9c2c");
        Hand handIsNotFlush = new Hand("Ad5c7c9c2c");

        assertTrue(handIsFlush.isFlush());
        assertFalse(handIsNotFlush.isFlush());

    }

    @org.junit.Test
    public void testIsStraight() throws Exception {
        Hand handIsStraight = new Hand("5s2dAc3h4d");
        Hand handIsNotStraight = new Hand("5s2d7c3h4d");

        assertTrue(handIsStraight.isStraight());
        assertFalse(handIsNotStraight.isStraight());
    }

    @org.junit.Test
    public void testIsStraightWheel() throws Exception {
        Hand handIsWheel = new Hand("3c4c5d2sAh");
        assertTrue(handIsWheel.isWheel());
    }

    @org.junit.Test
    public void testIsQuads() throws Exception {
        Hand handIsQuads = new Hand("AcAsAdAhKd");
        Hand handIsNotQuads = new Hand("AcAsAdQhKd");

        assertTrue(handIsQuads.isQuads());
        assertFalse(handIsNotQuads.isQuads());

    }

    @org.junit.Test
    public void testIsTrips() throws Exception {
        Hand handIsTrips = new Hand("AcAsAdQhKd");
        Hand handIsTrips2 = new Hand("5c4s3d3h3c");
        Hand handIsNotTrips = new Hand("AcAsAdAhKd");


        assertTrue(handIsTrips.isTrips());
        assertFalse(handIsNotTrips.isTrips());
        assertTrue(handIsTrips2.isTrips());
    }

    @org.junit.Test
    public void testIsOnePair() throws Exception {
        Hand handIsOnePair = new Hand("AcAsQdKh9s");
        Hand handIsNotOnePair = new Hand("AcAsKdKh9s");
        Hand handIsNotOnePair2 = new Hand("AcAsKdKhKs");


        assertTrue(handIsOnePair.isOnePair());
        assertFalse(handIsNotOnePair.isOnePair());
        assertFalse(handIsNotOnePair2.isOnePair());
    }

    @org.junit.Test
    public void testIsTwoPair() throws Exception {
        Hand handIsTwoPair = new Hand("AcAsKdKh8d");
        Hand handIsNotTwoPair = new Hand("AcAsKdKhKc");
        Hand handIsNotTwoPair2 = new Hand("AcAsTdJhKc");

        assertTrue(handIsTwoPair.isTwoPair());
        assertFalse(handIsNotTwoPair.isTwoPair());
        assertFalse(handIsNotTwoPair2.isTwoPair());
    }

    @org.junit.Test
    public void testIsBoat() throws Exception {
        Hand handIsBoat = new Hand("AcAsAhKdKc");
        Hand handIsBoat2 = new Hand("3c3s3hKdKc");
        Hand handIsNotBoat = new Hand("AcAsAhKdQc");
        Hand handIsNotBoat2 = new Hand("AcAsKhKdQc");
        Hand handIsNotBoat3 = new Hand("5c4s3d3h3c");

        assertTrue(handIsBoat.isBoat());
        assertTrue(handIsBoat2.isBoat());
        assertFalse(handIsNotBoat.isBoat());
        assertFalse(handIsNotBoat2.isBoat());
        assertFalse(handIsNotBoat3.isBoat());
    }

    @org.junit.Test
    public void testIsStraightFlush() throws Exception {
        Hand handIsStraightFlush = new Hand("AcQcKcJcTc");
        Hand handIsNotStraightFlush = new Hand("AdQcKcJcTc");
        Hand handIsNotStraightFlush2 = new Hand("AdQcKcJc9c");

        assertTrue(handIsStraightFlush.isStraight());
        assertTrue(handIsStraightFlush.isFlush());
        assertTrue(handIsStraightFlush.isStraightFlush());
        assertFalse(handIsNotStraightFlush.isStraightFlush());
        assertFalse(handIsNotStraightFlush2.isStraightFlush());
    }

    @org.junit.Test
    public void testGetCardAt() throws Exception {

    }

    @org.junit.Test
    public void testPrintCards() throws Exception {

    }
}