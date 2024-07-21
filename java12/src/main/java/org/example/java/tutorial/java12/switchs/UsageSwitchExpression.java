package org.example.java.tutorial.java12.switchs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsageSwitchExpression {

    public static void main(String[] args) {

        // Switch Expression
        // in previous versions, the switch statement presents a lot of boilerplate code.
        // you have to use the break statement to prevent the fall-through behavior.

        int monthNumber = 1;
        String monthName;
        switch (monthNumber) {
            case 1: monthName = "January";
                break;
            case 2: monthName = "February";
                break;
                //rest of cases
            default: monthName = "Invalid month";
                break;
        }
        log.info("Month name : {}", monthName);

        // Switch expression is a new feature in Java 12 that is used to simplify the switch statement.
        // This must be used with --enable-preview flag as it is a preview feature.
        // It will be introduced in Java 13 as a standard feature.
        /*
        monthName = switch (monthNumber) {
            case 1 -> "January";
            case 2 -> "February";
            case 3,4,5 -> "February";
            //rest of cases
            default -> "Invalid month";
        };
        log.info("Month name : {}", monthName);
         */
    }
}
