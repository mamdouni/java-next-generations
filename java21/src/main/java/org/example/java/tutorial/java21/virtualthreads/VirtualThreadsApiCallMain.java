package org.example.java.tutorial.java21.virtualthreads;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadsApiCallMain {

    static HttpClient CLIENT = HttpClient.newHttpClient();	// you can also use a builder to build a client

    static HttpRequest REQUEST = HttpRequest.newBuilder(URI.create("https://www.google.com"))
            .GET()
            .build();

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        Runnable task = () -> {
            try {
                HttpResponse<Void> response = CLIENT.send(REQUEST, HttpResponse.BodyHandlers.discarding());
                if(response.statusCode()==200)
                    log.info("Call succeeded on {}",Thread.currentThread());
                else
                    log.error("Got response {}",response.statusCode());
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        };

        try(var es = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0, 50)
                    .mapToObj(_ -> es.submit(task))
                    .toList();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Elapsed time: [{}ms , {}s]", elapsedTime, elapsedTime / 1000);

        // check the logs of this example,
        // it shows that multiple virtual threads are running on the same worker thread
        /*
            So here you clearly see the benefits of virtual threads in action.
            We spawn 50 calls and rather than blocking 50 platform threads, the virtual threads are actually unmounted
            from the worker threads until a response to the HTTP call comes in and then the work is resumed and we print out call succeeded.
            And to achieve this, we could write simple, straightforward, plain and boring codes using blocking APIs while getting non‑blocking behavior.
         */
    }
}
