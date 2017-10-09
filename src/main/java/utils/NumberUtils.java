package utils;

import java.util.ArrayList;
import java.util.List;

public final class NumberUtils {

    /**
     * Return whether or not a number is palindromic (i.e. digits read the same way forward as they do backward).
     *
     * @param number
     * @return
     */
    public static boolean isPalindrome(final int number) {
        List<Integer> digits = new ArrayList<>();
        int temp = number;
        while (temp != 0) {
            digits.add(temp % 10);
            temp /= 10;
        }
        final int numDigits = digits.size();
        final int numToCheck = (numDigits / 2) + ((numDigits % 2 == 0) ? 0 : 1);

        for (int i = 0; i < numToCheck; i++) {
            if (digits.get(i) != digits.get(numDigits - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
