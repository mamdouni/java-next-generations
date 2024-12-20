package org.example.java.tutorial.java9.features.collectors;

import lombok.extern.slf4j.Slf4j;
import org.example.java.tutorial.java9.features.Book;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

@Slf4j
public class NewCollectors {

	public static void main(String[] args) {

		// We want to group the books by authors and get only books that has a price greater than 10 dollars
		Map<Set<String>,Set<Book>> booksByAuthors = Book.getBooks()
				.stream()
				.collect(groupingBy(Book::getAuthors, toSet())); // toSet() is the collector method to be applied on elements of each group

		// in java 8 we have to process and filter the map created above but in java 9 we can use the filtering collector
		// public static <...> Collector<...> filtering(Predicate<...> predicate, Collector<...> downstream)
		booksByAuthors = Book.getBooks()
				.stream()
				.collect(groupingBy(Book::getAuthors, filtering(b -> b.getPrice() > 10.0, toSet())));

		log.info("{}",booksByAuthors);

		// What if we want to group the books by price and get the authors of each group (authors that sell books with a price of 10$ for example)
		Map<Double, Set<Set<String>>> authorsByPrice = Book.getBooks()
				.stream()
				.collect(groupingBy(Book::getPrice, mapping(Book::getAuthors, toSet())));

		log.info("{}",authorsByPrice);
		// as you can see we've achieved our goal but the problem here is that the value type is Set<Set<String>> because the authors of a book is a set and not a simple type
		// let's fo the same with java 9
		// public static <...> Collector<...> flatMapping(Function<...> mapper, Collector<...> downstream)
		Map<Double, Set<String>> authorsByPrice9 = Book.getBooks()
				.stream()
				.collect(groupingBy(Book::getPrice, flatMapping(b -> b.getAuthors().stream(), toSet())));
		log.info("{}",authorsByPrice9);
	}
}
