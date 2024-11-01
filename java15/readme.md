# Java 15

## Clean UP

### Removed Ports

Starting from Java 15, the following ports have been removed :
- The support for Solaris operating system
- The support for SPARC processors architecture

The java language became more and more big and it is difficult to maintain all the features for all the platforms.

### RMI deprecations

RMI stands for Remote Method Invocation. 
It is a Java API that performs remote method invocation, allowing Java objects to invoke methods on remote objects running on other machines.
Not all the RMI features but only the java.rmi.activation package has been deprecated in Java 15.
It is used to activate objects in the JVM without having a JVM running continuously.

### Nashorn JavaScript Engine Removal

The Nashorn JavaScript Engine has been removed in Java 15. It was deprecated in Java 11 and now it has been removed.
It was used to run JavaScript code in the JVM.
As the javascript language is evolving, it is difficult to maintain the Nashorn engine.

### Added

### Helpful NullPointerExceptions

The Helpful NullPointerExceptions is no more a preview feature in Java 15. It is now a standard feature.
Check this demo for details :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/1564bf75-6d47-4168-b12b-b59ba4db0775/bcc6d1e6-2c97-4432-a979-8146a5811aef

## Text Blocks

The Text Blocks is not no more a preview feature in Java 15. It is now a standard feature.
You can find the demo here :
- [java13](../java13)

A feature that has been added is the backslash at the end of the line. It is used to remove the line terminator.

Example :
```java
String html = """
    <html> \
        <body> \
            <p>Hello, World</p> \
        </body> \
    </html>""";
```

Result :
```html
    <html>     <body>         <p>Hello, World</p>     </body> </html>
```

You can find more examples here :
- https://docs.oracle.com/en/java/javase/14/text-blocks/index.html

And you can check the course here :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/978b17cb-e5f0-4d7b-877a-af21cc7fd77a/09f00c59-f56a-4e55-85eb-9bc65832ae1b
the demo here :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/978b17cb-e5f0-4d7b-877a-af21cc7fd77a/e62bcfc3-a1c7-4df3-a438-87a09a59190f
and when use it in practice here :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/978b17cb-e5f0-4d7b-877a-af21cc7fd77a/ea5f1ad0-509d-4548-9cf3-df97c9ccc915
(in openapi documentation and testing for example)

## Preview Features

### Pattern Matching for instanceof

Pattern matching for instanceof is a preview feature in Java 14. It is used to simplify the code when you want to check the type of an object.
It still a preview feature in Java 15.
You can find the details here :
- [java14](../java14)

### Records

Records are a preview feature in Java 14. They are used to create classes that are simple data carriers.
They are used to create classes that are immutable and have a fixed set of properties.
Records are a restricted form of class and there are some restrictions on them :
- They cannot extend other classes or records
- They can implement interfaces
- They cannot have instance fields
- All the data is declared in the constructor
- The is no setters because the class is immutable

You can check more details in the last lecture of the course :
- [java14](../java14)

### Local Records

Local records are introduced as a preview feature in Java 15. They are used to create records in a method.

Example :
```java
public void printPerson() {
    record Person(String name, int age) { }
    Person person = new Person("John", 30);
    System.out.println(person);
}
```
Here, the Person record is created in the printPerson method and can only be used within it.

