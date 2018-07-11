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
 * Solution: For a box of R red disks and B blue disks, the arrangement would be met if we had:
 *           B(B-1) / R(R+1) = 1/2 --> 2B^2 - 2B - (R^2 - R).
 *
 * We can find such arrangements quickly by solving the quadratic formula and finding when we have integer solutions
 * for B:
 *
 * B = 0.5 * (1 + sqrt(1 + 2(R^2 - R)))
 *
 * In other words, we'll have such an arrangement when sqrt(1 + 2() yields an even number.
 *
 * However, because the problem is asking to look for arrangements after 10^12, brute force searching will not yield
 * a good result. Instead, we can reduce our search space simply by **generating** the results in advance.
 *
 * sqrt(10^12) = 10^6. So if we just iterate from 10^6
 */
public final class Problem100 implements EulerProblem {

    public static void main(String[] args) {
        System.out.println(new Problem100().run());
    }

    @Override
    public long run() {
        System.out.println(NumberUtils.sumOfFirstN(14));
        System.out.println(NumberUtils.sumOfFirstN(20));
        return 0;
    }
}
