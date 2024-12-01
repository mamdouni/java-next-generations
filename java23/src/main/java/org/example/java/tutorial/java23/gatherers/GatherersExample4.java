package org.example.java.tutorial.java23.gatherers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;

@Slf4j
public class GatherersExample4 {

    void main() {

        /*
            In this example, we want to count the number of elements in a stream.
            example: var ints = List.of(1, 2, 2, 3, 4, 1, 3, 2, 4, 5);
            result :
            1=2
            2=3
            3=2
            4=2
            5=1
            To follow the example, check this video: https://www.youtube.com/watch?v=jqUhObgDd5Q&t=3664s
         */

        var ints = List.of(1, 2, 2, 3, 4, 1, 3, 2, 4, 5);
        var countedIntegers = ints.stream()
                .gather(counting())
                .toList();
        log.info("Counted integers using Gatherers:\n{}", countedIntegers);

        log.info("----------");

        // Same example using the Collectors API
        Map<Integer, Long> countedIntegers2 = ints.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        log.info("Counted integers using the counting() method of Collectors API:\n{}", countedIntegers2);

        log.info("----------");

        Map<Integer, Long> countedIntegers3 = ints.stream()
                .collect(new CountingCollector<>());
        // remember that collectors are terminal operations
        log.info("Counted integers using a custom Collector (similar to the gather finally):\n{}", countedIntegers3);
    }

    static <T> Gatherer<T, ?, Map.Entry<T, Integer>> counting() {

        // T is the type of your stream elements
        // the mutable state will be a map

        class Counter {
            int count;
        }

        // The supplier will produce a new map that handles the stream element as key and its count as value
        Supplier<Map<T, Counter>> initializer = HashMap::new;

        Gatherer.Integrator<
                Map<T, Counter>,   // the mutable state
                T,                 // the element of the stream
                Map.Entry<T, Integer> // the type of the object this Gatherer can produce
                > integrator = (state, element, downstream) -> {

            if(downstream.isRejecting()) {
                return false;
            }
            state.computeIfAbsent(element, _ -> new Counter()).count++;
            return true; // process the next element
        };

        BiConsumer<
                Map<T, Counter>, // the mutable state
                Gatherer.Downstream<? super Map.Entry<T, Integer>> // the downstream
                > finisher = (state, downstream) -> {
                // you should not expose the mutable state to the external world
                if(!downstream.isRejecting()) {

                    state.entrySet().stream()
                            // you should map the map entries to the object you want to push to the downstream (and hide the use of Counter calls)
                            .map(entry -> Map.entry(entry.getKey(), entry.getValue().count))
                            .takeWhile(_ -> !downstream.isRejecting()) // you need to make sure that the downstream is not  rejecting these new elements.
                            .forEach(downstream::push);
                }
        };
        // The finisher will be called when the stream is done producing elements

        return Gatherer.ofSequential(
                initializer,
                integrator,
                finisher
        );
    }

    static class CountingCollector<T> implements Collector<T, Map<T, Long>, Map<T, Long>> {

        @Override
        public Supplier<Map<T, Long>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<T, Long>, T> accumulator() {
            // map replaces state in gathers
            return (map, element) -> map.merge(element, 1L, Long::sum);
            /*
                The merge method in a Map is used to combine a new value with an existing value associated with a specified key.
                If the key is not already present in the map, the method inserts the key with the new value.
                If the key is present, it applies a specified remapping function to the existing value and the new value, and updates the key with the result.
                V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
             */
        }

        @Override
        public BinaryOperator<Map<T, Long>> combiner() {

            // This combiner is not used in the sequential stream but in parallel streams to combine the results of the different threads
            // For Gatherers, we did not implement this method because we are not using parallel streams
            return (map1, map2) -> {
                map2.forEach((key, value) -> map1.merge(key, value, Long::sum));
                return map1;
            };
        }

        @Override
        public Function<Map<T, Long>, Map<T, Long>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.IDENTITY_FINISH);
        }
    }
}
