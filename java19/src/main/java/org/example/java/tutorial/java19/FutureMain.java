package org.example.java.tutorial.java19;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Slf4j
public class FutureMain {

    public static void main(String[] args) {

        // A future that completes successfully
        Future<String> successfulFuture = CompletableFuture.completedFuture("Hello, Future!");

        // A future that completes exceptionally
        CompletableFuture<String> failedFuture = new CompletableFuture<>();
        failedFuture.completeExceptionally(new RuntimeException("Something went wrong"));

        // Using resultNow on successful future
        try {
            String result = successfulFuture.resultNow();
            log.info("Result of successful future: %s , Status: %s".formatted(result, successfulFuture.state()));
        } catch (IllegalStateException e) {
            log.info("Future not complete yet or completed exceptionally.");
        }

        // Using resultNow on failed future
        try {
            String result = failedFuture.resultNow();
            log.info("Result of failed future: %s , Status: %s".formatted(result, failedFuture.state()));
        } catch (IllegalStateException e) {
            log.info("Future not complete yet or completed exceptionally , Status: %s".formatted(failedFuture.state()));
        }

        // Using exceptionNow on failed future
        try {
            Throwable exception = failedFuture.exceptionNow();
            log.info("Exception from failed future: %s, Status: %s".formatted(exception.getMessage(), failedFuture.state()));
        } catch (IllegalStateException e) {
            log.info("Future not complete yet or completed successfully.");
        }

        // Result
        // Result of successful future: Hello, Future! , Status: SUCCESS
        // Future not complete yet or completed exceptionally , Status: FAILED
        // Exception from failed future: Something went wrong, Status: FAILED

        // Explanation
        // successfulFuture: This future completes successfully with the result "Hello, Future!". Calling resultNow() on it will return "Hello, Future!".
        // failedFuture: This future completes exceptionally with a RuntimeException. Calling resultNow() on this future throws an IllegalStateException
        // because the future completed exceptionally. However, calling exceptionNow() will retrieve the RuntimeException.

        log.info("--------------------------------------------------");
        // another example
        Stream<Future<String>> futures = Stream.of(successfulFuture, failedFuture, successfulFuture, failedFuture, successfulFuture, failedFuture);
        futures.forEach(future -> {
            if(future.isDone() && future.state()!= Future.State.FAILED)
                log.info("Future is done and the result is : {}", future.resultNow());
        });
        // this will print only the already completed successful futures
    }
}