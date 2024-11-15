package org.example.java.tutorial.java20;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

@Slf4j
public class VirtualThreadExample {

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> log.info("Hello from virtual thread!"));
        }
    }
}
