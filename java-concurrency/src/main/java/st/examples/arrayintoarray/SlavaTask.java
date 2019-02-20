package st.examples.arrayintoarray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SlavaTask {

    @Test
    public void test1() {
        Object[] fixture1 = new Object[]{1, new Object[]{2, 3}, 4};
        Integer[] expected1 = new Integer[]{1, 2, 3, 4};

        Object[] fixture2 = new Object[]{1, new Object[]{2, 3}, 4, new Object[]{5, new Object[]{6, new Object[]{7, 8}}, 9}, new Object[]{10}};
        Integer[] expected2 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Thread thread = new Thread(() -> System.out.println("Hello World!"));

        FixtureIterator iterator1 = new FixtureIterator(fixture1);
        List<Integer> actual1 = new ArrayList();
        while (iterator1.hasNext()) {
            actual1.add(iterator1.next());
        }

        FixtureIterator iterator2 = new FixtureIterator(fixture2);
        List<Integer> actual2 = new ArrayList();
        while (iterator2.hasNext()) {
            actual2.add(iterator2.next());
        }

        System.out.println(actual1);
        System.out.println(Arrays.asList(expected1));

        System.out.println(actual2);
        System.out.println(Arrays.asList(expected2));
    }

    private static Function<Object, Stream<Integer>> getMapper() {
        return new Function<Object, Stream<Integer>>() {
            @Override
            public Stream<Integer> apply(Object element) {
                if (element instanceof Integer) {
                    return Stream.of((Integer) element);
                } else {
                    return Stream.of((Object[]) element).flatMap(getMapper());
                }
            }
        };
    }

    public static class FixtureIterator implements Iterator<Integer> {

        private final Iterator<Integer> iterator;

        public FixtureIterator(Object[] array) {
            iterator = Stream.of(array).flatMap(getMapper()).iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Integer next() {
            return (Integer) iterator.next();
        }
    }
}
