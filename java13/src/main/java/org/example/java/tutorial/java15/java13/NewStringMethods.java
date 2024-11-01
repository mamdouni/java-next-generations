package org.example.java.tutorial.java15.java13;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class NewStringMethods {

    public static void main(String[] args) throws IOException {

        String text = "" +
                "    <html>\n" +
                "      <body>\n" +
                "        <p>Hello, World</p>\n" +
                "      </body>\n" +
                "    </html>";
        log.info(text);
        log.info(text.stripIndent());
        // this will remove the incidental white spaces in the beginning of each line
        // this uses the same algorithm as text blocks

        // The escape sequences is a feature that allows you translate escape sequences in a string
        // If your input is a file for example, the escape sequences will not be translated as shows the example below (file is in the resources folder)
        ClassLoader classLoader = NewStringMethods.class.getClassLoader();
        text = Files.readString(Path.of(classLoader.getResource("input.txt").getPath()));
        log.info(text);
        // an if you use escape sequences
        log.info(text.translateEscapes());

        // the formatted method is a new method that allows you to format a string using the same syntax as the printf method
        // this is useful when you use the text block feature
        String formatted = """
                  <html>
                      <body>
                        <p>%s, %s</p>
                      </body>
                  </html>
                  """.formatted("Hello", "World");
        log.info(formatted);

        formatted = "%s, %s".formatted("Hello", "World");
        log.info(formatted);

    }
}
