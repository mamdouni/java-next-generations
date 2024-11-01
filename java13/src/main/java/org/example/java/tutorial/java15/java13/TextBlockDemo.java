package org.example.java.tutorial.java15.java13;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextBlockDemo {

    public static void main(String[] args) {

        // This is a preview feature in Java 13 so make sure you enable it in your IDE
        String text = """
                This is a text block
                i can even use double quotes " Here " and single quotes ' Here '
                that spans across multiple lines
                and it is a new feature in Java 13
                """;
        log.info(text);
        log.info(replaceWhiteSpaces(text));

        text = """
                This is a text block
                i can even use double quotes " Here " and single quotes ' Here '
                that spans across multiple lines
                and it is a new feature in Java 13
        """;
        log.info(replaceWhiteSpaces(text));
        // you can see here the importance of the closing triple quotes in the indentation.
    }

    private static String replaceWhiteSpaces(String text) {
        return text.replaceAll(" ", ".").replaceAll("\n", "\\\\n\n");
    }
}
