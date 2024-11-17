package org.example.java.tutorial.java21.recordpatterns;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class RecordPatternsMain {

    public static void main(String[] args) {

        double totalArea = shapes().mapToDouble(RecordPatternsMain::area).sum();
        log.info("Total area of all shapes: {}", totalArea);

        totalArea = shapes().mapToDouble(RecordPatternsMain::areaRefactored).sum();
        log.info("Total area of all shapes: {}", totalArea);

        totalArea = shapes().mapToDouble(RecordPatternsMain::areaRefactoredWithWhen).sum();
        log.info("Total area of all shapes: {}", totalArea);
    }

    private static Stream<Shape> shapes() {
        return Stream.of(
                new Circle(new Point(0, 0), 4),
                new Rectangle(new Point(1, 2), new Point(4, 6)),
                new Square(new Point(5, 7), 3)
        );
    }

    private static double area(Shape shape) {

        return switch (shape) {
            case Circle(Point center, int radius) -> Math.PI * radius * radius;
            case Rectangle(Point topLeft, Point bottomRight) -> {
                int width = bottomRight.x() - topLeft.x();
                int height = topLeft.y() - bottomRight.y();
                yield Math.abs(width * height);
            }
            case Square(Point topLeft, int length) -> length * length;
            // no need for a default case because the Shape interface is sealed and no other implementation is allowed
        };
    }

    private static double areaRefactored(Shape shape) {

        return switch (shape) {
            case Circle(var __ , var radius) -> Math.PI * radius * radius;

            case Rectangle(
                    Point(var lx, var ly) ,
                    Point(var rx, var ry)
            ) -> Math.abs((rx - lx) * (ry - ly));

            case Square(var _ , int length) -> length * length;
            // 1. we can start by removing the Point class and use var as it is redundant (you can do the same for the primitive values like radius also)
            // 2. Points in the Circle and Rectangle records are not used, so we can remove them and use _ instead (this is a preview feature so you can replace it __ as it is a legal identifier if you want)
            // 3. we can replace the nested Points in the Rectangle record with lx, ly, rx, ry
            // It is important to use the nested pattern matching in the Rectangle record to prevent any NullPointerException (if the Rectangle Point is null and you call rectangle.topLeft().x() for example)
            // the compiler will evaluate the expression to false if the nested point is null and will not throw a NullPointerException
        };
    }

    private static double areaRefactoredWithWhen(Shape shape) {

        return switch (shape) {
            case Circle(var _ , var radius) -> Math.PI * radius * radius;
            case Rectangle(Point(var lx, var ly) , Point(var rx, var ry)) -> Math.abs((rx - lx) * (ry - ly));

            // What if we want to prevent the user from introducing a negative point into the Square record?
            // We can implement this by using an if statement
            // Or using the when keyword in the pattern matching (introduced in java 21)
            case Square(Point(var x, var y) , int length) when x > 0 && y > 0
                    -> length * length;

            // the compiler is not happy as it cannot handle the negative values in the Square record
            // we can fix the issue using a default case or just a default case for the Square record only

            case Square _ -> 0;
        };
    }
}
