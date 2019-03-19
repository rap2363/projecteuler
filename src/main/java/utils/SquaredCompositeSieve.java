package utils;

/**
 * Similar to a composite sieve, except that it keeps the sum of the squares of its divisors.
 */
public final class SquaredCompositeSieve {
    private final long[] divisorSums;

    public SquaredCompositeSieve(final int N) {
        divisorSums = sieveComposites(N);
    }

    public long getSquaredSumOfDivisors(final int n) {
        return this.divisorSums[n - 1];
    }

    private static long[] sieveComposites(final int N) {
        final long[] divisorSums = new long[N - 1];
        for (long divisor = 1; divisor < ((N / 2) + 1); divisor++) {
            for (int i = (int) (2 * divisor); i < N; i += divisor) {
                divisorSums[i - 1] += divisor * divisor;
            }
        }

        return divisorSums;
    }
}
