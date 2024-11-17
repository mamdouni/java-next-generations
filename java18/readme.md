# Java 18

## Introduction

Java 18 was released on March 22, 2022.

You can check the differences between Java 17 and Java 18 here: 
- https://javaalmanac.io/jdk/18/apidiff/17/

## Language Enhancements

### Api improvements

You can check some api improvements here :
- [ApiUpdatesMain.java](src/main/java/org/example/java/tutorial/java18/ApiUpdatesMain.java)

### @snippet javadoc

In Javadoc, the **{@snippet}** tag, introduced in Java 18, allows developers to include source code snippets in documentation in a more structured and readable way. 
This tag is especially useful for embedding code examples directly within Javadoc comments. 
It provides syntax highlighting and additional formatting options, improving readability and making the documentation more informative.

```java
/**
 * The code below shows the content of {@code helloBaeldung()} method
 * {@snippet :
 * public void helloBaeldung() {
 *     System.out.println("Hello From Team Baeldung");
 * }
 * }
 */
public class GreetingsInlineSnippet {
    public void helloBaeldung() {
        System.out.println("Hello From Team Baeldung");
    }
}
```
- https://www.baeldung.com/wp-content/uploads/2023/09/an-in-line-snippet-to-show-hello-baeldung-method-2.png

(source : https://www.baeldung.com/java-doc-code-snippets#1-in-line-code-snippets)

The {@snippet} tag includes several features to enhance how code examples appear in Javadocs:
- Syntax Highlighting: The {@snippet} tag automatically formats and highlights the code, making it easier to read.
- Optional Imports and Regions: You can specify required imports or use regions to focus on specific parts of the code snippet.
- Line Numbering and Custom Formatting: Customize the display, including line numbers, to help users follow along with more complex examples.
- Comments within Snippets: You can annotate lines to provide additional explanation within the snippet, which improves understandability.

more details here : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/d3c314b3-d0f5-4fea-a839-d9f824a1b4f2/4dd25651-5deb-4e83-8fac-b4dd804b30c5

### Java simple web server

The Java Simple Web Server is a lightweight, built-in HTTP file server introduced in Java 18 as part of the jdk.httpserver module. 
It’s designed to make it easy to serve static files for local development, prototyping, and testing. 
This web server provides a convenient way to serve static content without needing additional configuration or setup, ideal for quickly testing web applications or APIs locally.

This may be useful when you have a backend in java and you want to run the frontend without having to install a web server or using third party tools.
Also if you want to preview the generated javadoc, you can use this server to serve the generated files.
This web server does not support TLS and authentication, so it is not suitable for production use.

This web server only handles GET and HEAD requests and serves files from the specified directory.

You can run it with one of the following command :
```shell
jwebserver

java -m jdk.httpserver

java -m jdk.httpserver -p 8080 -d /path/to/directory
```
 
-p: Sets the port (default is 8000).

-d: Specifies the root directory for the server to serve files from (default is the current directory).

Check the course here : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/274192ca-3753-4953-9073-d4cfff04f1db/a4bc76fb-dde6-4633-8eff-c16e6cfc9464
And the demo here :
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/274192ca-3753-4953-9073-d4cfff04f1db/e089f1ff-b5d6-4b2a-8031-0a40559808eb

You can also start the simple web server from within a Java program, which allows for more customization (such as adding handlers or specific paths).

Here’s an example of how to start a simple server programmatically:

```java
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class SimpleWebServerExample {
    public static void main(String[] args) throws IOException {
        // Create an HttpServer instance on port 8000
        HttpServer server = SimpleFileServer.createFileServer(
                new InetSocketAddress(8000),
                Path.of("/path/to/directory"),
                SimpleFileServer.OutputLevel.INFO
        );
        
        HttpHandler handler = SimpleFileServer.createFileHandler(Path.of("/dev/javadoc"));
        server.createContext("/javadoc", handler);

        // Define a context (endpoint) that will handle HTTP requests
        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Hello, World!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        // Start the server
        server.start();
        System.out.println("Server is listening on http://localhost:8000");
    }
}
```

You can find more about how to use this API and how to create and compose handlers here :
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/274192ca-3753-4953-9073-d4cfff04f1db/b020c02e-220b-4bf8-b00c-c1bc03c00601

## Platform Improvements

### JEP 418: DNS Platform Changes

The current implementation of the java.net.InetAddress class uses the system's default DNS resolver to resolve hostnames to IP addresses.
Well, this leads to some limitations and issues, such as:
- Blocking : The DNS resolution process is blocking, which can cause delays in network operations.
- Extensibility : The current implementation does not integrate emerging resolver protocols like DNS over QUIC.
- Hard to test : The current implementation is hard to test and mock in unit tests.
- Customization : The current implementation does not allow customization of the DNS resolver. what if you need to influence the resolving result?

The solution introduced by java 18 is InetAdressResolver API, which provides a more flexible and extensible way to resolve hostnames to IP addresses.
It resolves all the limitations of the current implementation and provides a more modern and flexible way to resolve hostnames.

You can check how it works here : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/0cb1c6d7-2eae-4241-8741-3e03c5507552/0c2d0276-55b4-48be-a9af-af5fc73112aa

### JEP 416: Reimplement Core Reflection with Method Handles

Java 18 introduces a new implementation of the core reflection API using method handles.
This new implementation aims to improve performance and maintainability by leveraging the method handles API introduced in Java 8.

What you need to remember is that the new implementation does not rely on **Bytecode generation** or the **Native code**.
This is important to be ready for the next bit feature of java which is **Virtual Threads**.

You can get more details here : 
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/0cb1c6d7-2eae-4241-8741-3e03c5507552/74deab6e-b22a-4ec8-8371-ba0756162731

### JEP 400: UTF-8 by Default

Write this file on macos :
```java
FileWriter writer = new FileWriter("test.txt");
writer.write("Alpha α. A. Beta β. B. Gamma γ. Γ. Delta δ. ∆. Epsilon ε. E. Zêta ζ. Z. Êta η. H. Thêta θ. Θ. Iota ι. I. Kappa κ. K. Lambda λ. Λ. Omega ω. Ω. ...");
```

Copy to windows and read it :
```java
FileReader reader = new FileReader("test.txt");
// read from reader in a loop
```

Well, as you may guess, you will get some weird characters.

Why? because the default charset on macos is UTF-8 and on windows is windows-1252.

```text
Macos : Charset.defaultCharset() = UTF-8 (this is the default system charset)
Windows : Charset.defaultCharset() = windows-1252 (this is determines by the user's locale)
```

Java 18 introduces a new feature that changes the default charset for the JVM to UTF-8.
This change is intended to improve compatibility with modern systems and applications that use UTF-8 as the default character encoding.

This is the default for file encoding but not for the console. Check this for more details :
- https://app.pluralsight.com/ilx/video-courses/310ed7a6-f653-46ea-8421-1c8aeca4ff05/0cb1c6d7-2eae-4241-8741-3e03c5507552/73eac6c6-6850-47f4-95e6-4b5927d021d4


