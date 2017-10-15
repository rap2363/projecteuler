package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumberUtilsTest {

    @Test
    public void testPalindromicNumbers() {
        assertTrue(NumberUtils.isPalindrome(0));
        assertTrue(NumberUtils.isPalindrome(3));
        assertTrue(NumberUtils.isPalindrome(1001));
        assertTrue(NumberUtils.isPalindrome(323515323));

        assertFalse(NumberUtils.isPalindrome(12));
        assertFalse(NumberUtils.isPalindrome(12320));
    }

    @Test
    public void testGreatestCommonDivisor() {
        assertEquals(1, NumberUtils.gcd(3, 2));
        assertEquals(3, NumberUtils.gcd(18, 21));
        assertEquals(10, NumberUtils.gcd(100, 30));
    }

    @Test
    public void testLeastCommonMultiple() {
        assertEquals(6, NumberUtils.lcm(3, 2));
        assertEquals(6, NumberUtils.lcm(3, 6));
        assertEquals(12, NumberUtils.lcm(6, 4));
    }

    @Test
    public void testGetDivisors() {
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 6L, 12L), NumberUtils.getDivisors(12));
    }

    @Test
    public void testLetterCounts() {
        assertEquals(8, NumberUtils.countLetters(14)); // fourteen --> 8
        assertEquals(10, NumberUtils.countLetters(100)); // one hundred --> 3 + 7 --> 10
        assertEquals(22, NumberUtils.countLetters(132)); // one hundred and thirty two --> 3 + 7 + 3 + 6 + 3 --> 22
        assertEquals(24, NumberUtils.countLetters(999)); // nine hundred and ninety nine --> 4 + 7 + 3 + 6 + 4 --> 24
        assertEquals(11, NumberUtils.countLetters(1000)); // one thousand --> 3 + 8
    }
}
