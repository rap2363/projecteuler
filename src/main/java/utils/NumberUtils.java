package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
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
        final long lesser;
        final long greater;
        if (a > b) {
            lesser = b;
            greater = a;
        } else {
            lesser = a;
            greater = b;
        }

        long gcd = 1;
        final double limit = Math.sqrt(lesser);
        for (long x = 1; x <= limit; x++) {
            if (lesser % x == 0) {
                final long y = lesser / x;
                if (greater % x == 0) {
                    gcd = Math.max(gcd, x);
                }
                if (greater % y == 0) {
                    gcd = Math.max(gcd, y);
                }
            }
        }

        return gcd;
    }

    /**
     * Returns an ordered list of the divisors of a number
     *
     * @param num
     * @return
     */
    public static List<Long> getDivisors(final long num) {
        final List<Long> smallerDivisors = new ArrayList<>();
        final List<Long> largerDivisors = new ArrayList<>();
        final double limit = Math.sqrt(num);
        for (long x = 1; x <= limit; x++) {
            if (num % x == 0) {
                smallerDivisors.add(x);
                if (x != limit) {
                    largerDivisors.add(0, num / x);
                }
            }
        }
        final List<Long> allDivisors = new ArrayList<>(smallerDivisors.size() + largerDivisors.size());
        for (long divisor : smallerDivisors) {
            allDivisors.add(divisor);
        }
        for (long divisor : largerDivisors) {
            allDivisors.add(divisor);
        }
        return allDivisors;
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
