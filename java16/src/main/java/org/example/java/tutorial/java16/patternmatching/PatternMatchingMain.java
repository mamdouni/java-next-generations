package org.example.java.tutorial.java16.patternmatching;

public class PatternMatchingMain {

    public static void main(String[] args) {

        Record o = new Person("John", 30);

        if(o instanceof Person p) {
            System.out.println("Person name: " + p.name());
            System.out.println("Person age: " + p.age());
        } else {
            System.out.println("Not a Person");
        }

        System.out.println("-----------------");

        if(o instanceof Person p && p.age() > 20) {
            // We can use p in right side of the && operator. The compiler knows that p is a Person.
            // if you change && by ||, the compiler will not know that p is a Person and you'll have a compiler error
            System.out.println("Is a Person " + p);
        }

        System.out.println("-----------------");
        if(!(o instanceof Person p)) {
            // The compiler is smart enough to know that p is not a Person here
            // if you use p here, you'll have a compiler error
            System.out.println("Not a Person");
        } else {
            System.out.println("Is a Person " + p);
        }
    }
}
