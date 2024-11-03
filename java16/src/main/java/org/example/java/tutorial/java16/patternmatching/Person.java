package org.example.java.tutorial.java16.patternmatching;

import java.util.Objects;

public record Person(String name, int age) {

    @Override
    public boolean equals(Object o) {

        // here's how we are using the instanceof operator with equals method
        //return (o instanceof Person) && Objects.equals(name, ((Person) o).name);

        // and how we can now use the instanceof operator with the pattern matching feature
        return o instanceof Person p && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
