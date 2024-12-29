# Java 23

## Introduction

This release is the Reference Implementation of version 23 of the Java SE Platform, as specified by JSR 398 in the Java Community Process.
JDK 23 reached General Availability on 17 September 2024. Production-ready binaries under the GPL are available from Oracle; binaries from other vendors will follow shortly.

Features
- 455:	Primitive Types in Patterns, instanceof, and switch (Preview)
- 466:	Class-File API (Second Preview)
- 467:	Markdown Documentation Comments
- 469:	Vector API (Eighth Incubator)
- 473:	Stream Gatherers (Second Preview)
- 471:	Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal
- 474:	ZGC: Generational Mode by Default
- 476:	Module Import Declarations (Preview)
- 477:	Implicitly Declared Classes and Instance Main Methods (Third Preview)
- 480:	Structured Concurrency (Third Preview)
- 481:	Scoped Values (Third Preview)
- 482:	Flexible Constructor Bodies (Second Preview)

source : https://openjdk.org/projects/jdk/23/

You can check the differences between Java 23 and Java 22 here : 
- https://javaalmanac.io/jdk/23/apidiff/22/

## Language Enhancements

### Gatherers (Rassembler in french) for Stream API

Stream gatherers are a new concept introduced in Java 22 and reviewed in java 23 to enhance the functionality of the Stream API. 
They allow more advanced and customizable operations for combining or aggregating data during stream processing. Unlike traditional Stream methods like map() and reduce(), gatherers focus on efficiently combining multiple values or streams into a new structure.
This feature will be finalized in Java 24.

JEP : https://openjdk.org/jeps/485

To better understand the concept of gatherers, please check this course:
- https://www.youtube.com/watch?v=jqUhObgDd5Q
It explains the Stream API and starting from the 43:00 mark, it explains the gatherers : 
- https://www.youtube.com/watch?v=jqUhObgDd5Q&t=2621s
It still important to watch the whole video to understand the Stream API and the gatherers.

This API comes with an interface Gather :
- https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Gatherer.html
A class Gatherer that contains static methods to create gatherers :
- https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Gatherer.html
And a gather method added to the Stream interface :
- https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Stream.html#gather(java.util.stream.Gatherer)

As explained in the video, gatherers are used to customize the way the data is collected in the stream. They are intermediate operations that allow you to specify how the data should be collected and aggregated.
They have the same mindset as Collectors. Collectors are used as terminal operations to collect the data in the stream, while gatherers are used as intermediate operations to specify how the data should be collected.
Check the examples from the source code to better understand how gatherers work.

Check examples here : 
- [gatherers](src/main/java/org/example/java/tutorial/java23/gatherers)

### Vector API (Eighth Incubation)

The Vector API, now in its eighth incubation in Java 23, continues its development to enable the creation and execution of vectorized computations efficiently. This API provides a platform-agnostic way for developers to utilize SIMD (Single Instruction, Multiple Data) hardware capabilities to achieve higher performance for data processing tasks.

Key Features of the Vector API

Platform Independence:
- Allows developers to write vectorized code without worrying about specific hardware details. The API translates operations into optimized instructions for the underlying CPU architecture (e.g., x86, ARM).

Enhanced Performance:
- Designed to take advantage of SIMD instructions, the Vector API enables processing multiple data points in parallel, improving performance in operations like numerical computations, graphics processing, and large-scale data analysis.
Rich Set of Operations:
- Includes support for common vector operations such as addition, subtraction, multiplication, division, comparisons, and type conversions.

Integration with the JVM:
- Seamless integration with the Java Virtual Machine ensures that vector computations can benefit from runtime optimizations.

Binary Compatibility:
- Ensures that applications using the Vector API remain compatible across different architectures, leveraging the best SIMD instructions available on the host machine.

```java
import jdk.incubator.vector.*;

public class VectorExample {
    
    public static void main(String[] args) {
        // Specify the vector species for double values
        VectorSpecies<Double> species = DoubleVector.SPECIES_PREFERRED;

        // Create two arrays of double values
        double[] a = {1.0, 2.0, 3.0, 4.0};
        double[] b = {5.0, 6.0, 7.0, 8.0};

        // Load arrays into vectors
        DoubleVector va = DoubleVector.fromArray(species, a, 0);
        DoubleVector vb = DoubleVector.fromArray(species, b, 0);

        // Perform a vectorized addition
        DoubleVector vc = va.add(vb);

        // Store the result back into an array
        double[] result = new double[species.length()];
        vc.intoArray(result, 0);

        // Print the result
        for (double d : result) {
            System.out.println(d);
        }
    }
}
```

Output:

6.0, 8.0, 10.0, 12.0

Benefits of the Vector API
- Improved Performance: Ideal for high-performance applications like machine learning, image processing, and financial modeling.
- Ease of Use: Abstracts away the complexities of low-level SIMD programming.
- Portability: Applications can leverage SIMD across different hardware without modification.

### Class-File API

