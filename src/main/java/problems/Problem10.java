package problems;

import utils.PrimeSieve;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 *
 * Find the sum of all the primes below two million.
 */
public final class Problem10 extends EulerProblem {
    private static final int LIMIT = (int) 2e6;
    public static void main(String[] args) {
        System.out.println(new Problem10().run());
    }

    @Override
    public long run() {
        return new PrimeSieve(LIMIT).getAllPrimes()
                .stream()
                .mapToLong(Integer::intValue)
                .sum();
    }
}
