package problems;

import utils.NumberUtils;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem5 extends EulerProblem {
    public static void main(String [] args) {
        new Problem5().run();
    }

    @Override
    public void run() {
        long currentLCM = 1;
        for (int i = 1; i <= 20; i++) {
            currentLCM = NumberUtils.lcm(currentLCM, i);
        }
        System.out.println(currentLCM);
    }
}
