package utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class NumberGridTest {
    private final NumberGrid numberGrid;

    public NumberGridTest() {
        this.numberGrid = new NumberGrid(new int[]{
                 1,  2,  3,  4,
                 5,  6,  7,  8,
                 9, 10, 11, 12,
                13, 14, 15, 16
        }, 4, 4);
    }

    @Test
    public void testNumberGridWithTwoElementMaximizingSearch() {

        final NumberGrid.SearchValue searchValue = numberGrid.findMaximizingValue(
                l -> (double) l.stream().mapToInt(Integer::intValue).sum(),
                2,
                Arrays.asList(NumberGrid.Direction.W));

        assertEquals(3, searchValue.x);
        assertEquals(3, searchValue.y);
        assertEquals(31.0, searchValue.value, Double.MIN_VALUE);
    }

    @Test
    public void testNumberGridWithThreeElementSearch() {

        final NumberGrid.SearchValue searchValue = numberGrid.findMaximizingValue(
                l -> (double) l.stream().mapToInt(Integer::intValue).sum(),
                3,
                Arrays.asList(NumberGrid.Direction.S, NumberGrid.Direction.SE));

        assertEquals(3, searchValue.x);
        assertEquals(1, searchValue.y);
        assertEquals(36.0, searchValue.value, Double.MIN_VALUE);
    }
}
