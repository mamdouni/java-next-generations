package org.example.java.tutorial.java22.scopedvalue;

import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

@Slf4j
public class ScopedValueExample {

    // Define a ScopedValue
    private static final ScopedValue<String> USER_CONTEXT = ScopedValue.newInstance();
    private static final ScopedValue<String> KEY = ScopedValue.newInstance();

    void main() throws InterruptedException {

        // starting from java 22, you can use this short syntax of the main method

        // Enter a scoped value region
        ScopedValue.where(USER_CONTEXT, "User1")
                // Here we are bounding the "User1" value to the USER_CONTEXT ScopedValue and to the current thread also
                .run(() -> {
            // Access the ScopedValue in the same thread
            log.info("In scope: {}", USER_CONTEXT.get());

            // Run a new task, still in the same scope
            new Thread(() -> {
                // we are doing a rebinding of the USER_CONTEXT ScopedValue to the current thread
                ScopedValue.where(USER_CONTEXT, "User2").run(() -> {
                    log.info("Nested thread scope: {}", USER_CONTEXT.get());
                });
            }).start();

            // Scoped value is restricted to the execution of this Callable/Thread which means it cannot be accessed outside of this scope
            // That's why the thread below will not have access to the USER_CONTEXT ScopedValue
            new Thread(
                    () -> {
                    try {
                        log.info("is Bound: {}", USER_CONTEXT.isBound());
                        log.info("Nested thread 2 scope: {}", USER_CONTEXT.get());
                    } catch (NoSuchElementException e) {
                        log.error("No User in scope , {}", e.toString());
                    }
                    }).start();
        });

        // Outside the scope, the ScopedValue is not accessible
        log.info("Outside scope: {}", USER_CONTEXT.orElse("No User"));

        Thread.sleep(1000);

        log.info("----------------------");

        Runnable task = () -> log.info("Task KEY -> {}", KEY.isBound() ? KEY.get() : "Unbound");
        task.run();
        ScopedValue.where(KEY, "Key A").run(task);
        ScopedValue.where(KEY, "Key B").run(task);
        task.run();

        Thread.sleep(1000);

        log.info("----------------------");

        // you can also chain the ScopedValue instances if you need several scoped values for your tasks
        final ScopedValue<String> KEY1 = ScopedValue.newInstance();
        final ScopedValue<String> KEY2 = ScopedValue.newInstance();
        final ScopedValue.Carrier scopedValueCarrier = ScopedValue.where(KEY1, "Key 1").where(KEY2, "Key 2");

        Runnable task1 = () -> log.info("Task 1 KEY -> {}", KEY1.isBound() ? KEY1.get() : "Unbound");
        Runnable task2 = () -> log.info("Task 2 KEY -> {}", KEY2.isBound() ? KEY2.get() : "Unbound");

        scopedValueCarrier.run(task1);
        scopedValueCarrier.run(task2);
    }
}