The Class-File API, introduced as a preview feature in Java 23 (JEP 457), provides a standard and modern approach to parsing, generating, and transforming Java class files. This API aims to simplify bytecode manipulation, a task traditionally performed using third-party libraries like ASM and BCEL. Here’s an overview of its key features and advantages:

Key Features:

Parsing Class Files:
- The API allows structured access to class file contents through patterns and models, such as ClassModel and CodeModel. Developers can efficiently traverse and analyze elements like methods, fields, and attributes without requiring extensive manual parsing.

Generating Class Files:
- The API provides a builder pattern to construct class files with high-level abstractions. For example, developers can define methods and attributes using lambda expressions, significantly reducing boilerplate code.

Transforming Class Files:
- The API aligns parsing and generation methods to facilitate seamless transformation. For example, developers can modify a parsed class file and regenerate it without starting from scratch.

Enhanced Developer Productivity:

- By leveraging Java's modern language features (e.g., pattern matching and lambdas), the Class-File API provides a concise and expressive way to interact with bytecode.

Built-in JVM Compatibility:
- Unlike third-party libraries, this API is part of the JDK itself, ensuring compatibility with the latest Java versions and reducing dependency management challenges.

Benefits Over Existing Tools:
- Better Abstraction: It reduces the complexity associated with manual bytecode manipulation.
- Consistency and Up-to-date Standards: Being part of the JDK, it eliminates the lag between Java version updates and library support.
- Error Reduction: Features like automatic handling of branch offsets reduce common errors in bytecode generation.

For instance, a method like fooBar can be generated using a combination of builder methods, automatically handling branch labels and local variable slots. This eliminates much of the tedious work required by older tools.

This API is still in preview, allowing the Java community to test and provide feedback for further refinement. It marks a significant step toward modernizing Java bytecode manipulation practices.


```java
import jdk.classfile.ClassBuilder;
import jdk.classfile.MethodBuilder;
import jdk.classfile.constantpool.ConstantPoolBuilder;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClassFileApiExample {
    public static void main(String[] args) throws Exception {
        // Define the class name
        String className = "HelloWorld";

        // Create a ConstantPoolBuilder
        ConstantPoolBuilder cpBuilder = ConstantPoolBuilder.create();

        // Start building the class
        var classFile = ClassBuilder.create(className)
                .withVersion(61) // Java 17 bytecode
                .withPublicAccess()
                .withSuperClass("java/lang/Object") // Inherit from Object
                .addMethod(MethodBuilder.create()
                        .withName("sayHello") // Method name
                        .withReturnType("V") // Void return type
                        .withPublicAccess()
                        .addCode(code -> {
                            code.loadConstant(cpBuilder.addString("Hello, World!"));
                            code.invokeVirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");
                            code.returnVoid();
                        }))
                .build();

        // Write the class file to disk
        Path outputPath = Path.of(className + ".class");
        try (OutputStream out = Files.newOutputStream(outputPath)) {
            classFile.writeTo(out);
        }

        System.out.println("Class file written to " + outputPath);
    }
}
```

This program generates a HelloWorld.class file. When run with the JVM, it creates a class containing the sayHello method that prints "Hello, World!".

To use the generated class:

```bash
javap -c HelloWorld.class  # To inspect the bytecode
java HelloWorld            # To run the class (ensure it has a main method or invoke it dynamically)
```
This API simplifies the otherwise complex process of working with Java bytecode manually.

### Types in Patterns (Preview)

The Primitive Types in Patterns feature, introduced as a preview in Java 23 and refined in Java 24 (anticipated), extends Java's pattern matching capabilities to include primitive types. This enhancement broadens the scope of pattern matching, which was initially limited to reference types, enabling more concise and expressive code when working with primitives.

Key Concepts

Primitive Patterns:
- You can now match values of primitive types (e.g., int, double) directly in pattern matching constructs like switch or instanceof.
- This capability reduces boilerplate code when performing type checks and value extractions.

Unified Pattern Matching:
- By integrating primitive types, the feature aligns with Java’s goal of supporting exhaustive, type-safe, and expressive pattern matching across both reference and primitive types.

Example Usage:

```java
public class PrimitivePatternExample {
    public static void main(String[] args) {
        Object value = 42;

        String result = switch (value) {
            case Integer i -> "Integer: " + i;
            case Double d -> "Double: " + d;
            case null -> "null";
            default -> "Other";
        };

        System.out.println(result);
    }
}
```

Output:

Integer: 42

Key Benefits

Type Safety:
- Eliminates the need for explicit type casting when working with primitives, making the code safer and easier to read.

Simplified Code:
- Reduces verbosity by allowing direct pattern matching rather than manually checking types and casting.

Seamless Integration:
- Works cohesively with other pattern matching constructs, including those for record, sealed, and array types.


## Conclusion

While Java 23 is not an LTS release, it continues to push Java's capabilities forward, making it a compelling update for developers working with cutting-edge features.
