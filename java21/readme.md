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

### Record Patterns (Second Preview) [JEP 432]

- **Purpose**: Enhances pattern matching by introducing record patterns, enabling decomposition of record objects.
- **Enhancements in Java 20**:
  - Record patterns can be used in switch expressions to match record instances.
  - Simplifies code by providing a concise and expressive way to destructure record objects.
  - Improves integration with pattern matching for switch.
  - Refined syntax and matching rules for better expressiveness.

This feature is previewed in Java 20, allowing developers to experiment with record patterns and provide feedback before finalizing the feature.
It will be a standard feature in a future release (java 21).

- **Example**:
```java
record Point(int x, int y) {}

static void printPoint(Object obj) {
    if (obj instanceof Point(int x, int y)) {
        System.out.println("x: " + x + ", y: " + y);
    }
}

```

### Virtual Threads (Second Preview) [JEP 436]

**Purpose**: Virtual threads make high-throughput concurrent programming more accessible and scalable.

**Key Features in Java 20**:
- Simplifies thread creation and management.
- Improves the scalability of applications handling many simultaneous connections.
Example:
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> System.out.println("Hello from virtual thread!"));
}
```

## Incubator Modules

An incubator module in Java is a special type of module designed to introduce new APIs and features into 
the Java Development Kit (JDK) for experimental purposes. 
These modules allow developers to try out new features and provide feedback before they are finalized and 
included as standard features in future Java releases.

### Key Characteristics of Incubator Modules

**Experimental Nature:**
- Incubator modules contain APIs or functionality that are not yet ready for general availability.
- They are subject to change, removal, or refinement based on feedback from developers and practical usage.

**Modular Design:**
- Incubator modules are Java modules (introduced with the module system in Java 9).
- They reside in the **jdk.incubator** namespace to clearly distinguish them from stable modules.

**Not Enabled by Default:**
- To use an incubator module, you must explicitly add it to your project's classpath or module path.
- For example, you might need to use a command-line option like --add-modules.
  
**Feedback-Oriented:**
- The main purpose of incubator modules is to gather real-world feedback from developers.
- This feedback helps refine the feature or API before it becomes a standard part of the JDK.

**No Backward Compatibility Promise:**
- APIs in incubator modules are not guaranteed to remain the same in subsequent Java versions.
- They may be removed or heavily modified based on developer feedback.

### Example: Foreign Function & Memory API in Incubator

A practical example of an incubator module is the Foreign Function & Memory API, which provides a way for Java programs to interact with native code and memory outside the JVM. 
This API has been delivered as an incubator module in multiple Java versions (e.g., Java 17, Java 18, Java 19, and Java 20).

To use this API in Java 20, you would do the following:

- Compile and Run with Incubator Module: Add the jdk.incubator.foreign module to your project using the --add-modules flag:
```shell
javac --add-modules jdk.incubator.foreign MyApp.java
java --add-modules jdk.incubator.foreign MyApp
```

- Here's an example of working with the Foreign Function & Memory API:
```java
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class ForeignMemoryExample {
    public static void main(String[] args) {
        try (MemorySegment segment = MemorySegment.allocateNative(4)) {
            segment.set(ValueLayout.JAVA_INT, 0, 42);
            int value = segment.get(ValueLayout.JAVA_INT, 0);
            System.out.println("Value: " + value); // Output: 42
        }
    }
}
```

### Difference Between Incubator Modules and Preview Features
|Aspect|Incubator Modules|Preview Features|
|:----|:----|:----|
|Scope|Focused on APIs and libraries.|Covers language features, APIs, and JVM enhancements.|
|Namespace|Delivered under the jdk.incubator namespace.|Delivered in standard packages (e.g., java.lang).|
|Enablement|Requires explicit --add-modules to use.|Requires --enable-preview flag to use.|
|Stability|APIs can change or be removed entirely.|Features are more stable but still subject to change.|
|Examples|Foreign Function & Memory API, Vector API.|Pattern Matching for switch, Virtual|

## Conclusion
Java 20 is primarily a refinement release that builds on preview and incubator features from Java 19, emphasizing the following areas:

- **Developer Productivity:** Enhancements to pattern matching, records, and concurrency tools.
- **Performance and Scalability:** Virtual threads and structured concurrency improve the scalability of modern applications.
- **Native Interoperability:** Continued evolution of the Foreign Function & Memory API for easier native code integration.

Since many features are still in preview or incubation, they are not enabled by default and require compiler flags like --enable-preview for use.