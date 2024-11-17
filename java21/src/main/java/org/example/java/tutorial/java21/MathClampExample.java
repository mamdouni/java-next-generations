package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MathClampExample {

    public static void main(String[] args) {

        /*
            Let's say you define two values, a lower bounds and an upper bounds,
            and we'll assign 40 and 120 here to these two bounds. Our goal is to build a piece of functionality
            that when given any integer will return 40 if the input is lower than the lower bounds,
            will return 120 when the input is higher than the upper bounds, or we'll return the actual value
            if it falls in the range between the lower and the upper bounds.
         */

        log.info("Clamped value is : {}", bounded(200));
        log.info("Clamped value is : {}", bounded(30));
        log.info("Clamped value is : {}", bounded(50));

        // well, with java 21, we can now use the Math::clamp method to achieve the same result

        log.info("Clamped value is : {}", Math.clamp(200, 40, 120));
        log.info("Clamped value is : {}", Math.clamp(30, 40, 120));
        log.info("Clamped value is : {}", Math.clamp(50, 40, 120));
    }

    static int bounded(int i) {
        int lower = 40;
        int upper = 120;
        if(i < lower) {
            return lower;
        } else if(i > upper) {
            return upper;
        } else {
            return i;
        }
    }
}
