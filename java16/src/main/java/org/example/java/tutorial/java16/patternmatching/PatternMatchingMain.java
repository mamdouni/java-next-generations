package org.example.java.tutorial.java16.patternmatching;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatternMatchingMain {

    public static void main(String[] args) {

        Record o = new Person("John", 30);

        if(o instanceof Person p) {
            log.info("Person name: {}", p.name());
            log.info("Person age: {}", p.age());
        } else {
            log.info("Not a Person");
        }


        if(o instanceof Person p && p.age() > 20) {
            // We can use p in right side of the && operator. The compiler knows that p is a Person.
            // if you change && by ||, the compiler will not know that p is a Person and you'll have a compiler error
            log.info("Is a Person {}", p);
        }

        if(!(o instanceof Person p)) {
            // The compiler is smart enough to know that p is not a Person here
            // if you use p here, you'll have a compiler error
            log.info("Not a Person");
        } else {
            log.info("Is a Person {}", p);
        }
    }
}
