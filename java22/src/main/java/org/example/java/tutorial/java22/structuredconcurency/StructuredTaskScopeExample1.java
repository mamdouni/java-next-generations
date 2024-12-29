package org.example.java.tutorial.java22.structuredconcurency;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;

@Slf4j
public class StructuredTaskScopeExample1 {

    // this main expression has been added in Java 21 as a preview feature
    void main() throws Exception {

        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var task1 = scope.fork(() -> {
                Thread.sleep(2000);
                throw new RuntimeException("Task 1 failed");
                //return "Task 1";
            });
            var task2 = scope.fork(() -> {
                Thread.sleep(10000);
                return "Task 2";
            });
            var before = Instant.now();

            scope.join().throwIfFailed();
            // this will throw an exception if any of the tasks failed

            var time = Duration.between(before, Instant.now());
            log.info(STR."\{task1.get()} and \{task2.get()} in \{time}");
        }
    }
    /*
        We can even create a hierarchy of scopes, where a scope can have a parent scope
        source: https://www.youtube.com/watch?v=9O7ukS-DHV0
     */
}
