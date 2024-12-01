package org.example.java.tutorial.java23.gatherers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

@Slf4j
public class GatherersExample5 {

    void main() {

        /*
            We will use the same example as the example 4, but now we will use parallel streams
            Like in the collectors from the last example, we will create a combiner to merge the partial results
         */

        var ints = List.of(1, 2, 2, 3, 4, 1, 3, 2, 4, 5);
        var countedIntegers = ints.stream()
                .gather(counting())
                .toList();
        log.info("Counted integers using Gatherers:\n{}", countedIntegers);
    }

    static <T> Gatherer<T, ?, Map.Entry<T, Integer>> counting() {

        class Counter {
            int count;
        }

        Supplier<Map<T, Counter>> initializer = HashMap::new;

        Gatherer.Integrator<Map<T, Counter>, T, Map.Entry<T, Integer>> integrator = (state, element, downstream) -> {

            if(downstream.isRejecting()) {
                return false;
            }
            state.computeIfAbsent(element, _ -> new Counter()).count++;
            return true;
        };

        BiConsumer<Map<T, Counter>, Gatherer.Downstream<? super Map.Entry<T, Integer>>> finisher = (state, downstream) -> {
            if(!downstream.isRejecting()) {
                state.entrySet().stream()
                        .map(entry -> Map.entry(entry.getKey(), entry.getValue().count))
                        .takeWhile(_ -> !downstream.isRejecting())
                        .forEach(downstream::push);
            }
        };

        BinaryOperator<Map<T, Counter>> combiner = (map1, map2) -> {
            map2.forEach(
                    (key, counter) -> map1.merge(
                            key,
                            counter,
                            (counter1, counter2) -> {
                                counter1.count += counter2.count;
                                return counter1;
                            }
                            )
            );
            return map1;
        };

        return Gatherer.of(
                initializer,
                integrator,
                combiner,
                finisher
        );
    }
}
