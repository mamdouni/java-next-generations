# Java 16

## Introduction

Java 16 is the latest release of the Java programming language. It was released on March 16, 2021.
Here are some of the changes and improvements in Java 16 :
- migrated to Git from Mercurial and hosting OpenJDK on GitHub (https://github.com/openjdk). Now developers can contribute to the OpenJDK project more easily.
- improvements in the stream API, DateTimeFormatter, pattern matching, and garbage collection.

## Improvements

### Stream API

#### toList() Method
The Stream API has been improved in Java 16 by introducing the new `toList()` method.
Here's an example of how you can use it:

**From**
```java
List<String> names = List.of("Alice", "Bob", "Charlie", "David");
List<String> filteredNames = names.stream()
    .filter(name -> name.length() > 4)
    .collect(Collectors.toList());
```
**To**
```java
List<String> names = List.of("Alice", "Bob", "Charlie", "David");
List<String> filteredNames = names.stream()
    .filter(name -> name.length() > 4)
    .toList();
```

The returned list is an unmodifiable list, which means you cannot add or remove elements from it (unlink Collectors method).
java16 only adds the `toList()` method to the Stream API (there is no toSet() for example until now).

#### mapMulti() Method

The `mapMulti()` method is a new method introduced in Java 16 that allows you to process multiple elements at once in a stream.
You can find more details about this method here:
- [mapMulti() Method in Java 16](mapMulti.md)

#### DateTimeFormatterBuilder

The `DateTimeFormatterBuilder` class has been enhanced in Java 16 to provide more flexibility when building date and time formatters.
You can now **Parse or format the textual name of the day Period**.
Which means you can parse a date and add the day period (morning, afternoon, evening, night) to it.
Check this demo for details:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/290e713c-5946-4e69-aa93-233c5e5ae222/14689bc7-ea1d-410b-b147-49cbab104f04

### Records

Records were introduced as a preview feature in Java 14 and became a standard feature in Java 16.
You can check Records and Local Records here:
- [java14](../java14)
- [java15](../java15)

You can also check the examples from the source code here:
- [java16](src/main/java/org/example/java/tutorial/java16/records)

And check this important course which explains the records characteristics and restrictions:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/61a21103-3428-4b07-885b-0c400703b4b9/9a84454a-2025-47d1-9e3d-e3e743581d8d

Here the standard use cases of utilizing records (like in jpa entities, dto, etc) :
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/61a21103-3428-4b07-885b-0c400703b4b9/f8fd4d9e-58a8-4a70-80c4-91cc1e11f0b4

### Pattern Matching for instanceof

Pattern matching for instanceof is a preview feature in Java 14. It is used to simplify the code when you want to check the type of an object.
It is now a standard feature in Java 16.
Check the course here :
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/61a21103-3428-4b07-885b-0c400703b4b9/c4d1a214-7b11-4d0e-b8f7-224c65b9a00c

and example here :
- [java14](../java14)
- [Pattern Matching](src/main/java/org/example/java/tutorial/java16/patternmatching)

Here's the future of pattern matching in Java :
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/61a21103-3428-4b07-885b-0c400703b4b9/ee4ff083-e99b-4198-bd82-d5f02e9d7bba

### Java Packaging Tool

The Java Packaging Tool (jpackage) has been enhanced in Java 16 to provide better support for packaging Java applications.
You can now use jpackage to create platform-specific installers for your Java applications. Jpackage will even include a JRE with your application, so users don't need to have Java installed on their system.
It is now an official supported tool in Java 16 (it has been introduced in java14 by the way).
- [readme.md](../java14/readme.md)

You can follow this awesome course which explains the features of the Java Packaging Tool:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/901b6f86-575c-40f2-80b3-a6e34c4aeb1c/4d15f35d-1721-4a6b-92c1-f30d0949873b

And this demo to see how to package a simple GUI application:
- https://app.pluralsight.com/ilx/video-courses/clips/72e7d3a4-7f48-420b-9913-5aa180143b08

And here how it works in practice:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/901b6f86-575c-40f2-80b3-a6e34c4aeb1c/3f11a44c-937d-468e-9441-aa8a4415c9f7

## JVM Improvements

### ZCG

The Z Garbage Collector (ZGC) was a production feature in Java 15. In java 16, it has been improved to reduce the pause time.
Using **Concurrent Thread-Stack Processing**, the pause time has been reduced to a few sub-milliseconds. Now, garbage collection is almost invisible to the user. It can be launched while your application is running.
- [java15](../java15)

Check this demo about ZGC improvements:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/7648cae0-86d4-41df-a842-a8684768a8c2/5853c5bf-4b5e-4d9d-ad5d-bb2752f80b4f

### Strong Encapsulation of JDK Internals

The JDK internals have been strongly encapsulated in Java 16. This means that you cannot access JDK internals directly from your code.
This is a security feature that prevents you from using internal JDK classes and methods that are not part of the public API.
Internal classes are classes that are not part of the public API and are not intended to be used by developers like sun or oracle classes.
For example : sun.misc.Unsafe, sun.misc.BASE64Encoder, sun.misc.BASE64Decoder, sun.security.x509.X500Name, etc.
Developers abuse these classes to access the internals of the JVM and the JDK by using the deep reflection for example.
They can for example make private fields accessible, invoke private methods, etc which can lead to security issues (make public private keys and sensitive data).
Now, you cannot access these classes directly from your code due to the strong encapsulation of JDK internals.
Check this course for more details:
- https://app.pluralsight.com/ilx/video-courses/38a35ba5-9b29-4406-b828-b04dce822900/7648cae0-86d4-41df-a842-a8684768a8c2/84bd30d8-e8c2-4135-94f7-4553bc878aaa