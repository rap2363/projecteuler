package problems;

import java.util.LinkedList;

/**
 * In England the currency is made up of pounds, £, and pence, p, and there are eight coins in general circulation:
 *
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 *
 * It is possible to make £2 in the following way:
 *
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 *
 * How many different ways can £2 be made using any number of coins?
 *
 * Solution: DP approach, first add n200 200p coins, (whatever feasible), then n100 coins (whatever feasible), etc.
 * Whenever we get something == 200, increment the count
 */
public final class Problem31 implements EulerProblem {
    private static final int FINAL_TOTAL = 200;
    private static final int[] DENOMINATIONS = {200, 100, 50, 20, 10, 5, 2, 1};

    public static void main(String[] args) {
        System.out.println(new Problem31().get());
    }

    @Override
    public Number get() {
        int numWaysToGetToFinalTotal = 0;
        final LinkedList<AmountAndDenomination> queue = new LinkedList<>();
        queue.add(AmountAndDenomination.first());
        while (!queue.isEmpty()) {
            final AmountAndDenomination original = queue.poll();
            int numCoins = 0;
            while (true) {
                final AmountAndDenomination newAmountAndDenomination = original.addAmount(numCoins);
                if (newAmountAndDenomination.amount == FINAL_TOTAL) {
                    numWaysToGetToFinalTotal++;
                    break;
                } else if (newAmountAndDenomination.amount > FINAL_TOTAL) {
                    break;
                }

                if (!newAmountAndDenomination.isDone()) {
                    queue.push(newAmountAndDenomination);
                }

                numCoins++;
            }
        }

        return numWaysToGetToFinalTotal;
    }

    private static class AmountAndDenomination {
        private final int amount;
        private final int denominationIndex;

        private AmountAndDenomination(final int amount, final int denominationIndex) {
            this.amount = amount;
            this.denominationIndex = denominationIndex;
        }

        public static AmountAndDenomination first() {
            return new AmountAndDenomination(0, -1);
        }

        public AmountAndDenomination addAmount(final int numCoins) {
            final int denominationIndex = this.denominationIndex + 1;
            return new AmountAndDenomination(
                this.amount + numCoins * DENOMINATIONS[denominationIndex],
                denominationIndex
            );
        }

        public boolean isDone() {
            return denominationIndex == DENOMINATIONS.length - 1;
        }
    }
}
