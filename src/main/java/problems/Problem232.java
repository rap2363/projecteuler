package problems;

import utils.Numbers;

/**
 * Two players share an unbiased coin and take it in turns to play "The Race". On Player 1's turn, he tosses the coin
 * once: if it comes up Heads, he scores one point; if it comes up Tails, he scores nothing. On Player 2's turn, she
 * chooses a positive integer T and tosses the coin T times: if it comes up all Heads, she scores 2^(T-1) points;
 * otherwise, she scores nothing. Player 1 goes first. The winner is the first to 100 or more points.
 *
 * On each turn Player 2 selects the number, T, of coin tosses that maximises the probability of her winning.
 *
 * What is the probability that Player 2 wins?
 *
 * Give your answer rounded to eight decimal places in the form 0.abcdefgh
 *
 * Solution: We can characterize the probability of winning from any state (s1, s2) where s1 and s2 range from 0 -> 100:
 *
 * p(s1, s2) = max T (1/(2^T + 1)) ([p(s1, s2 + 2^(T-1)), p(s1 + 1, s2 + 2^(T-1))] + (2^T + 1) * p(s1 + 1, s2))
 *
 * with the specific conditions: p(s1, 100) = 1 for all s1 in [0, 100] (P2 has already won)
 *                           and p(100, s2) = 0 for s1 in [0, 99] (P1 has won)
 *
 * We use DP and backwards recursion to solve for the probability of winning in any of these states. To obtain the final
 * probability, we need to remember that P1 goes first, so our likelihood of winning under optimal actions is
 * 0.5 * (P(s1 = 0, s2 = 0) + P(s1 = 1, s2 = 0)) (in case the first player flips heads).
 */
public final class Problem232 implements EulerProblem {
    private static final int MAX_SCORE = 100;
    private static final double PRECISION = 100000000;
    private static final double UNEVALUATED = -1;

    public static void main(String[] args) {
        System.out.println("0." + new Problem232().get());
    }

    @Override
    public long get() {
        final double[][] probabilityMatrix = new double[MAX_SCORE + 1][MAX_SCORE + 1];
        final int[][] optimalActionsMatrix = new int[MAX_SCORE + 1][MAX_SCORE + 1];

        for (int s1 = 0; s1 <= MAX_SCORE; s1++) {
            for (int s2 = 0; s2 <= MAX_SCORE; s2++) {
                probabilityMatrix[s1][s2] = UNEVALUATED;
            }
        }

        // P2 wins for all of these entries
        for (int s1 = 0; s1 <= MAX_SCORE; s1++) {
            probabilityMatrix[s1][MAX_SCORE] = 1;
        }

        // P2 loses for all of these entries
        for (int s2 = 0; s2 < MAX_SCORE; s2++) {
            probabilityMatrix[MAX_SCORE][s2] = 0;
        }

        // Evaluate the entries in the matrix
        for (int s2 = MAX_SCORE - 1; s2 >= 0; s2--) {
            for (int s1 = MAX_SCORE - 1; s1 >= 0; s1--) {
                final double winningProbability
                    = getWinningProbability(s1, s2, probabilityMatrix, optimalActionsMatrix);
                probabilityMatrix[s1][s2] = winningProbability;
            }
        }

        for (int s1 = 0; s1 <= MAX_SCORE; s1++) {
            for (int s2 = 0; s2 <= MAX_SCORE; s2++) {
                System.out.print(optimalActionsMatrix[s1][s2] + " ");
            }
            System.out.println();
        }

        final double winningProbability = 0.5 * (probabilityMatrix[0][0] + probabilityMatrix[1][0]);
        return Math.round(winningProbability * PRECISION);
    }

    private double getWinningProbability(final int s1,
                                         final int s2,
                                         final double[][] probabilityMatrix,
                                         final int[][] optimalActionsMatrix) {
        // Evaluate the recurrence relation for all t from 0 to 100 (until s2 is guaranteed to win with a lower t).
        final int maxT = (int) Math.ceil(Numbers.log2(MAX_SCORE - s2) + 1);
        double maxProbability = UNEVALUATED;

        for (int T = 1; T <= maxT; T++) {
            final double twoToTheT = Math.pow(2, T);
            final int twoToTheTMinusOne = (int) Math.pow(2, T - 1);
            final double denominator = 1 / (Math.pow(2, T) + 1);
            final double numerator = twoToTheT - 1;
            final double value = denominator * (
                probabilityMatrix[s1][cappedIndex(s2 + twoToTheTMinusOne)] + probabilityMatrix[cappedIndex(s1 + 1)][cappedIndex(s2 + twoToTheTMinusOne)]
                    + numerator * probabilityMatrix[cappedIndex(s1 + 1)][s2]);

            if (value > maxProbability) {
                maxProbability = value;
                optimalActionsMatrix[s1][s2] = T;
            }
        }

        return maxProbability;
    }

    private static int cappedIndex(final int index) {
        return Math.min(MAX_SCORE, index);
    }
}
