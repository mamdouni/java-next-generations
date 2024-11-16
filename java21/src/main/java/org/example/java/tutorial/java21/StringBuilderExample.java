package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringBuilderExample {

    public static void main(String[] args) {

        // Java 11 has already introduced the String::repeat method
        log.info("Hello world! ".repeat(3));
    }
}