# Java 20

## Introduction

Java 20, released on March 21, 2023, introduced several new features and enhancements. 
While it did not bring revolutionary changes, it continued to refine and enhance existing features, 
with a strong focus on preview features, incubator modules, and improvements to the language, runtime, and tooling. 
Below is a summary of the features added or improved in Java 20.

You can check the differences between Java 20 and Java 19 here : 
- https://javaalmanac.io/jdk/20/apidiff/19/

## Removed or Deprecated Features

Java 20 did not explicitly remove major features, but some APIs or tools continue to undergo deprecation or warnings for removal in future versions:
- Features marked as deprecated for removal in earlier versions remain in place but are on track for removal in future releases (e.g., some legacy APIs).
- The focus was on evolving new, experimental features rather than removing existing ones.

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