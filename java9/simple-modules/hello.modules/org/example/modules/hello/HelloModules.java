package org.example.modules.hello;

public class HelloModules {
	public static void doSomething() {
		System.out.println(GenerateHelloMessage.MESSAGE);
	}
}

class GenerateHelloMessage {
	public static final String MESSAGE = "Hello, Modules!";
}