package org.example.java.tutorial.java22.scopedvalue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalExample {

    // Define a ThreadLocal variable to store user IDs for each thread
    private static ThreadLocal<String> threadLocalUserId = ThreadLocal.withInitial(() -> "Unknown User");
    /*
        The ThreadLocal class in Java provides thread-local variables. Each thread accessing such a variable
        (via its get or set methods) has its own, independently initialized copy of the variable.
        ThreadLocal is commonly used to maintain state or context information that is specific to the current thread.
     */

    void main() {

        // Create and start two threads
        Thread thread1 = new Thread(() -> {
            log.info("Thread 1 User ID: {}", threadLocalUserId.get());
            threadLocalUserId.set("User1"); // Set thread-specific user ID
            log.info("Thread 1 User ID: {}", threadLocalUserId.get());
        });

        Thread thread2 = new Thread(() -> {
            log.info("Thread 2 User ID: {}", threadLocalUserId.get());
            threadLocalUserId.set("User2"); // Set thread-specific user ID
            log.info("Thread 2 User ID: {}", threadLocalUserId.get());
        });

        thread1.start();
        thread2.start();

        // Main thread uses its own thread-local value
        log.info("Main Thread User ID: {}", threadLocalUserId.get());

        /*
            ThreadLocal simplifies this process by providing each thread with its own independent copy of the variable.
            This way, each thread can access and modify the variable without affecting other threads.

            Reasons to use ThreadLocal:
            - ThreadLocal variables are thread-safe, as each thread has its own copy of the variable.
            - Cleaner code : ThreadLocal can help avoid passing variables through method parameters or using global variables (the design becomes cleaner and modular).
            - Sharing mutable data among threads typically requires synchronization mechanisms like locks to ensure thread safety (not needed with ThreadLocal).
            - Many Java frameworks (e.g., Spring, Hibernate) rely on ThreadLocal to manage thread-scoped resources like database connections or user sessions.

            Usage:
            - Web Servers: Store user session data for the lifetime of a request handled by a single thread.
            - Logging: Store a unique identifier (e.g., request ID) for each log entry, ensuring that logs from different threads are distinguishable.
            - Security: Store user authentication information for the duration of a user session (prevent that third party libraries changes this also so make your code more secure).
            - Database Connection Management: Store database connection objects for each thread, ensuring that each thread uses a separate connection. Manage connections at the thread level to ensure they are reused correctly and closed safely.
         */
    }
}
