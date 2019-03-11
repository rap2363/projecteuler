package problems;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * See https://projecteuler.net/problem=107 for graphics.
 *
 * The following undirected network consists of seven vertices and twelve edges with a total weight of 243.
 *
 * The same network can be represented by the matrix below.
 *
 *     	A	B	C	D	E	F	G
 * A	-	16	12	21	-	-	-
 * B	16	-	-	17	20	-	-
 * C	12	-	-	28	-	31	-
 * D	21	17	28	-	18	19	23
 * E	-	20	-	18	-	-	11
 * F	-	-	31	19	-	-	27
 * G	-	-	-	23	11	27	-
 *
 * However, it is possible to optimise the network by removing some edges and still ensure that all points on the network
 * remain connected. The network which achieves the maximum saving is shown below. It has a weight of 93, representing a
 * saving of 243 − 93 = 150 from the original network.
 *
 * Using network.txt, a 6K text file containing a network with forty vertices, and given in matrix form, find the
 * maximum saving which can be achieved by removing redundant edges whilst ensuring that the network remains connected.
 *
 * Solution: We can use the reverse-delete algorithm to solve this. Basically we sort the edges of the tree by
 * weight in descending order, and remove them as long as they don't cause disconnections. This means we need to
 * keep a sorted list of edges and every time we ask whether we can remove an edge, we need to run a BFS to see whether
 * the graph will stay connected after deleting the edge in question.
 */
public final class Problem107 implements EulerProblem {
    private static final int NUM_VERTICES = 40;
    private static final String HYPHEN_CHAR = "-";
    private final PriorityQueue<Edge> edges;
    private final Map<Integer, Integer> nodesToEdgeCount;
    private final MutableGraph graph;

    public Problem107() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File networkFile = new File(classLoader.getResource("network.txt").getFile());
        // Descending order
        this.edges = new PriorityQueue<>(Comparator.comparing(edge -> -edge.weight));
        this.nodesToEdgeCount = new HashMap<>();
        final Map<Integer, Set<Edge>> nodeToEdgesMap = new HashMap<>();
        try (final Scanner scanner = new Scanner(networkFile)) {
            for (int fromNode = 0; fromNode < NUM_VERTICES; fromNode++) {
                final String[] weights = scanner.nextLine().split(",");
                for (int toNode = fromNode + 1; toNode < NUM_VERTICES; toNode++) {
                    if (weights[toNode].equals(HYPHEN_CHAR)) {
                        continue;
                    }
                    final Edge edge = new Edge(fromNode, toNode, Integer.parseInt(weights[toNode]));
                    incrementValueInMap(nodesToEdgeCount, fromNode);
                    incrementValueInMap(nodesToEdgeCount, toNode);
                    this.edges.add(edge);
                    nodeToEdgesMap.computeIfAbsent(fromNode, HashSet::new).add(edge);
                    nodeToEdgesMap.computeIfAbsent(toNode, HashSet::new).add(edge);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.graph = new MutableGraph(nodeToEdgesMap);
    }

    public static void main(String[] args) {
        System.out.println(new Problem107().get());
    }

    @Override
    public long get() {
        // Now we pop all the edges off the queue, removing them if they don't disconnect the graph.
        long totalSavingWeight = 0L;
        while (!edges.isEmpty()) {
            final Edge edge = edges.poll();
            if (nodesToEdgeCount.get(edge.from) <= 1 || nodesToEdgeCount.get(edge.to) <= 1) {
                continue;
            }

            // Check if removing the edge keeps graph connected
            if (graph.removeEdge(edge).isConnected()) {
                // Remove the edge from the graph
                decrementValueInMap(nodesToEdgeCount, edge.from);
                decrementValueInMap(nodesToEdgeCount, edge.to);
                totalSavingWeight += edge.weight;
            } else {
                // Otherwise add the edge back and skip.
                graph.addEdge(edge);
            }
        }

        return totalSavingWeight;
    }

    private <K> Map<K, Integer> incrementValueInMap(final Map<K, Integer> map, final K key) {
        return addValueInMap(map, key, 1);
    }

    private <K> Map<K, Integer> decrementValueInMap(final Map<K, Integer> map, final K key) {
        return addValueInMap(map, key, -1);
    }

    private <K> Map<K, Integer> addValueInMap(final Map<K, Integer> map, final K key, final int value) {
        final int incrementedValue = map.getOrDefault(key, 0) + value;
        map.put(key, incrementedValue);
        return map;
    }

    private static final class Edge {
        public final int from;
        public final int to;
        public final int weight;

        public Edge(final int from, final int to, final int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static final class MutableGraph {
        private final Map<Integer, Set<Edge>> nodeToEdgesMap;

        public MutableGraph(final Map<Integer, Set<Edge>> nodeToEdgesMap) {
            this.nodeToEdgesMap = nodeToEdgesMap;
        }

        public MutableGraph removeEdge(final Edge edge) {
            this.nodeToEdgesMap.get(edge.from).remove(edge);
            this.nodeToEdgesMap.get(edge.to).remove(edge);
            return this;
        }

        public MutableGraph addEdge(final Edge edge) {
            this.nodeToEdgesMap.get(edge.from).add(edge);
            this.nodeToEdgesMap.get(edge.to).add(edge);
            return this;
        }

        // Run a BFS to check if graph is fully connected
        public boolean isConnected() {
            final Set<Integer> visited = new HashSet<>();
            final Queue<Integer> nodesToVisit = new PriorityQueue<>();
            nodesToVisit.add(0);

            while (!nodesToVisit.isEmpty()) {
                final int node = nodesToVisit.poll();
                if (visited.contains(node)) {
                    continue;
                }
                visited.add(node);

                nodeToEdgesMap.get(node)
                    .stream()
                    .map(edge -> edge.from == node ? edge.to : edge.from)
                    .filter(nodeId -> !visited.contains(nodeId))
                    .forEach(nodesToVisit::add);

            }

            return visited.size() == nodeToEdgesMap.size();
        }
    }
}
