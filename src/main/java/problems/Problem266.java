package problems;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import utils.Iterators;
import utils.Iterators.PowerSetIterator;
import utils.PrimeSieve;

/**
 * The divisors of 12 are: 1,2,3,4,6 and 12.
 * The largest divisor of 12 that does not exceed the square root of 12 is 3.
 * We shall call the largest divisor of an integer n that does not exceed the square root of n the pseudo square root
 * (PSR) of n. It can be seen that PSR(3102)=47.
 *
 * Let p be the product of the primes below 190.
 * Find PSR(p) mod 10^16.
 *
 * Solution: We define the set of all primes below 190 as {p_i} and assume its product yields some number N. We note
 * that the unique prime factorization of N, and that all of its divisors are products of subsets of {p_i}. Finding the
 * PSR(N) then means we need to find a subset, x, of {p_i}, that maximizes the product while remaining smaller than
 * the sqrt(N). In other words we need to solve the optimization problem:
 *
 * max prod(x) s.t. x <= sqrt(N) , where x is a subset of {p_i}. We can transform this optimization problem to an
 * equivalent one by using the natural log:
 *
 * max log(prod(x)) = max sum(log(x)) s.t. log(x) <= log(sqrt(N)) = 0.5 * log(N).
 *
 * This is equivalent to a 0-1 Knapsack problem with capacity 0.5 * log(N), where the weights and values are equivalent
 * and equal to the logs of the primes, {log(p_i)}, and we can solve it accordingly. Specifically, we'll use a meet in
 * the middle approach, where we split the subsets arbitrarily into two sets of equal size A and B, find all of the
 * subsets and their sums of each, and find the optimal combined subset of the two.
 *
 * At the end we'll have some maximizing subset x, which we can take the actual product of (while taking mod 10^16) to
 * avoid overflow.
 */
public final class Problem266 implements EulerProblem {
    private static final int MAX_PRIME = 190;
    private static final long MOD_16 = 10000000000000000L;

    public static void main(String[] args) {
        System.out.println(new Problem266().get());
    }

    @Override
    public long get() {
        final List<LogPrime> logPrimes = new PrimeSieve(MAX_PRIME).getAllPrimes()
            .stream()
            .map(p -> new LogPrime(p, Math.log(p)))
            .collect(java.util.stream.Collectors.toList());

        final double capacity = 0.5 * logPrimes.stream().mapToDouble(LogPrime::getLogPrime).sum();

        // Split into two subsets A and B
        final List<LogPrime> setA = Lists.newArrayList();
        final List<LogPrime> setB = Lists.newArrayList();

        for (int i = 0; i < logPrimes.size(); i++) {
            if (i % 2 == 0) {
                setA.add(logPrimes.get(i));
            } else {
                setB.add(logPrimes.get(i));
            }
        }

        final PowerSetIterator<LogPrime> setAIterator = Iterators.getPowerSetIterator(setA);
        final PowerSetIterator<LogPrime> setBIterator = Iterators.getPowerSetIterator(setB);

        // Find all subsets of B and also sort according to the sums
        final List<LogPrimeSet> sortedSubsetsOfB = Lists.newArrayList();
        setBIterator.forEachRemaining(subset -> sortedSubsetsOfB.add(new LogPrimeSet(subset)));
        sortedSubsetsOfB.sort(Comparator.comparingDouble(LogPrimeSet::getSum));

        // Now we simply iterate through the A's and binary search into B to find the largest summed value that
        // doesn't exceed our capacity.

        double maxValue = -1;
        LogPrimeSet bestLogPrimeSet = null;

        while (setAIterator.hasNext()) {
            final LogPrimeSet logPrimeSetA = new LogPrimeSet(setAIterator.next());
            final Optional<LogPrimeSet> maybeLogPrimeSetB
                = getBestUnderCapacity(sortedSubsetsOfB, capacity - logPrimeSetA.sum);

            if (!maybeLogPrimeSetB.isPresent()) {
                continue;
            }

            final LogPrimeSet logPrimeSetB = maybeLogPrimeSetB.get();

            if (logPrimeSetA.sum + logPrimeSetB.sum > maxValue) {
                maxValue = logPrimeSetA.sum + logPrimeSetB.sum;
                bestLogPrimeSet = LogPrimeSet.combine(logPrimeSetA, logPrimeSetB);
            }
        }

        // Once we have the best log prime set, we multiply the primes of the set and mod 16.
        long result = 1L;
        for (final LogPrime logPrime : bestLogPrimeSet.getLogPrimes()) {
            result = (result * logPrime.getPrime()) % MOD_16;
        }

        return result;
    }

    // A simple binary search to find the subset with
    private static Optional<LogPrimeSet> getBestUnderCapacity(final List<LogPrimeSet> sortedSubsetsOfB,
                                                              final double capacityLeft) {
        if (sortedSubsetsOfB.get(0).sum > capacityLeft) {
            return Optional.empty();
        }

        if (sortedSubsetsOfB.get(sortedSubsetsOfB.size() - 1).sum < capacityLeft) {
            return Optional.of(sortedSubsetsOfB.get(sortedSubsetsOfB.size() - 1));
        }

        int upper = sortedSubsetsOfB.size() - 1;
        int lower = 0;
        while (lower <= upper) {
            final int middle = (upper + lower) / 2;
            if (capacityLeft < sortedSubsetsOfB.get(middle).sum) {
                upper = middle - 1;
            } else if (capacityLeft > sortedSubsetsOfB.get(middle).sum) {
                lower = middle + 1;
            } else {
                return Optional.of(sortedSubsetsOfB.get(middle));
            }
        }

        return Optional.of(sortedSubsetsOfB.get(upper));
    }

    private static class LogPrimeSet {
        private final List<LogPrime> logPrimes;
        private final double sum;

        public LogPrimeSet(final List<LogPrime> logPrimes) {
            this(logPrimes, logPrimes.stream().mapToDouble(LogPrime::getLogPrime).sum());
        }

        public LogPrimeSet(final List<LogPrime> logPrimes, final double sum) {
            this.logPrimes = logPrimes;
            this.sum = sum;
        }

        public List<LogPrime> getLogPrimes() {
            return logPrimes;
        }

        public double getSum() {
            return sum;
        }

        public static LogPrimeSet combine(final LogPrimeSet set1, final LogPrimeSet set2) {
            final List<LogPrime> combinedList = new ArrayList<>(set1.logPrimes);
            combinedList.addAll(set2.logPrimes);
            return new LogPrimeSet(combinedList, set1.sum + set2.sum);
        }
    }

    private static class LogPrime {
        private final long prime;
        private final double logPrime;

        public LogPrime(final long prime, final double logPrime) {
            this.prime = prime;
            this.logPrime = logPrime;
        }

        public long getPrime() {
            return prime;
        }

        public double getLogPrime() {
            return logPrime;
        }
    }
}
