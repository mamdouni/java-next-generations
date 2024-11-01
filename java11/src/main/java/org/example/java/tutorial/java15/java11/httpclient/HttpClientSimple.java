package org.example.java.tutorial.java15.java11.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class HttpClientSimple {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
         * Replacement for java.net.HttpURLConnection
         * Supports HTTP/2, WebSocket
         * Reactive Streams integration
         */

        HttpClient httpClient = HttpClient.newHttpClient(); // using the by default configuration

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com"))
                .GET()
                .build();

        HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        log.info("{}",res.headers().map());
    }
}
