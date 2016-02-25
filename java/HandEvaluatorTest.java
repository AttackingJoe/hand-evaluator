import org.junit.Test;

import static org.junit.Assert.*;

public class HandEvaluatorTest {

    @Test
    public void testCompareHands() throws Exception {
        HandEvaluator eval = new HandEvaluator("AcKcQcJcTcKhQhJhTh9h");
        assertTrue(eval.compareHands() == 1);

        HandEvaluator eval2 = new HandEvaluator("2d3s4h6d8dThTcTdTsAh");
        assertTrue(eval2.compareHands() == 2);
    }
}