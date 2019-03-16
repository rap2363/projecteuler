package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class NumbersTest {

    @Test
    public void testPalindromicNumbers() {
        assertTrue(Numbers.isPalindrome(0));
        assertTrue(Numbers.isPalindrome(3));
        assertTrue(Numbers.isPalindrome(1001));
        assertTrue(Numbers.isPalindrome(323515323));

        assertFalse(Numbers.isPalindrome(12));
        assertFalse(Numbers.isPalindrome(12320));
    }

    @Test
    public void testGreatestCommonDivisor() {
        assertEquals(1, Numbers.gcd(3, 2));
        assertEquals(3, Numbers.gcd(18, 21));
        assertEquals(10, Numbers.gcd(100, 30));
    }

    @Test
    public void testLeastCommonMultiple() {
        assertEquals(6, Numbers.lcm(3, 2));
        assertEquals(6, Numbers.lcm(3, 6));
        assertEquals(12, Numbers.lcm(6, 4));
    }

    @Test
    public void testGetDivisors() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 6, 12), Numbers.getDivisors(12));
    }

    @Test
    public void testPrimeFactorization() {
        assertEquals(Arrays.asList(2L, 2L, 3L), Numbers.getPrimeFactorization(12));
        assertEquals(Arrays.asList(23L, 41L), Numbers.getPrimeFactorization(943));
        assertEquals(Arrays.asList(13L, 17L, 23L, 41L), Numbers.getPrimeFactorization(208403));
    }

    @Test
    public void testLetterCounts() {
        assertEquals(8, Numbers.countLetters(14)); // fourteen --> 8
        assertEquals(10, Numbers.countLetters(100)); // one hundred --> 3 + 7 --> 10
        assertEquals(22, Numbers.countLetters(132)); // one hundred and thirty two --> 3 + 7 + 3 + 6 + 3 --> 22
        assertEquals(24, Numbers.countLetters(999)); // nine hundred and ninety nine --> 4 + 7 + 3 + 6 + 4 --> 24
        assertEquals(11, Numbers.countLetters(1000)); // one thousand --> 3 + 8
    }

    @Test
    public void testIsPerfectSquare() {
        assertTrue(Numbers.isPerfectSquare(121));
        assertTrue(Numbers.isPerfectSquare(36));
        assertFalse(Numbers.isPerfectSquare(40));
    }

    @Test
    public void testModPower() {
        assertEquals(8, Numbers.modPower(2, 3, 10));
        assertEquals(6, Numbers.modPower(2, 4, 10));
        // This m^e is peculiar as a modPower with any base yields the same result.
        for (int i = 1; i < 703; i++) {
            assertEquals(i, Numbers.modPower(i, 181, 703));
        }
    }

    @Test
    public void testCycleN() {
        assertEquals(5, Numbers.findCycleExponent(3, 10));
        assertEquals(13, Numbers.findCycleExponent(2, 13));
    }
}
