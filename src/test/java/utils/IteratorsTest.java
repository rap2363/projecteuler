package utils;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class IteratorsTest {

    @Test
    public void testPowerSetIterator() {
        final List<String> animals = Lists.newArrayList("cat", "dog", "sheep");
        final Iterator<List<String>> powerSetIterator = Iterators.getPowerSetIterator(animals);
        final List<List<String>> animalGroups = Lists.newArrayList();
        powerSetIterator.forEachRemaining(animalGroups::add);

        assertEquals(Lists.newArrayList(
            Lists.newArrayList(),
            Lists.newArrayList("cat"),
            Lists.newArrayList("dog"),
            Lists.newArrayList("cat", "dog"),
            Lists.newArrayList("sheep"),
            Lists.newArrayList("cat", "sheep"),
            Lists.newArrayList("dog", "sheep"),
            Lists.newArrayList("cat", "dog", "sheep")),
            animalGroups
        );
    }
}
