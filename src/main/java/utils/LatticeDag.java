package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lattice (grid) with dependencies that point to the right and down. The bottom-right corner of the
 * lattice is initialized with an input value.
 *
 * Lattice points are ordered from top-down, left-to-right, from 0 --> M*N - 1. M*N - 1 represents the bottom-right
 * corner of the lattice.
 */
public final class LatticeDag {
    private final int M;
    private final int N;
    private final DirectedAcylicGraph dag;

    public LatticeDag(int gridHeight, int gridLength, long initialValue) {
        this.M = gridHeight + 1;
        this.N = gridLength + 1;
        final DirectedAcylicGraph.Builder dagBuilder = new DirectedAcylicGraph.Builder();
        for (int i = 0; i < M*N; i++) {
            final List<Integer> dependencies = new ArrayList<>(2);
            // Add a right-pointing dependency if we're not on the right border
            if ((i + 1) % N != 0) {
                dependencies.add(i + 1);
            }

            // Add a bottom-pointing dependency if we're not on the bottom border
            if (i < (M - 1) * N) {
                dependencies.add(i + N);
            }
            if (dependencies.size() > 0) {
                dagBuilder.addNode(i, dependencies);
            } else {
                dagBuilder.addNode(i, initialValue);
            }
        }

        this.dag = dagBuilder.build();
    }

    /**
     * Obtain the value for a lattice point (x, y) (x is left-right, y is top-down).
     *
     * @param x
     * @param y
     * @return
     */
    public long getValue(final int x, final int y) {
        return this.dag.getValue(y * this.N + x);
    }
}
