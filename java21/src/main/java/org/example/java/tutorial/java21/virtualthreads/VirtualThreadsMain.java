package org.example.java.tutorial.java21.virtualthreads;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadsMain {

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

        List<Thread> threads = IntStream.range(0, 10000)
                .mapToObj(_ -> Thread.ofVirtual().start(task))
                .toList();
        // notice the use of Thread.ofVirtual() instead of Thread.ofPlatform() here
        // notice also the use of ( VirtualThread[#10020]/runnable@ForkJoinPool-1-worker-1 ) in the logs instead of ( Thread[#10020] )
        // ForkJoinPool is the Thread Platform that is used to run the virtual threads ( I have 10 workers in my machine )

        for (Thread t : threads) {
            t.join();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Elapsed time: [{}ms , {}s]", elapsedTime, elapsedTime / 1000);
    }
}
