package org.example.java.tutorial.java9.features.optionals;

import org.example.java.tutorial.java9.features.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalStreams {

	public static void main(String[] args) {

		/*
			Optional class : public Stream<T> stream()
		 	If a value is present, returns a sequential Stream containing only that value, otherwise returns an empty Stream.
		 	but why turn an optional into a stream : well it's a interoperability matter between Optional and Stream check the examples
		 */

		Stream<Optional<Integer>> optionalStream = Stream.of(Optional.of(1), Optional.empty(), Optional.of(2));
		// how can you turn it to a stream of integers ?
		optionalStream
				.flatMap(Optional::stream)
				.forEach(System.out::println);	// shows 1 and 2

		// can you find the maximum of authors in each book ?
		List<String> authors = Book.getBooks()
				.stream()
				.map(b -> b.getAuthors().stream().max(Comparator.naturalOrder()))
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
		System.out.println(authors);
	}
}
