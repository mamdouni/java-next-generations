package org.example.java.tutorial.java22.structuredconcurency;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;

@Slf4j
public class StructuredTaskScopeExample2 {

    // this main expression has been added in Java 21 as a preview feature
    void main() throws Exception {

        try(var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            scope.fork(() -> {
                Thread.sleep(2000);
                throw new RuntimeException("Task 1 failed");
                //return "Task 1";
            });
            scope.fork(() -> {
                Thread.sleep(5000);
                return "Task 2";
            });
            var before = Instant.now();

            var result = scope.join().result();
            // this will throw an exception if any of the tasks succeeded

            var time = Duration.between(before, Instant.now());
            log.info(STR."\{result}, in \{time}");
        }
    }
    /*
        Here we are only interested on tasks that succeeded, if any of the tasks failed, we will look for the next task
        source: https://www.youtube.com/watch?v=9O7ukS-DHV0
     */
}
