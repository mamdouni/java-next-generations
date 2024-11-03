package org.example.java.tutorial.java16;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;

public class DateTimeFormatterBuilderExample {

    public static void main(String[] args) {

        var formatter = new DateTimeFormatterBuilder()
                .appendDayPeriodText(TextStyle.FULL)
                .toFormatter();

        var output = formatter.format(LocalDateTime.of(2021, 6, 1, 10, 0));
        System.out.println(output);
        output = formatter.format(LocalDateTime.of(2021, 6, 1, 19, 0));
        System.out.println(output);
        output = formatter.format(LocalDateTime.now());
        System.out.println(output);
    }
}
