package org.example.java.tutorial.java15.java11;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LibraryImprovement {

    public static void main(String[] args) throws IOException {

        // ### String class new methods
        System.out.println("Repeat method : " + "na ".repeat(16).concat("Batman!"));
        System.out.println("Is Blank method : " + "     \r\n".isBlank());

        // the strip method removes white characters even the blank unicode because it is based on the Character.isWhitespace('') method
        String whiteString =  "\n\t     This is a text     \u2005";
        System.out.println("Strip method : " + "'" + whiteString.strip() + "'");
        System.out.println("Trim method  : " + "'" + whiteString.trim() + "'");

        // process multilines
        String multilines = "1\n2\n3";
        multilines.lines().forEach(System.out::println);
        System.out.println("------------------------");

        // ### Files class new methods
        if(false) {
            Path myPath = Path.of("Your Path");
            String fileContent = Files.readString(myPath);    // don't care about opening and closing the file, UTF 8 by default
            fileContent = Files.readString(myPath, Charset.defaultCharset()); // custom encoding

            Path filePath = Files.writeString(myPath, "This is a new line");
            filePath = Files.writeString(myPath, "This is a new line", StandardOpenOption.APPEND); // open option to define the strategy (here append to the file content)
            filePath = Files.writeString(myPath, "This is a new line", Charset.defaultCharset(), StandardOpenOption.APPEND);
        }

        // ### Optional
        var opt = Optional.ofNullable(null);
        System.out.println(opt.isEmpty());  // like isPresent, the isEmpty method has been added
        System.out.println("------------------------");

        // ### Predicate    :   Add of a static method to the Predicate interface
        Stream<String> strings = Stream.of("str", "ing", "");
        // Before
        strings
                .filter(((Predicate<String>)(String::isBlank)).negate())
                .forEach(System.out::println);
        // After
        strings = Stream.of("str", "ing", "");
        strings
                .filter(Predicate.not(String::isBlank))
                .forEach(System.out::println);
        System.out.println("------------------------");


        // ### Unicode 10   :   upgrade from unicode 8 to unicode 10 , Adds 16.000+ new characters
        System.out.println('\u20BF');   // Bitcoin character
    }
}
