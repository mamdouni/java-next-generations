package org.example.java.tutorial.java12.api;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class UsageTeeingCollector {

    public static void main(String[] args) {

        var ints = buildIntStream();
        log.info("List : {}",
                ints.collect(Collectors.toList())
        );

        ints = buildIntStream();
        log.info("Summing : {}",
                ints.collect(Collectors.summingInt(Integer::valueOf))
        );

        ints = buildIntStream();
        log.info("Counting : {}",
                ints.collect(Collectors.counting())
        );

        // Teeing Collector
        // The teeing collector is a new feature in Java 12 that is used to collect the result of two collectors.
        ints = buildIntStream();
        long average = ints.collect(
                Collectors.teeing(
                        Collectors.summingInt(Integer::valueOf),
                        Collectors.counting(),
                        (sum, count) -> sum / count
                )
        );
        log.info("Average : {}", average);
    }

    private static Stream<Integer> buildIntStream() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

}
