package org.example.java.tutorial.java16.records;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordsMain {

    public static void main(String[] args) {

        Product product1 = new Product("Laptop", 1000, true);
        log.info("Product name: {}", product1.name());
        log.info("Product price: {}", product1.price());
        log.info("Product in stock: {}", product1.inStock());
        log.info("{}",product1);
        // Notice that the generated toString() method prints the values of the components of the record and that you can access the components using the accessor methods.
        // Records are immutable, so you cannot change the values of the components once the record is created.

        Product product2 = new Product("Laptop", 1000, true);
        log.info("{}",product1 == product2);
        log.info("{}",product1.equals(product2));
        // Don't forget that records implement equals() and hashCode() methods based on the components of the record.

        // We can also define local records inside a method as below
        record DiscountedProduct(Product product, int discount) {}
        log.info("{}",new DiscountedProduct(product1, 100));
    }
}
