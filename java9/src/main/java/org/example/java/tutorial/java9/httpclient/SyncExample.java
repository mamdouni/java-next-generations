package org.example.java.tutorial.java9.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SyncExample {

	public static void main(String[] args) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();	// you can also use a builder to build a client

		HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.pluralsight.com"))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if(response.statusCode()==200)
			System.out.println(response.headers().map());
	}
}
