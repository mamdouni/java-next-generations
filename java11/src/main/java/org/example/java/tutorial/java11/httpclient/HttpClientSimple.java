package org.example.java.tutorial.java11.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientSimple {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * Replacement for java.net.HttpURLConnection
         * Supports HTTP/2, WebSocket
         * Reactive Streams integration
         */

        HttpClient httpClient = HttpClient.newHttpClient(); // using the by default configuration

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://pluralsight.com"))
                .GET()
                .build();

        HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println(res.headers().map());
    }
}
