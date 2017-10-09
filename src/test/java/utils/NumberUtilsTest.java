package utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

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
}
