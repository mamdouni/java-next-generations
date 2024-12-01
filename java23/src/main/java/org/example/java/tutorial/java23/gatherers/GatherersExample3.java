package org.example.java.tutorial.java23.gatherers;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherer;

@Slf4j
public class GatherersExample3 {

     void main() {

         /*
          Well, if we have the limit method in the Stream API, why we need to write such a complex Gatherer to do the same thing?
          The answer is that the Gatherer API is more flexible than the Stream API. You can do things with the Gatherer API that you cannot do with the Stream API.
          Check the example here:
          https://www.youtube.com/watch?v=jqUhObgDd5Q&t=3311s
          */

         var integers = List.of(1, 2, 3, 4);
         // Let's say that we want to transform this list to a list of lists, where each list contains the elements of two consecutive elements of the original list
         // So, the result should be List.of(List.of(1, 2), List.of(2, 3), List.of(3, 4))
         // This is not possible using the Stream API methods

         // Let's create a Gatherer that does this
         var mappedIntegers = integers.stream()
                 .gather(slidingWindow(2))
                 .toList();
         log.info("Mapped integers with slidingWindow: {}", mappedIntegers);

         // Now, we want a fixed window of 2 elements instead of a sliding window.
         // The result must be [[1, 2], [3, 4]] in this case, Let's do that
         mappedIntegers = integers.stream()
                 .gather(fixedWindow(2))
                 .toList();
         log.info("Mapped integers with fixedWindow: {}", mappedIntegers);

         // what if we add an element to the list, let's say 5, the result should be [[1, 2], [3, 4], [5]]
         integers = List.of(1, 2, 3, 4, 5);

         mappedIntegers = integers.stream()
                 .gather(fixedWindow(2))
                 .toList();
         log.info("Mapped integers with fixedWindow (second example): {}", mappedIntegers);

         // What the result is : [[1, 2], [3, 4]] instead of [[1, 2], [3, 4], [5]] ???
         // This is normal as in the last iteration, we added the 5 to the mutable state without pushing it to the downstream
         // That's why the Gatherer API provides the finisher. The finisher does not take any element, because it is called when the
         // upstream is done producing elements. So there is no more element to consume. This finisher can then examine your mutable state,
         // and decide what to do with it. It could even do something if this state object is empty by the way.

         mappedIntegers = integers.stream()
                 .gather(fixedWindowWithFinisher(2))
                 .toList();
         log.info("Mapped integers with fixedWindowWithFinisher: {}", mappedIntegers);
    }

    static Gatherer<Integer, ?, List<Integer>> slidingWindow(int limit) {

         class SlidingWindow {
             private final List<Integer> window = new ArrayList<>(limit);
         }

        return Gatherer.ofSequential(
                SlidingWindow::new,
                (state, element, downstream) -> {

                    if(downstream.isRejecting()) {
                        return false;
                    }
                    state.window.add(element);
                    if(state.window.size() == limit) {
                        downstream.push(List.copyOf(state.window));
                        state.window.removeFirst();
                    }
                    return true; // we are not interrupting the stream in this case
                }
        );
    }

    static Gatherer<Integer, ?, List<Integer>> fixedWindow(int limit) {

        class SlidingWindow {
            private final List<Integer> window = new ArrayList<>(limit);
        }

        return Gatherer.ofSequential(
                SlidingWindow::new,
                (state, element, downstream) -> {

                    if(downstream.isRejecting()) {
                        return false;
                    }
                    state.window.add(element);
                    if(state.window.size() == limit) {
                        downstream.push(List.copyOf(state.window));
                        state.window.clear();
                    }
                    return true;
                }
        );
    }

    static Gatherer<Integer, ?, List<Integer>> fixedWindowWithFinisher(int limit) {

        class SlidingWindow {
            private final List<Integer> window = new ArrayList<>(limit);
        }

        return Gatherer.ofSequential(
                SlidingWindow::new,
                (state, element, downstream) -> {
                    if (downstream.isRejecting()) {
                        return false;
                    }
                    state.window.add(element);
                    if (state.window.size() == limit) {
                        downstream.push(List.copyOf(state.window));
                        state.window.clear();
                    }
                    return true;
                },
                // Finisher here
                (state, downstream) -> {
                    if (!downstream.isRejecting() && !state.window.isEmpty()) {
                        downstream.push(List.copyOf(state.window));
                    }
                }
        );
    }
}
