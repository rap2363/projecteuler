package problems;

import utils.Numbers;

/**
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public final class Problem6 implements EulerProblem {

    public static void main(String[] args) {
        System.out.println(new Problem6().get());
    }

    @Override
    public long get() {
        final long sum = Numbers.sumOfFirstN(100);
        final long answer = sum * sum - Numbers.sumOfFirstNSquares(100);

        return answer;
    }
}
