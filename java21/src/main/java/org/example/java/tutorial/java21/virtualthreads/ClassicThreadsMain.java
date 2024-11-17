package org.example.java.tutorial.java21.virtualthreads;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class ClassicThreadsMain {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        Runnable task = () -> {
            try {

                log.info("Sleeping on thread: {}", Thread.currentThread());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = Thread.ofPlatform().start(task); // creates a regular old java thread
        // ofPlatform : A builder for creating Thread or ThreadFactory objects , since java 21

        thread.join();

        List<Thread> threads = IntStream.range(0, 10000)
                .mapToObj(_ -> Thread.ofPlatform().start(task))
                .toList();

        for (Thread t : threads) {
            t.join();
        }

        // it throws : "Thread-8162" : unable to create native thread: possibly out of memory or process/resource limits reached
        // after 8k threads, seems like the limit is reached and we cannot create more threads
        // check the virtual threads example to see how to overcome this limitation

        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Elapsed time: [{}ms , {}s]", elapsedTime, elapsedTime / 1000);
    }
}
