package org.example.java.tutorial.java15.sealed;

public sealed class Shape permits Circle, Square, Rectangle {

    // permits keyword is used to specify the classes that are allowed to extend the sealed class
    // We have to explicitly mention the classes that are allowed to extend the sealed class because the Square is in another file
    // Subclasses of sealed class must be final, sealed or non-sealed.
}

final class Circle extends Shape {
    // Implementation specific to Circle
    // Circle is final because it inherits from a sealed class
}

non-sealed class Rectangle extends Shape {
    // Implementation specific to Rectangle
    // Other classes can extend Rectangle
    // This subclass removes the restriction, allowing any class to extend it.
}

class Rhombus extends Rectangle {
    // Implementation specific to Square
    // Additional properties and methods for Rhombus
}
