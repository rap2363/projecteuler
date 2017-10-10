package utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class DirectedAcyclicGraphTest {

    @Test
    public void testSingleNodeDag() {
        final int INITIAL_VALUE = 10;
        final DirectedAcylicGraph dag = new DirectedAcylicGraph.Builder().addNode(0, INITIAL_VALUE).build();
        assertEquals(INITIAL_VALUE, dag.getValue(0));
    }

    @Test
    public void testChainDag() {
        final int INITIAL_VALUE = 10;
        final DirectedAcylicGraph dag = new DirectedAcylicGraph.Builder()
                .addNode(0, INITIAL_VALUE)
                .addNode(1, Collections.singletonList(0))
                .addNode(2, Collections.singletonList(1))
                .build();

        assertEquals(INITIAL_VALUE, dag.getValue(0));
        assertEquals(INITIAL_VALUE, dag.getValue(1));
        assertEquals(INITIAL_VALUE, dag.getValue(2));
    }

    @Test
    public void testDiamondDag() {
        final int INITIAL_VALUE = 10;
        final DirectedAcylicGraph dag = new DirectedAcylicGraph.Builder()
                .addNode(0, INITIAL_VALUE)
                .addNode(1, Collections.singletonList(0))
                .addNode(2, Collections.singletonList(0))
                .addNode(3, Arrays.asList(1, 2))
                .build();

        assertEquals(INITIAL_VALUE, dag.getValue(0));
        assertEquals(INITIAL_VALUE, dag.getValue(1));
        assertEquals(INITIAL_VALUE, dag.getValue(2));
        assertEquals(INITIAL_VALUE * 2, dag.getValue(3));
    }

    @Test
    public void testThreeByThreeGrid() {
        final int INITIAL_VALUE = 1;
        int NODE_ID = 0;
        final int NW = NODE_ID++;
        final int N = NODE_ID++;
        final int NE = NODE_ID++;
        final int W = NODE_ID++;
        final int C = NODE_ID++;
        final int E = NODE_ID++;
        final int SW = NODE_ID++;
        final int S = NODE_ID++;
        final int SE = NODE_ID++;

        final DirectedAcylicGraph dag = new DirectedAcylicGraph.Builder()
                .addNode(SE, INITIAL_VALUE)
                .addNode(E, Collections.singletonList(SE))
                .addNode(S, Collections.singletonList(SE))
                .addNode(C, Arrays.asList(S, E))
                .addNode(NE, Collections.singletonList(E))
                .addNode(SW, Collections.singletonList(S))
                .addNode(N, Arrays.asList(C, NE))
                .addNode(W, Arrays.asList(C, SW))
                .addNode(NW, Arrays.asList(N, W))
                .build();

        assertEquals(6, dag.getValue(NW));
        assertEquals(1, dag.getValue(NE));
        assertEquals(2, dag.getValue(C));
    }

    @Test
    public void testFourByFourGrid() {
        final LatticeDag latticeDag = new LatticeDag(4, 4, 1);
        assertEquals(6, latticeDag.getValue(2, 2));
        assertEquals(20, latticeDag.getValue(1, 1));
        assertEquals(70, latticeDag.getValue(0, 0));
    }
}
