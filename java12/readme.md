# New Features

## API

### Indentation :: String

Adjusts the indentation of each line of this string based on the value of n, and normalizes line termination characters.

```java
String indent(int n) 
```

### Transform :: String

This method allows the application of a function to this string.
    
```java
<R> R transform(Function<? super String,? extends R> f)
```

### Compact Number Format

The compact number format is a new feature in Java 12. It is used to format a number in a compact form.

### Teeing Collector :: Stream

This method is used to perform a transformation on the elements of the stream and then combine the results.
Previously, we can use only one collector with the stream api, but now we can use two collectors and combine them to have a single result.

![teeing.png](images%2Fteeing.png)

## Switch Expressions

You can find the issues with the old switch api here : 
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/e9430a6a-bff0-48a1-8433-1d6ef1a2f2db/9ad42007-8484-47e7-bf87-190f0279f3b8

And using the new feature here :
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/e9430a6a-bff0-48a1-8433-1d6ef1a2f2db/903db2e5-69d8-4301-87eb-1bd14de8f997

This is a preview feature in Java 12, so you need to enable it in your IDE.

```shell
$ javac --enable-preview --release 12 UsageSwitchExpressions.java 
$ java UsageSwitchExpressions
```

## JMH

Java Microbenchmarking Harness (JMH) is a Java library that helps you implement Java microbenchmarks. It is a Java harness for building, running, and analyzing nano/micro/milli/macro benchmarks written in Java and other languages targetting the JVM.
It helps you to write benchmarks that measure the performance of your code.

Here's more details :
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/4defdb99-9f80-4f96-9e35-ad69030cab67/ceb85404-6a50-45f9-a049-69d3c660b796
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/4defdb99-9f80-4f96-9e35-ad69030cab67/82173708-455f-46e4-a9ce-28bde818b7a7

The basics of JMH are here :
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/4defdb99-9f80-4f96-9e35-ad69030cab67/91eee665-e53c-43c3-8a8c-7283e85be0ad

and a Demo :
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/4defdb99-9f80-4f96-9e35-ad69030cab67/12e3d374-d053-4a3d-9f99-50f9a1b6e183

## JVM Changes

Some improvements has been made in the G1GC (Garbage First Garbage Collector introduced in java 9) in Java 12.
- ![gc.png](images%2Fgc.png)

JEP 344 : Abortable Mixed Collections for G1 (https://openjdk.org/jeps/344)
JEP 346 : Promptly Return Unused Committed Memory from G1 (https://openjdk.org/jeps/346)

### Shenandoah GC

The Shenandoah GC is a new garbage collector introduced in Java 12.
It is a low-pause-time garbage collector that reduces GC pause times by doing evacuation work concurrently with the running Java threads.
It is a good choice for applications that require low latency and/or have a large heap.
It is available as an experimental feature in Java 12.

Find more here about this section here : 
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/0860a29f-f364-4932-b388-be54e09c2a9c/1bc2d93c-0d7c-40c3-a635-02aad24b6540

### JVM Constants API

The JVM Constants API is a new feature in Java 12. It is used to model nominal descriptions of key class-file and run-time artifacts, in particular constants that are loadable from the constant pool.
find more here : 
- https://app.pluralsight.com/ilx/video-courses/3529b7e5-a68e-450a-826b-ff0abf52304a/0860a29f-f364-4932-b388-be54e09c2a9c/e3956dbd-030a-439d-af7e-fbc6e1313062