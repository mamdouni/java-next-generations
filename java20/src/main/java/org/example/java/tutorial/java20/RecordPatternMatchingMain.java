package org.example.java.tutorial.java20;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordPatternMatchingMain {

    public static void main(String[] args) {

        printPoint(new Point(10, 20)); // x: 10, y: 20
        log.info("----------  Second Example  ----------");
        printSecondPoint(new Point(30, 40)); // x: 30, y: 40
    }

    static void printPoint(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            log.info("x: {}, y: {}", x , y);
        }
    }

    static void printSecondPoint(Object obj) {
        if (obj instanceof Point p) {
            log.info("x: {}, y: {}", p.x() , p.y());
            log.info(p.printMe());
        }
    }
}

record Point(int x, int y) {

    public String printMe() {
        return "Point(x: " + x + ", y: " + y + ")";
    }
}

