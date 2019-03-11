package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import utils.NumberUtils;

/**
 * The RSA encryption is based on the following procedure:
 *
 * Generate two distinct primes p and q.
 * Compute n=pq and φ=(p-1)(q-1).
 * Find an integer e, 1<e<φ, such that gcd(e,φ)=1.
 *
 * A message in this system is a number in the interval [0,n-1].
 * A text to be encrypted is then somehow converted to messages (numbers in the interval [0,n-1]).
 * To encrypt the text, for each message, m, c=m^e mod n is calculated.
 *
 * To decrypt the text, the following procedure is needed: calculate d such that ed=1 mod φ, then for each encrypted message, c, calculate m=cd mod n.
 *
 * There exist values of e and m such that me mod n=m.
 * We call messages m for which me mod n=m unconcealed messages.
 *
 * An issue when choosing e is that there should not be too many unconcealed messages.
 * For instance, let p=19 and q=37.
 * Then n=19*37=703 and φ=18*36=648.
 * If we choose e=181, then, although gcd(181,648)=1 it turns out that all possible messages
 * m (0≤m≤n-1) are unconcealed when calculating me mod n.
 * For any valid choice of e there exist some unconcealed messages.
 * It's important that the number of unconcealed messages is at a minimum.
 *
 * Choose p=1009 and q=3643.
 * Find the sum of all values of e, 1<e<φ(1009,3643) and gcd(e,φ)=1, so that the number of unconcealed messages for this value of e is at a minimum.
 *
 * Solution: To solve this efficiently we first make use of the fact that if m^e == 1 % N, then m^(ne) == 1 % N for
 * all n. We iterate through the messages and find these e values we call "min cycle" exponents. Once we find these
 * we can claim that m^(ne + 1) == m % N for all these min cycle exponents. We keep a map that with <k,v> pairs as the
 * min cycle exponent and the number of messages that have them as their min cycle. In order to make our computations
 * fast, we also use the same map to check whether we already have a min cycle exponent for a particular message
 * (instead of recalculating the larger min cycle exponents).
 *
 * Finally, we iterate through all the exponents, filtering on gcd(e, PHI) == 1, and iterate through the min cycle map
 * (which is roughly 36 elements), filtering on (e - 1) % minCycleExponent == 0. These are messages which will map
 * to themselves under the current exponent. Finally we just count the number of total messages that will map to themselves
 * for the exponent. Then we take a min across the exponent map, finding those exponents that have the fewest number
 * of degenerate messages, and sum them up to calculate the final value.
 */
public final class Problem182 implements EulerProblem {
    private static long P = 1009;
    private static long Q = 3643;
    private static long N = P * Q;
    private static long PHI = (P - 1) * (Q - 1);

    public static void main(String[] args) {
        System.out.println(new Problem182().get());
    }

    @Override
    public long get() {
        final Map<Long, Long> cycleEToCounts = new HashMap<>();
        for (long m = 2; m < N; m++) {
            final long minCycleE = findMinCycleExponent(m, N, cycleEToCounts);
            final long countValue = cycleEToCounts.getOrDefault(minCycleE, 0L) + 1;
            cycleEToCounts.put(minCycleE, countValue);
        }

        // Now we can simply cycle through the e values and update counts
        final Map<Long, Long> eToCounts = new HashMap<>();
        for (long e = 2; e < PHI; e++) {
            if (NumberUtils.gcd(PHI, e) != 1) {
                continue;
            }

            final long finalExponent = e;

            // Iterate through map of cycleECounts to find divisors
            final long totalDivisorCounts = cycleEToCounts.entrySet()
                .stream()
                .filter(entry -> (finalExponent - 1) % entry.getKey() == 0)
                .mapToLong(Entry::getValue)
                .sum();

            eToCounts.put(finalExponent, totalDivisorCounts);
        }

        final long minNumUnconcealedMessages = eToCounts
            .values()
            .stream()
            .mapToLong(Long::longValue)
            .min()
            .getAsLong();

        long totalSum = 0L;
        for (long e = 0; e < PHI; e++) {
            if (eToCounts.containsKey(e)) {
                if (eToCounts.get(e) == minNumUnconcealedMessages) {
                    totalSum += e;
                }
            }
        }

        return totalSum;
    }

    private static long findMinCycleExponent(final long m,
                                             final long n,
                                             final Map<Long, Long> cycleEToCounts) {
        // First check the counts map to see if our min cycle exponent already exists there
        return cycleEToCounts.keySet()
            .stream()
            .filter(e -> NumberUtils.modPower(m, e, n) == 1)
            .min(Long::compare)
            .orElseGet(() -> NumberUtils.findCycleExponent(m, n) - 1);
    }
}
