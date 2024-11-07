# Java 17

## Introduction

Java 17, released on September 14, 2021, is a significant release for the Java ecosystem as it is a Long-Term Support (LTS) version. 
This means it will receive extended support and updates, making it ideal for production use in enterprises. 
Java 17 introduces several language enhancements, library updates, new features, and API changes.

Here are the key features and changes introduced in Java 17:

## Removal

### JEP 403: Strongly Encapsulate JDK Internals
JDK internals are strongly encapsulated, preventing direct access to internal APIs by default. 
This encapsulation encourages the use of standard, supported APIs for modular applications.

## Language Enhancements

### JEP 406: Pattern Matching for switch (Preview)

Adds pattern matching to switch statements and expressions, allowing developers to perform conditional logic based on patterns rather than just values. 
This feature expands the expressiveness and readability of switch.
This is a preview feature in Java 17, which means it is not yet final and may undergo further changes before being standardized.
Check the source code for examples.

### JEP 409: Sealed Classes (Final)

Sealed classes, introduced as a preview in Java 15, are now a finalized feature. 
Sealed classes allow developers to control the class hierarchy by restricting which classes can extend a class, 
creating more controlled and predictable inheritance structures.

- [java15](../java15)

### JEP 356: Enhanced Pseudo-Random Number Generators

Introduces new interfaces and implementations for pseudo-random number generators, 
improving flexibility and adding new algorithms for random number generation. 
This includes the RandomGenerator interface and additional implementations like Xoshiro and LXM for high-performance use cases.
- https://openjdk.org/jeps/356  

## JVM Improvements

### JEP 407: Remove RMI Activation

Removes the rarely-used RMI (Remote Method Invocation) Activation mechanism from the Java platform, 
simplifying the RMI API and reducing the maintenance burden.

### JEP 356: Enhanced Pseudo-Random Number Generators
Improves the random number generation API with better algorithms, including Xoshiro and LXM, 
adding flexibility for users who need high-quality random values.

### Improved Garbage Collectors
Java 17 also brings incremental improvements to garbage collectors, 
including the Z Garbage Collector (ZGC) and the Shenandoah GC, both of which are optimized for 
large heaps and low pause times.
