package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquaredCompositeSieveTest {
    @Test
    public void testSquaredCompositeSieveUpToTwenty() {
        final SquaredCompositeSieve compositeSieve = new SquaredCompositeSieve(21);

        assertEquals(1 + 4 + 9 + 16 + 36, compositeSieve.getSquaredSumOfDivisors(12));
        assertEquals(1, compositeSieve.getSquaredSumOfDivisors(17));
        assertEquals(1 + 4 + 16 + 25 + 100, compositeSieve.getSquaredSumOfDivisors(20));
    }
}
