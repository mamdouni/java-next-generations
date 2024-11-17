package org.example.java.tutorial.java21.recordpatterns;

record Point(int x, int y) {}

public sealed interface Shape {}

record Circle(Point center, int radius) implements Shape {}

record Rectangle(Point topLeft, Point bottomRight) implements Shape {}

record Square(Point topLeft, int length) implements Shape {}