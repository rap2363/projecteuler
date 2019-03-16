package problems;

import java.util.List;
import utils.PrimeSieve;

/**
 * A composite is a number containing at least two prime factors. For example, 15 = 3 × 5; 9 = 3 × 3; 12 = 2 × 2 × 3.
 *
 * There are ten composites below thirty containing precisely two, not necessarily distinct, prime factors:
 * 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
 *
 * How many composite integers, n < 10^8, have precisely two, not necessarily distinct, prime factors?
 *
 * Simple Solution: Generate all primes up to 10^8. For each number, generate the prime factorization and check how many
 * prime divisors divide the number.
 *
 * Smarter: Generate all primes up to 10^8 / 2. (maybe we can do with less but whatever). Take all pairs and multiply
 * multiply and add them up, making sure the value is less than 10^8. When it exceeds 10^8 we can break. We don't need
 * to even take a sum, so it will just be a matter of breaking early and counting.
 */
public final class Problem187 implements EulerProblem {
    private static final long MAX_NUM = 100000000;

    public static void main(String[] args) {
        System.out.println(new Problem187().get());
    }

    @Override
    public long get() {
        final List<Integer> primes = new PrimeSieve((int) MAX_NUM / 2).getAllPrimes();
        long total = 0L;
        for (int i = 0; i < primes.size(); i++) {
            for (int j = i; j < primes.size(); j++) {
                final long firstPrime = (long) primes.get(i);
                final long secondPrime = (long) primes.get(j);
                if (firstPrime * secondPrime < MAX_NUM) {
                    total++;
                } else {
                    break;
                }
            }
        }

        return total;
    }
}
