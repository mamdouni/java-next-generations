# Java 14

## Clean UP

### Removed Packages

Here's some packages that have been removed in Java 14 (after being deprecated in older versions) :
- java.security.acl
- java.util.jar.Pack200

### Deprecated for removal

- Thread::suspend
- Thread::resume

### Added

#### @java.io.Serial annotation

The `@java.io.Serial` annotation is used to declare that a class is serializable. It is used to replace the `Serializable` interface.
This annotation is used to tell the compiler that the class is serializable and that it should generate the serialVersionUID field.
The compiler will check if the serialVersionUID field is present and with a long type and if not, it will generate a warning (like the @override annotation).

### Helpful NullPointerExceptions

The NullPointerException message has been improved in Java 14. It now includes the name of the variable that is null.
Check this demo for details :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/1b9d2c22-892e-4326-bdc6-d68ce2de0631/fb4c757e-9572-49e0-a66a-14333297191a

## Switch Expressions

The switch expression is not no more a preview feature in Java 14. It is now a standard feature.
It was introduced in Java 12 as a preview feature and in Java 13, it was still in preview but now you can use it in your production code.
You can find the demo here :
- [java12](../java12)

## Preview Features

### Pattern Matching for instanceof

Pattern matching for instanceof is a preview feature in Java 14. It is used to simplify the code when you want to check the type of an object.

### Text Blocks

Text blocks have been improved in Java 14. 
It has been introduced in Java 13 as a preview feature and now it still a preview feature.
- [java13](../java13)

The feature that has been added is the backslash at the end of the line. It is used to remove the line terminator.
You can find the demo here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/feaf7082-bb95-4db7-87f0-a1f745cb4680/4896ee86-89fe-4aa4-9966-fec88caa9d39

### Java Packaging Tool

The Java Packaging Tool is a tool introduced in Java 14. It is used to package your application in a platform-specific format.
Its goal is to create platform-specific installers/packages like .exe, .msi, .dmg, .deb, .rpm, etc without the need to install java.

You can check his usage here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/feaf7082-bb95-4db7-87f0-a1f745cb4680/1ad2d184-6c52-452a-ae98-db9f5c10aaf6

### Records

Records is a preview feature in Java 14. It is used to create a class with a constructor, getters, equals, hashcode, and toString methods.
It is used to create a class with a few lines of code. Useful for DTOs objects.
Find more here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/feaf7082-bb95-4db7-87f0-a1f745cb4680/819f2893-0a79-479b-a9c4-a3579442c43d

Here's the ChatGPT introduction for this feature.

In Java, a record is a special kind of class introduced in Java 14 (as a preview feature) and officially added in Java 16. 
It provides a simpler way to define classes intended to hold immutable data. Records are especially useful for creating data-carrying classes where you want to store values and don't need complex behavior.

**Key Characteristics of Records** :
- Immutable by Default: Records are immutable; once created, their fields can't be modified.
- Concise Syntax: Records allow you to define a class with less boilerplate code by automatically generating constructors, equals(), hashCode(), and toString() methods.
- Compact Form: You declare fields directly in the class header, which the record uses to generate necessary methods.

A record is declared using the record keyword:
```java
public record Person(String name, int age) { }
```

Here, Person is a record class with two fields: name and age. Java automatically generates the following methods:
- A constructor: public Person(String name, int age)
- Accessor methods: name() and age()
- equals(), hashCode(), and toString() implementations based on name and age

**Example Usage** :
```java
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);
        System.out.println(person.name()); // Alice
        System.out.println(person.age());  // 30
        System.out.println(person);        // Person[name=Alice, age=30]
    }
}
```

**Why Use Records?**
Records are ideal for:
- Data Transfer Objects (DTOs), where the object is primarily a container for values.
- Reducing boilerplate code in applications that rely heavily on immutable data.
- Using records makes Java code more readable and concise, especially when you only need a class to carry data without complex behavior.

Check the demo here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/feaf7082-bb95-4db7-87f0-a1f745cb4680/53e11fd7-238b-45a9-be55-25d0859a291c

## JVM Improvements

Some improvements have been made in the JVM G1GC (G1 garbage collection) and the ZGC.
You can find them here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/2ec6636d-fb8e-467c-acb4-9d7dbfc75d0d/b4a35397-414b-441b-ac7a-708f9a7977f1

**The CMS Garbage Collector (Concurrent Mark-Sweep) has been deprecated in Java 9 and removed in Java 14**

- The Z Garbage Collector (ZGC) is a low-latency garbage collector introduced in Java 11. It is designed for applications requiring short pause times, even with large heaps (up to multiple terabytes). ZGC aims to keep garbage collection (GC) pauses under 10 milliseconds regardless of the heap size, making it suitable for applications where responsiveness is critical.

- The G1 Garbage Collector (Garbage-First GC or G1 GC) is a low-pause garbage collector in Java that was introduced as an alternative to the CMS (Concurrent Mark-Sweep) GC and became the default garbage collector in Java 9. G1 GC is designed to provide predictable pause times, making it suitable for applications that need both efficient memory management and moderate responsiveness. It achieves this by breaking the heap into regions, allowing for more flexible and targeted memory reclamation.

## Low-Level Improvements

Some other improvements have been made in Java 14 :
- Non-volatile Mapped Byte Buffers : It is used to save the memory after the machine restarts.
- Foreign-Memory Access API : It is used to access memory outside the JVM heap (which is so difficult for now).
- Java Flight Recorder : It is a tool used to record the events in the JVM. It is used to monitor the JVM and to analyze the performance of the application.

Find more here :
- https://app.pluralsight.com/ilx/video-courses/ebf4411f-5401-4515-b3bc-272b4451155a/2ec6636d-fb8e-467c-acb4-9d7dbfc75d0d/e9df1bdb-d51c-4696-94ca-fba64325aeba

