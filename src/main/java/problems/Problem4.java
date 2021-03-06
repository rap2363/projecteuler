package problems;

import utils.Numbers;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is
 * 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public final class Problem4 implements EulerProblem {
    private static final int THREE_DIGIT_UPPER_BOUND = 1000;

    public static void main(String[] args) {
        System.out.println(new Problem4().get());
    }

    @Override
    public Long get() {
        long largestProduct = 0;
        for (int x = 0; x < THREE_DIGIT_UPPER_BOUND; x++) {
            for (int y = 0; y < THREE_DIGIT_UPPER_BOUND; y++) {
                int product = x * y;
                if (Numbers.isPalindrome(product)) {
                    largestProduct = Math.max(largestProduct, product);
                }
            }
        }

        return largestProduct;
    }
}
