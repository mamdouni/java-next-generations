package org.example.java.tutorial.java15.java9.features.optionals;

import org.example.java.tutorial.java15.java9.features.Book;

import java.util.Optional;

public class OptionalIfPresentOrElse {

	public static void main(String[] args) {

		// earlier in java 8 , print the optional if isn't empty otherwise print nothing here
		Optional<Book> book = Optional.of(Book.getBook());
		//book = Optional.empty();

		book.ifPresent(System.out::println);
		if(book.isEmpty()){
			System.out.printf("Noting here !!! %n");
		}
		System.out.printf("%n	-------------------------%n");

		// using java 9 , you can pass the statement to be executed if the optional is empty
		// public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
		// If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
		book.ifPresentOrElse(System.out::println, () -> System.out.printf("Noting here !!! %n"));
	}
}
