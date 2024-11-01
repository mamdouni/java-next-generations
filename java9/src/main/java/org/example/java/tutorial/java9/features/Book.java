package org.example.java.tutorial.java9.features;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@ToString
public class Book {
	public final String title;
	public final Set<String> authors;
	public final double price;

	public static final Book getBook(){

		return new Book("Spring In Action", Set.of("Graig Walls"), 35.99);
	}

	public static final List<Book> getBooks(){

		return List.of(new Book("Kubernetes In Action", Set.of("Marko Luksa", "Dylan scott"), 45.99), new Book("Kafka In Action", Set.of("Dave Klein"), 26.99));
	}
}
