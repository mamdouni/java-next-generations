package org.example.java.tutorial.java15.sealed;

public class Main {

    public static void main(String[] args) {

        Expr e = new Add(
                new Const(1),
                new Mul(new Const(2), new Const(3)
                )
        );

        System.out.printf("%s = %d%n", e, e.eval());
    }
}

final class Square extends Shape {
    // Implementation specific to Square
    // Shape must mention Square in the permits keyword as this file is in another file
    // Square is final because it inherits from a sealed class
}
