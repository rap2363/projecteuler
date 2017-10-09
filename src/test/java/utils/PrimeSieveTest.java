package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrimeSieveTest {

    @Test
    public void testPrimeSieveUpTo100() {
        final PrimeSieve primeSieve = new PrimeSieve(100);

        final int[] primesUpTo100 = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        for (int i = 0; i < primesUpTo100.length; i++) {
            int n = i + 1;
            assertEquals(primesUpTo100[i], primeSieve.getNthPrime(n));
        }
    }


    @Test
    public void testPrimeSieveThrowsForValuesBeyondLimit() {
        boolean exceptionThrown = false;
        try {
            new PrimeSieve(100).getNthPrime(26);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Prime Sieve found only 25 values!");
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}
