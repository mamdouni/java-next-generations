package org.example.java.tutorial.java18;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
public class ApiUpdatesMain {

    public static void main(String[] args) throws UnknownHostException {

        // # Charset :
        // before java 18
        // If you want to get the charset from a string, you can use the forName method from the Charset class
        // this will fail if the charset is not available with UnsupportedCharsetException
        // your code may not work in different environments
        Charset charset = Charset.forName("windows-1252");

        // after java 18
        // now you can define a fallback charset if the charset is not available
        charset = Charset.forName("windows-1252", StandardCharsets.UTF_8);

        // # Duration :
        // add of isPositive method to Duration class
        var duration = Duration.ofSeconds(10); // PT10S
        duration = Duration.ofHours(-1); // PT-1H
        // we had already the isNegative and isZero methods. Now we have the isPositive method :
        log.info("{}", duration.isPositive());

        // # HttpClient :
        // Now you can define another interface to the HttpClient. It may be an IP address in your local network.
        HttpClient client = HttpClient.newBuilder()
                //.localAddress(InetAddress.getLocalHost())
                // i don't know why the below line does not compile even with preview features flag : https://download.java.net/java/early_access/loom/docs/api/java.net.http/java/net/http/HttpClient.Builder.html#localAddress(java.net.InetAddress)
                .build();

        // now you can define a Head request using the HttpRequest class
        HttpRequest headRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .HEAD()
                .build();
    }
}