# Java 19

## Introduction

JDK 19 is the open-source reference implementation of version 19 of the Java SE Platform, as specified by by JSR 394 in the Java Community Process. JDK 19 reached General Availability on 20 September 2022.

You can check the differences between Java 19 and Java 18 here: 
- https://javaalmanac.io/jdk/19/apidiff/18/

## Removal

### JEP 421: Deprecate the Finalization for Removal

The finalize() method in Java is a protected method of the Object class, designed to perform cleanup operations before an object is garbage-collected. It was intended as a way for objects to release resources or perform other cleanup tasks that may not be handled by the automatic garbage collection process.
Invocation by the Garbage Collector: The finalize() method is called by the garbage collector on an object when it determines that there are no more references to that object. This is typically done before the object's memory is reclaimed.

example : 
````java
protected void finalize() throws Throwable {
    try {
        // Cleanup code, e.g., releasing resources
        System.out.println("Object is being garbage collected");
    } finally {
        super.finalize(); // Ensure the superclass's finalize() is called
    }
}
````
The finalize() method has been deprecated in Java 9 and is now deprecated for removal in Java 19.

The finalize() method has several drawbacks and limitations, including:
- Unpredictable Timing: The garbage collector does not guarantee when, or even if, finalize() will be called on an object. This can lead to resource leaks if you rely on it for resource management.
- Performance Impact: Objects with a finalize() method require extra processing during garbage collection, which can impact performance.
- Error-Prone: If an exception is thrown in finalize() and not handled, the exception is ignored, and the object’s cleanup may be incomplete.

Instead of relying on finalize(), it is recommended to use try-with-resources blocks, the AutoCloseable interface, or other cleanup mechanisms (like Cleaner API) to manage resources and perform cleanup operations.
You can find more details about the Cleaner API and finalize here :
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/d3c314b3-d0f5-4fea-a839-d9f824a1b4f2/52dcb516-f323-46b1-a29a-47b5697d71ce

## Language Enhancements

### Future interface

The Future interface has been enhanced in Java 19 to provide more flexibility and control over asynchronous computations.
To get the result of a computation, you can use the `get()` method, which will block until the result is available.
If you want to wait for the result with a timeout, you can use the `get(long timeout, TimeUnit unit)` method.

````java
V get() throws InterruptedException, ExecutionException;
V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
````

Well, seems that these methods are so restrictive. 
So, Java 19 introduces new methods to the Future interface that allow you to check the state of the computation and get the result or exception immediately if it is available.

The interface Future has been extended and now includes :
- `exceptionNow()`: Returns the result of the Future if it has completed successfully. If the Future hasn’t completed yet or completed with an exception, this method throws an exception..
- `resultNow()`: Returns the exception thrown by the Future if it completed exceptionally. If the Future hasn’t completed or completed successfully, this method throws an exception.
- `state()`: this method will inspect the current state of the Future and return a value from the enum `Future.State`.

````text
CANCELLED   The task was cancelled.
FAILED      The task completed with an exception.
RUNNING     The task has not completed.
SUCCESS     The task completed with a result.
````

- resultNow and exceptionNow throw IllegalStateException if the future hasn’t completed or completed in the opposite state (successfully vs. exceptionally).
- These methods are useful for non-blocking checks, allowing you to handle results or exceptions immediately without waiting.

more details here : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/d3c314b3-d0f5-4fea-a839-d9f824a1b4f2/4b2e2474-97e2-432e-9c07-388e1f8315bf

## Platform Improvements

### JEP 400: UTF-8 by Default

Some improvements has been made to the UTF-8 charset console in Java 19.

Check the end of this course : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/0cb1c6d7-2eae-4241-8741-3e03c5507552/73eac6c6-6850-47f4-95e6-4b5927d021d4
