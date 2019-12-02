package com.st.interviewtasks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayIteratorDecoratorTest_Agileengine {


    //task agileengine https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/

    @Test
    public void testIterator() {

//        Integer[] arr = {1, 2, 3, 4, 5};
        Object[] arr = { 1, 2, new Object[]{ 3, 4, new Object[]{ 5 }, 6, 7 }, 8, new Object[]{ 9, 10 } };

        ArrayOfArraysIterator<Integer> iterator = new ArrayOfArraysIterator(arr);

//        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
//        Iterator<Integer> iterator = list.iterator();


        while (iterator.hasNext()) {
            Integer next = iterator.next();

            if (next == 3) {
                iterator.remove();
            }
        }
    }

    static class ArrayOfArraysIterator<E> implements Iterator<E> {

        private int cursor;
        private E[] array;
        private Iterator<E> iterator;

        public ArrayOfArraysIterator(E[] array) {
//            this.array = array;
            this.array = (E[]) flatten(array).toArray();
            this.iterator = flatten(array).iterator();

        }

        private Stream<E> flatten(E[] array) {
            Stream<E> flatStream = Arrays.stream(array)
                    .flatMap(el -> el.getClass().isArray() ? flatten((E[]) el) : Stream.of(el));
            return flatStream;
        }

        @Override
        public boolean hasNext() {
            return array.length >= cursor;
        }

        @Override
        public E next() {
            if (cursor > array.length) {
                throw new NoSuchElementException();
            }
            E obj = array[cursor];
            cursor++;
            return obj;
        }

        public void remove() {
            int removePosition = cursor - 1;
            System.arraycopy(array, cursor, array, removePosition, array.length - 1 - removePosition);
            cursor--;
            array[array.length - 1] = null;
        }

    }


    @Test
    public void testArrayCopy() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] copiedArray = new int[3];

        System.arraycopy(array, 2, copiedArray, 0, 3);
        System.arraycopy(array, 2, array, 0, 3);

    }
}
