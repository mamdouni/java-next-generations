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
