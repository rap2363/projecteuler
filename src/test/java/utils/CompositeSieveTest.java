package utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompositeSieveTest {

    @Test
    public void testCompositeSieveUpToTwenty() {
        final CompositeSieve compositeSieve = new CompositeSieve(21);

        assertEquals(1 + 2 + 3 + 4 + 6, compositeSieve.getSumOfDivisors(12));
        assertEquals(1, compositeSieve.getSumOfDivisors(17));
        assertEquals(1 + 2 + 4 + 5 + 10, compositeSieve.getSumOfDivisors(20));
    }

    @Test
    public void testAmicablePair() {
        final CompositeSieve compositeSieve = new CompositeSieve(300);
        assertTrue(compositeSieve.getSumOfDivisors(220) == 284);
        assertTrue(compositeSieve.getSumOfDivisors(284) == 220);
    }
}
