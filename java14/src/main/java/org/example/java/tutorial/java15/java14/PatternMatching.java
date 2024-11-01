package org.example.java.tutorial.java15.java14;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j

public class PatternMatching {

    public static void main(String[] args) {

        Number salary = new BigDecimal("1234567890.0987654321");

        if(salary instanceof BigDecimal) {

            BigDecimal b = (BigDecimal) salary;
            log.info("Salary is a BigDecimal with a precision of : {}", b.precision());
        } else {
            log.info("Salary is not a BigDecimal");
        }

        // each time you want to use the value of salary as a BigDecimal, you have to cast it.
        // With pattern matching, you can use the value directly.

        if(salary instanceof BigDecimal b) {
            log.info("Salary is a BigDecimal with a precision of : {}", b.precision());
        } else {
            log.info("Salary is not a BigDecimal");
        }

        // be careful, the variable b will be available in the inside block only if the instanceof condition is true.
        // the compiler is smart enough to know if the variable should be instantiated or not.
        // this code for example will not compile because the variable b is not available in the else block.

        /*

        if(salary instanceof BigDecimal b) {
            log.info("Salary is a BigDecimal with a precision of : {}", b.precision());
        } else {
            log.info("Salary is a BigDecimal with a precision of : {}", b.precision());
        }

        This code also should not compile

        if(!(salary instanceof BigDecimal b)) {
            log.info("Salary is a BigDecimal with a precision of : {}", b.precision());
        }

         */
    }
}