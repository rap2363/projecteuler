package problems;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utils.CompositeSieve;

/**
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example,
 * the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 *
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum
 * exceeds n.
 *
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of
 * two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be
 * written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even
 * though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than
 * this limit.
 *
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public final class Problem23 implements EulerProblem {
    private static final int LIMIT = 28123;

    public static void main(String[] args) {
        System.out.println(new Problem23().get());
    }

    @Override
    public long get() {
        final CompositeSieve compositeSieve = new CompositeSieve(LIMIT);
        final List<Integer> abundantNumbers = IntStream.range(1, LIMIT)
            .filter(n -> compositeSieve.getSumOfDivisors(n) > n)
            .boxed()
            .collect(Collectors.toList());

        // Create a set of all sums of these abundant numbers up to the limit
        final Set<Integer> abundantSums = Sets.newHashSet();
        for (int i = 0; i < abundantNumbers.size(); i++) {
            for (int j = i; j < abundantNumbers.size(); j++) {
                final int sum = abundantNumbers.get(i) + abundantNumbers.get(j);
                if (sum > LIMIT) {
                    break;
                }
                abundantSums.add(sum);
            }
        }

        // Now iterate through the numbers and check if it's not in the set
        long totalSum = 0L;
        for (int num = 1; num < LIMIT; num++) {
            if (!abundantSums.contains(num)) {
                totalSum += num;
            }
        }

        return totalSum;
    }
}
