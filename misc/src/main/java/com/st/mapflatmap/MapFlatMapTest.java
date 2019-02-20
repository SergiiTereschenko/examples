package com.st.mapflatmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapFlatMapTest {

    @Test
    public void testOptionalMapFlatMap() {
        Optional<String> mapOpt = Optional.of("dsd").map(String::toUpperCase);
        Optional<String> sds = Optional.of("sds").flatMap(s -> Optional.of(s.toUpperCase()));

        Optional<String> s = Optional.of("input");
        System.out.println(s.map(MapFlatMapTest::getOutput));
        System.out.println(s.flatMap(MapFlatMapTest::getOutputOpt));
    }

    static String getOutput(String input) {
        return input == null ? null : "output for " + input;
    }

    static Optional<String> getOutputOpt(String input) {
        return input == null ? Optional.empty() : Optional.of("output for " + input);
    }

    @Test
    public void testStreamMapFlatMap() {
        List<List<String>> people = new ArrayList<>();
        people.add(Arrays.asList("555-1123", "555-3389"));
        people.add(Arrays.asList("555-2243", "555-5264"));
        people.add(Arrays.asList("555-6654", "555-3242"));

        Stream<List<String>> stream = people.stream();
        Stream<String> streanFlatMap = stream.flatMap(Collection::stream);
        List<String> phones = streanFlatMap.collect(Collectors.toList());
    }
}
