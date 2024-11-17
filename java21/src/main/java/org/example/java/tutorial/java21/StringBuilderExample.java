package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringBuilderExample {

    public static void main(String[] args) {

        // Java 11 has already introduced the String::repeat method
        log.info("Hello world / ".repeat(3));

        // This method has been added to the StringBuilder and StringBuffer classes in Java 21
        var builder = new StringBuilder();
        builder.repeat("Hello world / ",3);
        log.info(builder.toString());
    }
}