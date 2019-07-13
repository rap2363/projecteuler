package utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;

public final class Numbers {

    private static final double LOG_2 = Math.log(2);

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
                    gcd = x < gcd ? gcd : x;
                }
                if (greater % y == 0) {
                    gcd = y < gcd ? gcd : y;
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

    public static List<Long> getPrimeFactorization(final long num) {
        return getPrimeFactorization(new PrimeSieve((int) num), num);
    }

    public static List<Long> getPrimeFactorization(final PrimeSieve primeSieve, final long num) {
        return getPrimeFactorization(primeSieve, num, new ArrayList<>());
    }

    private static List<Long> getPrimeFactorization(final PrimeSieve primeSieve,
                                                    final long num,
                                                    final List<Long> appendingList) {
        final long numPrimes = primeSieve.getAllPrimes().size();
        if (num == 1) {
            return appendingList;
        }

        for (int i = 0; i < numPrimes; i++) {
            final long primeToCheck = primeSieve.getNthPrime(i + 1);

            if (num % primeToCheck == 0) {
                appendingList.add(primeToCheck);
                return getPrimeFactorization(primeSieve, num / primeToCheck, appendingList);
            }
        }

        return appendingList;
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

    public static long averageLcm(final long a) {
        long averageLcm = 0L;
        for (int i = 1; i <= a; i++) {
            averageLcm += lcm(a, i) / a;
        }

        return averageLcm;
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

    /**
     * Count the number of total letters in a number representation (from English).
     *
     * For example: 3 --> three --> 5 (letters in the word)
     *              103 --> one hundred and three --> 3 + 7 + 3 + 5 --> 18
     *
     * @param num
     * @return
     */
    public static int countLetters(final int num) {
        return LetterCounter.count(num);
    }

    public static boolean isPerfectSquare(final double radicalValue) {
        final double value = Math.sqrt(radicalValue);
        return value == Math.round(value);
    }

    private static class LetterCounter {
        private static final int AND = 3;
        private static final ImmutableMap<Integer, Integer> numberCounts = ImmutableMap.<Integer, Integer>builder()
            .put(1, 3)    // one
            .put(2, 3)    // two
            .put(3, 5)    // three
            .put(4, 4)    // four
            .put(5, 4)    // five
            .put(6, 3)    // six
            .put(7, 5)    // seven
            .put(8, 5)    // eight
            .put(9, 4)    // nine
            .put(10, 3)   // ten
            .put(11, 6)   // eleven
            .put(12, 6)   // twelve
            .put(13, 8)   // thirteen
            .put(14, 8)   // fourteen
            .put(15, 7)   // fifteen
            .put(16, 7)   // sixteen
            .put(17, 9)   // seventeen
            .put(18, 8)   // eighteen
            .put(19, 8)   // nineteen
            .put(20, 6)   // twenty
            .put(30, 6)   // thirty
            .put(40, 5)   // forty
            .put(50, 5)   // fifty
            .put(60, 5)   // sixty
            .put(70, 7)   // seventy
            .put(80, 6)   // eighty
            .put(90, 6)   // ninety
            .put(100, 7)  // hundred
            .put(1000, 8) // thousand
            .build();

        /**
         * Counts the number of letters via recursion. Only works on numbers less than 1000
         *
         * @param num
         * @return
         */
        private static int count(final int num) {
            Preconditions.checkArgument(num <= 1000);
            if (num == 0) {
                return 0;
            }

            if (num < 20) {
                return numberCounts.get(num);
            }

            if (num < 100) {
                return numberCounts.get((num / 10) * 10) + count(num % 10);
            }

            // Since we never say "and zero", we need to handle this case specially.
            if (num % 100 == 0 && num < 1000) {
                return numberCounts.get(num / 100) + numberCounts.get(100);
            }

            if (num < 1000) {
                return numberCounts.get(num / 100) + numberCounts.get(100) + AND + count(num % 100);
            }

            if (num == 1000) {
                return numberCounts.get(1) + numberCounts.get(1000);
            }

            return 0;
        }
    }

    /**
     * Calculates x ^ y mod n quickly.
     */
    public static long modPower(long x, long y, final long n) {
        // Initialize result
        long res = 1;

        // Update x if it is more
        // than or equal to n
        x %= n;

        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if ((y & 1) == 1) {
                res = (res * x) % n;
            }

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % n;
        }
        return res;
    }

    public static long findCycleExponent(final long base, final long n) {
        long cycleN = 1;
        long newNumber = base;
        do {
            newNumber *= base;
            newNumber %= n;
            cycleN++;
        } while (base != newNumber);
        return cycleN;
    }

    public static double log2(final double value) {
        return Math.log(value) / LOG_2;
    }

    public static boolean isPrime(final long value) {
        for (int i = 2; i < Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }
}
