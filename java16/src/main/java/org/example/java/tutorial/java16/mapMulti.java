package org.example.java.tutorial.java16;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class mapMulti {
    public static void main(String[] args) {
        /*

        Stream<T> stream.mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper)

        The goal of this method is for each input elements in the Stream of type T to return zero or more elements in the output Stream of type R.
        This sounds like a flatMap but within a flatMap you need to turn each input element of the stream into a new possibly empty stream of elements.
        That works but is not conveniently possible to construct these inner streams and constructing these streams incurs a performance overhead as well.

        Let's take this example with flatMap and then translate it to mapMulti
         */

        List<Integer> evenNumbers = Stream.of(1, 2, 3, 4, 5)
                .flatMap(number -> {
                    if (number % 2 == 0) {
                        return Stream.of(number, number);
                        // if the number is even, return the number twice
                    } else {
                        return Stream.of();
                        // if the number is odd, return an empty stream and ignore the number
                    }
                })
                .toList();
        log.info("even numbers using flatMap: {}", evenNumbers);

        evenNumbers = Stream.of(1, 2, 3, 4, 5)
                .<Integer>mapMulti((number, downstream) -> {
                    if (number % 2 == 0) {
                        downstream.accept(number);
                        downstream.accept(number);
                    }
                }).toList();
        log.info("even numbers using mapMulti: {}", evenNumbers);

    }
}