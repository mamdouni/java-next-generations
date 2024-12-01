package org.example.java.tutorial.java23.gatherers;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Gatherer;

@Slf4j
public class GatherersExample2 {

    void main() {

        /*
            There's some methods in the Stream API that can interrupt the flow of the stream, like :
            - limit and takeWhile: that can stop the stream before processing all the elements
            - skip and dropWhile: that can skip some elements before processing the rest of the stream
            interrupting is not like the same as filtering as in the filtering you can remove some elements but the stream will continue to process all the elements
            those methods need to manage an internal state to know when to stop or skip the elements
            limit and skip have an internal counter (mutable state) to know how many elements to skip or to process
         */

        var strings = List.of("one", "two", "three", "four", "five", "six");
        var mappedStrings = strings.stream()
                .map(element -> {
                    log.info("Mapping element: {}", element);
                    return element.toUpperCase();
                })
                .limit(3L)
                .toList();
        // the call to limit here will interrupt the stream after processing 3 elements exactly
        log.info("Mapped strings: {}", mappedStrings);

        log.info("----------");

        // let's do the same using gatherers using this signature
        // https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Gatherer.html#ofSequential(java.util.function.Supplier,java.util.stream.Gatherer.Integrator)
        // static <T,A,R> GathererPREVIEW<T,A,R> ofSequential(Supplier<A> initializer, Gatherer.IntegratorPREVIEW<A,T,R> integrator)

        mappedStrings = strings.stream()
                .map(element -> {
                    log.info("Mapping element: {}", element);
                    return element.toUpperCase();
                })
                .gather(limit(3L))
                .toList();
        log.info("Mapped strings: {}", mappedStrings);

        // Well, in this example and as you can see the gatherer did not interrupt the stream after processing 3 elements
        // how to fix this?
        // Well, we have to manage the boolean value returned by the integrator function
        // If you return false, then what you are telling the Stream API is that you are not going to push an anymore element to this downstream.  And the Stream API will react accordingly
        // by interrupting the stream. And by the way, because this downstream may itself interrupt the stream, before doing anything, you should check for that in your Integrator implementation.
        // There is a isRejecting() method on the Downstream interface, that you should check before doing anything.

        log.info("----------");

        mappedStrings = strings.stream()
                .map(element -> {
                    log.info("Mapping element: {}", element);
                    return element.toUpperCase();
                })
                .gather(limit2(3L))
                .toList();
        log.info("Mapped strings: {}", mappedStrings);

    }

    static Gatherer<String, ? ,String> limit(long limit) {

        class Counter {
            int count;
        }

        return Gatherer.<String,Counter,String>ofSequential(
                Counter::new,
                (state, element, downstream) -> {
                    if (state.count < limit) {
                        state.count++;
                        downstream.push(element);
                    }
                    return true;
                }
                // This lambda returns an integrator: https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Gatherer.Integrator.html
                // Interface Gatherer.Integrator<A,T,R>
                // A - the type of state used by this integrator
                // T - the type of elements this integrator consumes
                // R - the type of results this integrator can produce
        );
        // And this method returns a Gatherer with this signature:
        // static <T,A,R> GathererPREVIEW<T,A,R> ofSequential(Supplier<A> initializer, Gatherer.IntegratorPREVIEW<A,T,R> integrator)
    }

    static Gatherer<String, ? ,String> limit2(long limit) {

        class Counter {
            int count;
        }

        return Gatherer.<String,Counter,String>ofSequential(
                Counter::new,
                (state, element, downstream) -> {
                    log.info("-> Calling the gatherer with the couple [{},{}]",element, state.count);
                    if(downstream.isRejecting()) {
                        return false;
                        // You need to check if the downstream accepts more values. You need to call downstream.isRejecting()
                        // for that. And if it does not, then you don't  need to do anything, and you can return false.
                        // here you need to notify your upstream that you are not accepting any more element neither.
                    }
                    if (state.count++ < limit) {
                        downstream.push(element);
                    }
                    return state.count < limit;
                    // Finally, you need to decide if you will be accepting more elements or not. If yes, you need to return true, and false if not.
                }
        );
    }
}
