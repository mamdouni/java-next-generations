package org.example.java.tutorial.java9.features.optionals;

import java.util.Optional;
import java.util.Set;
import org.example.java.tutorial.java9.features.Book;

public class OptionalOR {

	public static void main(String[] args) {

		Optional<Book> localFallback = Optional.of(Book.getBook());

		// We have to get the best offer, if not available check the external offer otherwise return the local offer
		// earlier in java 8
		Book bestBook = getBestOffer()
				.orElse(
						getExternalOffer().orElse(
								localFallback.get()
						)
				);
		System.out.println(bestBook);
		// well the code works fine but it's ugly as there is a lot of nested calls. We know that localFallback is not empty but imagine the opposite -> a null pointer will be thrown
		// all that is because of the orElse which returns a Book and not an Optional

		// with java 9
		// public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier) {
		// If a value is present, returns an Optional describing the value, otherwise returns an Optional produced by the supplying function.
		Optional<Book> orBook = getBestOffer()
				.or(() -> Optional.empty())
				.or(() -> getExternalOffer())
				.or(() -> localFallback);
		System.out.println(orBook);
	}

	static Optional<Book> getBestOffer(){
		return Optional.empty();
	}

	static Optional<Book> getExternalOffer(){
		return Optional.of(new Book("External Book", Set.of(), 11.99));
	}
}
