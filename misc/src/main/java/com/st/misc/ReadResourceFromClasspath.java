package com.st.misc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ReadResourceFromClasspath {
     static AtomicInteger counter = new AtomicInteger(0);
    //https://www.baeldung.com/java-classpath-resource-cannot-be-opened
    public static final String NAME = "data2.csv"; //file must be in resource folder
    //The leading slash on the input to getResourceAsStream() tells the loader to read from the base of the classpath.

    public static void main(String[] args) throws Exception {
        logMemory("Before");
        readFileApacheFileUtils();
//        readFileFiles();
//        readFileJavaBufferReaderStream();
        logMemory("After");
        System.out.println("Done");
    }

    private static void readFileFiles() throws IOException, URISyntaxException {
        Path path = Paths.get(ReadResourceFromClasspath.class.getClassLoader()
                .getResource(NAME).toURI());

//        BufferedReader bufferedReader = Files.newBufferedReader(path);
//        bufferedReader.lines();

        Stream<String> lines = Files.lines(path);
        lines.forEach(row -> {
            if(counter.getAndIncrement() % 10000 == 0) System.out.println(1);
        });
    }

    private static void readFileApacheFileUtils() throws IOException, URISyntaxException {
        ClassLoader classLoader = ReadResourceFromClasspath.class.getClassLoader();
        File file = new File(classLoader.getResource(NAME).getFile());

        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")){
            while (it.hasNext()) {
                String line = it.nextLine();
                if(counter.getAndIncrement() % 10000 == 0) System.out.println(1);
            }
        }
    }

    private static void readFileJavaBufferReaderStream() throws IOException {
        Class<ReadResourceFromClasspath> clazz = ReadResourceFromClasspath.class;
        InputStream inputStream = clazz.getResourceAsStream("/" + NAME);

//        logMemory("Before");

        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            reader.lines().forEach(x -> {
                if(counter.getAndIncrement() % 10000 == 0) System.out.println(1);
            });
            logMemory("After1");

//            List<String> collect = reader.lines().collect(Collectors.toList());

//            logMemory("After");


        } catch (Exception e) {
            throw e;
        }
    }

    private static void logMemory(String... when) {
        if(when != null) System.out.println(when[0]);
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory() / 1048576);
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1048576);
    }
}
