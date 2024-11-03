package org.example.java.tutorial.java9.features.optionals;

import lombok.extern.slf4j.Slf4j;
import org.example.java.tutorial.java9.features.Book;

import java.util.Optional;

@Slf4j
public class OptionalIfPresentOrElse {

	public static void main(String[] args) {

		// earlier in java 8 , print the optional if isn't empty otherwise print nothing here
		Optional<Book> book = Optional.of(Book.getBook());
		//book = Optional.empty();

		book.ifPresent(b -> log.info("{}",b));
		if(book.isEmpty()){
			log.info("{}","Noting here !!!");
		}

		// using java 9 , you can pass the statement to be executed if the optional is empty
		// public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
		// If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
		book.ifPresentOrElse(b -> log.info("{}",b), () -> log.info("{}","Noting here !!! %n"));
	}
}
