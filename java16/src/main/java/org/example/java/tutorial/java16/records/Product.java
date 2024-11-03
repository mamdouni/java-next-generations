package org.example.java.tutorial.java16.records;

public record Product(String name, int price, boolean inStock) {
    // Product has two components: name and price
    // Records are immutable and implicitly final, so you cannot extend a record.

    // private boolean inStock;
    // Error: Records cannot declare instance variables, the compiler will complain. You can declare static variables in a record if you want.
    // Error: you cannot extend a class but you can implement interfaces.

    // what if we want to do some validation on the components of the record before instantiating it ?
    // You can add a constructor to the record to do this.
    // We can either rewrite all the constructed parameters to take all the components of the record or we can use this compact constructor syntax.
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    // You can also override the toString() method if you want
}
