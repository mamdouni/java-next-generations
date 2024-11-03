package org.example.java.tutorial.java16.records;

public class RecordsMain {

    public static void main(String[] args) {

        Product product1 = new Product("Laptop", 1000, true);
        System.out.println("Product name: " + product1.name());
        System.out.println("Product price: " + product1.price());
        System.out.println("Product in stock: " + product1.inStock());
        System.out.println(product1);
        // Notice that the generated toString() method prints the values of the components of the record and that you can access the components using the accessor methods.
        // Records are immutable, so you cannot change the values of the components once the record is created.

        System.out.println("-----------------");
        Product product2 = new Product("Laptop", 1000, true);
        System.out.println(product1 == product2);
        System.out.println(product1.equals(product2));
        // Don't forget that records implement equals() and hashCode() methods based on the components of the record.

        System.out.println("-----------------");
        // We can also define local records inside a method as below
        record DiscountedProduct(Product product, int discount) {}
        System.out.println(new DiscountedProduct(product1, 100));
    }
}
