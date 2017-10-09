package utils;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class NumberGrid {
    private final int M;
    private final int N;
    private final int[][] numbers;

    public NumberGrid(final int[] allNumbers, final int M, final int N) {
        Preconditions.checkArgument(allNumbers.length == (M * N));
        this.M = M;
        this.N = N;
        this.numbers = new int[M][N];
        for (int i  = 0; i < M; i++) {
            System.arraycopy(allNumbers, i * M, numbers[i], 0, M);
        }
    }

    /**
     * According to a func, find the maximizing index in the grid by traversing the grid in specific directions and
     * incorporating a numElements number of elements.
     *
     * Returns an array of two elements (the x,y tuple which maximizes the function)
     *
     * @param func
     * @param directionsToCheck
     * @return
     */
    public SearchValue findMaximizingValue(final Function<List<Integer>, Double> func,
                                           final int numElements,
                                           final List<Direction> directionsToCheck) {
        Preconditions.checkArgument(numElements > 0);
        SearchValue maximizingSearchValue = new SearchValue(-1, -1, numElements, Direction.N, Double.NEGATIVE_INFINITY);

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (final Direction direction : directionsToCheck) {
                    final double value = getValueAlongDirection(direction, x, y, func, numElements);
                    if (value > maximizingSearchValue.value) {
                        maximizingSearchValue = new SearchValue(x, y, numElements, direction, value);
                    }
                }
            }
        }

        return maximizingSearchValue;
    }

    public double getValueAlongDirection(final Direction direction,
                                         final int x,
                                         final int y,
                                         final Function<List<Integer>, Double> func,
                                         final int numElements) {
        if (direction == Direction.N) {
            return getValueAlongNorth(x, y, func, numElements);
        } else if (direction == Direction.NE) {
            return getValueAlongNorthEast(x, y, func, numElements);
        } else if (direction == Direction.E) {
            return getValueAlongEast(x, y, func, numElements);
        } else if (direction == Direction.SE) {
            return getValueAlongSouthEast(x, y, func, numElements);
        } else if (direction == Direction.S) {
            return getValueAlongSouth(x, y, func, numElements);
        } else if (direction == Direction.SW) {
            return getValueAlongSouthWest(x, y, func, numElements);
        } else if (direction == Direction.W) {
            return getValueAlongWest(x, y, func, numElements);
        } else if (direction == Direction.NW) {
            return getValueAlongNorthWest(x, y, func, numElements);
        }

        throw new IllegalStateException();
    }

    private double getValueAlongNorth(final int x,
                                      final int y,
                                      final Function<List<Integer>, Double> func,
                                      final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y - offset) >= 0; offset++) {
            elements.add(this.numbers[y - offset][x]);
        }
        return func.apply(elements);
    }

    private double getValueAlongNorthEast(final int x,
                                          final int y,
                                          final Function<List<Integer>, Double> func,
                                          final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y - offset) >= 0 && (x + offset) < N; offset++) {
            elements.add(this.numbers[y - offset][x + offset]);
        }
        return func.apply(elements);
    }

    private double getValueAlongEast(final int x,
                                     final int y,
                                     final Function<List<Integer>, Double> func,
                                     final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (x + offset) < N; offset++) {
            elements.add(this.numbers[y][x + offset]);
        }
        return func.apply(elements);
    }

    private double getValueAlongSouthEast(final int x,
                                          final int y,
                                          final Function<List<Integer>, Double> func,
                                          final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y + offset) < M && (x + offset) < N; offset++) {
            elements.add(this.numbers[y + offset][x + offset]);
        }
        return func.apply(elements);
    }

    private double getValueAlongSouth(final int x,
                                      final int y,
                                      final Function<List<Integer>, Double> func,
                                      final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y + offset) < M; offset++) {
            elements.add(this.numbers[y + offset][x]);
        }
        return func.apply(elements);
    }

    private double getValueAlongSouthWest(final int x,
                                          final int y,
                                          final Function<List<Integer>, Double> func,
                                          final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y + offset) < M && (x - offset) >= 0; offset++) {
            elements.add(this.numbers[y + offset][x - offset]);
        }
        return func.apply(elements);
    }

    private double getValueAlongWest(final int x,
                                     final int y,
                                     final Function<List<Integer>, Double> func,
                                     final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (x - offset) >= 0; offset++) {
            elements.add(this.numbers[y][x - offset]);
        }
        return func.apply(elements);
    }

    private double getValueAlongNorthWest(final int x,
                                          final int y,
                                          final Function<List<Integer>, Double> func,
                                          final int numElements) {
        final List<Integer> elements = new ArrayList<>();
        for (int offset = 0; offset < numElements && (y - offset) >= 0 && (x - offset) >= 0; offset++) {
            elements.add(this.numbers[y - offset][x - offset]);
        }
        return func.apply(elements);
    }

    public static final class SearchValue {
        public final int x;
        public final int y;
        public final int numElements;
        public final Direction direction;
        public final double value;

        public SearchValue(final int x,
                           final int y,
                           final int numElements,
                           final Direction direction,
                           final double value) {
            this.x = x;
            this.y = y;
            this.numElements = numElements;
            this.direction = direction;
            this.value = value;
        }
    }

    public enum Direction {
        N, E, S, W, NW, NE, SE, SW
    }
}
