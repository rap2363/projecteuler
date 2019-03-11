package problems;

import utils.LatticeDag;

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly
 * 6 routes to the bottom right corner.
 *
 * How many such routes are there through a 20×20 grid?
 */
public final class Problem15 implements EulerProblem {
    private final int gridLength;

    public Problem15(final int gridLength) {
        this.gridLength = gridLength;
    }

    public Problem15() {
        this(20);
    }

    public static void main(String[] args) {
        System.out.println(new Problem15(20).get());
    }

    @Override
    public long get() {
        final LatticeDag latticeDag = new LatticeDag(gridLength, gridLength, 1);

        return latticeDag.getValue(0, 0);
    }
}
