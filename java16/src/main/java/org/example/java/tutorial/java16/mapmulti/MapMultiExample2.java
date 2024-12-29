package org.example.java.tutorial.java16.mapmulti;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class MapMultiExample2 {

    public static void main(String[] args) {

        // Let's say that we have a file with some numbers and we want to read them and print them.
        // This file contains some comments that we want to skip.
        String textOfIntegers = """
                # Some numbers in a file
                234
                567
                8910
                """;
        var lines = textOfIntegers.lines()
                .filter(line -> !line.startsWith("#"))
                .map(Integer::parseInt)
                .toList();
        log.info("Numbers in the file: {}", lines);

        textOfIntegers = """
                # Some numbers in a file
                234
                567
                @@@@@
                8910
                """; // added some invalid data

        lines = textOfIntegers.lines()
                .filter(line -> !line.startsWith("#"))
                .filter(line -> {
                    try {
                        Integer.parseInt(line);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;   // skip invalid data not only the @@@@@ line
                    }
                })
                .map(Integer::parseInt)
                .toList();
        log.info("Numbers in the file: {}", lines);

        // Well, this is a bit verbose and we are parsing the integers twice. We can resolve this using flatMap

        lines = textOfIntegers.lines()
                .filter(line -> !line.startsWith("#"))
                .flatMap(line -> {
                    try {
                        int i = Integer.parseInt(line);
                        return Stream.of(i);
                    } catch (NumberFormatException e) {
                        return Stream.empty();   // skip invalid data not only the @@@@@ line
                    }
                })
                .toList();
        log.info("Numbers in the file: {}", lines);

        // Now, let's use mapMulti to achieve the same result

        lines = textOfIntegers.lines()
                .filter(line -> !line.startsWith("#"))
                .<Integer>mapMulti((line, downstream) -> {
                    try {
                        int i = Integer.parseInt(line);
                        downstream.accept(i);
                    } catch (NumberFormatException e) {
                        // skip invalid data not only the @@@@@ line
                    }
                })
                .toList();
        log.info("Numbers in the file: {}", lines);

        // The mapMulti method is a more efficient way to handle such cases where we need to filter and map the elements of a stream.
        // It allows us to process the elements of a stream in a single pass, without the need to create intermediate streams.

    }
}
