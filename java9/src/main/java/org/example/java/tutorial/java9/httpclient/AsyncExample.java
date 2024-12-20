package org.example.java.tutorial.java9.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AsyncExample {

	public static void main(String[] args) {

		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.followRedirects(HttpClient.Redirect.ALWAYS)	// Specifies whether requests will automatically follow redirects issued by the server.
				.build();

		HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.google.com")) // using google as it supports http2
				.headers("User-Agent", "Java")
				.timeout(Duration.ofMillis(1000))
				.GET()
				.build();

		CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

		response.thenAccept(res -> {
			log.info("Version : {}", res.version());
			log.info(res.body());
		});

		response.join(); // wait for the response before terminating this program
	}
}
