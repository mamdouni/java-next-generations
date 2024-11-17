package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmojiExample {

    public static void main(String[] args) {

        // We just want to know whether a given character is an emoji or not, and this method will help.
        // This method has been added to the Character class in Java 21
        log.info("Is this an emoji ?\n -> {}", Character.isEmoji('😀'));

        // Well, the answer is false. But why?
        // we see that .isEmoji takes an integer, a code point rather than a character. And this has everything to
        // do with the fact that the character type within Java is pretty limited in its range.
        log.info( "{} is the maximum value for a character in Java", (int)Character.MAX_VALUE);

        // So if we want to get the code point for this emoji character, we can do so
        int codePoint = "😀".codePointAt(0);
        log.info("The emoji codePoint is : {}", codePoint);
        log.info("Is this an emoji ?\n -> {}", Character.isEmoji(codePoint));
    }
}
