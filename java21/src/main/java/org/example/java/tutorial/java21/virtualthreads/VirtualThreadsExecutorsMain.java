package org.example.java.tutorial.java21.virtualthreads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadsExecutorsMain {

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

        try(var es = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0, 10000)
                    .mapToObj(_ -> es.submit(task))
                    .toList();
        }
        //  The nice thing about this Executor service and using it inside of a try with resources is that after the loop,
        //  it automatically calls close on the Executor service. And close in this Executor service is implemented in such
        //  a way that it will wait for all tasks to finish before it returns, which means we don't have to manually join all
        //  threads anymore

        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Elapsed time: [{}ms , {}s]", elapsedTime, elapsedTime / 1000);
    }
}