Here what you have to know about local records :
- They can only be used in the method where they are declared
- They are implicitly static which means its body cannot refer to variables in the enclosing method or class (unlike local classes which have been part of java for a long time already)
- You can now declare local interfaces and enums in a similar way (even doesn't make sense for interfaces)

### Sealed Classes

Sealed classes are introduced as a preview feature in Java 15. They are used to restrict the subclasses of a class.
A sealed class can have a limited set of subclasses and the subclasses must be declared in the same file as the sealed class or via the permits keyword.

Check the course here for details :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/88f5289b-9ced-46b4-92d1-d671a6b66f02/0e8a2ceb-2b53-4416-9ca9-35b7ef436622

And the chatGPT definition below.

A sealed class in Java is a type of class that restricts which other classes can inherit from it. 
This feature was introduced in Java 15 as a preview and became a standard feature in Java 17. 
The purpose of sealed classes is to give developers more control over the class hierarchy, helping to design more predictable and maintainable inheritance structures.

Here's how it works:

- **Definition:** A sealed class is declared with the sealed keyword, and it explicitly lists the subclasses that are allowed to extend it. This list is provided using the permits keyword.
- **Permitted Subclasses:** Only the classes specified in the permits clause can extend the sealed class. This means that the inheritance structure is controlled and limited, unlike regular inheritance in Java.
- **Subclass Types:** Subclasses of a sealed class must be declared in one of the following ways:
  - final: No further inheritance is allowed from this subclass. 
  - sealed: The subclass can restrict its own subclasses further. 
  - non-sealed: This subclass removes the restriction, allowing any class to extend it.


```java
public sealed class Shape permits Circle, Square, Rectangle {
    // Common attributes and methods for all shapes
}

public final class Circle extends Shape {
    // Implementation specific to Circle
}

public final class Square extends Shape {
    // Implementation specific to Square
}

public non-sealed class Rectangle extends Shape {
    // Implementation specific to Rectangle
    // Other classes can extend Rectangle
}
```

In this example:
- Shape is a sealed class that allows only Circle, Square, and Rectangle as subclasses.
- Circle and Square are marked final, meaning they cannot be subclassed further.
- Rectangle is marked non-sealed, so other classes can extend it.

Benefits of Sealed Classes
- **Enhanced Code Safety:** By limiting subclasses, sealed classes make class hierarchies more predictable.
- **Exhaustive Pattern Matching:** Sealed classes work well with pattern matching, allowing for exhaustive switch statements since the compiler knows all possible subclasses.
- **Encapsulation of Business Logic:** They help in enforcing business rules where only specific types should be allowed.

- Sealed classes are particularly useful when modeling closed or controlled hierarchies, like in domain-driven design or for classes where future extensions are undesirable.

## JVM Improvements

### ZCG

The Z Garbage Collector (ZGC) is a low-latency garbage collector in the Java Virtual Machine (JVM) that was introduced to improve garbage collection (GC) performance, especially in applications with large heaps. 
ZGC is designed to operate with minimal pauses, aiming for a pause time of less than 10 milliseconds, even with heap sizes in the range of terabytes.

Key Features of ZGC
- **Low Pause Times:** ZGC aims for extremely low pause times (<10ms), making it ideal for applications with high responsiveness requirements.
- **Scalability:** It’s designed to handle large heaps (up to terabytes) without significantly increasing the pause time, which is critical for applications that require a lot of memory.
- **Concurrent Work:** Most of the GC work (marking, relocation, compaction) is done concurrently with the application threads, so application performance is minimally affected.
- **Region-Based Heap:** ZGC divides the heap into small regions, which allows it to process only certain regions at a time instead of the entire heap. This design contributes to ZGC’s efficiency and scalability.
- **Compaction:** ZGC includes concurrent compaction, so it can reduce memory fragmentation while the application is running, which prevents "stop-the-world" pauses that can occur in other GCs during compaction.

By the way, The pause time in the context of garbage collection (GC) refers to the duration when the Java application threads are paused to allow the garbage collector to perform certain operations. 
During these pauses, the application stops executing its own code, which can impact responsiveness and latency.

ZGC is no more an experimental feature in Java 15. It is now a standard feature.
ZCG is now a production ready garbage collector and can be adopted in real-world applications.

You can find more about this garbage collector here :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/93e8fcf8-c54f-4ce8-8a9b-473efe6f2888/c12d69e5-8a5b-4274-a182-76de1e36ab12

You can add this flag **-XX:+UseZGC** to enable the ZGC garbage collector.

### Shenandoah Garbage Collector

Shenandoah is a low-pause-time garbage collector that reduces GC pause times by performing most of the GC work concurrently with the application threads.
Developed by Red Hat.
It was introduced as an experimental feature in Java 12 and is now a standard feature in Java 15.
Shenandoah has similar design goals to ZGC, it's meant for large heaps and wants to offer low pause times.
You can add this flag **-XX:+UseShenandoahGC** to enable the Shenandoah garbage collector (be aware, some OpenJDK distributions ho not include this garbage collector).

Some improvements have been made in the JVM G1GC (G1 garbage collection) and the ZGC.
You can find them here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/2ec6636d-fb8e-467c-acb4-9d7dbfc75d0d/b4a35397-414b-441b-ac7a-708f9a7977f1

### Other Improvements

Some improvements has been done on Hidden Classes, Cryptographic Algorithms, and the DatagramSocket API.
Find more here :
- https://app.pluralsight.com/ilx/video-courses/b6e31e25-ed0b-4bd1-8d1a-4854f63a268c/93e8fcf8-c54f-4ce8-8a9b-473efe6f2888/85b9070f-dcf5-4de3-8a39-50cabffafa79

