package utils;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * A Directed Acyclic Graph (DAG) can be used to represent dependency chains and can be "solved" for a given node. The
 * DAG is immutable, and is immediately solved upon creation. Currently the nodes only add their dependencies and
 * carry int values.
 **/
public final class DirectedAcylicGraph {
    private final Map<Integer, Node> nodeMap;

    public DirectedAcylicGraph(final Map<Node, List<Integer>> dependencyMap) {
        final Map<Integer, Node> nodeMap = new HashMap<>();
        // Add the nodes into the map
        for (final Node node : dependencyMap.keySet()) {
            nodeMap.put(node.nodeId, node);
        }

        // Then add their dependencies
        for (final Map.Entry<Node, List<Integer>> entry : dependencyMap.entrySet()) {
            final Node node = entry.getKey();
            for (int dependentNode : entry.getValue()) {
                node.addDependency(nodeMap.get(dependentNode));
            }
        }

        this.nodeMap = nodeMap;

        // Now solve the dependency graph
        this.solve(nodeMap);
    }

    public long getValue(final int nodeId) {
        if (nodeMap.get(nodeId).solved) {
            return nodeMap.get(nodeId).value;
        }

        throw new IllegalStateException("Each node should be solved!");
    }

    /**
     * Cycle through and solve each of the node map's dependencies. Does this by evaluating nodes with the least
     * number of dependencies first. The current number of unsolved dependencies for the top-most node should always be
     * 0, otherwise we have a DAG with cycles.
     *
     * @param nodeMap
     */
    private void solve(final Map<Integer, Node> nodeMap) {
        PriorityQueue<Node> unsolvedNodes = new PriorityQueue<>(Node.nodeComparator);
        for (final Node node : nodeMap.values()) {
            unsolvedNodes.add(node);
        }

        while (unsolvedNodes.size() > 0) {
            final Node nodeToSolve = unsolvedNodes.poll();
            if (nodeToSolve.numUnsolvedDependencies() > 0) {
                throw new IllegalStateException("Provided graph contains cycles!");
            }

            nodeToSolve.setValue(nodeToSolve.dependencies.stream().mapToLong(Node::getValue).sum() + nodeToSolve.value);
            nodeToSolve.solved = true;

            // Then go through and reset the queue
            unsolvedNodes.clear();
            for (final Node node : nodeMap.values()) {
                if (!node.solved) {
                    unsolvedNodes.add(node);
                }
            }
        }
    }

    private static class Node {
        private final int nodeId;
        private List<Node> dependencies;
        private long value;
        private boolean solved;

        public Node(final int nodeId, final long value) {
            this(nodeId, new ArrayList<>());
            this.value = value;
        }

        public Node(final int nodeId, final List<Node> dependencies) {
            this.nodeId = nodeId;
            this.dependencies = dependencies;
            this.value = 0;
            this.solved = false;
        }

        public long getValue() {
            return value;
        }

        public void setValue(final long value) {
            this.value = value;
        }

        public void addDependency(final Node node) {
            this.dependencies.add(node);
        }

        public int numUnsolvedDependencies() {
            return this.dependencies.stream().mapToInt(n -> n.solved ? 0 : 1).sum();
        }

        public static Comparator<Node> nodeComparator = new Comparator<Node>() {
            @Override
            public int compare(final Node o1, final Node o2) {
                return Integer.compare(o1.numUnsolvedDependencies(), o2.numUnsolvedDependencies());
            }
        };
    }

    public static class Builder {
        private Map<Node, List<Integer>> dependencyMap;

        public Builder() {
            this.dependencyMap = new HashMap<>();
        }

        public Builder addNode(final int nodeId, final long value) {
            return this.addNode(new Node(nodeId, value), new ArrayList<>());
        }

        public Builder addNode(final int nodeId, final List<Integer> dependencies) {
            return this.addNode(new Node(nodeId, 0), dependencies);
        }

        private Builder addNode(final Node node, final List<Integer> dependencies) {
            this.dependencyMap.put(node, dependencies);
            return this;
        }

        public DirectedAcylicGraph build() {
            Preconditions.checkArgument(this.dependencyMap.size() > 0);
            return new DirectedAcylicGraph(dependencyMap);
        }
    }
}
