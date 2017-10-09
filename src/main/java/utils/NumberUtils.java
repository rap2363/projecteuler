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
    public static boolean isPalindrome(final long number) {
        List<Long> digits = new ArrayList<>();
        long temp = number;
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

    /**
     * Return the greatest common divisor of a and b
     *
     * @param a
     * @param b
     * @return
     */
    public static long gcd(final long a, final long b) {
        final long lesser = Math.min(a, b);
        long gcd = 1;
        for (long i = 1; i <= lesser; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    /**
     * Return the least-common multiple of two integers a and b. Uses the well-known formula:
     * lcm(a, b) = a * b / (gcd(a, b))
     *
     * @param a
     * @param b
     * @return
     */
    public static long lcm(final long a, final long b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Return the sum of the first n integers
     *
     * @param n
     * @return
     */
    public static long sumOfFirstN(final long n) {
        return n * (n + 1) / 2;
    }

    /**
     * Return the sum of the first n squared integers
     * @param n
     * @return
     */
    public static long sumOfFirstNSquares(final long n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }
}
