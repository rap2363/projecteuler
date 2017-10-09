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
}
