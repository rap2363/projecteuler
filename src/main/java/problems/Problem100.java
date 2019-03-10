package problems;

import utils.NumberUtils;

/**
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs, and two discs were
 * taken at random, it can be seen that the probability of taking two blue discs, P(BB) = (15/21)×(14/20) = 1/2.
 *
 * The next such arrangement, for which there is exactly 50% chance of taking two blue discs at random, is a box
 * containing eighty-five blue discs and thirty-five red discs.
 *
 * By finding the first arrangement to contain over 10^12 = 1,000,000,000,000 discs in total, determine the number of
 * blue discs that the box would contain.
 *
 * Solution: For a box of T disks total and B blue disks, the arrangement would be met if we had:
 *           B(B-1) / T(T+1) = 1/2 --> 2B^2 - 2B = (T^2 - T).
 *
 * We can find such arrangements quickly by solving the quadratic formula and finding when we have integer solutions
 * for B:
 *
 * B = 0.5 * (1 + sqrt(1 + 2(T^2 - T)))
 *
 * In other words, we'll have such an arrangement when sqrt(1 + 2(T^2 - T)) yields an integer.
 *
 * We can brute force search to find the lowest T >= 10**12 such that we obtain an integer value for the radical.
 */
public final class Problem100 implements EulerProblem {
    private static final long STARTING_VALUE = 1000000000000L;

    public static void main(String[] args) {
        System.out.println(new Problem100().run());
    }

    @Override
    public long run() {
        for (long totalDisks = STARTING_VALUE; totalDisks < 10 * STARTING_VALUE; totalDisks++) {
            final double radicalValue = 1 + 2 * totalDisks * (totalDisks - 1);
            if (NumberUtils.isPerfectSquare(radicalValue)) {
                System.out.println("Total Disks: " + totalDisks);
                return (long) (0.5 * (1 + Math.sqrt(radicalValue)));
            }
        }

        throw new IllegalStateException();
    }
}
