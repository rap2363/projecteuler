package utils;

/**
 * Similar to a prime sieve, a composite sieve keeps an in-memory array up to a certain number N. Upon creation, each
 * slot in the array will be populated with the sum of that number's proper divisors.
 */
public final class CompositeSieve {
    private final int N;
    private final int[] divisorSums;

    public CompositeSieve(final int N) {
        this.N = N;
        divisorSums = sieveComposites(N);
    }

    public int getSumOfDivisors(final int n) {
        return this.divisorSums[n - 1];
    }

    private int[] sieveComposites(final int N) {
        final int[] divisorSums = new int[N - 1];
        for (int divisor = 1; divisor < ((N / 2) + 1); divisor++) {
            for (int i = 2 * divisor; i < N; i += divisor) {
                divisorSums[i - 1] += divisor;
            }
        }

        return divisorSums;
    }
}
