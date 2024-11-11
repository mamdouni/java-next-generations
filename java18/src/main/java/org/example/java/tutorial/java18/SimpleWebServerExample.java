package org.example.java.tutorial.java18;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

@Slf4j
public class SimpleWebServerExample {

    public static void main(String[] args) {

        // Create an HttpServer instance on port 8000
        HttpServer server = SimpleFileServer.createFileServer(
                new InetSocketAddress(8000),
                Path.of("").toAbsolutePath(),
                SimpleFileServer.OutputLevel.INFO
        );
        // http://localhost:8000/ : will serve files from the current directory

        // Define a context (endpoint) that will handle HTTP requests
        server.createContext("/hello", exchange -> {
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        // http://localhost:8000/hello : will return "Hello, World!"

        // Start the server
        server.start();
        log.info("Server is listening on http://localhost:8000 and http://localhost:8000/hello");
    }
}
