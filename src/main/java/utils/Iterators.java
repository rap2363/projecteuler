package utils;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public final class Iterators {
    public static <T> PowerSetIterator<T> getPowerSetIterator(final List<T> elements) {
        return new PowerSetIterator<>(elements);
    }

    public static class PowerSetIterator<T> implements Iterator<List<T>> {
        private final List<T> elements;
        private final int maxSize;
        private int index;

        private PowerSetIterator(final List<T> elements) {
            this.elements = elements;
            this.maxSize = (int) Math.pow(2, elements.size());
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < maxSize;
        }

        @Override
        public List<T> next() {
            final List<T> nextElements = Lists.newArrayList();
            for (int i = 0; i < elements.size(); i++) {
                if ((index >> i & 1) == 1) {
                    nextElements.add(elements.get(i));
                }
            }
            index++;
            return nextElements;
        }
    }
}
