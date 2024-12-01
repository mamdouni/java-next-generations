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


### Scoped Values (Finalized) [JEP 439]

**Purpose:** Provide a lightweight, thread-local alternative for sharing immutable data within a task or thread.

```java
import jdk.incubator.concurrent.ScopedValue;

ScopedValue<String> userId = ScopedValue.newInstance();

ScopedValue.where(userId, "12345").run(() -> {
        System.out.println("User ID: " + userId.get());
        });
```

**Benefits:**
- Simplifies thread-local-like behavior.
- Better suited for modern, multi-threaded applications.

### Sequenced Collections [JEP 431]

**Purpose**: Sequenced collections provide a more efficient way to manage ordered collections of elements.
Introduces a new interface, SequencedCollection, implemented by List, Set, and Map.
Provides methods for first and last elements in collections with a defined encounter order.

Java provides multiple collection types, such as List, Set, and Map, each with its own characteristics and use cases.
Even if the collections are ordered, there is no standard way to access the first or last element in a collection.

You can understand this with the following demo :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/18afca11-b3cf-4e3d-a531-75698d33c667/712cabf5-f748-47d0-aca0-70978d229d33

The SequencedCollection interface introduces methods to access the first and last elements in a collection with a defined encounter order.

```java
SequencedSet<String> set = SequencedSet.of("A", "B", "C");
System.out.println(set.getFirst()); // "A"
System.out.println(set.getLast());  // "C"
```

**Key Methods:**
- first() and last() for retrieval.
- reversed() for reverse order.

And you can check here the added interfaces and the hierarchy between them and the Collection interface:
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/18afca11-b3cf-4e3d-a531-75698d33c667/3e0fcb50-eb32-46d6-9ceb-61645d4baa8c

### Pattern Matching for switch (Finalized) [JEP 441]

Pattern matching for switch is now **a standard feature**.
Enables concise and expressive code when working with different types and patterns.
Supports exhaustive and null-safe handling.

Check this demo to know more about checks and exhaustive type patterns of sealed classes :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/16b0bc4f-ef2b-421d-9fd6-de3032fb3bbd/3ac60e0c-0d9c-4da9-bedc-0937b2e730da

And this one to know more about how to use records with the new switch expression :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/16b0bc4f-ef2b-421d-9fd6-de3032fb3bbd/788aeb66-b411-4a7c-aaaa-7e43dc98750b

And a demo here : 
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/16b0bc4f-ef2b-421d-9fd6-de3032fb3bbd/a59aad5e-ec59-4bfc-8b29-7fcbd25f9eb7

### Virtual Threads (Second Preview) [JEP 436]

Virtual threads are now standard in Java 21, simplifying concurrent programming by enabling lightweight threads.

**Purpose:** Improve scalability of applications with many concurrent tasks.

You can check here how the threads works in java until now and what are the downsides of using them :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/245f59b2-6974-4f39-b4a8-294837136b5f/e6838d1a-4ca2-4120-b448-4574cdf89194

The same course will show you :
- The alternative solutions for non-blocking code

What you need to know about asynchronous programming in Java :
- Hard to code
- Hard to read
- Hard to debug
- Hard to test
- Hard to maintain
- Provided by third-party libraries

To resume: 

Virtual threads are lightweight threads that are managed by the JVM, which means that they are not managed by the operating system.
They are more efficient than the traditional threads because they are not managed by the operating system, so they are not as expensive to create and to run.
They are also more scalable because you can create millions of them without running out of memory. They are also more predictable because they are not managed by the operating system, so you don't have to worry about the operating system scheduling them.
They are also more flexible because you can create them in a way that is similar to creating a regular thread, so you don't have to learn a new API.

They are not faster than the traditional threads, but they are more efficient, more scalable, more predictable, and more flexible.
When creating a virtual thread, it will be mounted on a **Platform Thread**. If it is blocked, it will be unmounted and the platform thread will be reused by another virtual thread.
It will only be mounted again to continue running when blocking call completes.
The **Platform Thread** can handle multiple virtual threads on the same time. The number of platform threads is by default the number of available CPU cores.

You can check this to know more about the new virtual threads :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/245f59b2-6974-4f39-b4a8-294837136b5f/6c1d0e49-c409-4128-99be-fb0848fcf8e0

This also means that you should use virtual threads only for tasks where it would perform a healthy amount of blocking operations mixed with CPU heavy operations.
If all virtual threads **would only run CPU‑intensive, non‑blocking codes,** they would never give up the underlying platform thread and there would be no benefit in using virtual threads. 
So virtual threads are not magical, faster threads. They do offer a simple model for rising applications with a lot of blocking operations 
(for example, a web server serving requests and doing calls to other web services in the database to fulfill these requests).

You can check here how to create a virtual thread :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/245f59b2-6974-4f39-b4a8-294837136b5f/1ab51a4b-86b5-457c-91af-595f880dad39

And here is some advice about using virtual threads (like not using Pooling or the use of synchronized keyword or File I/O) :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/245f59b2-6974-4f39-b4a8-294837136b5f/7790dbfb-f1d6-4970-a6cf-5f2973af5fa6

## Conclusion

Java 21 is an LTS (Long-Term Support) release, which means it will receive extended updates and support (similar to Java 17 and 11).

**Developer Productivity:** Finalization of major features like virtual threads, pattern matching, and record patterns make the language more expressive and scalable.

**Performance and Scalability:** With virtual threads and garbage collector enhancements, Java 21 is better suited for modern, high-throughput applications.

**Summary Table of Features in Java 21**

|Feature|Status|Description|
|:----|:----|:----|
|Pattern Matching for switch|Finalized (JEP 441)|Adds powerful and concise pattern matching for switch.|
|Record Patterns|Finalized (JEP 440)|Decompose records with pattern matching.|
|Virtual Threads|Finalized (JEP 444)|Lightweight threads for high-concurrency applications.|
|Sequenced Collections|Standard (JEP 431)|Adds first(), last(), and reversed() to ordered collections.|
|Foreign Function & Memory API|Finalized (JEP 442)|Interact with native memory and code efficiently.|
|String Templates|Preview (JEP 430)|Simplifies dynamic string creation using templates.|
|Scoped Values|Finalized (JEP 439)|Lightweight alternative to thread-local storage.|
|Structured Concurrency|Incubator (JEP 443)|Simplifies multi-threaded task management.|

Java 21 cements its position as a major step forward for developers with modernized concurrency tools, powerful pattern matching, and finalized experimental features. 
Its LTS status ensures it will be widely adopted and supported for years to come.
