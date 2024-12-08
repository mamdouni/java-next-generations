package org.example.java.tutorial.java22.structuredconcurency;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;

@Slf4j
public class LegacyConcurrentExample {

    // https://www.youtube.com/watch?v=9O7ukS-DHV0

    // this main expression has been added in Java 21 as a preview feature
    void main() throws Exception {

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            var task1 = executor.submit(() -> {
                Thread.sleep(2000);
                throw new RuntimeException("Task 1 failed");
                //return "Task 1";
            });
            var task2 = executor.submit(() -> {
                Thread.sleep(10000);
                return "Task 2";
            });
            var before = Instant.now();

            log.info("Waiting for the tasks to finish");
            var result1 = task1.get();
            log.info("Got the first result");
            var result2 = task2.get();

            var time = Duration.between(before, Instant.now());
            log.info(STR."\{result1} and \{result2} in \{time}");
        }
    }
    /*

      What happens here is that if task 1 is failed, we will wait for the execution of task 2 to finish
      even if we already know that task 1 has failed. This is because the get method is blocking
      What if the result of task 2 is based on the result of task 1? In this case, we need to wait ? no
      This case can be resolved using the StructuredConcurrency API

     */
}
