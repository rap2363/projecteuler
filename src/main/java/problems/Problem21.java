package problems;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import utils.CompositeSieve;

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable
 * numbers.
 *
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The
 * proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 *
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public final class Problem21 implements EulerProblem {
    private static final int LIMIT = 10000;

    public static void main(String[] args) {
        System.out.println(new Problem21().get());
    }

    @Override
    public long get() {
        final CompositeSieve compositeSieve = new CompositeSieve(LIMIT);
        final IntPredicate isAmicableNumber = value -> {
            final int sumOfDivisors = compositeSieve.getSumOfDivisors(value);
            if (sumOfDivisors < LIMIT && sumOfDivisors >= 1 && sumOfDivisors != value) {
                final int backValue = compositeSieve.getSumOfDivisors(sumOfDivisors);
                return backValue == value;
            }
            return false;
        };

        return IntStream.range(1, LIMIT)
            .filter(isAmicableNumber)
            .mapToLong(compositeSieve::getSumOfDivisors)
            .sum();
    }
}
