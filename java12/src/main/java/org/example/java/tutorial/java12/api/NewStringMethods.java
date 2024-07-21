package org.example.java.tutorial.java12.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewStringMethods {
    public static void main(String[] args) {

        // indent method
        String oneLineString = "Hello World";
        log.info(oneLineString.indent(2) + "This is a new line");
        // As you can see, the indent method is used to add a number of spaces to the beginning of the string and an \n at the end.

        // The indent is more useful when you have a multi-line string
        String multiLineString = "This\nis\na\nmulti-line\nstring";
        log.info(multiLineString.indent(2));

        // transform method
        String text = "$some text $with$ some$ $dollar $signs";
        log.info("{}",
                (Object) StringUtils.words(StringUtils.clean(text.toUpperCase()))
        );
        // As you can see, it is hard to read the code above. We can use the transform method to make it more readable.
        // The transform method is used to apply a function to the string and return the result.
        log.info("{}",
                (Object) text.transform(String::toUpperCase)
                        .transform(StringUtils::clean)
                        .transform(StringUtils::words)
        );
    }

    public static final class StringUtils {

        private StringUtils() {
            throw new AssertionError("No instances for you!");
        }

        public static String clean(String str) {
            return str.replaceAll("\\$", "");
        }

        public static String[] words(String str) {
            return str.split(" ");
        }
    }
}
