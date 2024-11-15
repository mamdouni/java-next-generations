package org.example.java.tutorial.java17;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwitchPatternMatchingMain {

    public static void main(String[] args) {

        //source : https://www.baeldung.com/java-switch-pattern-matching
        // In the earlier versions of Java, the selector expression had to be a number, a string, or a constant.
        // Also, the case labels could only contain constants:
        final String b = "B";
        switch (args[0]) {
            case "A" -> System.out.println("Parameter is A");
            case b -> System.out.println("Parameter is b");
            default -> System.out.println("Parameter is unknown");
        }

        // Applying type patterns to the instanceof operator simplifies type checking and casting.
        log.info("{}",getDoubleUsingIf(10)); // 10.0

        // In Java 17, we can use pattern matching in switch expressions to match the case labels with patterns.
        // Let’s look at how type patterns and the instanceof operator can be applied in switch statements.
        log.info("{}",getDoubleUsingSwitch(20)); // 20.0

        // Type patterns help us transfer control based on a particular type.
        // However, sometimes, we also need to perform additional checks on the passed value.
        log.info("{}",getDoubleValueUsingIf("30")); // 30.0

        // We can solve the same problem using guarded patterns.
        // They use a combination of a pattern and a boolean expression:
        log.info("{}",getDoubleValueUsingGuardedPatterns("40")); // 40.0

    }

    static double getDoubleUsingIf(Object o) {
        double result;
        if (o instanceof Integer) {
            result = ((Integer) o).doubleValue();
        } else if (o instanceof Float) {
            result = ((Float) o).doubleValue();
        } else if (o instanceof String) {
            result = Double.parseDouble(((String) o));
        } else {
            result = 0d;
        }
        return result;
    }

    static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case Integer i -> i.doubleValue();
            case Float f -> f.doubleValue();
            case String s -> Double.parseDouble(s);
            case null -> 1d;
            default -> 0d;
        };
        // In earlier versions of Java, the selector expression was limited to only a few types. However, with type patterns, the switch selector expression can be of any type.
    }

    static double getDoubleValueUsingIf(Object o) {
        return switch (o) {
            case String s -> {
                if (!s.isEmpty()) {
                    yield Double.parseDouble(s);
                } else {
                    yield 0d;
                    // The yield statement is used to return a value from a switch expression.
                    // it was introduced in Java 13 as a preview feature and became a standard feature in Java 14.
                }
            }
            default -> 0d;
        };
    }

    static double getDoubleValueUsingGuardedPatterns(Object o) {
        return switch (o) {
            case String s && s.length() > 0 -> Double.parseDouble(s);
                default -> 0d;
        };
    }
}