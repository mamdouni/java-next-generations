package org.example.java.tutorial.java15.java9.features.streams;

import org.example.java.tutorial.java15.java9.features.Book;

import java.util.List;
import java.util.stream.Stream;

import static org.example.java.tutorial.java15.java9.features.Book.getBook;

public class OfNullable {

	public static void main(String[] args) {

		// ofNullable : Returns a sequential Stream containing a single element, if non-null, otherwise returns an empty Stream
		// useful when you don't know if your collection is null or not. No exception will be thrown in the code below
		long zero = Stream.ofNullable(null).count();
		long one = Stream.ofNullable(getBook()).count();
		System.out.printf("zero : %d , one : %d %n", zero, one);

		List<Book> collection = null;
		// before
		Stream<Book> stream = collection == null ? Stream.empty() : collection.stream();
		stream.map(Book::getTitle)
				.filter(t -> t.startsWith("Spring"))
				.forEach(System.out::println);
		// after
		Stream.ofNullable(collection)
				.flatMap(list -> list.stream())
				.map(Book::getTitle)
				.filter(t -> t.startsWith("Spring"))
				.forEach(System.out::println);
	}
}
