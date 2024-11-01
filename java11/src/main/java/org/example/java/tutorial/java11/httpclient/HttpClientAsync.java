package org.example.java.tutorial.java11.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class HttpClientAsync {

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com"))  // we are using google because it implements the HTTP 2
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> resFuture =
                httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        resFuture.thenAccept(res -> log.info(String.valueOf(res.version()))); // we expect the return version from google to be HTTP 2

        log.info("This is not blocking");

        resFuture.join();   // wait for the google response before stopping the application.
    }
}
