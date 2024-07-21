package org.example.java.tutorial.java11.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientAsync {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com"))  // we are using google because it implements the HTTP 2
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> resFuture =
                httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        resFuture.thenAccept(res -> System.out.println(res.version())); // we expect the return version from google to be HTTP 2

        System.out.println("This is not blocking");

        resFuture.join();   // wait for the google response before stopping the application
    }
}
