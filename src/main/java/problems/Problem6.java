package problems;

import utils.NumberUtils;

/**
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class Problem6 extends EulerProblem {

    public static void main(String[] args) {
        new Problem6().run();
    }

    @Override
    public void run() {
        final long sum = NumberUtils.sumOfFirstN(100);
        final long answer = sum * sum - NumberUtils.sumOfFirstNSquares(100);

        System.out.println(answer);
    }
}
