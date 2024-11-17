# Java 21

## Introduction

Java 21, released on September 19, 2023, is a long-term support (LTS) release that includes many enhancements 
and features, continuing the evolution of the Java platform. 
This version solidifies experimental features introduced in earlier versions and introduces new capabilities.

You can check the differences between Java 21 and Java 20 here : 
- https://javaalmanac.io/jdk/21/apidiff/20/

## Deprecations and Removals

### Thread

These methods have been deprecated for removal in Java 21:
- `Thread.suspend()`
- `Thread.resume()`
- `Thread.stop(Throwable)`
- `Thread.stop()`

These methods have been unsafe for a long time, and they have been deprecated for removal since java 1.2.
When you invoke these methods, you will get an UnsupportedOperationException now.

```java
@Deprecated(since="1.2", forRemoval=true)
public final void stop() {
  throw new UnsupportedOperationException();
}
```

### URL

A completely new deprecation in Java 21 happened on URL. 
There's two reasons for this. Historically, these URL constructors in some cases delay the parting and validation of the provided input until the URL is actually used, so that's less than a deal. 
Also, these constructors don't take into account escaping of URL unsafe characters, so that is something that you as a user of this API need to be aware of. 
```java
URL url = new URL("http://example.com/with space"); // & all other constructors
```

**So what should you do instead?**

The recommended replacement for using the URL constructor is using the create factory methods on URI and passing in the string representing the URL that you want to create.
Then subsequently calling toURL on this URI that you just created.
```java
URI uri = URI.create("http://example.com/with space");
URL url = uri.toURL();
```

**Are you already confused between URL and URI?**

Me too because they're very closely related. Any URL is also a URI, but the converse is not true. 
So an arbitrary URI is not necessarily a URL. 
A URL, a uniform resource locator, always points to something that you can connect to, for example, on an HTTP server, whereas URIs can also serve as abstract identifiers that identify a resource without necessarily pointing to a specific server. 
If you use URI.create, it will eagerly validate the URL that you pass in and it will also escape any URL and safe characters. So when migrating to Java 21, this is something that you will have to change in your code.

## Language Enhancements

### Api Updates

Some API updates were made in Java 21 on :
- The String Builder
- Emojis characters support
- Math.clam method
- and more

You can find examples here :
- [java21](src/main/java/org/example/java/tutorial/java21)

and look here for more details :
- https://app.pluralsight.com/ilx/video-courses/da9fb269-cdaa-4984-9262-330ae78f24a4/18afca11-b3cf-4e3d-a531-75698d33c667/4908d2f8-2b16-4f4c-af1e-bc9b08a5ad37

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
