# Java 22

## Introduction

Java 22 introduces several exciting features and enhancements, including updates to the language, libraries, performance, and tooling.

You can check the differences between Java 22 and Java 21 here : 
- https://javaalmanac.io/jdk/22/apidiff/21/

## Language Enhancements

### Scoped Values

Scoped values enable developers to store and share immutable data within and across threads. 
This new API is introduced in Java 20 as an incubator preview feature proposed in JEP 439.
This feature is still in preview in Java 22.

Since Java 1.2, we can make use of thread-local variables to share data between components without resorting to method arguments. 
A thread-local variable is simply a variable of a special type ThreadLocal.
While ThreadLocal is a powerful tool for managing thread-specific data, it has several limitations and potential pitfalls. 
Understanding these drawbacks is essential to using it effectively:
- Mutability: ThreadLocal is mutable, that's why it is not thread-safe and java has to copy it for each thread. Nothing prevents you also from setting the value in the same thread and that can leads to confuse in your code.
- Memory Leaks: If ThreadLocal variables are not explicitly removed after use, they can lead to memory leaks, particularly in environments like application servers with thread pooling.
- Misuse or Overuse: Developers might overuse ThreadLocal as a shortcut for passing variables, leading to code that is harder to debug and maintain.
- Debugging Complexity: Debugging thread-local issues can be challenging because the data is implicitly tied to threads rather than explicitly passed.
- No Support for Asynchronous Programming: ThreadLocal is designed for single-threaded access and does not work seamlessly with asynchronous frameworks like CompletableFuture or reactive programming models.
- Potential Performance Overhead: While ThreadLocal is designed for thread-specific data, excessive use or large thread-local data structures can introduce overhead and degrade performance.
- FootPrint: ThreadLocal is a heavy object and costs a lot in terms of resources.

Check more about the Scoped Values here :
- https://www.youtube.com/watch?v=fjvGzBFmyhM
- https://www.youtube.com/watch?v=Vi_Unj-I3BU

https://belief-driven-design.com/looking-at-java-22-stream-gatherers-9485d/


### JEP 453: Structured Concurrency (Preview)

Structured concurrency aims to simplify the management of asynchronous operations by treating related tasks as a single unit of work.
This approach enhances readability, error handling, and cancellation management.
This feature is introduced as an incubator api in java 19 and as a previewed feature in java 21.

Benefits:
- Improved Readability: Clearly defines the start, execution, and completion of concurrent tasks within a structured block.
- Streamlined Error Handling: Propagates errors through the entire unit of work, simplifying error management.
- Enhanced Cancellation: Ensures all tasks within the group are terminated properly when cancellation is requested.

- https://openjdk.org/jeps/453

You can find more here :
- https://www.youtube.com/watch?v=9O7ukS-DHV0

You can create your own custom scope to manage the tasks in a structured way. Look at this demo :
- https://youtu.be/2nOj8MKHvmw?t=1027


### Statements Before Super [JEP 447]

Prior to JDK 22, code execution within a constructor had to occur after the super call, 
which initializes the parent class.
This restriction often forced developers
to place initialization logic within the parent class constructor or use instance initialization blocks,
which could lead to code duplication or awkward workarounds.
Same case for this() call, it should be the first statement in the constructor in previous versions.

So previously, Java required that super() or this() be the first statement in a constructor, which limited the ability to perform certain initialization tasks before the parent constructor was called.

Why Was This Feature Introduced?
- Initialization Logic: In many scenarios, you might need to compute values or perform checks before passing parameters to a superclass constructor.
- Readability and Maintainability: The restriction of having super() or this() as the first statement often forced developers to refactor initialization logic into static methods or other constructs, which could make the code harder to follow.
- Flexibility: This feature simplifies constructor code by allowing local initialization logic to coexist with constructor chaining.

Example Before Java 22 :
```java
public class Shape {
  private String color;

  public Shape(String color) {
    this.color = color;
  }
}

public class Rectangle extends Shape {
  private int width;
  private int height;

  // Prior to JDK 22, initialization logic had to go here
  public Rectangle(int width, int height, String color) {
    super(color); // Call to superclass constructor
    this.width = width;
    this.height = height;
  }
}
```

With Statements Before super() (Java 22) :
```java
public class Rectangle extends Shape {
  private int width;
  private int height;

  public Rectangle(int width, int height, String color) {
    this.width = width;
    this.height = height;
    super(color); // Call to superclass constructor after setting width and height
  }
}
```


### Implicitly Declared Classes and Instance Main Methods (JEP 463)
JEP 463, introduced in Java 22, focuses on simplifying Java's entry point for small programs and scripts by supporting Implicitly Declared Classes and Instance Main Methods.

Previously, writing even simple Java programs required defining a public class with a static main method. 
This feature eliminates the need for explicit class declarations in some cases.
Java code can now directly declare a void main() method as an instance method without defining a class.

Example After Java 22 :
```java
void main() {
    System.out.println("Hello, World!");
}
```
By reducing the complexity of Java's structure, this feature makes Java more approachable for newcomers and suitable for quick scripting tasks.

This feature is still in the preview stage in Java 22, refining its design and implementation based on developer feedback.

### String Templates (JEP 430, Second Preview) in Java 22

String Templates, introduced in Java 21 and refined in Java 22, are a powerful new feature that simplify the creation of dynamic strings. They integrate string literals and expressions seamlessly, improving readability, maintainability, and reducing common pitfalls like injection vulnerabilities.

Key Features of String Templates
- Dynamic String Construction:
Use embedded expressions directly in strings, eliminating the need for cumbersome concatenation or String.format() calls.

- Syntax:
A string template starts with the keyword STR and allows the use of ${} to embed expressions.
example :
```java
String name = "Alice";
String greeting = STR."Hello, ${name}!";
System.out.println(greeting); // Outputs: Hello, Alice!
```
- Compile-Time Safety:
The template is processed at compile time, ensuring type safety and reducing runtime errors.

- Custom Processors:
String templates support custom processors that can transform or handle the template's content. This is useful for advanced scenarios like SQL query building, localization, or HTML rendering.

You can find more about String Templates here :
- https://medium.com/@viraj_63415/java-21-string-templates-79fd908f30ff

## Conclusion

These changes aim to make Java more expressive, performant, and user-friendly, appealing to modern programming needs.