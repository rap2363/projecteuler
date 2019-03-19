package problems;

import utils.Numbers;
import utils.SquaredCompositeSieve;

/**
 *
 *
 * For a positive integer n, let σ2(n) be the sum of the squares of its divisors. For example,
 * σ(10) = 1 + 4 + 25 + 100 = 130.
 * Find the sum of all n, 0 < n < 64,000,000 such that σ(n) is a perfect square.
 *
 * Solution: We can use a SquaredCompositeSieve and simply find the numbers that are perfect squares.
 */
public final class Problem211 implements EulerProblem {
    private static final int LIMIT = 64000000;

    public static void main(String[] args) {
        System.out.println(new Problem211().get());
    }

    @Override
    public long get() {
        long totalSum = 0L;
        final SquaredCompositeSieve squaredCompositeSieve = new SquaredCompositeSieve(LIMIT);
        for (long num = 1; num < LIMIT; num++) {
            // The Squared Composite Sieve only computes the squares of the proper divisors, so we need to add the
            // number squared to get the correct amount.
            final long squaredSum = squaredCompositeSieve.getSquaredSumOfDivisors((int) num) + num * num;
            if (Numbers.isPerfectSquare(squaredSum)) {
                totalSum += num;
            }
        }
        return totalSum;
    }
}
